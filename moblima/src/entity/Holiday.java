package entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a Holiday defined in the system settings.
 * Used in determining ticket pricing.
 */
public class Holiday implements Serializable {
    /** Date of this holiday. */
    private LocalDate date;

    /**
     * Creates a Holiday with the given attribute.
     * 
     * @param date The holiday's date.
     */
    public Holiday(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the date of this holiday.
     * 
     * @return This holiday's date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Changes the date of this holiday.
     * 
     * @param date The input date.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns a string containing this holiday's date.
     * 
     * @return String containing this holiday's date.
     */
    @Override
    public String toString() {
        return "Holiday [date=" + date + "]";
    }

}
