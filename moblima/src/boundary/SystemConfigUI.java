package boundary;

import java.time.LocalDate;
import java.util.ArrayList;

import control.HolidayController;
import entity.Holiday;

public class SystemConfigUI {
    private static HolidayController holidayController = new HolidayController();

    public static void main() {
        int selection;
        do {
            // TODO: Manage Seating Layouts? Manage Cineplexes?
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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
                        System.out.println("Holiday on " + date + " added to Holiday database!");
                        holidayController.addToDatabase(new Holiday(date));
                        break;
                    }
                    System.out.println("Holiday on " + date + " already exists in Holiday database!");
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
                    for (Holiday holiday : holidays) {
                        System.out.println(holiday);
                    }
                    break;
                case 0:
                    return;
            }
        } while (true);
    }
}
