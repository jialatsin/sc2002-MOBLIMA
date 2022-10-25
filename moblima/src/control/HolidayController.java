package control;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.Holiday;

public class HolidayController extends DatabaseController<Holiday> {
    public HolidayController() {
        super(MainController.FILEPATH_HOLIDAY);
    }

    // Check if a holiday exists on the input date
    public boolean holidayExists(LocalDate date) {
        ArrayList<Holiday> holidays = readFromDatabase();
        for (Holiday holiday : holidays) {
            if (holiday.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    // Returns true on successful deletion
    public boolean deleteHoliday(LocalDate date) {
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
}
