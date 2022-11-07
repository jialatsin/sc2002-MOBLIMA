package entity;

import java.io.Serializable;
import entity.Enumerators.CinemaClass;

/**
 * It represents a Cinema that belongs to a Cineplex. 
 * @author OOP SSP1 Lab Group 4
 * @version 30/10/2022
 */
public class Cinema implements Serializable {
    
	/**
	 *  The cinema code identifier 
	 *  code is the cinema code
	 */
	private String code;
	/**
	 *  seatingLayout is the seatingLayout of the movie
	 */
	private SeatingLayout seatingLayout;
	/**
	 *  cinemaClass is for the type of the cinema
	 */
    private CinemaClass cinemaClass;
    /**
     * Creates a Cinema with these given attributes
     * @param code             The cinema code identifier
     * @param seatingLayout    The cinema seating layout
     * @param cinemaClass      The tye of cinema 
     */
    public Cinema(String code, SeatingLayout seatingLayout, CinemaClass cinemaClass) {
        this.code = code;
        this.seatingLayout = seatingLayout;
        this.cinemaClass = cinemaClass;
    }
    /**
     * String to return cinema code and its class
     * @return String displaying cinema code and its class
     */
    @Override
    public String toString() {
        return "Cinema [code=" + code + ", cinemaClass=" + cinemaClass + "]";
    }
    /**
     * Get the cinema code 
     * @return code The cinema code
     */
    public String getCode() {
        return code;
    }
    /**
     * The Cinema code
     * @param code The cinema code
     */

    public void setCode(String code) {
        this.code = code;
    }
    /**
     * Get the seating layout of the cinema
     * @return seatingLayout The seating layout of the cinema
     */
    public SeatingLayout getSeatingLayout() {
        return (SeatingLayout) seatingLayout.clone();
    }
    /**
     * The seating layout of the cinema
     * @param seatingLayout The seating layout of the cinema
     */

    public void setSeatingLayout(SeatingLayout seatingLayout) {
        this.seatingLayout = seatingLayout;
    }
    /**
     * Get the class of the cinema chosen by movie-goer
     * @return cinemaClass The class of cinema chosen by movie-goer
     */
    
    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }
    /**
     * The class of the cinema chosen by movie-goer
     * @param cinemaClass The class of cinema chosen by movie-goer
     */

    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }
    /**
     * Compare 2 Cinema Instances to check if they are identical
     * @return boolean      Return true if both cinemas are identical based on their cinemacode, seating layout and cinema class, else false
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
