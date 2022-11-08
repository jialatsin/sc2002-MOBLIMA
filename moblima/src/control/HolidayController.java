package control;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Holiday;
import entity.Enumerators.Day;

/**
 * Represents a HolidayController which contains the logic for handling Holiday
 * data.
 */
public class HolidayController extends DatabaseController<Holiday> {
    /**
     * Creates a HolidayController with the file path of the holiday database file
     * to access.
     */
    public HolidayController() {
        super(MainController.FILEPATH_HOLIDAY);
    }

    /**
     * Returns a holiday by searching with the given date in the
     * holiday database.
     * 
     * @param date Date of holiday to search for.
     * @return Returns holiday with matching date if found in database, else null.
     */
    public Holiday getHolidayByDate(LocalDate date) {
        ArrayList<Holiday> holidays = readFromDatabase();
        for (Holiday holiday : holidays) {
            if (holiday.getDate().equals(date)) {
                return holiday;
            }
        }
        return null;
    }

    /**
     * Deletes holiday by searching with the given date in the holiday database.
     * 
     * @param date Date of holiday to be deleted.
     * @return Returns true if holiday is successfully deleted, else false.
     */
    public boolean deleteHolidayByDate(LocalDate date) {
        ArrayList<Holiday> holidays = readFromDatabase();
        for (Holiday holiday : holidays) {
            if (holiday.getDate().equals(date)) {
                holidays.remove(holiday);
                overwriteDatabase(holidays);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the corresponding type of day for the given date.
     * 
     * @param date Date to get corresponding type of day for.
     * @return Returns Day enumerator 'HOLIDAY' if date is on a holiday, 'WEEKDAY'
     *         if date is on a regular weekday, 'WEEKEND' if date is on a regular
     *         weekend.
     */
    public Day getDayType(LocalDateTime date) {
        // Showtime is on a holiday
        if (getHolidayByDate(date.toLocalDate()) != null) {
            return Day.HOLIDAY;
        }
        // Showtime is not on a holiday
        DayOfWeek day = date.getDayOfWeek();
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                return Day.WEEKDAY;
            case SATURDAY:
            case SUNDAY:
                return Day.WEEKEND;
            default:
                return null;
        }
    }
}
