package entity;

import java.io.Serializable;
import java.util.*;

import entity.Constants.SeatStatus;

public class SeatingLayout implements Serializable {
    Seat[][] seats;
    int rows;
    int columns;
    int availableSeatsCount;

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

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getAvailableSeatsCount() {
        return availableSeatsCount;
    }

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
                if ((c + 1) % 3 == 0) {
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
            if ((c + 1) % 3 == 0) {
                seatsString += "  ";
            }
        }
        seatsString += "\n";

        return seatsString + "\nNumber of Available Seats: " + availableSeatsCount + "\n";
    }
}
