package entity;

import java.io.Serializable;
import java.time.LocalDate;

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

}
