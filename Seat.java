public class Seat {
    //class attributes
    private String seatId;
    private boolean assigned;
    private String viewerId; //from the viewer class
    //constructor
    public Seat(String seat_id){
        this.seatId = seat_id;
        this.assigned = false;
        this.viewerId = "0";
    }
    //methods
    public String getSeatId(){
        return this.seatId;
    }
    public String getViewerId(){
        return this.viewerId;
    }
    public boolean isAssigned(){
        return this.assigned;
    }
    public void assign(String viewer_id){
        this.viewerId = viewer_id;
        this.assigned = true;
    }
    public void unassign(){
        this.viewerId = "0";
        this.assigned = false;
    }


}