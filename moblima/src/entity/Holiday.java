package entity;

import java.time.LocalDate;

public class Holiday {
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
