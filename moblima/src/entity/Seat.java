package entity;

import java.io.Serializable;

import entity.Enumerators.SeatStatus;

/**
 * Represents a Seat in the seating layout of a cinema.
 * Contains the seat's position and occupancy status.
 */
public class Seat implements Serializable {
    /** Row number of this seat. */
    private int row;
    /** Column number of this seat. */
    private int column;
    /** Occupancy status of this seat. */
    private SeatStatus status;

    /**
     * Creates a Seat with given attributes.
     * 
     * @param row    The row number of the seat.
     * @param column The column number of the seat.
     * @param status The occupancy status of the seat.
     */
    public Seat(int row, int column, SeatStatus status) {
        this.row = row;
        this.column = column;
        this.status = status;
    }

    /**
     * Gets the row number of this seat.
     * 
     * @return Row number of this seat.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column number of this seat.
     * 
     * @return column number of this seat.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets the occupancy status of this seat.
     * 
     * @return Occupancy status of this seat.
     */
    public SeatStatus getStatus() {
        return status;
    }

    /**
     * Changes the occupancy status of this seat.
     * 
     * @param status New seat occupancy status.
     */
    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    /**
     * Changes the row number of this seat.
     * 
     * @param row New row number.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Changes the column number of this seat.
     * 
     * @param column New column number.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Returns a string displaying this seat and its availability.
     * 
     * @return String displaying this seat and its availability.
     */
    @Override
    public String toString() {
        if (status == SeatStatus.UNOCCUPIED) {
            return "[ ]";
        } else {
            return "[X]";
        }
    }

    /**
     * Compares 2 Seat instances to check if they are identical.
     * 
     * @return Returns true if all attributes of both seats are equal, else
     *         false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Seat) {
            Seat other = (Seat) obj;
            return this.row == other.getRow()
                    && this.column == other.getColumn()
                    && this.status == other.getStatus();
        }
        return false;
    }

    /**
     * Creates and returns a deep copy of this Seat.
     * 
     * @return A deep copy of this seat.
     */
    @Override
    public Object clone() {
        try {
            return (Seat) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Seat(this.row, this.column, this.status);
        }
    }
}
