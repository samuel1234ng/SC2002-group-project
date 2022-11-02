import java.util.Scanner;
import java.util.Arrays;




public class MovieListing {
	enum Movietypes{blockbuster,imax3D};
	private Movie movieName;	
	private Movietypes movietype;
	private final char[] letters = {'I','H','G','F','E','D','C','B','A'};
	private TimeSlots[] timeSlots;
	private double[] TicketPrice;

	
	public MovieListing( movie movie,int timeslot,double[] TicketPrice,movietype movietype) {
		this.movieName=movie;
		this.TimeSlots.timing=timeslot;
		this.TicketPrice=TicketPrice;
		this.Movietype=movietype;
		for(int i=0;i<10;i++) {
			String alpha = letters[i];
			for(int j=0;j<16;j++) {
				String num = Integer.toString(j+1);
				String code = num+alpha;
				this.timeSlots.seating[i][j]=new Seat(code);
			}
		}
		
		
		
	}
	
	
	void updateMovieListing(MovieListing[] movieListing, double[] TicketPrice,String name,TimeSlots[] timeslot) {
		for(int i=0;i<movieListing.length;i++) {
			if(name==movieListing[i].movieName.title)
			
			movieListing[i].timeSlot=timeslot;
			movieListing[i].TicketPrice=TicketPrice;
		}

		
				
	}
	void createMovieListing(movie[] movie) {
		MovieListing Movie[]= new MovieListing[movie.length];
		for(int k=0;k<movie.length;k++) {
			Movie[k].movieName=movie[k];
			for(int i=0;i<16;i++) {
			for(int j=0;j<13;j++) {
				this.movie[k].Seat[i][j]=0;
			}
		}
			Scanner scan=new Scanner(System.in);
			System.out.println("Enter number of MovieType:");
			Movie[k].movietype= movietypes.valueOf(scan.nextLine());
			System.out.println("Enter number of Timeslot:");
			int size=scan.nextInt();
			System.out.println("Enter "+size +" Timeslots:");
			for (int j = 0 ; j < size; j++ ) {
		           Movie[k].timeSlots.timing = scan.nextInt();
		        }
			for(int i=0;i<10;i++) {
			String alpha = letters[i];
			for(int j=0;j<16;j++) {
				String num = Integer.toString(j+1);
				String code = num+alpha;
				Movie[k].timeSlots.seating[i][j]=new Seat(code);
			}
		}
			System.out.println("Enter 3 TicketPrice for children, adults and seniors respectively:");
			for (int l = 0 ; l < 3; l++ ) {
		           Movie[k].TicketPrice[l] = scan.nextDouble();
		        }
			
			
		}
		
		
		
		
		
		
	}
	MovieListing[] removeElement(MovieListing[] arr,  string Name) {

	 
	        MovieListing[] anotherArray = new MovieListing[arr.length - 1];
	
	        for (int i = 0, k = 0; i < arr.length; i++) {

	            if (Name == arr[i].movie.title) {
	                continue;
	            }
	            anotherArray[k++] = arr[i];
	        }
	        return anotherArray;
	}


		

}
