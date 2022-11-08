package boundary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import control.AdminController;
import control.HolidayController;
import control.PriceController;
import entity.Admin;
import entity.Holiday;
import entity.PriceType;
import entity.Enumerators.Age;
import entity.Enumerators.CinemaClass;
import entity.Enumerators.Day;
import entity.Enumerators.MovieType;

/**
 * Represents the admin interface to configure system settings related to
 * holidays, ticket prices and admin accounts.
 */
public class SystemConfigUI {
    /** The holiday controller to be referenced. */
    private static HolidayController holidayController = new HolidayController();
    /** The price controller to be referenced. */
    private static PriceController priceController = new PriceController();
    /** The admin controller to be referenced. */
    private static AdminController adminController = new AdminController();

    /**
     * Menu that displays options to configure the system settings. Displays 4
     * options: Manage Holidays, Manage Ticket Prices, Manage Admin Accounts, Return
     * to Admin Menu.
     */
    public static void main() {
        int selection;
        do {
            System.out.println("\n===== CONFIGURE SYSTEM SETTINGS =====\n"
                    + "1. Manage Holidays\n"
                    + "2. Manage Ticket Prices\n"
                    + "3. Manage Admin Accounts\n"
                    + "0. Return to Admin Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1: // Manage Holidays
                    manageHolidays();
                    break;
                case 2: // Manage Ticket Prices
                    managePrices();
                    break;
                case 3: // Manage Admin Accounts
                    manageAdmin();
                    break;
                case 0: // Return to Admin Menu
                    return;
            }
        } while (true);
    }

    /**
     * Menu for managing holidays in the holiday database. Displays 4 options: Add a
     * Holiday, Delete a Holiday, View All Holidays, Return to System Configuration
     * Menu.
     */
    private static void manageHolidays() {
        int selection;
        do {
            System.out.println("\n===== HOLIDAYS MANAGER =====\n"
                    + "1. Add a Holiday\n"
                    + "2. Delete a Holiday\n"
                    + "3. View All Holidays\n"
                    + "0. Return to System Configuration Menu\n");

            selection = InputHandler.scanInt();
            LocalDate date;
            switch (selection) {
                case 1:
                    System.out.println("\nADDING A HOLIDAY...");
                    System.out.println("Enter date of holiday:");
                    date = InputHandler.scanDate();
                    if (holidayController.getHolidayByDate(date) != null) {
                        System.out.println("Holiday on " + date + " already exists in Holiday database!");
                        break;

                    }
                    System.out.println("Holiday on " + date + " added to Holiday database!");
                    holidayController.addToDatabase(new Holiday(date));
                    break;
                case 2:
                    System.out.println("\nDELETING A HOLIDAY...");
                    System.out.println("Enter date of holiday to delete:");
                    date = InputHandler.scanDate();
                    if (holidayController.deleteHolidayByDate(date)) {
                        System.out.println("Deleted holiday on " + date + "!");
                        break;
                    }
                    System.out.println("Unable to delete holiday on " + date + "!");
                    break;
                case 3:
                    ArrayList<Holiday> holidays = holidayController.readFromDatabase();
                    if (holidays.isEmpty()) {
                        System.out.println("No holidays exist in Holiday database!");
                        break;
                    }

                    for (Holiday holiday : holidays) {
                        System.out.println(holiday);
                    }
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    /**
     * Menu for managing ticket prices in the price database. Displays 3 options:
     * Update a Ticket Price, View Price List, Return to System Configuration Menu.
     */
    private static void managePrices() {
        int selection;
        do {
            System.out.println("\n===== TICKET PRICING MANAGER =====\n"
                    + "1. Update a Ticket Price\n"
                    + "2. View Price List\n"
                    + "0. Return to System Configuration Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    updatePrice();
                    break;
                case 2:
                    viewPriceList();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    /**
     * Menu for updating prices in the price database. Displays 5 options: Update
     * movieType fee, Update cinemaClass fee, Update movieGoerAge fee, Update
     * dayOfWeek fee, Update base price.
     */
    private static void updatePrice() {
        int selection;
        double value;
        do {
            System.out.println("\nUPDATING A PRICE...\n"
                    + "1. Update movieType fee\n"
                    + "2. Update cinemaClass fee\n"
                    + "3. Update movieGoerAge fee\n"
                    + "4. Update dayOfWeek fee\n"
                    + "5. Update base price\n");
            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    MovieType movieType = UserHandler.getMovieTypeFromUser();
                    System.out.printf("Enter new price: ");
                    value = InputHandler.scanDouble();
                    priceController.updatePriceType(movieType, value);
                    break;
                case 2:
                    CinemaClass cinemaClass = UserHandler.getCinemaClassFromUser();
                    System.out.printf("Enter new price: ");
                    value = InputHandler.scanDouble();
                    priceController.updatePriceType(cinemaClass, value);
                    break;
                case 3:
                    Age age = UserHandler.getAgeFromUser();
                    System.out.printf("Enter new price: ");
                    value = InputHandler.scanDouble();
                    priceController.updatePriceType(age, value);
                    break;
                case 4:
                    Day day = UserHandler.getDayFromUser();
                    System.out.printf("Enter new price: ");
                    value = InputHandler.scanDouble();
                    priceController.updatePriceType(day, value);
                    break;
                case 5:
                    System.out.printf("Enter new base price: ");
                    value = InputHandler.scanDouble();
                    priceController.updatePriceType(MovieType.REGULAR_TWO_D, value);
                    break;
            }
        } while (selection < 1 || selection > 5);
    }

    /**
     * Displays all the current prices in the price database.
     * Regular 2D MovieType is taken as the base price, while all other prices are
     * additional fees added onto the base price.
     */
    private static void viewPriceList() {
        Map<PriceType, Double> priceList = priceController.getPriceList();
        priceList.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
    }

    /**
     * Menu for managing admin accounts in the admin database. Displays 4 options:
     * Create an Admin, Delete an Admin, View All Admins, Return to System
     * Configuration Menu.
     */
    private static void manageAdmin() {
        int selection;
        do {
            System.out.println("\n===== ADMIN ACCOUNT MANAGER =====\n"
                    + "1. Create an Admin\n"
                    + "2. Delete an Admin\n"
                    + "3. View All Admins\n"
                    + "0. Return to System Configuration Menu\n");

            selection = InputHandler.scanInt();

            String username = null;
            switch (selection) {
                case 1:
                    System.out.println("\nADDING AN ADMIN...");
                    System.out.println("Enter username of admin:");
                    username = InputHandler.scanString();
                    if (adminController.getAdminByUsername(username) != null) {
                        System.out.println("Username already exists on Admin database!");
                        return;
                    }
                    System.out.println("Enter password of admin:");
                    String password = InputHandler.scanString();

                    Admin admin = new Admin(username, password);
                    adminController.addToDatabase(admin);
                    break;
                case 2:
                    System.out.println("\nDELETING AN ADMIN...");
                    System.out.println("Enter username of admin to delete:");
                    username = InputHandler.scanString();
                    if (adminController.deleteAdminByUsername(username)) {
                        System.out.println("Deleted admin " + username + "!");
                        break;
                    }
                    System.out.println("Unable to delete admin " + username + "!");
                    break;
                case 3:
                    ArrayList<Admin> admins = adminController.readFromDatabase();
                    if (admins.isEmpty()) {
                        System.out.println("No admins exist in Admin database!");
                        break;
                    }

                    for (Admin a : admins) {
                        System.out.println(a);
                    }
                    break;
                case 0:
                    return;
            }
        } while (true);
    }
}
