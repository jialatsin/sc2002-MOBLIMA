package control;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.Holiday;

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
}
