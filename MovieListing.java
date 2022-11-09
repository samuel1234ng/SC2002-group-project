import java.util.ArrayList;
import java.util.Scanner;


public class MovieListing {
    ArrayList<TimeSlot> timeSlots;

    private Movie movieName;

    private Moviestatus moviestatus;
    private Movietypes movietype;

    //private double[] TicketPrice;
    public MovieListing(Movie movie, String movietype, String moviestatus) {
        this.movieName = movie;
        this.timeSlots = new ArrayList<TimeSlot>();
        //this.TicketPrice = TicketPrice;
//        this.movietype = Movietypes.valueOf(movietype);
//        this.moviestatus = Moviestatus.valueOf(moviestatus);
    }
    public MovieListing() {
    }

    public Movie getMovie() {
        return movieName;
    }

    public void setMovie(Movie movie) {
        this.movieName = movie;
    }

//    public Moviestatus getStatus() {
//        return this.moviestatus;
//    }

    public void setStatus(String status) {
        this.moviestatus = Moviestatus.valueOf(status);
    }

public int getMovieType() {
        String typeMovie = this.movietype.name();
        int returnType;
        if(typeMovie=="IMAX_3D"){
            returnType = 1;
        }
        else if(typeMovie=="BLOCKBUSTER"){
            returnType = 2;
        }
        else{
            returnType = 0;
        }
        return returnType;

    }

    public ArrayList<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

//    public void setTimeSlots(ArrayList<TimeSlot> timeslots) {
//        this.timeSlots = timeslots;
//    }

    public void addTimeSlots(int timing, String date) {
        TimeSlot newtiming = new TimeSlot(timing, date);
        this.timeSlots.add(newtiming);


    }

//    public void setMovieTypes(Movietypes movietype) {
//        this.movietype = movietype;
//    }

    public TimeSlot getTime(int time) {
        for (TimeSlot timeSlot : timeSlots) {
            if (timeSlot.getTime() == time) {
                return timeSlot;
            }
        }
        return null;
    }

    public void setTimeSlots(ArrayList<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public void addShowtime(int time, String date){
        TimeSlot t = new TimeSlot(time, date);
        this.timeSlots.add(t);
    }

    enum Movietypes {BLOCKBUSTER, IMAX_3D, IMAX_2D};

    enum Moviestatus {COMING_SOON, NOW_SHOWING, END_OF_SHOWING}


}
