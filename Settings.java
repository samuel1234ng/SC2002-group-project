import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Settings {

	int baseTicketPrice;
	int [] typeMod = new int[3];
	int [] seatMod = new int[2];
	int [] ageMod = new int[3];
	int [] dayMod = new int[3];
	int [] holidays = new int[365];

	
	Settings()
	{
		for(int j=0;j<365;j++)
		{
			holidays[j] = 0;
		}
		holidays[299] =  1;

		baseTicketPrice = 10;

		typeMod[0] = 0; // imax2d
		typeMod[1] = 2; // imax3d
		typeMod[2] = 3; // Blockbuster

		seatMod[0] = 0; // normal
		seatMod[1] = 2; // Elite

		ageMod[0] = 0; //adult
		ageMod[1] = -2; //child
		ageMod[2] = -1; //senior citizen

		dayMod[0] = 0; //weekday
		dayMod[1] = 2; //weekend
		dayMod[2] = 5; //holiday
	}
	public void storeSettings()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add(Integer.toString(baseTicketPrice));
		data.add("\n");
		data.add(Integer.toString(typeMod[0]));
		data.add("\n");
		data.add(Integer.toString(typeMod[1]));
		data.add("\n");
		data.add(Integer.toString(typeMod[2]));
		data.add("\n");
		data.add(Integer.toString(seatMod[0]));
		data.add("\n");
		data.add(Integer.toString(seatMod[1]));
		data.add("\n");
		data.add(Integer.toString(ageMod[0]));
		data.add("\n");
		data.add(Integer.toString(ageMod[1]));
		data.add("\n");
		data.add(Integer.toString(ageMod[2]));
		data.add("\n");
		data.add(Integer.toString(dayMod[0]));
		data.add("\n");
		data.add(Integer.toString(dayMod[1]));
		data.add("\n");
		data.add(Integer.toString(dayMod[2]));

		try {
			SettingsDB.writeFile("data/settings.txt", data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> data2 = new ArrayList<String>();

		for(int j=0;j<365;j++)
		{
			data2.add(String.valueOf(holidays[j]));
			data2.add("\n");
		}

		try {
			SettingsDB.writeFile("data/holidays.txt", data2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void loadSettings()
	{
		List<String> data = null;
		try {
			data=SettingsDB.readFile("data/settings.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		baseTicketPrice = Integer.parseInt(data.get(0));
		typeMod[0] = Integer.parseInt(data.get(1));
		typeMod[1] = Integer.parseInt(data.get(2));
		typeMod[2] = Integer.parseInt(data.get(3));
		seatMod[0] = Integer.parseInt(data.get(4));
		seatMod[1] = Integer.parseInt(data.get(5));
		ageMod[0] = Integer.parseInt(data.get(6));
		ageMod[1] = Integer.parseInt(data.get(7));
		ageMod[2] = Integer.parseInt(data.get(8));
		dayMod[0] = Integer.parseInt(data.get(9));
		dayMod[1] = Integer.parseInt(data.get(10));
		dayMod[2] = Integer.parseInt(data.get(11));
		
		List<String> data2 = null;
		try {
			data2=SettingsDB.readFile("data/holidays.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int j=0;j<365;j++)
		{
			holidays[j] = Integer.parseInt(data2.get(j));
		}
	}
	public void printSettings()
	{
		System.out.println("Base Ticket Price: $" + baseTicketPrice);
		System.out.println("IMAX 2D movie price modifier: " + typeMod[0]);
		System.out.println("IMAX 3D movie price modifier: " + typeMod[1]);
		System.out.println("Blockbuster movie price modifier: " + typeMod[2]);
		System.out.println("Normal seat price modifier: " + seatMod[0]);
		System.out.println("Elite seat price modifier: " + seatMod[1]);
		System.out.println("Adult price modifier: " + ageMod[0]);
		System.out.println("Child price modifier: " + ageMod[1]);
		System.out.println("Senior Citizen price modifier: " + ageMod[2]);
		System.out.println("Weekday price modifier: " + dayMod[0]);
		System.out.println("Weekend price modifier: " + dayMod[1]);
		System.out.println("Holiday price modifier: " + dayMod[2]);
		System.out.print("Holidays: ");
		for(int j=0;j<365;j++)
		{
			if (holidays[j]==1)
			{
				System.out.print(j);
				System.out.print(" ");
			}
		}
		System.out.println();
		System.out.println();
	}
	public void addHoliday(int day)
	{
		holidays[day] = 1;
	}
	public void removeHoliday(int day)
	{
		holidays[day] = 0;
	}
	public void changeBasePrice(int newPrice)
	{
		baseTicketPrice = newPrice;
	}
	
	public void changeTicketPrice(int setting,int option,int value)
	{
		if (setting==1)
		{
			typeMod[option-1]=value;
		}
		if (setting==2)
		{
			seatMod[option-1]=value;
		}
		if (setting==3)
		{
			ageMod[option-1]=value;
		}
		if (setting==4)
		{
			dayMod[option-1]=value;
		}
	}

	public int calculateTicketPrice(int cinemaType,int cinemaClass, int age, int day)
	{
		return baseTicketPrice + typeMod[cinemaType]+ seatMod[cinemaClass] + ageMod[age] + dayMod[day];
	}
	

}
