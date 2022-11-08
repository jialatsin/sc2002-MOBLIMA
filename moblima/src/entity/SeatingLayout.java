package entity;

import java.io.Serializable;
import java.util.*;

import entity.Enumerators.SeatStatus;

/** Represents the SeatingLayout of a cinema. */
public class SeatingLayout implements Serializable {
    /** The seat representation for this seating layout. */
    Seat[][] seats;
    /** The number of rows of seats in this seating layout. */
    int rows;
    /** The number of columns of seats in this seating layout. */
    int columns;
    /** The available number of seats remaining in this seating layout. */
    int availableSeatsCount;

    /**
     * Creates a seating layout with given attributes.
     * By default, all initialized seats will have an unoccupied seat status.
     * 
     * @param rows    The number of rows of the seating layout.
     * @param columns The number of columns of the seating layout.
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
     * Gets the seat representation of this seating layout.
     * 
     * @return Seat representation of this seating layout.
     */
    public Seat[][] getSeats() {
        return seats;
    }

    /**
     * Changes the seat representation of this seating layout.
     * 
     * @param seats New seat matrix.
     */
    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    /**
     * Gets the number of rows of this seating layout.
     * 
     * @return Number of rows of this seating layout.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Changes the number of rows of this seating layout.
     * 
     * @param rows New number of rows.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Gets the number of columns of this seating layout.
     * 
     * @return Number of columns of this seating layout.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Changes the number of columns of this seating layout.
     * 
     * @param columns New number of columns.
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Gets the number of available seats in this seating layout.
     * 
     * @return Number of available seats in this seating layout.
     */
    public int getAvailableSeatsCount() {
        return availableSeatsCount;
    }

    /**
     * Changes the number of available seats in this seating layout.
     * 
     * @param availableSeatsCount New number of available seats.
     */
    public void setAvailableSeatsCount(int availableSeatsCount) {
        this.availableSeatsCount = availableSeatsCount;
    }

    /**
     * Compares 2 SeatingLayout instances to check if they are identical.
     * 
     * @return Returns true if all attributes of both seating layouts are equal,
     *         else false.
     */
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
     * Returns a string displaying this seating layout.
     * 
     * @return String displaying this seating layout.
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
     * Assigns a seat in this seating layout.
     * Sets a seat in given row and column to be OCCUPIED for a showing.
     * 
     * @param row    Row number of the seat to be assigned.
     * @param column Column number of the seat to be assigned.
     * @return Returns assigned seat if UNOCCUPIED seat is successfully set to
     *         OCCUPIED, else returns null if seat is already OCCUPIED.
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
     * Unassigns a seat in this seating layout.
     * Sets a seat in given row and column to be UNOCCUPIED for a showing.
     * 
     * @param row    Row number of the seat to be unassigned.
     * @param column Column number of the seat to be unassigned.
     * @return Returns assigned seat if OCCUPIED seat is successfully set to
     *         UNOCCUPIED, else returns null if seat is already UNOCCUPIED.
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
     * Returns the seat positioned in the given row and column.
     * 
     * @param row    Row number of the seat to return.
     * @param column Column number of the seat to return.
     * @return Seat in the given row and column.
     */
    public Seat getSeat(int row, int column) {
        return seats[row][column];
    }

    /**
     * Creates and returns a deep copy of this SeatingLayout.
     * 
     * @return A deep copy of this seating layout.
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
