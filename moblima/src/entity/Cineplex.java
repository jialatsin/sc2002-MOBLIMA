package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
    private String name;
    private String location;
    private String vendor;
    private ArrayList<Cinema> cinemas;

    public Cineplex(String name, String location, String vendor, ArrayList<Cinema> cinemas) {
        this.name = name;
        this.location = location;
        this.vendor = vendor;
        this.cinemas = cinemas;
    }
}
