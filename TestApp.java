import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestApp {
    // use below constants in print to change output colours
    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void main(String[] aArgs)  {
        String movieFile = "data/movies.txt" ;
        Scanner sc = new Scanner(System.in);

        try {
            // read file containing Movies records.
            ArrayList movieList = MovieDB.readMovies(movieFile);

            for (int i = 0 ; i < movieList.size() ; i++) {
                int count = 0;
                Movie movie = (Movie) movieList.get(i);
                System.out.println(BOLD + "Movie Title: " + RESET + PURPLE + movie.getTitle() + RESET); // prints in bold and purple
                System.out.println(BOLD + "Synopsis: " + RESET + PURPLE + movie.getSynopsis() + RESET);
                System.out.println(BOLD + "Director: " + RESET + PURPLE + movie.getDirector() + RESET);
                System.out.println(BOLD + "Cast: " + RESET + PURPLE + movie.getCast() + RESET);
                System.out.println(BOLD + "Genre: " + RESET + PURPLE + movie.getGenre() + RESET);
                System.out.println(BOLD + "Language: " + RESET + PURPLE + movie.getLanguage() + RESET);
                System.out.println(BOLD + "Runtime: " + RESET + PURPLE + movie.getRunTime() + RESET);
                System.out.println(BOLD + "Rating: " + RESET + PURPLE + movie.getMovieRating() + RESET);
                System.out.println(BOLD + "Release: " + RESET + PURPLE + movie.getRelease() + RESET);
                System.out.println(BOLD + "Overall Reviewer Rating: " + RESET + PURPLE + movie.getOverallReviewerRating() + RESET);
                System.out.println(BOLD + "Past Reviews: " + RESET);
                for(String element : movie.getPastReviews()){
                    if(element.equalsIgnoreCase("NA")) {
                        System.out.print(PURPLE + element + RESET);
                        break;
                    }
                    if(count >= 3) {
                        System.out.println();
                        count = 0;
                    }
                    System.out.print(PURPLE + element + RESET + "|");
                    count++;
                }
                System.out.println();
                System.out.println(BOLD + "Number of tickets sold: " + RESET + PURPLE + movie.getNoOfTickets() + RESET);
                System.out.println();
            }

            //ArrayList movieList = new ArrayList<>(); //MovieDBText.readMovies(movieFile);

            ArrayList<String> cast = new ArrayList<>();
            String casting;

            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Synopsis: ");
            String synopsis = sc.nextLine();
            System.out.print("Director: ");
            String director = sc.nextLine();
            System.out.println("Cast (Done to finish): ");
            casting = sc.nextLine();
            while(!casting.equals("Done")){
                cast.add(casting);
                casting = sc.nextLine();
            }

            System.out.print("Genre: ");
            String genre = sc.nextLine();
            System.out.print("Language: ");
            String language = sc.nextLine();
            System.out.print("Runtime: ");
            String runtime = sc.nextLine();
            System.out.print("Rating: ");
            String rating = sc.nextLine();
            System.out.print("Release: ");
            String release = sc.nextLine();

            ArrayList<String> reviewDescription = new ArrayList<>();

            Movie m1 = new Movie(title, synopsis, director, cast, genre, language, runtime,
                    rating, release, "NA", reviewDescription, 0);

            // movieList is an array list containing movie objects
            movieList.add(m1);

            // write movie records to file.
            MovieDB.saveMovies(movieFile, movieList);

        /*// reviewList is an array list containing review objects

            // read file containing Review records.

            ArrayList reviewList  = ReviewDBText.readReviews(reviewFile) ;
            for (int i = 0 ; i < reviewList.size() ; i++) {
                Review review = (Review) reviewList.get(i);
                System.out.println("User's Name: " + review.getReviewer());
                System.out.println("Movie Title: " + review.getMovieTitle());
                System.out.println("User's Movie Rating: " + review.getRating());
                System.out.println("User's Movie Review: " + review.getReviewDescription());
                System.out.println();
            }

            System.out.print("Please enter your name: ");
            String name = sc.nextLine();
            System.out.print("Please enter the movie title: ");
            String movieTitle = sc.nextLine();
            System.out.print("Please input your rating (1-5): ");
            double rating = sc.nextFloat();
            sc.nextLine();
            System.out.println("Please input your review: ");
            String review = sc.nextLine();

            Review r1 = new Review(name, movieTitle, rating, review);

            reviewList.add(r1);

            // write review records to file.
            ReviewDBText.saveReviews(reviewFile, reviewList);*/
        }
        catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }
}
