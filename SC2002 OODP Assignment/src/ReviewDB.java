import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Represents a review database which can be used to write and read from a text file (reviews.txt).
 * @author Karishein Chandran
 * @version 1.0
 * @since 2022-11-05
 */
public class ReviewDB{

    /**
     * A string variable to represent the separator type between different variables in the text file.
     */
    public static final String SEPARATOR = "|";

    /**
     * Creates an array list of review objects after reading all the reviews and ratings from the reviews.txt file.
     * @param filename The file path of the reviews.txt file which contains all the reviews and ratings.
     * @return An array list containing multiple review objects.
     * @throws IOException
     * @see #read(String)
     */
    public static ArrayList readReviews(String filename) throws IOException {
        ArrayList stringArray = (ArrayList)read(filename); // read String from text file
        ArrayList reviewRead = new ArrayList(); // to store reviews data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);

            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

            // extracts each field to its respective variable
            String reviewer = star.nextToken().trim();
            String movieTitle = star.nextToken().trim();
            double rating = Double.parseDouble(star.nextToken().trim());
            String reviewDescription = star.nextToken().trim();

            // create review object from file data
            Review review = new Review(reviewer, movieTitle, rating, reviewDescription);

            // add to review list
            reviewRead.add(review) ;
        }
        return reviewRead;
    }

    /**
     * Converts a new review object into a string and stores it into list.
     * This list is written to the existing reviews.txt file.
     * @param filename The file path of the reviews.txt file which contains all the reviews and ratings.
     * @param al A list containing the new review object.
     * @throws IOException
     * @see #write(String, List)
     */
    public static void saveReviews(String filename, List al) throws IOException {
        List reviewWrite = new ArrayList() ;// to store reviews data

        for (int i = 0 ; i < al.size() ; i++) {
            Review review = (Review) al.get(i);
            StringBuilder st =  new StringBuilder();
            st.append(review.getReviewerName().trim());
            st.append(SEPARATOR);
            st.append(review.getMovieTitle().trim());
            st.append(SEPARATOR);
            st.append(review.getRating());
            st.append(SEPARATOR);
            st.append(review.getReviewDescription().trim());

            reviewWrite.add(st.toString());
        }
        write(filename,reviewWrite);
    }

    /**
     * Writes the review data obtained from saveReviews() into the reviews.txt file.
     * @param fileName The file path of the reviews.txt file which contains all the reviews and ratings.
     * @param data A list containing the review data in string format.
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
     * Reads all the reviews and ratings from the reviews.txt file.
     * @param fileName The file path of the reviews.txt file which contains all the reviews and ratings.
     * @return An array list containing the reviews and ratings in string format.
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