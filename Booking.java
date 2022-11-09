import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class Booking {

    public static void availableTimings(MovieListing movieListing){
        ArrayList<TimeSlot> timings = movieListing.getTimeSlots();
        System.out.println("Available timings:");
        for(int i=0;i<timings.size();i++){
            System.out.printf("Timeslot %d : %d\n",i+1,timings.get(i).getTiming());
        }
    }

        
    public static void availableSeats(TimeSlot timeslot){ //add numbering
        Seat[][] seats = timeslot.getSeats();

        System.out.printf("  ");
		int x = 1;
		for(int q=0;q<17;q++){
		    if(q==8){
                System.out.printf("  ");
                continue;
            }
                
            System.out.printf("%-3d",x);
            x++;
		}
		System.out.println();
		char[] letters = {'*','I','H','G','F','E','D','C','B','A'};
        
        for(int row=0;row<10;row++){
            System.out.printf("%c",letters[row]);
            System.out.printf(" ");
            int column = 0;
            for(int j=0;j<17;j++){
                if(j==8){
                    System.out.printf("  ");
                    continue;
                }
                
                if(seats[row][column].isAssigned()){
                    if(row==8 || row==9){
                    
                        if(column%2!=0){
                            System.out.printf("%-3s","X");
                            
                        }
                        else{
                            System.out.printf("%-3s","X--");
                        }
                    }
                    else{
                        
                        System.out.printf("%-3s","X");
                    }
                }
                else{
                    if(row==8 || row==9){
                    
                        if(column%2!=0){
                            System.out.printf("%-3s","O");
                            
                        }
                        else{
                            System.out.printf("%-3s","O--");
                        }
                    }
                    else{
                        
                        System.out.printf("%-3s","O");
                    }
                }
                column++;
            }
            System.out.println();
        }
    }

    public static void makeBooking(MovieListing listing, TimeSlot timeslot, Viewer viewer, ConfigureSystemSettings settings){
        //assign a seat from 2D seat array in movieListing
        Seat[][] seats = timeslot.getSeats();

        
        char[] letters = {'*','I','H','G','F','E','D','C','B','A'};
        Scanner sc = new Scanner(System.in);
//        String[] selectedSeats = new String[];
        ArrayList<String> selectedSeats = new ArrayList<>();
        ArrayList<Integer> ages = new ArrayList<>();
        int numBooking = 0;
        while(true){
            System.out.println("Book a seat? (yes/no): ");
            String flag = sc.next();
            if(flag.equals("no")){
                break;
            }
            

            System.out.println("Select seat:");
            
            
            String seatNo = sc.next();
            int ageIndex = 0;
            
            if(seatNo.charAt(1)=='B' || seatNo.charAt(1)=='A'){
                for(int numAges=0;numAges<2;numAges++){
                    System.out.printf("Enter age of person %d in couple:",numAges+1);
                    int age = sc.nextInt();
                    ages.add(ageIndex,age);
                    ageIndex++;
                }
            }
            else{
                System.out.printf("Enter age:");
                int age2 = sc.nextInt();
                ages.add(ageIndex,age2);
                ageIndex++;
            }
            
            selectedSeats.add(numBooking, seatNo);
            numBooking++;
        }
        double totalPrice = 0.0;
        int p = 0;
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
            int priceAdded;
            if(letter==0){ //elite
                seats[letter][(int) num-1].assign(viewer.getViewerID());
                int movieType = listing.getMovieType();
                int myAge = ages.get(p++);
                myAge = getAgeType(myAge);
                int day = timeslot.getDayType();
                
                priceAdded = settings.calculateticketprice(movieType,1,myAge,day);

            }
            else if(letter==8 || letter==9){ //couple
                seats[letter][(int) num-1].assign(viewer.getViewerID());
                if(num%2!=0){
                    seats[letter][(int) num].assign(viewer.getViewerID());
                }
                else{
                    seats[letter][(int) num-1].assign(viewer.getViewerID());
                }
                int movieType = listing.getMovieType();
                int myAge1 = ages.get(p++);
                myAge1 = getAgeType(myAge1);
                int myAge2 = ages.get(p++);
                myAge2 = getAgeType(myAge2);
                int day = timeslot.getDayType();

                priceAdded = settings.calculateticketprice(movieType,0,myAge1,day);
                priceAdded+= settings.calculateticketprice(movieType,0,myAge2,day);
            }
            else{
                seats[letter][(int) num -1].assign(viewer.getViewerID());
                int movieType = listing.getMovieType();
                int myAge = ages.get(p++);
                myAge = getAgeType(myAge);
                int day = timeslot.getDayType();
                
                priceAdded = settings.calculateticketprice(movieType,0,myAge,day);
            }
            totalPrice+=priceAdded;
            timeslot.setSeats(seats);
            //set value of seats

        }
        System.out.printf("The amount for your tickets is: %.2f",totalPrice);
        

    }

    public static String completePayment(Cinema cinema){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  
        LocalDateTime now = LocalDateTime.now();  
        String currTime = dtf.format(now);  
        String cinemaCode = cinema.getCinemaId();
        return cinemaCode + currTime;
    }
    
    public static int getAgeType(int myAge){
        if(myAge<12){
            myAge = 1;
        }
        else if(myAge>60){
            myAge=2;
        }
        else{
            myAge=0;
        }
        return myAge;
    }
    
}
