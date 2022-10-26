import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
	String []movie=new String[8];
	int numEmptySeat;
	int cinematype;
	
	Cinema(int type)
	{
		cinematype=type;
		for(int j=0;j<8;j++)
		{
			movie[j+1]= "";
		}
	}
	
	void storesettings()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add(Integer.toString(cinematype));
		data.add("\n");
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
		cinematype=Integer.parseInt(data.get(0));
		for(int j=0;j<8;j++)
		{
			movie[j+1]= data.get(j+1);
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
