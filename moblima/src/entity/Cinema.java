package entity;

import java.io.Serializable;

import entity.Enumerators.CinemaClass;

/**
 * Represents a Cinema that belongs to a Cineplex.
 */
public class Cinema implements Serializable {
    /** Unique code identifier of this cinema. */
    private String code;
    /** Seating layout template of this cinema. */
    private SeatingLayout seatingLayout;
    /** Class of this cinema. */
    private CinemaClass cinemaClass;

    /**
     * Creates a Cinema with the given attributes.
     * 
     * @param code          The cinema code identifier.
     * @param seatingLayout The cinema seating layout.
     * @param cinemaClass   The cinema class.
     */
    public Cinema(String code, SeatingLayout seatingLayout, CinemaClass cinemaClass) {
        this.code = code;
        this.seatingLayout = seatingLayout;
        this.cinemaClass = cinemaClass;
    }

    /**
     * Returns a string containing this cinema's code and its class.
     * 
     * @return String containing this cinema's code and its class.
     */
    @Override
    public String toString() {
        return "Cinema [code=" + code + ", cinemaClass=" + cinemaClass + "]";
    }

    /**
     * Gets the code of this cinema.
     * 
     * @return String containing this cinema's code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Changes the code of this cinema.
     * 
     * @param code The input cinema code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the seating layout template for this cinema.
     * 
     * @return Seating layout template of this cinema.
     */
    public SeatingLayout getSeatingLayout() {
        return (SeatingLayout) seatingLayout.clone();
    }

    /**
     * Changes the seating layout template for this cinema.
     * 
     * @param seatingLayout The input seating layout.
     */
    public void setSeatingLayout(SeatingLayout seatingLayout) {
        this.seatingLayout = seatingLayout;
    }

    /**
     * Gets the class of this cinema.
     * 
     * @return Class of this cinema.
     */
    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Changes the class of this cinema.
     * 
     * @param cinemaClass The input cinema class.
     */
    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    /**
     * Compares 2 Cinema instances to check if they are identical.
     * 
     * @return Returns true if all attributes of both cinemas are equal, else
     *         false.
     */
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
