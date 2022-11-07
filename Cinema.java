import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
	ArrayList<MovieListing> movielistings=new ArrayList<>();
	private final String cinemaId;
	
	
	Cinema(String id)
	{
		this.cinemaId=id;
	}
	
	
//	public void Createcinemashowtime(int time, Movie movie)
//	{
//		for (MovieListing movielisting : movielistings) {
//			if (movielisting.getMovie() == movie) {
//				movielisting.updateMovieListing(time);
//			}
//		}
//		//TODO: FIX!!
//		MovieListing mvl = new MovieListing();
//		mvl.createMovieListing(movie);
//        mvl.updateMovieListing(time);
//		movielistings.add(mvl);
//
//	}
//	void Removecinemashowtime(int time, Movie movie)
//	{
//		for (int i = 0; i < movielistings.size(); i++)
//		{
//			if ((movielistings.get(i)).getMovie()==movie)
//			{
//				movielistings.get(i).removeTime(time);
//				return;
//			}
//		}
//	}
//	void Removecinemamovie(int time, Movie movie)
//	{
//		for (int i = 0; i < movielistings.size(); i++)
//		{
//			if ((movielistings.get(i)).getMovie()==movie)
//			{
//				movielistings.remove(i);
//				return;
//			}
//		}
//	}
//	Movie getmovie(int time)
//	{
//		for (MovieListing movielisting : movielistings) {
//			if (movielisting.getMovieTime() == time) {
//				return movielisting.getMovie();
//			}
//		}
//		System.out.print("no movie at this time scheduled\n");
//		return null;
//	}
//    public MovieListing gettime(Movie movie)
//	{
//		for (MovieListing movielisting : movielistings) {
//			if (movielisting.getMovie() == movie) {
//				return movielisting;
//			}
//		}
//		System.out.print("this movie is not scheduled\n");
//		return null;
//	}

    public String getCinemaId(){
        return this.cinemaId;
    }

    public MovieListing getListing(String movieName){
		for (MovieListing movielisting : movielistings) {
			String name = movielisting.getMovie().getTitle();
			if (name.equals(movieName)) {
				return movielisting;
			}
		}
		return null;
	}

}
