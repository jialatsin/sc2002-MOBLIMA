package entity;

import java.io.Serializable;
import entity.Enumerators.SeatStatus;
/**
 * Represents a Seat in a SeatingLayout 
 * Contain the Seat's position and occupancy status
 * @author OOP SSP1 Lab Group 4
 * @version 7/11/2022
 */
public class Seat implements Serializable {
	/**
	 * row of the seat 
	 */
    private int row;
    /**
     * column of the seat
     */
    private int column;
    /**
     * status of the seat as in whether its available or not
     */
    private SeatStatus status;
    /**
     * Creates Information of the Seat using these give attributes
     * @param row    The row number of the seat
     * @param column The column number of the seat
     * @param status The status of the seat as in its availability
     */
    public Seat(int row, int column, SeatStatus status) {
        this.row = row;
        this.column = column;
        this.status = status;
    }
    /**
     * Get the row number of the seat
     * @return row The row number of the seat
     */
    public int getRow() {
        return row;
    }
    /**
     * Get the column number of the seat
     * @return column The column number of the seat
     */
    public int getColumn() {
        return column;
    }	
    /**
     * Get the status of the seat as in its availability
     * @return status The status of the seat as in its availability
     */
    public SeatStatus getStatus() {
        return status;
    }
    /**
     * The status of the seat as in its availability
     * @param status Ths status of the seat as in its availability
     */
    public void setStatus(SeatStatus status) {
        this.status = status;
    }
    /**
     * The row number of the seat
     * @param row The row number of the seat
     */
    public void setRow(int row) {
        this.row = row;
    }
    /**
     * The column number of the seat
     * @param column The column number of the seat
     */
    public void setColumn(int column) {
        this.column = column;
    }
    /**
     * String to display the seat layout and its availability
     * @return string to display the seat layout and its availability
     */
    @Override
    public String toString() {
        if (status == SeatStatus.UNOCCUPIED) {
            return "[ ]";
        } else if (status == SeatStatus.OCCUPIED) {
            return "[X]";
        } else {
            return "   ";
        }
    }
    /**
     * Compare 2 Seat Instances to check if they are identical
     * @return boolean      Return true if both seats are identical based on their row, column and status, else false
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
}
