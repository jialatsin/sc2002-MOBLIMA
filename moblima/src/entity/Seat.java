package entity;

import java.io.Serializable;

import entity.Constants.SeatStatus;

public class Seat implements Serializable {

    private int row;
    private int column;
    private SeatStatus status;

    public Seat(int row, int column, SeatStatus status) {
        this.row = row;
        this.column = column;
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        if (status == SeatStatus.UNOCCUPIED) {
            return "[ ]";
        } else {
            return "[X]";
        }
    }

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
