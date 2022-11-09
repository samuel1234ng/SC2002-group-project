import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class Booking {

    public static void availableTimings(MovieListing movieListing){
        ArrayList<TimeSlot> timings = movieListing.getTimeSlots();
        System.out.println("Available timings:");
        for(int i=0;i<timings.size();i++){
            System.out.printf("Timeslot %d : %d\n",i+1,timings.get(i).getTime());
        }
    }

        
    public static void availableSeats(TimeSlot timeslot){ //add numbering
        Seat[][] seats = timeslot.getSeats();
        char[] letters = {'I','H','G','F','E','D','C','B','A'};
        for(int row=0;row<10;row++){
            System.out.printf("%c  ",letters[row]);
            int column = 0;
            for(int j=0;j<17;j++){
                if(j%9==0){
                    System.out.print("  ");
                    continue;
                }
                
                if(seats[row][column].isAssigned()){
                    System.out.print("X ");
                }
                else{
                    System.out.print("O ");
                }
                column++;
            }
            System.out.println();
        }
    }

    public static void makeBooking(TimeSlot timeslot, Viewer viewer){
        //assign a seat from 2D seat array in movieListing
        Seat[][] seats = timeslot.getSeats();
        char[] letters = {'I','H','G','F','E','D','C','B','A'};
        Scanner sc = new Scanner(System.in);
//        String[] selectedSeats = new String[];
        ArrayList<String> selectedSeats = new ArrayList<>();
        int numBooking = 0;
        while(true){
            System.out.println("Book a seat? (yes/no): ");
            String flag = sc.next();
            if(flag.equals("no")){
                break;
            }
            System.out.println("Enter Seat:");
            String seatNo = sc.next();
            selectedSeats.add(numBooking, seatNo);
            numBooking++;
        }
        for(int i=0;i<numBooking;i++){
            String seatSelect = selectedSeats.get(i);
            char num = seatSelect.charAt(0);
            char alpha = seatSelect.charAt(1);
            int letter;
            for(letter=0;letter<letters.length;letter++){
                if(letters[letter]==alpha){
                    break;
                }
            }
            seats[letter][(int) num -1].assign(viewer.getViewerID());
            timeslot.setSeats(seats);
            //set value of seats

        }

    }

    public static String completePayment(Cinema cinema){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  
        LocalDateTime now = LocalDateTime.now();  
        String currTime = dtf.format(now);  
        String cinemaCode = cinema.getCinemaId();
        return cinemaCode + currTime;
    }
    

    
}
