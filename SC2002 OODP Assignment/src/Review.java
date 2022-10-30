public class Review {
    private String reviewerName;
    private String movieTitle;
    private double rating;
    private String reviewDescription;

    public Review(String reviewerName, String movieTitle, double rating, String reviewDescription){
        this.reviewerName = reviewerName;
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

}
