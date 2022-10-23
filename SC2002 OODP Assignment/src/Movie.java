import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Movie implements Serializable {
    private String title ;

    public enum ShowingStatus{COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING};
    private String synopsis;
    private String director;
    private ArrayList<String> cast;
    private String overallReviewerRating;
    private ArrayList<String> pastReviews;
    private ShowingStatus status;
    private String reviewFile = "reviews.txt" ; // review file path

    public Movie(String title, ShowingStatus status, String synopsis, String director,
                 ArrayList<String> cast, String overallReviewerRating, ArrayList<String> pastReviews){
        this.title = title;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.overallReviewerRating = overallReviewerRating;
        this.pastReviews = pastReviews;
        reviewsRatings(); // updates the average ratings and user reviews everytime with the latest changes
    }

    public String getTitle() {
        return title;
    }

    public ShowingStatus getStatus() {
        return status;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getDirector() {
        return director;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public String getOverallReviewerRating() {
        return overallReviewerRating;
    }

    public ArrayList<String> getPastReviews() {
        return pastReviews;
    }

    public void reviewsRatings(){
        double ans = 0.0, result = 0.0;
        int count = 0;
        try{
            ArrayList reviewList = ReviewDBText.readReviews(reviewFile);
            pastReviews.clear();
            for (int i = 0; i < reviewList.size(); i++) {
                Review review = (Review) reviewList.get(i);
                if(review.getMovieTitle().equalsIgnoreCase(getTitle())){
                    pastReviews.add(review.getReviewDescription());
                    ans += review.getRating();
                    count++;
                }
            }
            if(pastReviews.isEmpty())
                pastReviews.add("NA");

            overallReviewerRating = String.valueOf(ans/count);
            if(overallReviewerRating.equalsIgnoreCase("NaN"))
                overallReviewerRating = "NA";
        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }
}