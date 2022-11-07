import java.util.ArrayList;
import java.util.Objects;

public class SearchMovie {
    public static void listMovies(ArrayList<Movie> movies){
        for (Movie movie : movies) {
            System.out.println("Movie Title: " + movie.getTitle());
            System.out.println("Showing Status: " + movie.getStatus());
            System.out.println("Synopsis: " + movie.getSynopsis());
            System.out.println();
        }
    }

    public static void listCurrentMovies(ArrayList<Movie> movies){
        int k = 1;
        for (Movie movie : movies) {
            if (movie.getStatus().name().equals("NOW_SHOWING") || movie.getStatus().name().equals("PREVIEW")) {
                System.out.printf("Movie %d : %s\n", k, movie.getTitle());
                k++;
            }
        }
    }

    public static Movie movieByName(String movieName, ArrayList<Movie> movies){
        for (Movie movie : movies) {
            //movielisting object has movie object attribute
            if (Objects.equals(movie.getTitle(), movieName)) {
                return movie;
            }
        }
        return null;
    }

    public void movieByCinema(Cinema cinema){
        ArrayList<MovieListing> all_listings = cinema.movielistings;
        for(int i=0;i<all_listings.size();i++){
            MovieListing listing = all_listings.get(i);
            Movie movie = listing.getMovie();
            System.out.printf("Movie %d : %s\n",i+1,movie.getTitle());
        }
    }

    public static ArrayList<Movie> movieByRating(ArrayList<Movie> pre_sorted_movies){
        Movie temp;
        for(int i=1;i<pre_sorted_movies.size();i++){
            for(int j=i;j>0;j--){
                String curr_rating = pre_sorted_movies.get(j).getOverallReviewerRating();
                double currRating = Double.parseDouble(curr_rating);
                String prev_rating = pre_sorted_movies.get(j-1).getOverallReviewerRating();
                double prevRating = Double.parseDouble(prev_rating);
                if(currRating<prevRating){
                    temp = pre_sorted_movies.get(j);
                    pre_sorted_movies.set(j, pre_sorted_movies.get(j-1));
                    pre_sorted_movies.set(j-1, temp);
                }
                else{
                    break;
                }
            }

        }
        return pre_sorted_movies;
    }



    public static ArrayList<Movie> movieByTickets(ArrayList<Movie> pre_sorted_movies) {
        Movie temp;
        for (int i = 1; i < pre_sorted_movies.size(); i++) {
            for (int j = i; j > 0; j--) {
                int curr_tickets = pre_sorted_movies.get(j).getNoOfTickets();
                int prev_tickets = pre_sorted_movies.get(j - 1).getNoOfTickets();

                if (curr_tickets < prev_tickets) {
                    temp = pre_sorted_movies.get(j);
                    pre_sorted_movies.set(j, pre_sorted_movies.get(j - 1));
                    pre_sorted_movies.set(j - 1, temp);
                } else {
                    break;
                }
            }

        }
        return pre_sorted_movies;
    }
    
    public static void printMovieDetails(Movie movie){
        if (movie == null){
            System.out.println("Sorry, there exists no such Movie");
            return;
        }
        int count = 0;
        System.out.println("Movie Title: " + movie.getTitle());
        System.out.println("Showing Status: " + movie.getStatus());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overall Reviewer Rating: " + movie.getOverallReviewerRating());
        System.out.println("Past Reviews: ");
        for (String element : movie.getPastReviews()) {
            if (element.equalsIgnoreCase("NA")) {
                System.out.print(element);
                break;
            }
            if (count >= 3) {
                System.out.println();
                count = 0;
            }
            System.out.print(element + "|");
            count++;
        }System.out.println();
        System.out.println("Number of tickets sold: " + movie.getNoOfTickets());
        System.out.println();
        }

}




