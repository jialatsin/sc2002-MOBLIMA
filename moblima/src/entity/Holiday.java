package entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
* It Represents a Holiday defined in the system settings which is used in determining ticket pricing
* @author OOP SSP1 Lab Group 4 
* @version 08/11/2022
*/
public class Holiday implements Serializable {
    /**
     * The holiday dates
     */
	private LocalDate date;
	/**
	 * Creates Holiday with the given attribute
	 * @param date The date of holidays
	 */
    public Holiday(LocalDate date) {
        this.date = date;
    }
    /**
     * Get the date of the holidays
     * @return date The date of the holidays
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * The date of the holidays
     * @param date The date of the holidays
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /**
     * String displaying the holiday dates
     * @return string displaying the holiday dates
     */
    @Override
    public String toString() {
        return "Holiday [date=" + date + "]";
    }

}

