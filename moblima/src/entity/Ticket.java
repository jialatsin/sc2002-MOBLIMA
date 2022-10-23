package entity;

public class Ticket {
    public enum Age {
        ADULT, SENIOR, CHILD
    };

    private Seat seat;
    private Age age;

    public Ticket(Seat seat, Age age) {
        this.seat = seat;
        this.age = age;
    }

    public Seat getSeat() {
        return seat;
    }

    public Age getAge() {
        return age;
    }
}
