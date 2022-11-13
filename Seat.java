/**
 * Class that stores the booking status of seats
 *
 * @author Samuel Ng
 * @version 1.0
 * @since 2022-11-05
 */
public class Seat {
    //class attributes
    private final String seatId;
    private boolean assigned;

    public Seat(String seat_id) {
        this.seatId = seat_id;
        this.assigned = false;
    }

    /**
     * Returns seat ID of viewer that booked this seat
     *
     * @return ID of viewer
     */
    public boolean isAssigned() {
        return this.assigned;
    }

    /**
     * change the status of the seat to booked
     */
    public void assign() {
        this.assigned = true;
    }

    /**
     * change the status of the seat to not booked, remove viewer ID
     */
    public void unAssign() {
        this.assigned = false;
    }

}