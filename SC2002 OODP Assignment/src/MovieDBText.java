import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.*;

public class MovieDBText {
    public static final String SEPARATOR = "|";

    // an example of reading
    public static ArrayList readMovies(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList movRead = new ArrayList() ;// to store movies data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            Movie.ShowingStatus status;
            ArrayList<String> cast = new ArrayList<>();
            ArrayList<String> pastReviews = new ArrayList<>();

            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

            String title = star.nextToken().trim();                                     // first token
            status = Movie.ShowingStatus.valueOf(star.nextToken().trim());              // second token
            String synopsis = star.nextToken().trim();                                  // third token
            String director = star.nextToken().trim();	                                // fourth token
            cast.add(star.nextToken().trim());                                          // fifth token
            String overallReviewerRating =star.nextToken().trim(); // sixth token
            pastReviews.add(star.nextToken().trim());

            // create movie object from file data
            Movie movie = new Movie(title, status, synopsis, director, cast, overallReviewerRating, pastReviews);
            // add to movie list
            movRead.add(movie) ;
        }
        return movRead ;
    }

    // an example of saving
    public static void saveMovies(String filename, List al) throws IOException {
        List movWrite = new ArrayList() ;// to store movies data

        for (int i = 0 ; i < al.size() ; i++) {
            Movie movie = (Movie)al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(movie.getTitle().trim());
            st.append(SEPARATOR);
            st.append(movie.getStatus());
            st.append(SEPARATOR);
            st.append(movie.getSynopsis().trim());
            st.append(SEPARATOR);
            st.append(movie.getDirector().trim());
            st.append(SEPARATOR);
            String castDisplay = movie.getCast().toString().replace("[", "").replace("]", "").trim();
            //castDisplay = castDisplay.substring(1, castDisplay.length() - 1);
            st.append(castDisplay);
            st.append(SEPARATOR);
            st.append(movie.getOverallReviewerRating()); // to store average ratings in file
            st.append(SEPARATOR);
            String pastReviewsDisplay = movie.getPastReviews().toString().replace("[", "").replace("]", ""); // to remove square brackets in text file
            //pastReviewsDisplay = pastReviewsDisplay.substring(1, pastReviewsDisplay.length() - 1);
            st.append(pastReviewsDisplay);

            movWrite.add(st.toString()) ;
        }
        write(filename,movWrite);
    }

    /** Write fixed content to the given file. */
    public static void write(String fileName, List data) throws IOException  {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        try {
            for (int i =0; i < data.size() ; i++) {
                out.println((String)data.get(i));
            }
        }
        finally {
            out.close();
        }
    }

    /** Read the contents of the given file. */
    public static List read(String fileName) throws IOException {
        List data = new ArrayList() ;
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()){
                data.add(scanner.nextLine());
            }
        }
        finally{
            scanner.close();
        }
        return data;
    }
}
