package entity;

public class Seat {
    public enum SeatStatus {
        UNOCCUPIED, OCCUPIED, NOTEXIST
    };

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
        } else if (status == SeatStatus.OCCUPIED) {
            return "[X]";
        } else {
            return "   ";
        }
    }
}
