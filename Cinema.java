
public class Cinema {
	Movielisting []movie=new Movielisting[8];
	int numEmptySeat;
	
	Cinema(String cinemaname)
	{
		String name = cinemaname;
		for(int j=0;j<8;j++)
		{
			movie[j]= new Movielisting();
		}
	}
	
	void Createcinemashowtime(int time, Movielisting movielisting)
	{
		int timeslot=(time-800)/200;
		if (movie[timeslot].name=="")
		{
			movie[timeslot]=movielisting;
		}
		else
		{
			System.out.print("There is already a movie schedule at this time \n");
		}
	}
	void Updatecinemashowtime(int time, Movielisting movielisting)
	{
		int timeslot=(time-800)/200;
		if (movie[timeslot].name=="")
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
		movie[timeslot].Removemovielisting();
		movie[timeslot]=new Movielisting();
		
	}

}
