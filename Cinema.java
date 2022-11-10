import java.util.ArrayList;

public class Cinema {
    private final String cinemaId;
    ArrayList<MovieListing> movieListings;


    public Cinema(int id, int cineplexNumber) {
        this.cinemaId = "00" + id;

        this.movieListings = MovieListingDB.getCinemaListing(cineplexNumber, id, MovieListingDB.readFile(), MovieDB.readMovies("data/movies.txt"));
    }

    public String getCinemaId() {
        return this.cinemaId;
    }

    public MovieListing getListing(String movieName) {
        for (MovieListing movielisting : this.getMovieListings()) {
            String name = movielisting.getMovie().getTitle();
            if (name.equals(movieName)) {
                return movielisting;
            }
        }
		return null;
    }

    public ArrayList<MovieListing> getMovieListings() {
        return movieListings;
    }
}
