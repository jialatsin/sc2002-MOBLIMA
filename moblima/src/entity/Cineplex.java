package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
    private String name;
    private String location;
    private ArrayList<Cinema> cinemas;

    public Cineplex(String name, String location, ArrayList<Cinema> cinemas) {
        this.name = name;
        this.location = location;
        this.cinemas = cinemas;
    }

    @Override
    public String toString() {
        return "Cineplex [name=" + name + ", location=" + location + ", cinemas=" + cinemas + "]";
    }

}
