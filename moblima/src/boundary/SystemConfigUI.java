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
import entity.Constants.Age;
import entity.Constants.CinemaClass;
import entity.Constants.Day;
import entity.Constants.MovieType;

public class SystemConfigUI {
    private static HolidayController holidayController = new HolidayController();
    private static PriceController priceController = new PriceController();
    private static AdminController adminController = new AdminController();

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
                case 1:
                    manageHolidays();
                    break;
                case 2:
                    managePrices();
                    break;
                case 3:
                    manageAdmin();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

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

    private static void viewPriceList() {
        Map<PriceType, Double> priceList = priceController.getPriceList();
        priceList.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
    }

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
