package entity;

import java.io.Serializable;
import java.util.*;

import entity.Constants.SeatStatus;

public class SeatingLayout implements Serializable {
    Seat[][] seats;
    int rows;
    int columns;

    public SeatingLayout(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new Seat[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                seats[r][c] = new Seat(r, c, SeatStatus.UNOCCUPIED);
            }
        }
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SeatingLayout) {
            SeatingLayout other = (SeatingLayout) obj;
            return Arrays.deepEquals(this.seats, other.getSeats()) 
                    && this.rows == other.getRows()
                    && this.columns == other.getColumns();
        }
        return false;
    }
}
