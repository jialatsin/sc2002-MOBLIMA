package entity;

import java.io.Serializable;
import java.time.LocalDate;

/* Represents a Holiday defined in the system settings 
 * Used in determining ticket pricing
*/
public class Holiday implements Serializable {
    private LocalDate date;

    public Holiday(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Holiday [date=" + date + "]";
    }

}
