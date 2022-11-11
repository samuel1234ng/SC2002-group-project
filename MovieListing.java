import java.util.ArrayList;

public class MovieListing {
    ArrayList<TimeSlot> timeSlots;

    private Movie movieName;

    private MovieStatus movieStatus;
    private MovieTypes movieType;

    //private double[] TicketPrice;
    public MovieListing(Movie movie, String movieType, String movieStatus) {
        this.movieName = movie;
        this.timeSlots = new ArrayList<>();
        this.movieType = MovieTypes.valueOf(movieType);
        this.movieStatus = MovieStatus.valueOf(movieStatus);
    }

    public Movie getMovie() {
        return movieName;
    }

    public void setMovie(Movie movie) {
        this.movieName = movie;
    }

    public void setStatus(String status) {
        this.movieStatus = MovieStatus.valueOf(status);
    }

public int getMovieType() {
        MovieTypes typeMovie = this.movieType;
        if(typeMovie== MovieTypes.IMAX_3D){
            return 1;
        }
        else if(typeMovie== MovieTypes.BLOCKBUSTER){
            return 2;
        }
        else if ((typeMovie== MovieTypes.IMAX_2D)){
            return 0;
        }else{
            return 0;
        }
    }



    public ArrayList<TimeSlot> getTimeSlots() {
        return timeSlots;
    }


    public void addTimeSlots(int timing, String date) {
        TimeSlot newTiming = new TimeSlot(timing, date);
        this.timeSlots.add(newTiming);
    }


    public TimeSlot getTime(int time) {
        for (TimeSlot timeSlot : timeSlots) {
            if (timeSlot.getTime() == time) {
                return timeSlot;
            }
        }
        return null;
    }

    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    public String getMovieTypeString(){
        return this.movieType.toString();
    }
    public void setTimeSlots(ArrayList<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public void addShowtime(int time, String date){
        TimeSlot t = new TimeSlot(time, date);
        this.timeSlots.add(t);
    }

    enum MovieTypes {BLOCKBUSTER, IMAX_3D, IMAX_2D}

    enum MovieStatus {COMING_SOON, NOW_SHOWING, END_OF_SHOWING}


}
