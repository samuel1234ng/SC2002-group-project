import java.util.ArrayList;

public class Cineplex{
	private final String cineplexName;
	private final Cinema[] cinemas;
	
	
	public Cineplex(String cineplexName, int numCinemas, int cineplexNumber) {
		this.cineplexName = cineplexName;
		this.cinemas= new Cinema[numCinemas];
        for(int i = 0; i < numCinemas; i++){
            this.cinemas[i] = new Cinema(i+1, cineplexNumber);
        }
	}

    public Cinema[] getCinemas(){
        return this.cinemas;
    }

    public ArrayList<Cinema> cinemasByMovie(String movieName){
		ArrayList<Cinema> movieCinemas= new ArrayList<>();
		for (Cinema cinema : cinemas) {
			ArrayList<MovieListing> cinemaListing = cinema.movieListings;
			for (MovieListing movieListing : cinemaListing) {
				String name = movieListing.getMovie().getTitle();
				if (name.equals(movieName)) {
					movieCinemas.add(cinema);
					break;
				}
			}
		}
		return movieCinemas;
	}

	public String getCineplexName() {
		return cineplexName;
	}

	public ArrayList<Movie> movieByCineplex(){
		ArrayList<Movie> cineplexMovies = new ArrayList<>();
		ArrayList<String> cineplexMovieNames = new ArrayList<>();
		for (Cinema c: this.cinemas) {
			ArrayList<Movie> cinemaMovies = SearchMovie.movieByCinema(c);
			for (Movie movie: cinemaMovies) {
				if(!cineplexMovieNames.contains(movie.getTitle())){
					cineplexMovies.add(movie);
					cineplexMovieNames.add(movie.getTitle());
				}
			}

		}
		return cineplexMovies;
	}

	public void listMovies(){
		for (Movie movie : this.movieByCineplex()) {
			System.out.println("Movie Title: " + movie.getTitle());
			System.out.println("Showing Status: " + movie.getStatus());
			System.out.println("Synopsis: " + movie.getSynopsis());
			System.out.println();
		}
	}
}