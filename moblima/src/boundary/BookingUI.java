package boundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import control.BookingController;
import control.HolidayController;
import control.MovieController;
import control.PriceController;
import control.ShowingController;
import control.MovieController.MovieAttribute;
import control.ShowingController.ShowingAttribute;
import entity.Booking;
import entity.Movie;
import entity.MovieGoer;
import entity.Seat;
import entity.SeatingLayout;
import entity.Showing;
import entity.Ticket;
import entity.Constants.Age;
import entity.Constants.CinemaClass;
import entity.Constants.Day;
import entity.Constants.MovieType;

public class BookingUI {
    private static PriceController priceController = new PriceController();
    private static HolidayController holidayController = new HolidayController();
    private static BookingController bookingController = new BookingController();
    private static MovieController movieController = new MovieController();
    private static ShowingController showingController = new ShowingController();

    public static void main() {
        Showing showing = null;
        do {
            System.out.println("\n===== TICKET BOOKING =====");
            showing = UserHandler.getShowingFromUser();
        } while (showing == null);

        SeatingLayout seatingAvailability = showing.getSeatingAvailability();

        System.out.printf("\n%d seats available for %s at %s (%s)\n",
                seatingAvailability.getAvailableSeatsCount(),
                showing.getMovie().getTitle(),
                showing.getCineplex().getName(),
                showing.getShowTime().format(InputHandler.getDateTimeFormat()));

        System.out.println("Enter number of tickets to you would like to buy:");
        int ticketCount = InputHandler.scanInt();
        if (ticketCount < 1 || ticketCount > seatingAvailability.getAvailableSeatsCount()) {
            System.out.println("Unable to buy that number of tickets!");
            return;
        }

        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        for (int i = 0; i < ticketCount; i++) {
            System.out.printf("\n----- TICKET %d -----\n", i + 1);
            Ticket ticket = bookTicket(showing);
            tickets.add(ticket);
        }

        double price = displayTotalPrice(tickets);

        char selection;
        do {
            System.out.println("Confirm booking? (Y/N)");
            selection = Character.toUpperCase(InputHandler.scanChar());
        } while (selection != 'Y' && selection != 'N');

        if (selection == 'N') {
            System.out.println("Booking cancelled!\n");
            return;
        }

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

        Booking booking = new Booking(transactionID, movieGoer, tickets, transactionTime, showing, price);
        bookingController.addToDatabase(booking);
        System.out.println("\nBooking created!");

        // Update the ticket sales for the movie
        Movie movie = showing.getMovie();
        int newTicketSales = movie.getTicketSales() + ticketCount;
        // Get all showings to be updated
        ArrayList<Showing> showings = showingController.findShowings(movie);
        movieController.updateMovieAttribute(movie, MovieAttribute.TICKET_SALES, newTicketSales);
        // Update movie attribute of all showings with the given updated movie
        if (showings != null) {
            for (Showing i : showings) {
                showingController.updateShowingAttribute(i, ShowingAttribute.MOVIE, movie);
            }
        }
    }

    private static double displayTotalPrice(ArrayList<Ticket> tickets) {
        double totalPrice = 0;
        int i = 1;
        System.out.println();
        for (Ticket ticket : tickets) {
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

    private static Ticket bookTicket(Showing showing) {
        // Choose seat for current ticket
        SeatingLayout seatingAvailability = showing.getSeatingAvailability();
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

        Age age = UserHandler.getAgeFromUser();

        Ticket ticket = new Ticket(showing, assignedSeat, age);
        return ticket;
    }

}
