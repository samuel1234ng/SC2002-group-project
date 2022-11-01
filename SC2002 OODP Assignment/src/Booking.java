
import java.util.Scanner;

public class Booking { 
    //each movielisting has 2D array of seat objects
    
    private Viewer viewer;
    private final char[] letters = {'I','H','G','F','E','D','C','B','A'};
    //private long transactionId; 
    //private MovieListing movieListing;
    
    public Booking(Viewer viewer)//,MovieListing movieListing){
        this.viewer = viewer;
        //this.movieListing = movieListing;
    }

    public void availableTimings(MovieListing movieListing){
        int[] timings = movieListing.getTimeSlot();
        for(int i=0;i<timings.length;i++){
            System.out.printf("Timeslot %d : %d\n",i+1,timings[i]);
        }
    }

        
    public void availableSeats(MovieListing movieListing){ //add numbering
        Seat[][] seats = movieListing.getSeat();
        for(int row=0;row<10;row++){
            System.out.printf("%c  ",this.letters[row]);
            int column = 0;
            for(int j=0;j<17;j++){
                if(j%9==0){
                    System.out.printf("  ");
                    continue;
                }
                column++;
                if(seats[row][column].isAssigned()){
                    System.out.printf("X ");
                }
                else{
                    System.out.printf("O ");
                }
            }
            System.out.println();
        }
    }

    public void makeBooking(MovieListing movieListing,Cinema cinema){
        //assign a seat from 2D seat array in movieListing
        Seat[][] seats = movieListing.getSeat();
        Scanner sc = new Scanner(System.in);
        String[] selectedSeats;
        int numBooking = 0;
        while(1>0){
            System.out.printf("Book another seat? (yes/no): ");
            String flag = sc.next();
            if(flag=="no"){
                break;
            }
            System.out.printf("Enter Seat:")
            String seatNo = sc.next();
            selectedSeats[numBooking] = new String(seatNo);
            numBooking++;
        }
        for(int i=0;i<numBooking;i++){
            String seatSelect = selectedSeats[i];
            char num = seatSelect.charAt(0);
            int digit = Integer.valueOf(num);
            char alpha = seatSelect.charAt(1);
            int letter;
            for(letter=0;letter<letters.length;letter++){
                if(letters[letter]==alpha){
                    break;
                }
            }
            seats[letter][digit-1].assign(this.viewer.getViewerId());

        }

        public long completePayment(){
            long transactionId; //CCCYYYYMMDDhhmm
            return transactionId;
        }

    }


    
    
    
    
    
    
}
