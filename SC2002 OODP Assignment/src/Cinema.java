import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
	ArrayList<MovieListing> movielistings=new ArrayList<MovieListing>();
	int cinemaid;
	
	
	Cinema(int id)
	{
		cinemaid=id;
	}
	
	
	int Createcinemashowtime(int time, Movie movie)
	{
		for (int i = 0; i < movielistings.size(); i++) 
		{
			if ((movielistings.get(i)).getmovie()==movie)
			{
				return movielistings.get(i).updatemovielisting(time);
			}
		}
		MovieListing mvl;
		mvl.createMovieListing(movie);
		movielistings.add(mvl);
		
	}
	int Removecinemashowtime(int time, Movie movie)
	{
		for (int i = 0; i < movielistings.size(); i++) 
		{
			if ((movielistings.get(i)).getmovie()==movie)
			{
				movielistings.get(i).removetime(time);
				return 0;
			}
		}
		return 1;
	}
	int Removecinemamovie(int time, Movie movie)
	{
		for (int i = 0; i < movielistings.size(); i++) 
		{
			if ((movielistings.get(i)).getmovie()==movie)
			{
				movielistings.remove(i);
				return 0;
			}
		}
		return 1;
	}
	MovieListing getmovie(int time)
	{
		int i=0;
		while(i<8)
		{
			if (movie[i].time==time&&movie[i].name!="")
			{
				return movie[i];
			}
			i++;
		}
		System.out.print("no movie at this time scheduled\n");
		return;
	}
    MovieListing getmovie(String mvname)
	{
		int i=0;
		while(i<8)
		{
			if (movie[i].name==mvname)
			{
				return movie[i];
			}
			i++;
		}
		System.out.print("no movie at this time scheduled\n");
		return;
	}

}
