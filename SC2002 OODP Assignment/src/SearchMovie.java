package sc2002.project;

public class SearchMovie {
    //moviebyname, moviebyrating, moviebytickets, array of currentmovies(now_showing), listall
    //movie class should extract info from txt file
    private MovieListing[] movieListing;
    private int numMovies;

    public SearchMovie(MovieListing[] movieListing){
        this.movieListing = movieListing;
        this.numMovies = movieListing.length;
    }

    public void listMovies(){
        for(int i=0;i<this.numMovies;i++){
            Movie movie = this.movieListing[i].movie;
            System.out.printf("Movie %d : %s\n",i+1,movie.getTitle());

        }
    }

    public void listCurrentMovies(){
        int k = 1;
        for(int i=0;i<this.numMovies;i++){
            Movie movie = this.movieListing[i].movie;
            if(movie.getStatus()=="NOW_SHOWING"){
                System.out.printf("Movie %d : %s\n",k,movie.getTitle());
                k++;
            }
        }
    }

    public Movie movieByName(String movieName){
        for(int i=0;i<this.numMovies;i++){
            //movielisting object has movie object attribute
            Movie movie = this.movieListing[i].movie;
            if(movie.getTitle()==movieName){
                return movie;
            }
        }

    }

    public void movieByCinema(Cinema cinema){
        MovieListing[] listing = cinema.movie;
        for(int i=0;i<listing.length;i++){
            Movie movie = listing[i].movie;
            System.out.printf("Movie %d : %s\n",i+1,movie.getTitle());
        }
    }

    public void movieByRating(int numOfMovies){
        MovieListing[] listing = this.movieListing;
       
        for(int i=1;i<this.numMovies;i++){
            for(int j=i;j>0;j--){
                String curr_rating = listing[j].movie.getOverallReviewerRating();
                double currRating = Double.valueOf(curr_rating);
                String prev_rating = listing[j-1].movie.getOverallReviewerRating();
                double prevRating = Double.valueOf(prev_rating);
                if(currRating<prevRating){
                    MovieListing temp = listing[j];
                    listing[j] = listing[j-1];
                    listing[j-1] = temp;
                }
                else{
                    break;
                }
            }

        }
        for(int k=0;k<numOfMovies;k++){
            Movie movie = listing[k].movie;
            System.out.printf("Movie %d : %s\n",k+1,movie.getTitle());
        }
        
    }

    public void movieByTickets(int numOfMovies){
        MovieListing[] listing = this.movieListing;
       
        for(int i=1;i<this.numMovies;i++){
            for(int j=i;j>0;j--){
                int currTickets = listing[j].movie.getNoOfTickets();
                int prevTickets = listing[j-1].movie.getNoOfTickets();
                if(currTickets<prevTickets){
                    MovieListing temp = listing[j];
                    listing[j] = listing[j-1];
                    listing[j-1] = temp;
                }
                else{
                    break;
                }
            }

        }
        for(int k=0;k<numOfMovies;k++){
            Movie movie = listing[k].movie;
            System.out.printf("Movie %d : %s\n",k+1,movie.getTitle());
        }
        
    }

}




