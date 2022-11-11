import java.util.ArrayList;

public class MovieListing {
    ArrayList<TimeSlot> timeSlots;

    private Movie movieName;

    private Movie.ShowingStatus movieStatus;
    private Movie.MovieType movieType;

    //private double[] TicketPrice;
    public MovieListing(Movie movie, String movieType, String movieStatus) {
        this.movieName = movie;
        this.timeSlots = new ArrayList<>();
        this.movieType = Movie.MovieType.valueOf(movieType);
        this.movieStatus = Movie.ShowingStatus.valueOf(movieStatus);
    }

    public Movie getMovie() {
        return movieName;
    }

    public void setMovie(Movie movie) {
        this.movieName = movie;
    }
    public void setStatus(String status) {
        this.movieStatus = Movie.ShowingStatus.valueOf(status);
    }

public int getMovieType() {
        Movie.MovieType typeMovie = this.movieType;
        if(typeMovie== Movie.MovieType.IMAX_3D){
            return 1;
        }
        else if(typeMovie== Movie.MovieType.BLOCKBUSTER){
            return 2;
        }
        else if ((typeMovie== Movie.MovieType.IMAX_2D)){
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

    public Movie.ShowingStatus getMovieStatus() {
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
}
