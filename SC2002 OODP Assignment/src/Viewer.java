import java.io.IOException;
import java.util.StringTokenizer;

public class Viewer {

    private static int idCounter;

    static {
        try {
            idCounter = DbIO.readViewerID("src/data/Viewer.txt")+1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final String email;
    private final String fullName;
    private final String mobileNumber;

    private final String viewerid;


    public Viewer(String email, String fullName, String mobileNumber) {
        this.email = email;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.viewerid = Integer.toString(idCounter);
        idCounter++;

    }

    public String getViewerid() {
        return viewerid;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
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
