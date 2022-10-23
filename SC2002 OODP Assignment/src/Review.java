public class Review {
    private String reviewer;
    private String movieTitle;
    private double rating;
    private String reviewDescription;

    public Review(String reviewer, String movieTitle, double rating, String reviewDescription){
        this.reviewer = reviewer;
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }

    public String getReviewer() {
        return reviewer;
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
