package control;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Holiday;
import entity.Enumerators.Day;

public class HolidayController extends DatabaseController<Holiday> {
    public HolidayController() {
        super(MainController.FILEPATH_HOLIDAY);
    }

    public Holiday getHolidayByDate(LocalDate date) {
        ArrayList<Holiday> holidays = readFromDatabase();
        for (Holiday holiday : holidays) {
            if (holiday.getDate().equals(date)) {
                return holiday;
            }
        }
        return null;
    }

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

    // Returns enum 'HOLIDAY' if date is on a holiday
    // Returns enum 'WEEKDAY' if date is on a regular weekday
    // Returns enum 'WEEKEND' if date is on a regular weekend
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
