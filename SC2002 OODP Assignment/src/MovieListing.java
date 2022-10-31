import java.util.Scanner;
import java.util.Arrays;




public class MovieListing {
	enum movietype{blockbuster,imax3D};
	private movie movieName;	
	private movietype movietype;
	private int[]timeSlot;
	private double[] TicketPrice;
	
	public MovieListing( moive movie,int[] timeslot,double[] TicketPrice,movietype movietype) {
		this.movieName=movie;
		this.timeSlot=timeslot;
		this.TicketPrice=TicketPrice;
		this.movietype=movietype;
		
		
		
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
		for(int k=0;k<movie.length;k++) {
			Movie[k].movieName=movie[k];
			Scanner scan=new Scanner(System.in);
			System.out.println("Enter number of MovieType:");
			Movie[k].movietype= valueOf(scan.nextLine());
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
	MovieListing[] remove_Element(MovieListing[] arr,  int index) {
		if (arr == null || index < 0
	            || index >= arr.length) {
	 
	            return arr;
	        }
	 

	        MovieListing[] anotherArray = new MovieListing[arr.length - 1];
	
	        for (int i = 0, k = 0; i < arr.length; i++) {

	            if (i == index) {
	                continue;
	            }
	            anotherArray[k++] = arr[i];
	        }
	        return anotherArray;
	}
		

}
