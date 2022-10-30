import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestApp {
    public static void main(String[] aArgs)  {
        String movieFile = "data/movies.txt" ;
        Scanner sc = new Scanner(System.in);

        try {
            // read file containing Movies records.
            ArrayList movieList = MovieDB.readMovies(movieFile);

            for (int i = 0 ; i < movieList.size() ; i++) {
                int count = 0;
                Movie movie = (Movie) movieList.get(i);
                System.out.println("Movie Title: " + movie.getTitle());
                System.out.println("Showing Status: " + movie.getStatus());
                System.out.println("Synopsis: " + movie.getSynopsis());
                System.out.println("Director: " + movie.getDirector());
                System.out.println("Cast: " + movie.getCast());
                System.out.println("Overall Reviewer Rating: " + movie.getOverallReviewerRating());
                System.out.println("Past Reviews: ");
                for(String element : movie.getPastReviews()){
                    if(element.equalsIgnoreCase("NA")) {
                        System.out.print(element);
                        break;
                    }
                    if(count >= 3) {
                        System.out.println();
                        count = 0;
                    }
                    System.out.print(element + "|");
                    count++;
                }
                System.out.println();
                System.out.println("Number of tickets sold: " + movie.getNoOfTickets());
                System.out.println();
            }

            //ArrayList movieList = new ArrayList<>(); //MovieDBText.readMovies(movieFile);

            ArrayList<String> cast = new ArrayList<>();
            Movie.ShowingStatus status = null;
            String casting;

            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.println("Choose Showing Status: ");
            System.out.println("1. Coming Soon");
            System.out.println("2. Preview");
            System.out.println("3. Now Showing");
            System.out.println("4. End Of Showing");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    status = Movie.ShowingStatus.COMING_SOON;
                    break;
                case 2:
                    status = Movie.ShowingStatus.PREVIEW;
                    break;
                case 3:
                    status = Movie.ShowingStatus.NOW_SHOWING;
                    break;
                case 4:
                    status = Movie.ShowingStatus.END_OF_SHOWING;
                    break;
            }
            sc.nextLine();

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

            ArrayList<String> reviewDescription = new ArrayList<>();

            Movie m1 = new Movie(title, status, synopsis, director, cast, "NA", reviewDescription, 0);

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
