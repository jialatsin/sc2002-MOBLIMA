package entity;

import java.io.Serializable;

import entity.Enumerators.CinemaClass;

/*
 * Represents a Cinema that belongs to a Cineplex
 */
public class Cinema implements Serializable {
    /* Cinema code identifier */
    private String code;
    /* Seating layout template */
    private SeatingLayout seatingLayout;
    private CinemaClass cinemaClass;

    public Cinema(String code, SeatingLayout seatingLayout, CinemaClass cinemaClass) {
        this.code = code;
        this.seatingLayout = seatingLayout;
        this.cinemaClass = cinemaClass;
    }

    @Override
    public String toString() {
        return "Cinema [code=" + code + ", cinemaClass=" + cinemaClass + "]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /* Get the seating layout template for this Cinema */
    public SeatingLayout getSeatingLayout() {
        return (SeatingLayout) seatingLayout.clone();
    }

    public void setSeatingLayout(SeatingLayout seatingLayout) {
        this.seatingLayout = seatingLayout;
    }

    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cinema) {
            Cinema other = (Cinema) obj;
            return this.code.equals(other.getCode())
                    && this.seatingLayout.equals(other.getSeatingLayout())
                    && this.cinemaClass.equals(other.getCinemaClass());
        }
        return false;
    }
}
