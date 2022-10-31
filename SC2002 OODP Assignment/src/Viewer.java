import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Viewer {

    private static int idCounter;

    static {
        try {
            idCounter = DbIO.readViewerID("src/data/Viewer.txt") + 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final String email;
    private final String fullName;
    private final String mobileNumber;
    private final String viewerid;

    private ArrayList<String> bookingHistory = new ArrayList<>();


    public Viewer(String email, String fullName, String mobileNumber) {
        this.email = email;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.viewerid = Integer.toString(idCounter);
        idCounter++;
        this.bookingHistory.add("NA");

    }

    public void addBookingToHistory(String booking) throws IOException {
        ArrayList<String> current = getBookingHistory();
        if (current.get(0).equals("NA")) {
            current.remove(0);
        }
        current.add(booking);
        this.bookingHistory = current;
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

    public ArrayList<String> getBookingHistory() {
        try {
//            for(String line : DbIO.readViewer("src/data/Viewer.txt").get(this.getEmail())){
//                StringTokenizer star = new StringTokenizer(line, "-");
//                String bdate = star.nextToken().trim();
//                String movie = star.nextToken().trim();
//                String mdate = star.nextToken().trim();
//                String cineplex = star.nextToken().trim();
//                 System.out.printf("Movie %s booked on %s for %s in Cineplex %s\n", movie, bdate, mdate, cineplex);
//            }
            return DbIO.readViewer("src/data/Viewer.txt").get(this.getEmail());
        } catch (Exception e) {
            System.out.println("IOException > " + e.getMessage());

        }
        return new ArrayList<>();
    }
}