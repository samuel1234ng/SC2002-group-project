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
        ArrayList<TimeSlot> newtimeSlots = new ArrayList<TimeSlot>();
        this.timeSlots = newtimeSlots;
        //this.TicketPrice = TicketPrice;
        this.movietype = Movietypes.valueOf(movietype);
        this.moviestatus = Moviestatus.valueOf(moviestatus);
    }
    public MovieListing() {
    }

    public Movie getMovie() {
        return movieName;
    }

    public void setMovie(Movie movie) {
        this.movieName = movie;
    }

    public Moviestatus getStatus() {
        return this.moviestatus;
    }

    public void setStatus(String status) {
        this.moviestatus = Moviestatus.valueOf(status);
    }

    public Movietypes getMovieType() {
        return movietype;
    }

    public ArrayList<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(ArrayList<TimeSlot> timeslots) {
        this.timeSlots = timeslots;
    }

    public void addTimeSlots(int timing) {
        TimeSlot newtiming = new TimeSlot(timing);
        this.timeSlots.add(newtiming);


    }

    public void setMovieTypes(Movietypes movietype) {
        this.movietype = movietype;
    }

    public TimeSlot getTime(int time) {
        for (TimeSlot timeSlot : timeSlots) {
            if (timeSlot.getTiming() == time) {
                return timeSlot;
            }
        }
        return null;
    }
    /*public double[] getTicketPrice() {
        return TicketPrice;
    }

    public void setTicketPrice(double[] ticketprice) {
        this.TicketPrice = ticketprice;
    }*/

    public void updateMovieListing(int time) {
        TimeSlot addition = new TimeSlot(time);
        this.timeSlots.add(addition);


    }

//    public void createMovieListing(Movie[] movie) {
//        ArrayList<MovieListing> Movie = new ArrayList<MovieListing>(movie.length);
//        for (int k = 0; k < movie.length; k++) {
//            Movie.get(k).movieName = movie[k];
//        }
//    }

//    MovieListing[] removeElement(MovieListing[] arr, String Name) {
//
//
//        MovieListing[] anotherArray = new MovieListing[arr.length - 1];
//
//        for (int i = 0, k = 0; i < arr.length; i++) {
//
//            if (Name.equals(arr[i].movieName.getTitle())) {
//                continue;
//            }
//            anotherArray[k++] = arr[i];
//        }
//        return anotherArray;
//    }

    enum Movietypes {blockbuster, imax3D, platinum}

    enum Moviestatus {comingSoon, nowShowing, endOfShowing}


}