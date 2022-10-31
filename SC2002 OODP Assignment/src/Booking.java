package sc2002.project;

public class Booking { //seat object should be part of movielisting
    private Viewer viewer;
    private long transaction_id; 
    private Cinema cinema;
    private Movie movie;
    
    public Booking(Viewer viewer,long transaction_id,Cinema cinema,Movie movie){
        this.viewer = viewer;
        this.transaction_id = transaction_id;
        this.cinema = cinema;
        this.movie = movie;
    }

    public void availableTimings()

    public void availableSeats()

    public void makeBooking()


    
    
    
    
    
    //cinema class needs cinema code
    //select movie, select cinema(movie), select time, available seats(cinema), select seat(will display)(cinema), makebooking
    //bookinghistory(should it be in this class)
}
