package control;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Holiday;
import entity.Enumerators.Day;
/**
 * It Represents the Holiday Controller 
 * @author OOP SSP1 Lab Group 4
 * @version 8/11/2022
 */
public class HolidayController extends DatabaseController<Holiday> {
	/** 
     * File path name of holiday database file to access. 
     */
	public HolidayController() {
        super(MainController.FILEPATH_HOLIDAY);
    }
	/**
	 *Extracting Information of the holiday from the date input from the Database
	 * @param date       The holiday date
     * @return holiday   If successful, it extracts the information of the holiday by matching it with a date in the database, else null value is returned.
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
     * Deletes the day of the holiday from the database
     * @param date       The date of the holiday
     * @return boolean   It returns true on successful deletion 
     */
    // Returns true on successful deletion
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
     * Get the corresponding Day enumeration for the given date.
     * @param date The date of the holiday 
     * @return Enum 'HOLIDAY' if date is on a holiday, enum 'WEEKDAY' if date is on
     *         a regular weekday, enum 'WEEKEND' if date is on a regular weekend
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
