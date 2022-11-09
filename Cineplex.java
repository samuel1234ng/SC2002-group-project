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

    public Cinema cinemaByMovie(String movieName){

		for (Cinema cinema : cinemas) {
			ArrayList<MovieListing> cinemaListing = cinema.movieListings;
			for (MovieListing movieListing : cinemaListing) {
				String name = movieListing.getMovie().getTitle();
				if (name.equals(movieName)) {
					return cinema;
				}
			}
		}
		return null;
	}

	public String getCineplexName() {
		return cineplexName;
	}
}