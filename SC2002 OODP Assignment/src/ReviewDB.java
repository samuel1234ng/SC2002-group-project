import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReviewDB{
    public static final String SEPARATOR = "|";

    // an example of reading
    public static ArrayList readReviews(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList)read(filename);
        ArrayList reviewRead = new ArrayList() ;// to store reviews data

        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);

            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

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

    // an example of saving
    public static void saveReviews(String filename, List al) throws IOException {
        List reviewWrite = new ArrayList() ;// to store reviews data

        for (int i = 0 ; i < al.size() ; i++) {
            Review review = (Review) al.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(review.getReviewerName().trim());
            st.append(SEPARATOR);
            st.append(review.getMovieTitle().trim());
            st.append(SEPARATOR);
            st.append(review.getRating());
            st.append(SEPARATOR);
            st.append(review.getReviewDescription().trim());

            reviewWrite.add(st.toString()) ;
        }
        write(filename,reviewWrite);
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
