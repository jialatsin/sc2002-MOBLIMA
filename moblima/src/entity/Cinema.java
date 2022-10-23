package entity;

import java.io.Serializable;

public class Cinema implements Serializable {
    public enum CinemaClass {
        PLATINUM, GOLD
    };

    private String code;
    private SeatingLayout seatingLayout;
    private CinemaClass cinemaClass;

    public Cinema(String code, SeatingLayout seatingLayout, CinemaClass cinemaClass) {
        this.code = code;
        this.seatingLayout = seatingLayout;
        this.cinemaClass = cinemaClass;
    }
}
