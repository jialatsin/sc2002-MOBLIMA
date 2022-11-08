package entity;

import java.io.Serializable;
import java.util.*;

import entity.Enumerators.SeatStatus;

/**
 * It Represents the SeatingLayout of a Cinema
 * @author OOP SSP1 Lab Group 4
 * @version 30/10/2022
 */
public class SeatingLayout implements Serializable {
    /**
     * seats
     */
	Seat[][] seats;
    /**
     * rows of seats
     */
	int rows;
	/**
	 * columns of seats
	 */
    int columns;
    /**
     * available seats
     */
    int availableSeatsCount;
    /**
     * Creates a SeatingLayout using the given attributes
     * @param rows    The rows of the cinema
     * @param columns The columns of the cinema
     */
    public SeatingLayout(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new Seat[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                this.seats[r][c] = new Seat(r, c, SeatStatus.UNOCCUPIED);
            }
        }
        this.availableSeatsCount = rows * columns;
    }
    /**
     * Get the seats
     * @return seats The seats in the cinema
     */
    public Seat[][] getSeats() {
        return seats;
    }
    /**
     * The seats in the cinema
     * @param seats The seats in the cinema
     */
    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }
    /**
     * Get the rows in the cinema
     * @return rows The rows in the cinema
     */
    public int getRows() {
        return rows;
    }
    /**
     * The rows in the cinema
     * @param rows The rows in the cinema
     */
    public void setRows(int rows) {
        this.rows = rows;
    }
    /**
     * Get the columns in the cinema
     * @return columns The columns in the cinema
     */
    public int getColumns() {
        return columns;
    }
   /**
    * The columns in the cinema
    * @param columns The columns in the cinema
    */
    public void setColumns(int columns) {
        this.columns = columns;
    }
    /**
     * Get the number of seats available 
     * @return availableSeatsCount The number of seats available 
     */
    public int getAvailableSeatsCount() {
        return availableSeatsCount;
    }
    /**
     * The number of seats available 
     * @param availableSeatsCount The number of seats available 
     */
    public void setAvailableSeatsCount(int availableSeatsCount) {
        this.availableSeatsCount = availableSeatsCount;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SeatingLayout) {
            SeatingLayout other = (SeatingLayout) obj;
            return Arrays.deepEquals(this.seats, other.getSeats())
                    && this.rows == other.getRows()
                    && this.columns == other.getColumns()
                    && this.availableSeatsCount == other.getAvailableSeatsCount();
        }
        return false;
    }
    /**
     * String displaying seating layout 
     * @return string displaying seating layout 
     */
    @Override
    public String toString() {
        String seatsString = "";
        for (int r = 0; r < rows; r++) {
            // print row index
            seatsString += String.format("%2s ", r);
            // print seat
            for (int c = 0; c < columns; c++) {
                seatsString += seats[r][c];
                // print aisle
                if (c + 1 == columns / 2) {
                    seatsString += "  ";
                }
            }
            // next row
            seatsString += "\n";
        }
        // print column index
        seatsString += "   "; // print bottom left corner padding
        for (int c = 0; c < columns; c++) {
            seatsString += String.format(" %s ", (char) (c + 'A'));
            // print aisle
            if (c + 1 == columns / 2) {
                seatsString += "  ";
            }
        }
        seatsString += "\n";

        return seatsString + "\nNumber of Available Seats: " + availableSeatsCount + "\n";
    }
    /**
     * Assigning of seats
     * Sets a seat in given row and column to be OCCUPIED for the given showing
     * Returns assigned seat if UNOCCUPIED seat is successfully set to OCCUPIED
     * Returns null if seat is already OCCUPIED
     * @param row    The row number of the seat
     * @param column The column number of seat
     * @return seats The row and column of seat
     */
    public Seat assignSeat(int row, int column) {
        if (seats[row][column].getStatus() == SeatStatus.OCCUPIED) {
            return null;
        }
        seats[row][column].setStatus(SeatStatus.OCCUPIED);
        availableSeatsCount--;
        return seats[row][column];
    }

    /** 
     * Unassigning of seats, Sets a seat in given row and column to be UNOCCUPIED for the given showing
     * Returns unassigned seat if OCCUPIED seat is successfully set to UNOCCUPIED
     * Returns null if seat is already UNOCCUPIED
     * @param row    The row number of seat
     * @param column The column number of seat
     * @return seats The row and column number of seat
     */
    public Seat unassignSeat(int row, int column) {
        if (seats[row][column].getStatus() == SeatStatus.UNOCCUPIED) {
            return null;
        }
        seats[row][column].setStatus(SeatStatus.UNOCCUPIED);
        availableSeatsCount++;
        return seats[row][column];
    }
    /**
     * It returns seat in given row and column
     * @param row    The row number of seat
     * @param column The column number of seat
     * @return seats The row and column number of seat
     */
    public Seat getSeat(int row, int column) {
        return seats[row][column];
    }
    /**
     * The new Seating Layout
     * @return new seating layout
     */
    @Override
    public Object clone() {
        try {
            return (SeatingLayout) super.clone();
        } catch (CloneNotSupportedException e) {
            return new SeatingLayout(rows, columns);
        }
    }
}
