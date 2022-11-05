import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.*;

/**
 * Represents a movie database which can be used to write and read from a text file (movies.txt).
 * @author Karishein Chandran
 * @version 1.0
 * @since 2022-11-05
 */
public class MovieDB {

    /**
     * A string variable to represent the separator type between different variables in the text file.
     */
    public static final String SEPARATOR = "|";

    /**
     * Creates an array list of movie objects after reading all the movies from the movies.txt file.
     * @param filename The file path of the movies.txt file which contains all the movies.
     * @return An array list containing multiple movie objects.
     * @throws IOException
     * @see #read(String)
     */
    public static ArrayList readMovies(String filename) throws IOException {
        ArrayList stringArray = (ArrayList)read(filename); // read String from text file
        ArrayList movRead = new ArrayList(); // to store movie objects

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            Movie.ShowingStatus status;
            ArrayList<String> cast = new ArrayList<>();
            ArrayList<String> pastReviews = new ArrayList<>();

            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

            // extracts each field to its respective variable
            String title = star.nextToken().trim();
            status = Movie.ShowingStatus.valueOf(star.nextToken().trim());
            String synopsis = star.nextToken().trim();
            String director = star.nextToken().trim();
            cast.add(star.nextToken().trim());
            String genre = star.nextToken().trim();
            String language = star.nextToken().trim();
            String runtime = star.nextToken().trim();
            String movieRating = star.nextToken().trim();
            String release = star.nextToken().trim();
            String overallReviewerRating = star.nextToken().trim();
            pastReviews.add(star.nextToken().trim());
            int tickets = Integer.parseInt(star.nextToken().trim());

            // create movie object from file data
            Movie movie = new Movie(title, status, synopsis, director, cast, genre, language, runtime,
                    movieRating, release, overallReviewerRating, pastReviews, tickets);

            // add to movie list
            movRead.add(movie) ;
        }
        return movRead ;
    }

    /**
     * Converts a new movie object into a string and stores it into list.
     * This list is written to the existing movies.txt file.
     * @param filename The file path of the movies.txt file which contains all the movies.
     * @param al A list containing the new movie object.
     * @throws IOException
     * @see #write(String, List)
     */
    public static void saveMovies(String filename, List al) throws IOException {
        List movWrite = new ArrayList() ;// to store movies data

        for (int i = 0 ; i < al.size() ; i++) {
            Movie movie = (Movie)al.get(i);
            StringBuilder st =  new StringBuilder();
            st.append(movie.getTitle().trim());
            st.append(SEPARATOR);
            st.append(movie.getStatus());
            st.append(SEPARATOR);
            st.append(movie.getSynopsis().trim());
            st.append(SEPARATOR);
            st.append(movie.getDirector().trim());
            st.append(SEPARATOR);
            String castDisplay = movie.getCast().toString().replace("[", "").replace("]", "").trim();
            st.append(castDisplay);
            st.append(SEPARATOR);
            st.append(movie.getGenre().trim());
            st.append(SEPARATOR);
            st.append(movie.getLanguage().trim());
            st.append(SEPARATOR);
            st.append(movie.getRunTime().trim());
            st.append(SEPARATOR);
            st.append(movie.getMovieRating().trim());
            st.append(SEPARATOR);
            st.append(movie.getRelease().trim());
            st.append(SEPARATOR);
            st.append(movie.getOverallReviewerRating());
            st.append(SEPARATOR);
            String pastReviewsDisplay = movie.getPastReviews().toString().replace("[", "").replace("]", ""); // to remove square brackets in text file
            st.append(pastReviewsDisplay);
            st.append(SEPARATOR);
            st.append(movie.getNoOfTickets());

            movWrite.add(st.toString());
        }
        write(filename,movWrite);
    }

    /**
     * Writes the movie data obtained from saveMovies() into the movies.txt file.
     * @param fileName The file path of the movies.txt file which contains all the movies.
     * @param data A list containing the movie data in string format.
     * @throws IOException
     * @see PrintWriter
     * @see FileWriter
     */
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

    /**
     * Reads all the movies from the movies.txt file.
     * @param fileName The file path of the movies.txt file which contains all the movies.
     * @return An array list containing the movies in string format.
     * @throws IOException
     * @see Scanner
     * @see FileInputStream
     */
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