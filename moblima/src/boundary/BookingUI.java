package boundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import control.BookingController;
import control.HolidayController;
import control.MovieController;
import control.PriceController;
import control.ShowingController;
import entity.Enumerators.MovieAttribute;
import entity.Enumerators.ShowingAttribute;
import entity.Booking;
import entity.Movie;
import entity.MovieGoer;
import entity.Seat;
import entity.SeatingLayout;
import entity.Showing;
import entity.Ticket;
import entity.Enumerators.Age;
import entity.Enumerators.CinemaClass;
import entity.Enumerators.Day;
import entity.Enumerators.MovieType;

/**
 * Represents the user interface for a moviegoer to make a booking for movie
 * tickets.
 */
public class BookingUI {
    /**
     * The price controller to be referenced.
     */
    private static PriceController priceController = new PriceController();
    /**
     * The holiday controller to be referenced.
     */
    private static HolidayController holidayController = new HolidayController();
    /**
     * The booking controller to be referenced.
     */
    private static BookingController bookingController = new BookingController();
    /**
     * The movie controller to be referenced.
     */
    private static MovieController movieController = new MovieController();
    /**
     * The showing controller to be referenced.
     */
    private static ShowingController showingController = new ShowingController();

    /**
     * Menu for Ticket Booking.
     * Prompts user to choose a showing, choose seats then purchase tickets.
     */
    public static void main() {
        Showing showing = null;
        System.out.println("\n===== TICKET BOOKING =====");
        // Prompts user for a Showing
        showing = UserHandler.getShowingFromUser();
        if (showing == null) {
            return;
        }

        // Displays number of seats available for this Showing
        SeatingLayout seatingAvailability = showing.getSeatingAvailability();
        System.out.printf("\n%d seats available for %s at %s (%s)\n",
                seatingAvailability.getAvailableSeatsCount(),
                showing.getMovie().getTitle(),
                showing.getCineplex().getName(),
                showing.getShowTime().format(InputHandler.getDateTimeFormat()));

        // Prompts user for number of tickets they want to purchase
        System.out.println("Enter number of tickets to you would like to buy:");
        int ticketCount = InputHandler.scanInt();
        if (ticketCount < 1 || ticketCount > seatingAvailability.getAvailableSeatsCount()) {
            System.out.println("Unable to buy that number of tickets!");
            return;
        }

        // Get showing to be updated in Showing database
        ArrayList<Showing> showings = showingController.readFromDatabase();
        int showingIndexInDatabase = showings.indexOf(showing);

        // Create tickets for the movie Showing
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (int i = 0; i < ticketCount; i++) {
            System.out.printf("\n----- TICKET %d -----\n", i + 1);

            Seat assignedSeat = chooseSeat(seatingAvailability);

            Age age = UserHandler.getAgeFromUser();
            Ticket ticket = new Ticket(showing, assignedSeat, age);
            tickets.add(ticket);
        }

        // Display final seat assignment
        System.out.println(showing.getSeatingAvailability());

        double price = displayTotalPrice(tickets);

        // Prompt user for booking confirmation
        char selection;
        do {
            System.out.println("Confirm booking? (Y/N)");
            selection = Character.toUpperCase(InputHandler.scanChar());
        } while (selection != 'Y' && selection != 'N');
        if (selection == 'N') {
            System.out.println("Booking cancelled!\n");
            return;
        }

        // Prompt user for MovieGoer details
        System.out.println("Enter your name:");
        String name = InputHandler.scanString();
        System.out.println("Enter your mobile number:");
        String mobileNumber = InputHandler.scanMobileNumber();
        System.out.println("Enter your email:");
        String email = InputHandler.scanEmail();

        MovieGoer movieGoer = new MovieGoer(name, mobileNumber, email);

        LocalDateTime transactionTime = LocalDateTime.now();
        DateTimeFormatter transactionTimeFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        // Transaction ID format: XXXYYYYMMDDhhmm
        String transactionID = showing.getCinema().getCode() + transactionTime.format(transactionTimeFormat);

        // Update showing with new seat layout in Showing database
        showings.set(showingIndexInDatabase, showing);
        showingController.overwriteDatabase(showings);

        // Add new Booking to Booking database
        Booking booking = new Booking(transactionID, movieGoer, tickets, transactionTime, showing, price);
        bookingController.addToDatabase(booking);
        System.out.println("\nBooking created! You will be able to view your booking history with your email.");

        // Update the ticket sales for the movie
        Movie movie = showing.getMovie();
        int newTicketSales = movie.getTicketSales() + ticketCount;

        // Get all showings to be updated with updated movie
        ArrayList<Showing> showingsWithMovie = showingController.findShowings(movie);
        movieController.updateMovieAttribute(movie, MovieAttribute.TICKET_SALES, newTicketSales);
        // Update movie attribute of all showings with the given updated movie
        if (showingsWithMovie != null) {
            for (Showing i : showingsWithMovie) {
                showingController.updateShowingAttribute(i, ShowingAttribute.MOVIE, movie);
            }
        }
    }

    /**
     * Calculates and prints the total price of all the tickets that the moviegoer
     * is trying to buy.
     * 
     * @param tickets List of tickets to be purchased.
     * 
     * @return Returns the total price of all the movie tickets chosen by the
     *         moviegoer.
     */
    private static double displayTotalPrice(ArrayList<Ticket> tickets) {
        double totalPrice = 0;
        int i = 1;
        System.out.println();
        for (Ticket ticket : tickets) {
            // Get ticket details
            Showing showing = ticket.getShowing();
            MovieType movieType = showing.getMovie().getMovieType();
            CinemaClass cinemaClass = showing.getCinema().getCinemaClass();
            Day day = holidayController.getDayType(showing.getShowTime());

            double price = priceController.calculatePriceType(day, movieType, cinemaClass, ticket.getAge());
            totalPrice += price;
            System.out.printf("Price of Ticket %d: %.2f\n", i++, price);
        }
        System.out.println("--------------------");
        System.out.printf("Total price of the %d tickets: %.2f\n\n", i - 1, totalPrice);
        return totalPrice;
    }

    /**
     * Prompts moviegoer to choose a seat for the current ticket and assigns the
     * seat.
     * 
     * @param seatingAvailability The current seating availability in the cinema for
     *                            the showing.
     * 
     * @return Returns the assigned seat in the cinema for the showing.
     */
    private static Seat chooseSeat(SeatingLayout seatingAvailability) {
        Seat assignedSeat = null;
        // Prompts user until a valid seat is selected
        do {
            // Displays seating layout for the selected showing
            System.out.println(seatingAvailability);

            System.out.printf("Enter seat row (%d-%d):\n", 0, seatingAvailability.getRows() - 1);
            int row = InputHandler.scanInt();
            if (row < 0 || row > seatingAvailability.getRows() - 1) {
                System.out.println("Invalid row!");
                continue;
            }

            System.out.printf("Enter seat column (%s-%s):\n", 'A',
                    (char) (seatingAvailability.getColumns() - 1 + 'A'));
            int column = (int) (Character.toUpperCase(InputHandler.scanChar()) - 'A');
            if (column < 0 || column > seatingAvailability.getColumns() - 1) {
                System.out.println("Invalid column!");
                continue;
            }

            assignedSeat = seatingAvailability.assignSeat(row, column);
            if (assignedSeat != null) {
                System.out.printf("Seat %s%d selected!\n\n", (char) (column + 'A'), row);
                break;
            } else {
                System.out.println("Cannot choose a seat that is already occupied!");
            }
        } while (true);

        return assignedSeat;
    }

}
