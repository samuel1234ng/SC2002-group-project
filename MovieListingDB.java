import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MovieListingDB {
    public static final String SEPARATOR1 = "|";
    public static final String SEPARATOR2 = ";";
    public static final String SEPARATOR3 = "-";


    /**
     * Reads data from a file and returns its contents in an ArrayList
     * @param fileName
     * the path of the file(from the content root)
     * @return
     * an ArrayList of strings containing lines of the read file
     */
    public static ArrayList<String> readFile(String fileName) {
        File f = new File(fileName);
        ArrayList<String> file_data = new ArrayList<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                file_data.add(scanner.nextLine());
            }
        }catch (Exception e){
            System.out.println("Exception >> " + e.getMessage());
        }
        file_data.remove(file_data.size()-1);
        return file_data;
    }

    public static ArrayList<MovieListing> getCinemaListing(int cineplexNumber, int cinemaNumber, ArrayList<String> data, ArrayList<Movie> allMovies){
        ArrayList<MovieListing> movieListings = new ArrayList<>();
        String string_listing = "";

        // deciding which line of the code the
        switch (cineplexNumber){
            case 1->{
                string_listing = data.get(cinemaNumber);
            }
            case 2->{
                string_listing = data.get(Integer.parseInt(data.get(0))+ 1 + cinemaNumber);
            }
            case 3 ->{
                int num1 = Integer.parseInt(data.get(0));
                int num2 = Integer.parseInt(data.get(num1+1));
                string_listing = data.get(num1+num2+2+cinemaNumber);
            }
        }
        StringTokenizer st = new StringTokenizer(string_listing, SEPARATOR1);

        // separates movieListings
        while(st.hasMoreTokens()){
            MovieListing ml = null;
            ArrayList<TimeSlot> timeSlots = new ArrayList<>();
            String movie = st.nextToken();
            StringTokenizer st2 = new StringTokenizer(movie, SEPARATOR2);
            String name = st2.nextToken().trim();
            Movie m = SearchMovie.movieByName(name, allMovies);
            ml = new MovieListing(m, "3D" , m.getStatus().name());

            // separates show-times
            while(st2.hasMoreTokens()){
                String showtime = st2.nextToken();
                StringTokenizer st3 = new StringTokenizer(showtime, SEPARATOR3);
                String date = st3.nextToken().trim();
                String time = st3.nextToken().trim();
                TimeSlot timeslot = new TimeSlot(Integer.parseInt(time), date);

                // separates seat rows
                int i = 0;
                while(st3.hasMoreTokens()){
                    String row = st3.nextToken().trim();
                    for(int j = 0; j < 16; j++){
                        char val = row.charAt(j);
                        timeslot.setSeat(i, j, val == 'x');
                    }
                    i++;
                }
                timeSlots.add(timeslot);
                ml.setTimeSlots(timeSlots);
            }
            movieListings.add(ml);
        }
        return movieListings;
    }
}
