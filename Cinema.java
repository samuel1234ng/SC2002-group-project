import java.util.ArrayList;

public class Cinema {
	ArrayList<MovieListing> movieListings;
	private final String cinemaId;


	public Cinema(int id, int cineplexNumber)
	{
		this.cinemaId="00" + id;

		MovieListingDB.getCinemaListing(cineplexNumber, id, MovieListingDB.readFile(), MovieDB.readMovies("data/movies.txt"));
	}

    public String getCinemaId(){
        return this.cinemaId;
    }

    public MovieListing getListing(String movieName){
		for (MovieListing movielisting : movieListings) {
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
