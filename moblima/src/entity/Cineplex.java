package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A Cineplex has a name identifier, a location and a list of cinemas that the cineplex contains. A Cineplex has at least 3 Cinemas. 
 * @author OOP SSP1 Lab Group 4
 * @version 30/10/2022
 */
public class Cineplex implements Serializable {
    /**
     * The name of cineplex
     */
	private String name;
	/**
	 * The location of cineplex
	 */
    private String location;
    /**
     * The list of cinemas
     */
    private ArrayList<Cinema> cinemas;
    /**
     * Creates a Cineplex with these given attributes. 
     * @param name	    The name of the cineplex
     * @param location	The location of the cineplex
     * @param cinemas	The the list of cinemas
     */
    public Cineplex(String name, String location, ArrayList<Cinema> cinemas) {
        this.name = name;
        this.location = location;
        this.cinemas = cinemas;
    }
    /**
     * String to display cineplex name, location and list of cinemas
     * @return String displaying cineplex name, location and list of cinemas
     */
    @Override
    public String toString() {
        return "Cineplex [name=" + name + ", location=" + location + ", cinemas=" + cinemas + "]";
    }
    /**
     * Get the name of the cineplex
     * @return name The name of the cineplex
     */
    public String getName() {
        return name;
    }
    /**
     * The name of cineplex
     * @param name The name of cineplex
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the location of the cineplex
     * @return location The location of the cineplex
     */
    public String getLocation() {
        return location;
    }
    /**
     * The location of the cineplex 
     * @param location The location of the cineplex
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Get the list of cinemas in the cineplex
     * @return cinemas The list of of cinemas in the cineplex
     */
    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }
    /**
     * The list of cinemas in the cineplex
     * @param cinemas The list of cinemas in the cineplex
     */
    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }
    /**
     * Compare 2 Cineplex Instances to check if they are identical
     * @return boolean  Return true if both cineplexes are identical based on their name, location and cinemas, else false
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
