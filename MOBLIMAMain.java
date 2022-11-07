import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class MOBLIMAMain {
    public static void guestUser(Scanner sc) {
        System.out.println("""
                Welcome to the booking system!
                Please follow the instructions below to create a new Viewer account.
                """);
        System.out.println("Please enter your full Name");
        String name = sc.nextLine();
        System.out.println("Please enter your email Id");
        String email = sc.nextLine();
        System.out.println("Please enter your mobile Number");
        String mobile = sc.nextLine();
        Viewer v = new Viewer(email, name, mobile);
        System.out.println("""
                Thank you for creating an account in out Booking System!
                We will now proceed to direct you to the Viewer Menu.
                """);
        ViewerDB.createViewerInFile(v);
        viewerUser(sc, v);


    }

    public static void viewerUser(Scanner sc, Viewer v) {
        /*
        Movie-goer module
        1. Search/List movie
        2. View movie details – including reviews and ratings
        3. Check seat availability and selection of seat/s.
        4. Book and purchase ticket
        5. View booking history
        6. List the Top 5 ranking by ticket sales OR by overall reviewers’ ratings
        */

        // ADD CHOOSE CINEPLEX CODE

        Cineplex c1 = new Cineplex("testCineplex", 5);

        System.out.printf(""" 
                Welcome %s ! What would you like to do? Enter your choice:
                (1) Search for a Movie and view its details
                (2) View a list of all the movies
                (3) Check seat availability for show-times
                (4) Select  and purchase seats and make a Booking
                (5) View your booking history
                (6) View the Top 5 ranked movies by
                    (i) Ticket sales
                    (ii) Overall reviewers’ ratings
                (7) Logout
                """, v.getFullName());

        int choice = sc.nextInt();
        while (choice <= 7 && choice >= 0) {
            switch (choice) {
                case 1 -> {
                    //Search for a Movie
                    // find the movie and print details
                    sc.nextLine();
                    System.out.println("Please enter the name of a movie to view its details.\n");
                    SearchMovie.printMovieDetails(SearchMovie.movieByName(sc.nextLine(), MovieDB.readMovies("data/movies.txt")));
                }
                case 2 -> {
                    //View a list of all the movies
                    ArrayList<Movie> movies = MovieDB.readMovies("data/movies.txt");
                    SearchMovie.listMovies(movies);
                }
                case 3 -> {
                    //Check seat availability for show-times
                    ArrayList<Movie> movies = MovieDB.readMovies("data/movies.txt");
                    SearchMovie.listCurrentMovies(movies);
                    System.out.println("Select movie : ");
                    String selection = sc.next();
                    //Add search cinema by movie function to Cineplex class
                    Cinema cinema = c1.cinemaByMovie(selection);
                    //Assume cinema already has MovieListing arraylist initialized
                    MovieListing listing = cinema.getListing(selection);
                    //Show user available timings 
                    Booking.availableTimings(listing);
                    //User selects timing
                    System.out.println("Enter timing : ");
                    int timeChosen = sc.nextInt();
                    TimeSlot chosenTiming = listing.getTime(timeChosen);
                    //Show seats
                    Booking.availableSeats(chosenTiming);
                }
                case 4 -> {
                    //Select  and purchase seats and make a Booking
                    ArrayList<Movie> movies = MovieDB.readMovies("data/movies.txt");
                    SearchMovie.listCurrentMovies(movies);
                    System.out.println("Select movie : ");
                    String selection = sc.next();
                    //Add search cinema by movie function to Cineplex class
                    Cinema cinema = c1.cinemaByMovie(selection);
                    //Assume cinema already has MovieListing arraylist initialized
                    MovieListing listing = cinema.getListing(selection);
                    //Show user available timings 
                    Booking.availableTimings(listing);
                    //User selects timing
                    System.out.println("Enter timing : ");
                    int timeChosen = sc.nextInt();
                    TimeSlot chosenTiming = listing.getTime(timeChosen);
                    //Show seats
                    Booking.availableSeats(chosenTiming);

                    Booking.makeBooking(chosenTiming, v);
                    //add calculating ticket price
                    System.out.println("Payment completed!");
                    Booking.completePayment(cinema);
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
                                Booking for movie: %s
                                Seat number: %s
                                Booking for date: %s
                                Transaction Id: %s
                                	
                                	""", movie_name, seat, date, transaction_id);
                    }
                }
                case 6 -> {
                    /* View the Top 5 ranked movies by
                          (i) Ticket sales
                         (ii) Overall reviewers’ ratings */
                    ArrayList<Movie> movies = MovieDB.readMovies("data/movies.txt");
                    System.out.println("View top 5 movies based on:");
                    System.out.println("1. Ticket sales");
                    System.out.println("2. Ratings");
                    int ans = sc.nextInt();
                    if (ans == 1) {
                        ArrayList<Movie> sortedMovies = SearchMovie.movieByTickets(movies);
                        for (int i = 0; i < 5; i++) {
                            System.out.printf("%d. %s\n", i + 1, sortedMovies.get(i).getTitle());
                        }
                    } else {
                        ArrayList<Movie> sortedMovies = SearchMovie.movieByRating(movies);
                        for (int i = 0; i < 5; i++) {
                            System.out.printf("%d. %s\n", i + 1, sortedMovies.get(i).getTitle());
                        }
                    }
                }
                case 7 -> {
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
                    Welcome back %s! What would you like to do? Enter your choice:
                    (1) Search for a Movie and view its details
                    (2) View a list of all the movies
                    (3) Check seat availability for show-times
                    (4) Select  and purchase seats and make a Booking
                    (5) View your booking history
                    (6) View the Top 5 ranked movies by
                        (i) Ticket sales
                        (ii) Overall reviewers’ ratings
                    (7) Logout
                    """, v.getFullName());
            choice = sc.nextInt();
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
        String adminFile = "data/Admin.txt";
        HashMap<String, String> adata = AdminDB.getAdminData(adminFile);
        if (password.equals(adata.get(userName))) {
            return 1;
        }

        // user login
        String viewerFile = "data/Viewer.txt";
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


        ConfigureSystemSettings set = new ConfigureSystemSettings();
        set.loadsettings();
        int choice, choice2, choice3, choice4, choice5;
        System.out.println("Hi Admin");
        do {
            System.out.println("""
                    What would you like to do?
                    (1) Configure the System's Settings
                    (2) Add an upcoming movie to the Cineplex
                    (3) Edit a movie
                    (4) Display the top 5 movies by Ticket Sales
                    (5) Display the top 5 movies by Reviews
                    (6) Logout
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    // Configure the System's Settings
                    do {
                        System.out.println("""
                                What would you like to do?
                                (1) Print settings
                                (2) changebaseprice
                                (3) changeticketprice
                                (4) addholiday
                                (5) removeholiday
                                (6) reload settings from file
                                (7) save and return
                                """);
                        choice2 = sc.nextInt();
                        while (choice2 > 7 || choice2 < 1) {
                            System.out.println("Invalid option");
                            choice2 = sc.nextInt();
                        }
                        switch (choice2) {
                            case 1 -> {
                                set.printsettings();
                            }
                            case 2 -> {
                                System.out.println("New base price");
                                choice3 = sc.nextInt();
                                set.changebaseprice(choice3);
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
                                while (choice3 > 4 || choice3 < 1) {
                                    System.out.println("Invalid option");
                                    choice3 = sc.nextInt();
                                }
                                switch (choice3) {
                                    case 1 -> {
                                        System.out.println("""
                                                What would you like to change
                                                (1) Cost of normal movie
                                                (2) Cost of 3D movie
                                                (3) Cost of blockbuster movie
                                                """);
                                    }
                                    case 2 -> {
                                        System.out.println("""
                                                What would you like to change
                                                (1) Cost of normal class theater
                                                (2) Cost of elite class theater
                                                (3) Cost of budget class theater
                                                """);
                                    }
                                    case 3 -> {
                                        System.out.println("""
                                                What would you like to change
                                                (1) Cost of adult
                                                (2) Cost of child
                                                (3) Cost of senior citizen
                                                """);
                                    }
                                    case 4 -> {
                                        System.out.println("""
                                                What would you like to change
                                                (1) Cost of weekday
                                                (2) Cost of weekend
                                                (3) Cost of holiday
                                                """);
                                    }
                                }
                                choice4 = sc.nextInt();
                                System.out.println("New price:");
                                choice5 = sc.nextInt();
                                set.changeticketprice(choice3, choice4, choice5);
                            }
                            case 4 -> {
                                System.out.println("Day to set as holiday:");
                                choice = sc.nextInt();
                                set.addholiday(choice);
                            }
                            case 5 -> {
                                System.out.println("Day to unset as holiday:");
                                choice = sc.nextInt();
                                set.removeholiday(choice);
                            }
                            case 6 -> {
                                set.loadsettings();
                            }
                            case 7 -> {
                                set.storesettings();
                            }
                        }
                    } while (choice2 < 7);
                }
                case 2 -> {
                    // Add an upcoming movie to the Cineplex
                    System.out.println("Enter movieType: ");
                    String movietyp = sc.nextLine();
                    // TODO: REMOVE
                    ArrayList<MovieListing> movieList = new ArrayList<>();

                    System.out.println("Enter name of new movie: ");
                    String nameMovie = sc.nextLine();
                    System.out.println("Enter status of new movie: ");
                    String status = sc.nextLine();
                    for (int i = 0; i < movieList.size(); i++) {
                        Movie movie = movieList.get(i).getMovie();
                        if (nameMovie.equals(movie.getTitle())) {
                            MovieListing newListing1 = new MovieListing(movie, movietyp, status);
                            System.out.println("Enter numbers showtimes of new movie: ");
                            int numberofshows1 = sc.nextInt();
                            for (int j = 0; j < numberofshows1; j++) {
                                System.out.println("Enter showtime of new movie: ");
                                int timing = sc.nextInt();
                                newListing1.addTimeSlots(timing);


                            }
                            System.out.println("""
                                    Select a Cineplex to add movie
                                    (1) Cineplex a
                                    (2) Cineplex b
                                    (3) Cineplex c
                                    """);
                            int optionCineplex = sc.nextInt();
                            System.out.println("""
                                    Select a Cinema to add movie
                                    (1) Cinema 1
                                    (2) Cinema 2
                                    (3) Cinema 3
                                    """);
                            int optionCinema = sc.nextInt();

                            //TODO:
                            cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1].movielistings.add(newListing1);

                            break;
                        }
                    }
                }
                case 3 -> {
                    // Edit a movie
                    System.out.println("""
                            Select a Cineplex to add movie
                            (1) Cineplex a
                            (2) Cineplex b
                            (3) Cineplex c
                            """);
                    int optionCineplex = sc.nextInt();
                    System.out.println("""
                            Select a Cinema to add movie
                            (1) Cinema 1
                            (2) Cinema 2
                            (3) Cinema 3
                            """);
                    int optionCinema = sc.nextInt();
                    System.out.println("Enter name of new movie: ");
                    String nameMovie = sc.nextLine();
                    for (int i = 0; i < cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1].movielistings.size(); i++) {
                        Movie movie = cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1].movielistings.get(i).getMovie();
                        if (nameMovie.equals(movie.getTitle())) {
                            System.out.println("""
                                    Select an option to change:
                                    (1)Status
                                    (2) Timing
                                    """);
                            int option = sc.nextInt();
                            switch (option) {

                                case 1 -> {
                                    System.out.println("""
                                            Enter new status of movie:
                                            (1)comingSoon
                                            (2)nowShowing
                                            (3)endOfShowing
                                            """);
                                    String status = sc.nextLine();
                                    cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1].movielistings.get(i).setStatus(status);
                                    if (status.equals("endOfShowing")) {
                                        cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1].movielistings.remove(i);

                                    }
                                }
                                case 2 -> {
                                    MovieListing movieNew = cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1].movielistings.get(i);
                                    System.out.println("Enter timing you would like to replace: ");
                                    int oldTime = sc.nextInt();
                                    for (int o = 0; o < cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1].movielistings.get(i).timeSlots.size(); o++) {
                                        if (oldTime == cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1].movielistings.get(i).timeSlots.get(o).getTiming()) {
                                            System.out.println("Enter new timing : ");
                                            int newTime = sc.nextInt();
                                            cineplexes[optionCineplex - 1].getCinemas()[optionCinema - 1].movielistings.get(i).timeSlots.get(o).setTiming(newTime);
                                        }


                                    }


                                }


                            }

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
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Dear User, Welcome!
                Please login to the Booking system.
                If you are an Admin, please enter your username and password.
                If you are a Viewer, please enter your emailID and mobile Number.
                If you are a Guest, please enter "g" in the username field.
                """);
        boolean logged_in = false;
        while (!logged_in) {
            logged_in = true;
            System.out.println("Enter your username or Email:");
            String username = sc.nextLine();
            if (username.equals("g")) {
                guestUser(sc);
            }
            System.out.println("Enter your password or mobile Number:");
            String password = sc.nextLine();

            int result = login(username, password);
            switch (result) {
                case 1 -> {
                    adminUser(sc, cineplexes);
                }
                case 2 -> {
                    viewerUser(sc, ViewerDB.getViewer(username));
                }
                case 3 -> {
                    System.out.println("The login details entered were wrong. Would you like to try again?");
                    System.out.println("enter your choice (y/n)");
                    String re_login = sc.nextLine();
                    if (re_login.equals("y")) {
                        logged_in = false;
                        break;
                    }
                    Viewer final_test = ViewerDB.getViewer(username);
                    if (final_test.getFullName().equals("")) {
                        guestUser(sc);
                    } else {
                        viewerUser(sc, final_test);
                    }
                }
            }
        }
    }
}
