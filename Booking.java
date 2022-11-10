import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";


    public static void availableSeats(TimeSlot timeslot) { //add numbering
        Seat[][] seats = timeslot.getSeats();

        System.out.print("  ");
        int x = 1;
        for (int q = 0; q < 17; q++) {
            if (q == 8) {
                System.out.print("  ");
                continue;
            }

            System.out.printf("%-3d", x);
            x++;
        }
        System.out.println();
        char[] letters = {'*', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};

        for (int row = 0; row < 10; row++) {
            System.out.printf("%c", letters[row]);
            System.out.print(" ");
            int column = 0;
            for (int j = 0; j < 17; j++) {
                if (j == 8) {
                    System.out.print("  ");
                    continue;
                }
                if (seats[row][column].isAssigned()) {
                    if (row == 8 || row == 9) {
                        if (column % 2 != 0) {
                            System.out.printf("%s%-3s%s", RED, "X", RESET);
                        } else {
                            System.out.printf("%s%-3s%s", RED, "X--", RESET);
                        }
                    } else {
                        System.out.printf("%s%-3s%s", RED, "X", RESET);
                    }
                } else {
                    if (row == 8 || row == 9) {

                        if (column % 2 != 0) {
                            System.out.printf("%s%-3s%s", GREEN, "O", RESET);

                        } else {
                            System.out.printf("%s%-3s%s", GREEN, "O--", RESET);
                        }
                    } else {
                        System.out.printf("%s%-3s%s", GREEN, "O", RESET);
                    }
                }
                column++;
            }
            System.out.println();
        }
        System.out.println("Legend:");
        System.out.printf("Seats that can be booked: %sO%s\n", GREEN, RESET);
        System.out.printf("Seats that are occupied: %sX%s\n", RED, RESET);
        System.out.printf("%-15s\n","Elite Seat: *");
        System.out.printf("%-15s\n\n","Couple Seat: O--O");
    }

    public static ArrayList<String> makeBooking(MovieListing listing, TimeSlot timeslot, Viewer viewer, Settings settings) {
        //assign a seat from 2D seat array in movieListing
        Seat[][] seats = timeslot.getSeats();
        char[] letters = {'*', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
        Scanner sc = new Scanner(System.in);
//        String[] selectedSeats = new String[];
        ArrayList<String> selectedSeats = new ArrayList<>();
        ArrayList<Integer> ages = new ArrayList<>();
        boolean firstBooking = true;
        a:while (true) {
            if(firstBooking){
                System.out.println("Would you like to book a seat? (y/n): ");
                firstBooking = false;
            }else{
                System.out.println("Would you like to book another seat? (y/n): ");
            }
            String flag;
            while(!(flag = sc.nextLine()).equals("y")) {
                if (flag.equals("n")) {
                    break a;
                }
                System.out.println("Invalid Entry. Please try again. ");
                System.out.println("Would you like to book another seat? (y/n): ");
            }
            System.out.println("Enter your choice of seat (eg. 1A):");
            String seatNo = sc.nextLine();

            if (seatNo.charAt(seatNo.length()-1) == 'B' || seatNo.charAt(seatNo.length()-1) == 'A') {
                for (int numAges = 0; numAges < 2; numAges++) {
                    System.out.printf("Enter age of person %d in couple: \n", numAges + 1);
                    int age = sc.nextInt();
                    sc.nextLine();
                    ages.add(age);
                }
                selectedSeats.add(seatNo);
                int seatNum = Integer.parseInt(seatNo.substring(0, seatNo.length()-1));
                if(seatNum%2==0){
                    selectedSeats.add(seatNum - 1 + seatNo.substring(seatNo.length()-1));
                }else {
                    selectedSeats.add(seatNum + 1 + seatNo.substring(seatNo.length()-1));
                }
            } else {
                System.out.println("Enter age of the viewer: ");
                int age2 = sc.nextInt();
                sc.nextLine();
                ages.add(age2);
                selectedSeats.add(seatNo);
            }
        }
        double totalPrice = 0.0;
        for (int i = 0; i < selectedSeats.size(); i++) {
            String seatSelect = selectedSeats.get(i);
            int num = Integer.parseInt(seatSelect.substring(0, seatSelect.length() - 1));
            char alpha = seatSelect.charAt(seatSelect.length() - 1);
            int letter;
            for (letter = 0; letter < letters.length; letter++) {
                if (letters[letter] == alpha) {
                    break;
                }
            }
            int priceAdded;
            if (letter == 0) { //elite
                seats[letter][num - 1].assign(viewer.getViewerID());
                int movieType = listing.getMovieType();
                int myAge = ages.get(i);
                myAge = getAgeType(myAge);
                int day = timeslot.getDayType();

                priceAdded = settings.calculateTicketPrice(movieType, 1, myAge, day);
            }
              else {
                seats[letter][num - 1].assign(viewer.getViewerID());
                int movieType = listing.getMovieType();
                int myAge = ages.get(i);
                myAge = getAgeType(myAge);
                int day = timeslot.getDayType();

                priceAdded = settings.calculateTicketPrice(movieType, 0, myAge, day);
            }
            totalPrice += priceAdded;
            timeslot.setSeats(seats);
            //set value of seats

        }
        System.out.printf("The amount for your tickets is: %s%.2f SGD%s, Inclusive of GST.\n", RED, totalPrice, RESET);

        return selectedSeats;
    }

    public static String completePayment(Cinema cinema) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();
        String currTime = dtf.format(now);
        String cinemaCode = cinema.getCinemaId();
        return cinemaCode + currTime;

    }

    public static int getAgeType(int myAge) {
        if (myAge < 12) {
            myAge = 1;
        } else if (myAge > 60) {
            myAge = 2;
        } else {
            myAge = 0;
        }
        return myAge;
    }

}
