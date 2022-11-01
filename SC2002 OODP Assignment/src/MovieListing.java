import java.util.Scanner;
import java.util.Arrays;




public class MovieListing {
	enum movietypes{blockbuster,imax3D};
	private movie movieName;	
	private movietypes movietype;
	private int[]timeSlot;
	private double[] TicketPrice;
	private Seat[][] seats;
	
	public MovieListing( movie movie,int[] timeslot,double[] TicketPrice,movietype movietype) {
		this.movieName=movie;
		this.timeSlot=timeslot;
		this.TicketPrice=TicketPrice;
		this.movietype=movietype;
		for(int i=0;i<16;i++) {
			for(int j=0;j<13;j++) {
				this.seats[i][j]=0;
			}
		}
		
		
		
	}
	
	
	void updateMovieListing(MovieListing[] movieListing, double[] TicketPrice,String name,int[] timeslot) {
		for(int i=0;i<movieListing.length;i++) {
			if(name==MovieListing[i].movieName.title)
			
			movieListing[i].timeSlot=timeslot;
			movieListing[i].TicketPrice=TicketPrice;
		}

		
				
	}
	void createMovieListing(movie[] movie) {
		MovieListing Movie[]= new MovieListing[movie.length];
		for(int i=0;i<16;i++) {
			for(int j=0;j<13;j++) {
				this.movie[i][j]=0;
			}
		}
		for(int k=0;k<movie.length;k++) {
			Movie[k].movieName=movie[k];
			Scanner scan=new Scanner(System.in);
			System.out.println("Enter number of MovieType:");
			Movie[k].movietype= movietypes.valueOf(scan.nextLine());
			System.out.println("Enter number of Timeslot:");
			int size=scan.nextInt();
			System.out.println("Enter "+size +" Timeslots:");
			for (int j = 0 ; j < size; j++ ) {
		           Movie[k].timeSlot[j] = scan.nextInt();
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
