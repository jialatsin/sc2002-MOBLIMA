package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Cineplex.
 * A Cineplex has at least 3 Cinemas.
 */
public class Cineplex implements Serializable {
    /** Name of this cineplex. */
    private String name;
    /** Location of this cineplex. */
    private String location;
    /** List of cinemas in this cineplex. */
    private ArrayList<Cinema> cinemas;

    /**
     * Creates a Cineplex with the given attributes.
     * 
     * @param name     The cineplex name.
     * @param location The cineplex locaiton.
     * @param cinemas  The list of cinemas in the cineplex.
     */
    public Cineplex(String name, String location, ArrayList<Cinema> cinemas) {
        this.name = name;
        this.location = location;
        this.cinemas = cinemas;
    }

    /**
     * Returns a string containing this cineplex's name, location and list of
     * cinemas.
     * 
     * @return String containing this cineplex's name, location and list of cinemas.
     */
    @Override
    public String toString() {
        return "Cineplex [name=" + name + ", location=" + location + ", cinemas=" + cinemas + "]";
    }

    /**
     * Gets the name of this cineplex.
     * 
     * @return String containing this cineplex's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of this cineplex.
     * 
     * @param name The input cineplex name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location of this cineplex.
     * 
     * @return String containing this cineplex's location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Changes the location of this cineplex.
     * 
     * @param location The input cineplex location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the location of this cineplex.
     * 
     * @return String containing this cineplex's location.
     */
    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    /**
     * Changes the list of cinemas in this cineplex.
     * 
     * @param cinemas The input list of cinemas.
     */
    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    /**
     * Compares 2 Cineplex instances to check if they are identical.
     * 
     * @return Returns true if all attributes of both cineplexes are equal, else
     *         false.
     */
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
