import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a movie stored in the movie database (in a movies.txt file).
 * There can be multiple movies stored in the movie database.
 * @author Karishein Chandran
 * @version 1.0
 * @since 2022-11-10
 */
public class Movie implements Serializable {

    /**
     * The title of this movie.
     */
    private String title;

    /**
     * The showing status of this movie in Cinema.
     */
    public enum ShowingStatus{COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING}

    /**
     * The synopsis of this movie.
     */
    private String synopsis;

    /**
     * The director of this movie.
     */
    private String director;

    /**
     * The cast of this movie.
     */
    private ArrayList<String> cast;

    /**
     * The genre of this movie.
     */
    private String genre;

    /**
     * The language (e.g. English, Korean, Tamil, etc.) of this movie.
     */
    private String language;

    /**
     * The duration of this movie.
     */
    private String runTime;

    /**
     * The movie rating (e.g. G, PG, PG13, NC16, M18, R21) of this movie.
     */
    private String movieRating;

    /**
     * The release date of this movie.
     */
    private String release;

    /**
     * The average rating of this movie based of all the reviewer's ratings.
     */
    private String overallReviewerRating;

    /**
     * The reviews of this movie written by the reviewers.
     */
    private ArrayList<String> pastReviews;

    /**
     * The number of tickets sold for this movie.
     */
    private int noOfTickets;

    /**
     * Stores the current status of this movie.
     */
    private ShowingStatus status;

    /**
     * The review file part of the reviews.txt file.
     */
    private String reviewFile = "data/reviews.txt" ;

    /**
     * Creates a new movie with the given parameters.
     * @param title This Movie's title.
     * @param status This Movie's status.
     * @param synopsis This Movie's synopsis.
     * @param director This Movie's director.
     * @param cast This Movie's list of cast.
     * @param language This Movie's language.
     * @param genre This Movie's genres.
     * @param runTime This Movie's running time.
     * @param movieRating This Movie's rating.
     * @param release This Movie's release date.
     * @param overallReviewerRating This Movie's average viewer rating.
     * @param pastReviews This Movie's past reviews written by viewers.
     * @param noOfTickets This Movie's number of tickets sold.
     * @see #updateReviewsRatings()
     */
    public Movie(String title, ShowingStatus status, String synopsis, String director, ArrayList<String> cast,
                 String language, String genre, String runTime, String movieRating, String release,
                 String overallReviewerRating, ArrayList<String> pastReviews, int noOfTickets){
        this.title = title;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.genre = genre;
        this.language = language;
        this.runTime = runTime;
        this.movieRating = movieRating;
        this.release = release;
        this.overallReviewerRating = overallReviewerRating;
        this.pastReviews = pastReviews;
        this.noOfTickets = noOfTickets;
        updateReviewsRatings();
    }

    /**
     * Gets the title of this Movie.
     * @return this Movie's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the current status of this Movie in cinemas.
     * @return this Movie's current status.
     */
    public ShowingStatus getStatus() {
        return status;
    }

    /**
     * Gets the synopsis of this Movie.
     * @return this Movie's synopsis.
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Gets the director of this Movie.
     * @return this Movie's director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Gets the cast of this Movie.
     * @return this Movie's list of cast.
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * Gets the genre of this Movie.
     * @return this Movie's genre.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets the language of this Movie.
     * @return this Movie's language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Gets the running time of this Movie.
     * @return this Movie's run time.
     */
    public String getRunTime() {
        return runTime;
    }

    /**
     * Gets the rating of this Movie.
     * @return this Movie's rating.
     */
    public String getMovieRating() {
        return movieRating;
    }

    /**
     * Gets the release date of this Movie.
     * @return this Movie's release date.
     */
    public String getRelease() {
        return release;
    }

    /**
     * Gets the viewer's average rating of this Movie.
     * @return this Movie's overall average rating.
     */
    public String getOverallReviewerRating() {
        return overallReviewerRating;
    }

    /**
     * Gets the past reviews written by the reviewers of this Movie.
     * @return this Movie's list of past reviews.
     */
    public ArrayList<String> getPastReviews() {
        return pastReviews;
    }

    /**
     * Gets the number of tickets sold for this Movie.
     * @return this Movie's current number of tickets sold.
     */
    public int getNoOfTickets() {
        return noOfTickets;
    }

    /**
     * Computes and updates the average rating of the reviewers and reviews written, by reading from the review file.<br>
     * The pastReviews and overallReviewer rating for this Movie is updated with any new ratings and reviews from the review database.<br>
     * This method also checks if there's only one review and does the necessary updates.<br>
     */
    private void updateReviewsRatings(){
        double ans = 0.0, result;
        int count = 0;
            ArrayList reviewList = ReviewDB.readReviews(reviewFile);
            pastReviews.clear();
            for (int i = 0; i < reviewList.size(); i++) {
                Review review = (Review) reviewList.get(i);
                if(review.getMovieTitle().equalsIgnoreCase(getTitle())){
                    pastReviews.add(review.getReviewerName());
                    pastReviews.add(String.valueOf(review.getRating()));
                    pastReviews.add(review.getReviewDescription());
                    ans += review.getRating();
                    count++;
                }
            }
            if(pastReviews.isEmpty())
                pastReviews.add("NA");

            if(count > 1){
                result = ans/count;
                result = Math.round(result*10)/10.0;
                overallReviewerRating = String.valueOf(result);
            }
            else
                overallReviewerRating = "NA";
        }
}
