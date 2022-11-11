import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MOBLIMAMain {
    // Text color changing constants
    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    public static void guestUser(Scanner sc, Cineplex[] cineplexes) {
        System.out.printf("""
                Welcome to the booking system!
                Please follow the instructions below to create a new %sViewer%s account.
                
                """, GREEN, RESET);
        System.out.println("Please enter your full Name");
        String name = sc.nextLine();
        System.out.println("Please enter your email Id");
        String email = sc.nextLine();
        System.out.println("Please enter your mobile Number");
        String mobile = sc.nextLine();
        switch(login(email, mobile)){
            case 1-> adminUser(sc, cineplexes);
            case 2-> {
                System.out.println("""
                An account with these details already exists! Log in successful.
                We will now proceed to direct you to the Viewer Menu.
                
                """);
                viewerUser(sc, new Viewer(email, name, mobile), cineplexes);
            }
            case 3->{
                Viewer v = new Viewer(email, name, mobile);
                System.out.println("""
                Account successfully created!
                We will now proceed to direct you to the Viewer Menu.
                
                """);
                ViewerDB.createViewerInFile(v);
                viewerUser(sc, v, cineplexes);
            }
        }
    }

    public static void viewerUser(Scanner sc, Viewer v, Cineplex[] cineplexes) {
        /*
        Movie-goer module
        1. Search/List movie
        2. View movie details – including reviews and ratings
        3. Check seat availability and selection of seat/s.
        4. Book and purchase ticket
        5. View booking history
        6. List the Top 5 ranking by ticket sales OR by overall reviewers’ ratings
        */
        Settings set = new Settings();
        set.loadSettings();
        // ADD CHOOSE CINEPLEX CODE

        System.out.printf(
                """
                Hello %s! Welcome to the Movie booking system
                
                The movies available in each cineplex are as follows:
                
                """, v.getFullName());

        for (Cineplex cineplex: cineplexes) {
            System.out.printf("Cineplex %s:\n", cineplex.getCineplexName());
            for (Movie movie: cineplex.movieByCineplex()) {
                System.out.print(movie.getTitle() + " | ");
            }
            System.out.println("\n");
        }
        System.out.printf(
               """ 
               Please select a Cineplex to proceed:
               %s(1) %s
               (2) %s
               (3) %s%s
               """,
                BOLD,
                cineplexes[0].getCineplexName(),
                cineplexes[1].getCineplexName(),
                cineplexes[2].getCineplexName(),
                RESET);

        int cineplexChoice = sc.nextInt();
        sc.nextLine();
        Cineplex cineplex = cineplexes[cineplexChoice-1];

        System.out.printf(""" 
                Welcome to the booking system for Cineplex %s!
                What would you like to do? Enter your choice:
                (1) View a list of all the movies
                (2) Search for a Movie and view its details
                (3) Check seat availability for show-times
                (4) Select  and purchase seats and make a Booking
                (5) View your booking history
                (6) Review a movie
                (7) View the Top 5 ranked movies by
                    (i) Ticket sales
                    (ii) Overall reviewers’ ratings
                (8) Logout
                """, cineplex.getCineplexName());

        int choice = sc.nextInt();
        sc.nextLine();
        while (choice <= 8 && choice >= 0) {
            switch (choice) {
                case 1 -> //View a list of all the movies
                        cineplex.listMovies();
                case 2 -> {
                    //Search for a Movie
                    // find the movie and print details
                    System.out.println("Please enter the name of a movie to view its details");
                    SearchMovie.printMovieDetails(SearchMovie.movieByName(sc.nextLine(), MovieDB.readMovies("data/movies.txt")));
                }
                case 3 -> {
                    //Check seat availability for show-times
                    System.out.println("Movies: ");
                    for(Movie m: cineplex.movieByCineplex()){
                        System.out.println(m.getTitle());
                    }
                    System.out.println("Enter a movie's name : ");
                    String selection = sc.nextLine();
                    //Add search cinema by movie function to Cineplex class
                    ArrayList<Cinema> movieCinemas = cineplex.cinemasByMovie(selection);
                    HashMap<Integer, Cinema> timeCinema = new HashMap<>();
                    ArrayList<Integer> allTimes = new ArrayList<>();
                    System.out.println("Choose a timeslot:");
                    int i = 1;
                    for(Cinema cinema: movieCinemas){
                        MovieListing listing = cinema.getListing(selection);
                        ArrayList<TimeSlot> times = listing.getTimeSlots();
                        for(TimeSlot time: times){
                            String date = time.getDate();
                            int intTime = time.getTime();
                            String timeS = String.valueOf(intTime);
                            String output = date + ", " + timeS;
                            System.out.printf("(%d) %s\n", i++, output);
                            timeCinema.put(intTime, cinema);
                            allTimes.add(intTime);
                        }
                    }
                    System.out.println("Select timeslot : ");
                    int selectedTime = sc.nextInt();
                    sc.nextLine();
                    int timeChosen = allTimes.get(selectedTime-1);
                    MovieListing listing = timeCinema.get(timeChosen).getListing(selection);
                    TimeSlot chosenTiming = listing.getTime(timeChosen);
                    //Show seats
                    Booking.availableSeats(chosenTiming);
                }
                case 4 -> {
                    //Check seat availability for show-times
                    System.out.println("Movies: ");
                    for(Movie m: cineplex.movieByCineplex()){
                        System.out.println(m.getTitle());
                    }
                    System.out.println("Enter a movie's name : ");
                    String selection = sc.nextLine();
                    //Add search cinema by movie function to Cineplex class
                    ArrayList<Cinema> movieCinemas = cineplex.cinemasByMovie(selection);
                    HashMap<Integer, Cinema> timeCinema = new HashMap<>();
                    ArrayList<Integer> allTimes = new ArrayList<>();
                    System.out.println("Choose a timeslot:");
                    int i = 1;
                    for(Cinema cinema: movieCinemas){
                        MovieListing listing = cinema.getListing(selection);
                        ArrayList<TimeSlot> times = listing.getTimeSlots();
                        for(TimeSlot time: times){
                            String date = time.getDate();
                            int intTime = time.getTime();
                            String timeS = String.valueOf(intTime);
                            String output = date + ", " + timeS;
                            System.out.printf("(%d) %s\n", i++, output);
                            timeCinema.put(intTime, cinema);
                            allTimes.add(intTime);
                        }
                    }
                    System.out.println("Select timeslot : ");
                    int selectedTime = sc.nextInt();
                    sc.nextLine();
                    int timeChosen = allTimes.get(selectedTime-1);
                    Cinema cinema =  timeCinema.get(timeChosen);
                    MovieListing listing = cinema.getListing(selection);
                    TimeSlot chosenTiming = listing.getTime(timeChosen);
                    //Show seats
                    Booking.availableSeats(chosenTiming);
                    ArrayList<String> selectedSeats = Booking.makeBooking(listing, chosenTiming, v,set);
                    //add calculating ticket price
                    System.out.println();
                    System.out.println("Waiting for payment");
                    for(int i1 = 0; i1<3; i1++){
                        for(int j = 0; j < 3; j++) {
                            System.out.print("•");
                            try {
                                TimeUnit.MILLISECONDS.sleep(500);
                            } catch (InterruptedException e) {
                                System.out.println("Exception << " + e);
                            }
                        }
                        System.out.println();
                    }
                    System.out.println("Payment completed!\n\n");
                    String t_id = Booking.completePayment(cinema);

                    for(String seat: selectedSeats){
                        v.createNewBookingInstance(t_id, seat, selection);
                    }
                }
                case 5 -> {
                    //View your booking history
                    ArrayList<String> booking_history = v.getBookingHistory();
                    for (String booking : booking_history) {
                        if (booking.equals("NA")) {
                            System.out.println("You have no previous bookings");
                            break;
                        }
                        StringTokenizer star = new StringTokenizer(booking, "-");
                        String transaction_id = star.nextToken().trim();
                        String seat = star.nextToken().trim();
                        String movie_name = star.nextToken().trim();
                        String date = star.nextToken().trim();
                        System.out.printf("""
                                %sBooking for movie%s: %s
                                %sSeat%s: %s
                                %sBooking for date%s: %s
                                %sTransaction Id%s: %s
                                	
                                	""", BOLD, RESET, movie_name,BOLD, RESET, seat, BOLD, RESET, date, BOLD, RESET, transaction_id);
                    }
                }
                case 6 -> {
                    try {
                        String reviewFile = "data/reviews.txt";
                        ArrayList<Review> reviewList = ReviewDB.readReviews(reviewFile);
                        System.out.print("Please enter the Movie which you want to review: ");
                        String movieTitle = sc.nextLine();
                        System.out.print("Please input your rating (1-5): ");
                        double rating = sc.nextFloat();
                        sc.nextLine();
                        System.out.println("Please input your review: ");
                        String review = sc.nextLine();

                        Review newReview = new Review(v.getFullName(), movieTitle, rating, review); // create new review object

                        reviewList.add(newReview);

                        // write review records to file.
                        ReviewDB.saveReviews(reviewFile, reviewList);
                    } catch (Exception e) {
                        System.out.println("Exception << " + e);
                    }
                }

                case 7 -> {
                    /* View the Top 5 ranked movies by
                          (i) Ticket sales
                         (ii) Overall reviewers’ ratings */
                    ArrayList<Movie> movies = MovieDB.readMovies("data/movies.txt");
                    System.out.println("View top 5 movies based on:");
                    System.out.println("1. Ticket sales");
                    System.out.println("2. Ratings");
                    int ans = sc.nextInt();
                    sc.nextLine();
                    if (ans == 1) {
                        String ticket = "🎟";
                        ArrayList<Movie> sortedMovies = SearchMovie.movieByTickets(movies);
                        for (int i = 0; i < 5; i++) {
                            System.out.printf("(%d) %s - %s%s%s %s tickets sold\n", i + 1, sortedMovies.get(i).getTitle(), RED, sortedMovies.get(i).getNoOfTickets(), RESET, ticket);
                        }
                        System.out.println();
                    } else {
                        ArrayList<Movie> sortedMovies = SearchMovie.movieByRating(movies);
                        String star = "✰";
                        for (int i = 0; i < 5; i++) {
                            System.out.printf("(%d) %s (Rated ", i + 1, sortedMovies.get(i).getTitle());
                            for(int j = 0; j< Math.round(Float.parseFloat(sortedMovies.get(i).getOverallReviewerRating())); j++){
                                   System.out.print(BOLD+ GREEN_BRIGHT+star+RESET);
                            }
                            System.out.printf(" - %s Stars)\n", sortedMovies.get(i).getOverallReviewerRating());
                        }
                        System.out.println();
                    }
                }
                case 8 -> {
                    return;
                    // logout
                }
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("Exception << " + e);
            }

            System.out.printf(""" 
                Welcome back %s%s%s! What would you like to do? Enter your choice:
                What would you like to do? Enter your choice:
                (1) View a list of all the movies
                (2) Search for a Movie and view its details
                (3) Check seat availability for show-times
                (4) Select  and purchase seats and make a Booking
                (5) View your booking history
                (6) Review a movie
                (7) View the Top 5 ranked movies by
                    (i) Ticket sales
                    (ii) Overall reviewers’ ratings
                (8) Logout
                    """, GREEN, v.getFullName(), RESET);
            choice = sc.nextInt();
            sc.nextLine();
        }

    }


    /**
     * Checks if a user is an admin, current viewer or a guest viewer
     *
     * @param userName username of the admin/emailID of the viewer
     * @param password password of the admin/mobile number of the viewer
     * @return 1 if the user is an admin, 2 if the user is a current viewer, else 3.
     */
    public static int login(String userName, String password) {
        // admin login
        String adminFile = "data/admin.txt";
        HashMap<String, String> adata = AdminDB.getAdminData(adminFile);
        if (password.equals(adata.get(userName))) {
            return 1;
        }

        // user login
        String viewerFile = "data/viewer.txt";
        HashMap<String, String> vdata = ViewerDB.getViewerData(viewerFile);
        if (password.equals(vdata.get(userName))) {
            return 2;
        }

        // guest user
        return 3;
    }

    public static void adminUser(Scanner sc, Cineplex[] cineplexes) {
        /*
        b. Cinema staff can configure the system settings (e.g., tic ket prices, holidays,etc.)   
        c. Cinema staff can enter the forthcoming movies, its type (Blockbuster/3D,etc.), movie rating (e.g. PG), show times, the cinema, showing status(Coming Soon, Preview, Now Showing, End Of Showing), etc.
        d. Cinema staff can also update the details of the movies or remove the movie by changing the status to ‘End of Showing’.
        e. Cinema staff can list the current top 5 ranking movies by:
        i. Ticket sales (display the movie title and total sales)
        ii. Overall reviewers’ rating (display the movie title and overall rating)
        */


        Settings set = new Settings();
        set.loadSettings();
        int choice, choice2, choice3, choice4, choice5;
        System.out.println("Welcome, Admin!");
        do {
            System.out.print("""
                    What would you like to do?
                    (1) Configure the System's Settings
                    (2) Add an upcoming movie to the Booking System
                    (3) Edit a movie
                    (4) Display the top 5 movies by Ticket Sales
                    (5) Display the top 5 movies by Reviews
                    (6) Logout
                    """);
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    // Configure the System's Settings
                    do {
                        System.out.println("""
                                What would you like to do?
                                (1) Print settings
                                (2) Change base price of tickets
                                (3) Change ticket prices
                                (4) Add a holiday
                                (5) Remove a holiday
                                (6) Reset Changes
                                (7) Save and return
                                """);
                        choice2 = sc.nextInt();
                        sc.nextLine();
                        while (choice2 > 7 || choice2 < 1) {
                            System.out.println("Invalid option");
                            choice2 = sc.nextInt();
                            sc.nextLine();
                        }
                        switch (choice2) {
                            case 1 -> set.printSettings();
                            case 2 -> {
                                System.out.println("New base price");
                                choice3 = sc.nextInt();
                                sc.nextLine();

                                set.changeBasePrice(choice3);
                            }
                            case 3 -> {
                                System.out.println("""
                                        What would you like to change?
                                        (1) Cost of type
                                        (2) Cost of class
                                        (3) Cost of age
                                        (4) Cost of day
                                        """);
                                choice3 = sc.nextInt();
                                sc.nextLine();

                                while (choice3 > 4 || choice3 < 1) {
                                    System.out.println("Invalid option");
                                    choice3 = sc.nextInt();
                                    sc.nextLine();

                                }
                                switch (choice3) {
                                    case 1 -> System.out.println("""
                                            What would you like to change
                                            (1) Cost of normal movie
                                            (2) Cost of 3D movie
                                            (3) Cost of blockbuster movie
                                            """);
                                    case 2 -> System.out.println("""
                                            What would you like to change
                                            (1) Cost of normal class theater
                                            (2) Cost of elite class theater
                                            (3) Cost of budget class theater
                                            """);
                                    case 3 -> System.out.println("""
                                            What would you like to change
                                            (1) Cost of adult
                                            (2) Cost of child
                                            (3) Cost of senior citizen
                                            """);
                                    case 4 -> System.out.println("""
                                            What would you like to change
                                            (1) Cost of weekday
                                            (2) Cost of weekend
                                            (3) Cost of holiday
                                            """);
                                }
                                choice4 = sc.nextInt();
                                sc.nextLine();

                                System.out.println("New price:");
                                choice5 = sc.nextInt();
                                sc.nextLine();

                                set.changeTicketPrice(choice3, choice4, choice5);
                            }
                            case 4 -> {
                                System.out.println("Enter date:");
                                int date = sc.nextInt();
                                System.out.println("Enter month(1-12):");
                                int month = sc.nextInt();
                                System.out.println("Enter year:");
                                int year = sc.nextInt();
                                
                                sc.nextLine();
                                set.addHoliday(date,month,year);
                            }
                            case 5 -> {
                                System.out.println("Enter date:");
                                int date = sc.nextInt();
                                System.out.println("Enter month(1-12):");
                                int month = sc.nextInt();
                                System.out.println("Enter year:");
                                int year = sc.nextInt();
                                
                                sc.nextLine();
                                set.removeHoliday(date,month,year);
                            }
                            case 6 -> set.loadSettings();
                            case 7 -> set.storeSettings();
                        }
                    } while (choice2 < 7);
                }
                 case 2 -> {
                    System.out.printf("""
                            Select a Cineplex
                            (1) %s
                            (2) %s
                            (3) %s
                                                                
                            """, cineplexes[0].getCineplexName(), cineplexes[1].getCineplexName(), cineplexes[2].getCineplexName());
                    int optionCineplex = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Select a Cinema to add a movie to: ");
                    for (int i = 0; i < cineplexes[optionCineplex - 1].getCinemas().length; i++) {
                        System.out.printf("(%d) Cinema %d\n", i, i);
                    }
                    int optionCinema = sc.nextInt();
                    sc.nextLine();
                    ArrayList<Movie> movieList = MovieDB.readMovies("data/movies.txt");

                    boolean correct_movie = false;
                    boolean found = false;
                    while (!correct_movie) {
                        System.out.println("Enter the name of the movie you would like to add to the Cinema: ");
                        String nameMovie = sc.nextLine();
                        for (Movie movie : movieList) {
                            if (nameMovie.equals(movie.getTitle())) {
                                found = true;
                                if ((movie.getStatus() == Movie.ShowingStatus.NOW_SHOWING) || (movie.getStatus() == Movie.ShowingStatus.PREVIEW)) {
                                    correct_movie = true;
                                } else {
                                    break;
                                }

                                System.out.println(
                                        """
                                                Enter your choice for the type of the movie:
                                                (1) IMAX_2D
                                                (2) IMAX_3D
                                                (3) BLOCKBUSTER
                                                                                        
                                                """);
                                String movieType;
                                int movieTypeChoice = sc.nextInt();
                                sc.nextLine();
                                if (movieTypeChoice == 1) {
                                    movieType = "IMAX_2D";
                                } else if (movieTypeChoice == 2) {
                                    movieType = "IMAX_3D";
                                } else {
                                    movieType = "BLOCKBUSTER";
                                }

                                MovieListing newListing = new MovieListing(movie, movieType, movie.getStatus().toString());
                                System.out.println("Enter the numbers shows you would like to add to the movie listing : ");
                                int numberOfShows = sc.nextInt();
                                sc.nextLine();

                                for (int j = 0; j < numberOfShows; j++) {
                                    System.out.printf("Enter a date for showtime #%d in the format DD/MM/YYYY: \n", j);
                                    String date = sc.nextLine();
                                    System.out.printf("Enter the time for showtime #%d of new movie: \n", j);
                                    int time = sc.nextInt();
                                    sc.nextLine();

                                    newListing.addShowtime(time, date);
                                }
                                // add movielisting to movieListings
                                Cinema cinema = cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1];
                                cinema.getMovieListings().add(newListing);

                                // update file
                                String strListing = MovieListingDB.movieListingToString(cinema.getMovieListings());
                                MovieListingDB.changeLine(MovieListingDB.readFile(), strListing, optionCineplex, optionCinema);
                                break;
                            }
                        }
                        if (found) {
                            if (!correct_movie) {
                                System.out.println("You can only add movies which are NOW_SHOWING or in PREVIEW.\n Please try again.");
                            }
                        } else {
                            System.out.printf("No movie with name %s was found.\n Please try again.\n", nameMovie);
                        }
                    }
                }
                 case 3 -> {
                    // Edit a movie
                    System.out.println("The Movies registered in the Booking System are: ");
                    for (Movie m : MovieDB.readMovies("data/movies.txt")) {
                        System.out.printf("%s - %s\n", m.getTitle(), m.getStatus().toString());
                    }
                    ArrayList<Movie> movies = MovieDB.readMovies("data/movies.txt");

                    boolean found = false;
                    while (!found) {
                        System.out.println("Enter the name of the movie you would like to add to the Cinema: ");
                        String nameMovie = sc.nextLine();
                        for (Movie movie : movies) {
                            if (nameMovie.equals(movie.getTitle())) {
                                found = true;
                                System.out.printf(
                                        """
                                                Currently, The Status of the movie is %s
                                                Enter your choice for the Status of the movie:
                                                (1) COMING_SOON
                                                (2) PREVIEW
                                                (3) NOW_SHOWING
                                                (4) END_OF_SHOWING
                                                                                        
                                                """, movie.getStatus().toString());
                                String movieType;
                                int movieTypeChoice = sc.nextInt();
                                sc.nextLine();
                                if (movieTypeChoice == 1) {
                                    movieType = "COMING_SOON";
                                } else if (movieTypeChoice == 2) {
                                    movieType = "PREVIEW";
                                } else if (movieTypeChoice == 4) {
                                    movieType = "END_OF_SHOWING";
                                    System.out.println("Please enter the date when the movie is to be set to END_OF_SHOWING (DD/MM/YYYY): ");
                                    String date = sc.nextLine();
                                    while(date.length()!=10 || date.charAt(2)!='/' || date.charAt(5)!='/'){
                                        System.out.println("Invalid imput of date format. Please try again:");
                                        System.out.println("Please enter the date when the movie is to be set to END_OF_SHOWING (DD/MM/YYYY): ");
                                        date = sc.nextLine();
                                    }
                                    movie.setEndOfShowingDate(date);
                                } else {
                                    movieType = "NOW_SHOWING";
                                }
                                movie.setStatus(Movie.ShowingStatus.valueOf(movieType));
                                break;
                            }
                        }
                        if (!found) {
                            System.out.printf("No movie with name %s was found.\n Please try again.\n", nameMovie);
                        }else{
                        MovieDB.saveMovies("data/movies.txt", movies);
                        }
                    }
                }
                case 4 -> {
                    // Display the top 5 movies by Ticket Sales
                    ArrayList<Movie> movies = MovieDB.readMovies("data/movies.txt");
                    ArrayList<Movie> sortedMovies = SearchMovie.movieByTickets(movies);
                    for (int i = 0; i < 5; i++) {
                        System.out.printf("%d. %s\n", i + 1, sortedMovies.get(i).getTitle());
                    }
                }
                case 5 -> {
                    // Display the top 5 movies by Reviews
                    ArrayList<Movie> movies = MovieDB.readMovies("data/movies.txt");
                    ArrayList<Movie> sortedMovies = SearchMovie.movieByRating(movies);
                    for (int i = 0; i < 5; i++) {
                        System.out.printf("%d. %s\n", i + 1, sortedMovies.get(i).getTitle());
                    }
                }
                case 6 -> {
                    return;
                }

            }
        } while (true);
    }

    public static void main(String[] args) {
        Cineplex[] cineplexes = new Cineplex[3];
        cineplexes[0] = new Cineplex("Jurong", 5, 1);
        cineplexes[1] = new Cineplex("Sengkang", 3, 2);
        cineplexes[2] = new Cineplex("Sentosa", 4, 3);

        Scanner sc = new Scanner(System.in);
        System.out.printf("""
                Dear User, Welcome!
                Please login to the Booking system.
                If you are an %sAdmin%s, please enter your username and password.
                If you are a %sViewer%s and have an existing account, please enter your emailID and mobile Number.
                If you would like to create a %sViewer%s account, please enter %s"g"%s in the username field.
                
                """, BLUE, RESET,GREEN, RESET, GREEN, RESET, RED, RESET);
        boolean logged_in = false;
        a:while (!logged_in) {
            logged_in = true;
            System.out.println("Enter your username or Email:");
            String username = sc.nextLine();
            if (username.equals("g")) {
                guestUser(sc, cineplexes);
            }
            System.out.println("Enter your password or mobile Number:");
            String password = sc.nextLine();

            int result = login(username, password);
            switch (result) {
                case 1 -> adminUser(sc, cineplexes);
                case 2 -> viewerUser(sc, ViewerDB.getViewer(username), cineplexes);
                case 3 -> {
                    System.out.printf("%sThe login details entered were wrong.%s \nWould you like to try again?\n", RED, RESET);
                    System.out.println("Enter your choice (y/n)");
                    String response;
                    while(!(response = sc.nextLine()).equals("n")) {
                        if (response.equals("y")) {
                            logged_in = false;
                            break;
                        }
                        System.out.println("Invalid Entry. Please try again. ");
                    }
                    if(response.equals("n")){
                        break a;
                    }
                }
            }
            if(logged_in) {
                System.out.println("Successfully logged out! Would you like to log in again? (y/n)");
                String ans;
                while(!(ans = sc.nextLine()).equals("n")) {
                    if (ans.equals("y")) {
                        logged_in = false;
                        break;
                    }
                    System.out.println("Invalid Entry. Please try again. ");
                }
            }
        }

        System.out.println("Thank you for using our Booking system!");
    }
}
