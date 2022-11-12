import java.util.ArrayList;
/**
 * Class representing booking status of a movie
 * @author Samuel Ng
 * @version 1.0
 * @since 2022-11-05
 *
 */
public class MovieListing {
    /**
	 * Arraylist of timeslots objects 
	 */
    private ArrayList<TimeSlot> timeSlots;
    /**
	 * Movie object
	 */
    private Movie movieName;
    /**
	 * status of movie
	 */
    private Movie.ShowingStatus movieStatus;
    /**
	 * type of movie
	 */
    private Movie.MovieType movieType;

    //private double[] TicketPrice;
    /**
	 * Constructor, sets movie, type and status
	 * @param movie movie
	 * @param movieType type of movie
	 * @param movieStatus status of movie
	 */
    public MovieListing(Movie movie, String movieType, String movieStatus) {
        this.movieName = movie;
        this.timeSlots = new ArrayList<>();
        this.movieType = Movie.MovieType.valueOf(movieType);
        this.movieStatus = Movie.ShowingStatus.valueOf(movieStatus);
    }
    /**
	 * Returns movie object
	 * @return movie object
	 */
    public Movie getMovie() {
        return movieName;
    }
    /**
	 * Change movie for this movielisting
	 * @param movie new movie
	 */
    public void setMovie(Movie movie) {
        this.movieName = movie;
    }
    /**
	 * Change status
	 * @param status status for this movielisting
	 */
    public void setStatus(String status) {
        this.movieStatus = Movie.ShowingStatus.valueOf(status);
    }
    /**
	 * Return type if movie
	 * @return type of movie
	 */
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


    /**
	 * Return timeslot arraylist
	 * @return timeslot arraylist
	 */
    public ArrayList<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    /**
     * Add new times that this movie is playing at
     * @param timing time of movie
     * @param date date of movie
     */
    public void addTimeSlots(int timing, String date) {
        TimeSlot newTiming = new TimeSlot(timing, date);
        this.timeSlots.add(newTiming);
    }

    /**
     * returns timeslot which is at a specified time
     * @param time time of timeslot
     * @return timeslot
     */
    public TimeSlot getTime(int time) {
        for (TimeSlot timeSlot : timeSlots) {
            if (timeSlot.getTime() == time) {
                return timeSlot;
            }
        }
        return null;
    }
    /**
     * return status of movie
     * @return status of movie
     */
    public Movie.ShowingStatus getMovieStatus() {
        return movieStatus;
    }
    /**
     * return type of movie as a string
     * @return type of movie
     */
    public String getMovieTypeString(){
        return this.movieType.toString();
    }
    /**
     * Changes entire timeslots array
     * @param timeSlots new timeslots array
     */
    public void setTimeSlots(ArrayList<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }
    /**
     * adds a new timeslot
     * @param time time of timeslot
     * @param date date of timeslot
     */
    public void addShowtime(int time, String date){
        TimeSlot t = new TimeSlot(time, date);
        this.timeSlots.add(t);
    }
}
