package entity;

import entity.Seat.SeatStatus;

public class SeatingLayout {
    Seat[][] seats;
    int rows;
    int columns;

    public SeatingLayout(Seat[][] seats, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new Seat[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                seats[r][c] = new Seat(r, c, SeatStatus.UNOCCUPIED);
            }
        }
    }
}
