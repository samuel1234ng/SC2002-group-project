import java.util.ArrayList;

public class Cineplex{
	private final String Cineplexname;
	private final Cinema[] cinemas;
	
	
	public Cineplex(String Cineplexname, int numCinemas) {
		this.Cineplexname = Cineplexname;
		this.cinemas= new Cinema[numCinemas];
        for(int i = 0; i < numCinemas; i++){
            this.cinemas[i] = new Cinema("00" + Integer.toString(i));
            // TODO: need to add movielistings to each cinema!!!
        }
	}

    public Cinema[] getCinemas(){
        return this.cinemas;
    }

    public Cinema cinemaByMovie(String movieName){

		for (Cinema cinema : cinemas) {
			ArrayList<MovieListing> cinemaListing = cinema.movielistings;
			for (MovieListing movieListing : cinemaListing) {
				String name = movieListing.getMovie().getTitle();
				if (name.equals(movieName)) {
					return cinema;
				}
			}
		}
		return null;
	}

	public String getCineplexname() {
		return Cineplexname;
	}
}
