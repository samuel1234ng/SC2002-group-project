import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
	String []movie=new String[8];
	int numEmptySeat;
	
	Cinema()
	{
		for(int j=0;j<8;j++)
		{
			movie[j]= "";
		}
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
	
	void Createcinemashowtime(int time, String movielisting)
	{
		int timeslot=(time-800)/200;
		if (movie[timeslot]=="")
		{
			movie[timeslot]=movielisting;
		}
		else
		{
			System.out.print("There is already a movie schedule at this time \n");
		}
	}
	void Updatecinemashowtime(int time, String movielisting)
	{
		int timeslot=(time-800)/200;
		if (movie[timeslot]=="")
		{
			movie[timeslot]=movielisting;
		}
		else
		{
			System.out.print("There is no movie schedule at this time \n");
		}
	}
	void Removecinemashowtime(int time)
	{
		int timeslot=(time-800)/200;
		movie[timeslot]="";
		
	}

}

