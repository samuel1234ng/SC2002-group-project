import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigureSystemSettings {

	int baseticketprice;
	int []typemod=new int[3];
	int []classmod=new int[3];
	int []agemod=new int[3];
	int []daymod=new int[3];
	
	int []holidays=new int[365];
	
	ConfigureSystemSettings()
	{
		for(int j=0;j<365;j++)
		{
			holidays[j]= 0;
		}
		holidays[299]=1;
		baseticketprice=10;
		
		typemod[0]=0;//imax_2d
		typemod[1]=2;//imax_3d
		typemod[2]=3;//Blockbuster
		
		classmod[0]=0;//normal
		classmod[1]=2;//Elite 
		classmod[2]=-2;//Budget

		agemod[0]=0;//adult
		agemod[1]=-2;//child
		agemod[2]=-1;//senior citizen

		daymod[0]=0;//weekday
		daymod[1]=2;//weekend
		daymod[2]=5;//holiday
	}
	public void storesettings()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add(Integer.toString(baseticketprice));
		data.add("\n");
		data.add(Integer.toString(typemod[0]));
		data.add("\n");
		data.add(Integer.toString(typemod[1]));
		data.add("\n");
		data.add(Integer.toString(typemod[2]));
		data.add("\n");
		data.add(Integer.toString(classmod[0]));
		data.add("\n");
		data.add(Integer.toString(classmod[1]));
		data.add("\n");
		data.add(Integer.toString(classmod[2]));
		data.add("\n");
		data.add(Integer.toString(agemod[0]));
		data.add("\n");
		data.add(Integer.toString(agemod[1]));
		data.add("\n");
		data.add(Integer.toString(agemod[2]));
		data.add("\n");
		data.add(Integer.toString(daymod[0]));
		data.add("\n");
		data.add(Integer.toString(daymod[1]));
		data.add("\n");
		data.add(Integer.toString(daymod[2]));
		
		
		try {
			SettingsDB.writeFile("data/Settings.txt", data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String> data2 = new ArrayList<String>();
		for(int j=0;j<365;j++)
		{
			data2.add(Integer.toString(holidays[j]));
			data2.add("\n");
		}
		
		
		try {
			SettingsDB.writeFile("data/Holidays.txt", data2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void loadsettings()
	{
		List<String> data = null;
		try {
			data=SettingsDB.readFile("data/Settings.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		baseticketprice=Integer.parseInt(data.get(0));
		typemod[0]=Integer.parseInt(data.get(1));
		typemod[1]=Integer.parseInt(data.get(2));
		typemod[2]=Integer.parseInt(data.get(3));
		classmod[0]=Integer.parseInt(data.get(4));
		classmod[1]=Integer.parseInt(data.get(5));
		classmod[2]=Integer.parseInt(data.get(6));
		agemod[0]=Integer.parseInt(data.get(7));
		agemod[1]=Integer.parseInt(data.get(8));
		agemod[2]=Integer.parseInt(data.get(9));
		daymod[0]=Integer.parseInt(data.get(10));
		daymod[1]=Integer.parseInt(data.get(11));
		daymod[2]=Integer.parseInt(data.get(12));
		
		List<String> data2 = null;
		try {
			data2=SettingsDB.readFile("data/Holidays.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int j=0;j<365;j++)
		{
			holidays[j]=Integer.parseInt(data2.get(j));
		}
	}
	void printsettings()
	{
		System.out.print("normal movie price modifier: ");
		System.out.print(Integer.toString(typemod[0]));
		System.out.print("\n3D movie price modifier: ");
		System.out.print(Integer.toString(typemod[1]));
		System.out.print("\nBlockbuster movie price modifier: ");
		System.out.print(Integer.toString(typemod[2]));
		System.out.print("\nnormal class price modifier: ");
		System.out.print(Integer.toString(classmod[0]));
		System.out.print("\nPlatinum Movie Suites price modifier: ");
		System.out.print(Integer.toString(classmod[1]));
		System.out.print("\nBudget class price modifier: ");
		System.out.print(Integer.toString(classmod[2]));
		System.out.print("\nadult price modifier: ");
		System.out.print(Integer.toString(agemod[0]));
		System.out.print("\nchild price modifier: ");
		System.out.print(Integer.toString(agemod[1]));
		System.out.print("\nsenior citizen price modifier: ");
		System.out.print(Integer.toString(agemod[2]));
		System.out.print("\nweekday price modifier: ");
		System.out.print(Integer.toString(daymod[0]));
		System.out.print("\nweekend price modifier: ");
		System.out.print(Integer.toString(daymod[1]));
		System.out.print("\nholiday class price modifier: ");
		System.out.print(Integer.toString(daymod[2]));
		System.out.print("\nholidays:");
		for(int j=0;j<365;j++)
		{
			if (holidays[j]==1)
			{
				System.out.print(Integer.toString(j));
				System.out.print(" ");
			}
		}
	}
	void addholiday(int day)
	{
		holidays[day]=1;
	}
	void removeholiday(int day)
	{
		holidays[day]=0;
	}
	void changebaseprice(int newprice)
	{
		baseticketprice=newprice;
	}
	
	void changeticketprice(int setting,int option,int value)
	{
		if (setting==1)
		{
			typemod[option-1]=value;
		}
		if (setting==2)
		{
			classmod[option-1]=value;
		}
		if (setting==3)
		{
			agemod[option-1]=value;
		}
		if (setting==4)
		{
			daymod[option-1]=value;
		}
	}
	int calculateticketprice(int cinematype,int cinemaclass, int age, int day)
	{
		return baseticketprice+typemod[cinematype]+classmod[cinemaclass]+agemod[age]+daymod[day];
	}
	

}
