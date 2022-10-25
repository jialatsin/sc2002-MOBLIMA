package entity;

import java.io.Serializable;

import entity.Constants.CinemaClass;

public class Cinema implements Serializable {
    private String code;
    private SeatingLayout seatingLayout;
    private CinemaClass cinemaClass;

    public Cinema(String code, SeatingLayout seatingLayout, CinemaClass cinemaClass) {
        this.code = code;
        this.seatingLayout = seatingLayout;
        this.cinemaClass = cinemaClass;
    }
}
