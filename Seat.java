/**
 * Class that stores the booking status of seats
 * @author Samuel Ng
 * @version 1.0
 * @since 2022-11-05
 *
 */
public class Seat {
    //class attributes
    private final String seatId;
    private boolean assigned;
    private String viewerId; //from the viewer class
    //constructor
    public Seat(String seat_id){
        this.seatId = seat_id;
        this.assigned = false;
        this.viewerId = "0";
    }


    //methods
   /**
    *  Returns seat ID of this seat
    * @return ID of seat
    */
    public String getSeatId(){
        return this.seatId;
    }
    /**
     * 
     * @return
     */
    public String getViewerId(){
        return this.viewerId;
    }
    /**
     * Returns seat ID of viewer that booked this seat
     * @return ID of viewer
     */
    public boolean isAssigned(){
        return this.assigned;
    }
    /**
     * Book the seat
     * @param viewer_id viewer that booked the seat
     */
    public void assign(String viewer_id){
        this.viewerId = viewer_id;
        this.assigned = true;
    }

    /**
     * change the status of the seat to booked
     */
    public void assign(){
        this.assigned = true;
    }
    /**
     * change the status of the seat to not booked, remove viewer ID
     */
    public void unassign(){
        this.viewerId = "0";
        this.assigned = false;
    }

}