
public class ConfigureSystemSettings {

	int baseticketprice;
	int []typemod=new int[3];
	int []classmod=new int[3];
	int []agemod=new int[3];
	int []daymod=new int[3];
	
	int []holidays=new int[50];
	
	ConfigureSystemSettings()
	{
		for(int j=0;j<10;j++)
		{
			holidays[j]= -1;
		}
		holidays[1]=2512;
		baseticketprice=10;
		
		typemod[0]=0;//normal
		typemod[1]=2;//3D
		typemod[2]=3;//Blockbuster
		
		classmod[0]=0;//normal
		classmod[1]=2;//Platinum Movie Suites
		classmod[2]=-2;//Budget

		agemod[0]=0;//adult
		agemod[1]=0;//child
		agemod[2]=0;//senior citizen

		daymod[0]=0;//weekday
		daymod[1]=2;//weekend
		daymod[2]=5;//holiday
	}
	
	void addholiday()
	{
		int i=0;
		while(holidays[i]==-1&&i<50)
		{
			i=i+1;
		}
	}
	void removeholiday(int date)
	{
		int i=0;
		while(holidays[i]==date&&i<50)
		{
			i=i+1;
		}
		holidays[i]=-1;
	}
	void changebaseprice(int newprice)
	{
		baseticketprice=newprice;
	}
	
	void changeticketprice(String setting,int option,int value)
	{
		if (setting=="type")
		{
			typemod[option]=value;
		}
		if (setting=="class")
		{
			classmod[option]=value;
		}
		if (setting=="age")
		{
			agemod[option]=value;
		}
		if (setting=="day")
		{
			daymod[option]=value;
		}
	}
	int calculateticketprice(int cinematype,int cinemaclass, int age, int day)
	{
		return baseticketprice+typemod[cinematype]+classmod[cinemaclass]+agemod[age]+daymod[day];
	}
	

}
