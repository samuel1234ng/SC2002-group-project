import java.io.IOException;
import java.util.StringTokenizer;

public class Viewer {

    private final String email;

    public Viewer(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void getHistory() {
        try{
            for(String line : DbIO.readViewer("src/data/Viewer.txt").get(this.getEmail())){
                StringTokenizer star = new StringTokenizer(line, "-");
                String bdate = star.nextToken().trim();
                String movie = star.nextToken().trim();
                String mdate = star.nextToken().trim();
                String cineplex = star.nextToken().trim();
                 System.out.printf("Movie %s booked on %s for %s in Cineplex %s\n", movie, bdate, mdate, cineplex);
            }
        }catch (Exception e){
            System.out.println("IOException > " + e.getMessage());
        }
    }
}
