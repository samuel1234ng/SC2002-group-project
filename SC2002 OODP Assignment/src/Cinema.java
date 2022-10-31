import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
	Movielisting[] movie=new Movielisting[8];
	int numEmptySeat;
	boolean[][] seat= new boolean[20][20];
	int cinemaid;
	
	
	Cinema(int id)
	{
		for(int i=0;i<8;i++)
		{
			movie[i]= new Movielisting();
		}
		for(int j=0;j<20;j++)
		{
			for(int k=0;k<20;k++)
			{
				seat[j][k]=false;
			}
		}
		id=cinemaid;
	}
	
	void storesettings()
	{
		ArrayList<String> data = new ArrayList<String>();
		
		for(int j=0;j<8;j++)
		{
			data.add(movie[j]);
			data.add("\n");
		}
		
		try {
			DbIO.writeFile("CinemaSettings", data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void loadsettings()
	{
		List<String> data = null;
		try {
			data=DbIO.readFile("CinemaSettings");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int j=0;j<8;j++)
		{
			movie[j]= data.get(j);
		}
	}
	
	int reserveseat(int x, int y)
	{
		if(seat[x][y]==true)
		{
			//System.out.print("This seat is already booked \n");
			return 1;//error
		}
		else
		{
			seat[x][y]=true;
			return 0;
		}
	}
	
	int unreserveseat(int x, int y)
	{
		if(seat[x][y]==true)
		{
			//System.out.print("This seat is not booked \n");
			return 1;//error
		}
		else
		{
			seat[x][y]=false;
			return 0;
		}
	}
	
	int Createcinemashowtime(int time, String movielisting)
	{
		int i=0;
		while(i<8)
		{
			if (movie[i].time==time)
			{
				//System.out.print("There is already a movie at this time \n");
				return 1;
			}
			i++;
		}
		i=0;
		while(i<8)
		{
			if (movie[i].name=="")
			{
				movie[i].name=movielisting;
				movie[i].time=time;
				return 0;
			}
			i++;
		}
		//System.out.print("No more time slots \n");
		return 1;
	}
	int Updatecinemashowtime(int time, String movielisting)
	{
		int i=0;
		while(movie[i].name!=movielisting&&i<8)
		{
			
			i++;
			if (i>8)
			{
				//System.out.print("no movie of this name scheduled\n");
				return 0;
			}
		}
		
		if (i==8)
		{
			//System.out.print("no movie of this name scheduled\n");
			return 0;
		}
		else
		{
			movie[i].name=movielisting;
			movie[i].time=time;
			return 1;
		}
	}
	int Removecinemashowtime(int time)
	{
		int i=0;
		while(i<8)
		{
			if (movie[i].time==time)
			{
				movie[i].name="";
				movie[i].time=-1;
				return 0;
			}
			i++;
		}
		System.out.print("no movie of this name scheduled\n");
		return 1;
	}

}
