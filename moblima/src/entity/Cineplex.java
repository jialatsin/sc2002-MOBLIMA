package entity;

import java.io.Serializable;
import java.util.ArrayList;

/* Represents a Cineplex 
 * A Cineplex has a name identifier, a location and a list of cinemas that the cineplex contains. 
 * A Cineplex has at least 3 Cinemas
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cineplex) {
            Cineplex other = (Cineplex) obj;
            return this.name.equals(other.getName())
                    && this.location.equals(other.getLocation())
                    && this.cinemas.equals(other.getCinemas());
        }
        return false;
    }
}
