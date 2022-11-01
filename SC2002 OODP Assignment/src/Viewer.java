import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Represents a viewer who watches and books tickets for movies through the booking system
 */
public class Viewer {

    /**
     * an integer value which is the value of the viewerID of the next user to be created
     */
    private static int idCounter = ViewerDB.getLastViewerID()+1;

    /**
     * the emailID of the viewer
     */
    private final String email;

    /**
     * the first middle and last name of the viewer
     */
    private final String fullName;

    /**
     * The viewer's mobile number
     */
    private final String mobileNumber;

    /**
     * The viewer's viewerID
     */
    private final String viewerID;

    /**
     * The viewer's booking history
     */
    private ArrayList<String> bookingHistory = new ArrayList<>();


    /**
     * Creates a new viewer
     * @param email
     * emailID of the viewer
     * @param fullName
     * name of the viewer
     * @param mobileNumber
     * mobile number of the viewer
     */
    public Viewer(String email, String fullName, String mobileNumber) {
        this.email = email;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.viewerID = Integer.toString(idCounter);
        idCounter++;
        this.bookingHistory.add("NA");
    }

    /**
     * Creates an instance of an already existing viewer from the text file with a given viewerID
     * @param email
     * emailID of the viewer
     * @param fullName
     * name of the viewer
     * @param mobileNumber
     * mobile number of the viewer
     * @param viewerID
     * viewerID of the viewer
     */
    public Viewer(String email, String fullName, String mobileNumber, String viewerID) {
        this.email = email;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.viewerID = viewerID;
        this.bookingHistory = getBookingHistory();
    }

    /**
     * Adds a new booking to the viewer's booking history
     * @param booking
     * A string of the new booking to be added to the booking history
     */
    public void addBookingToHistory(String booking){
        ArrayList<String> current = getBookingHistory();
        if (current.get(0).equals("NA")) {
            current.remove(0);
        }
        current.add(booking);
        this.bookingHistory = current;
    }

    /**
     * Find and return an arraylist of the booking history from the viewer text file
     * @return
     * booking history
     */
    public ArrayList<String> getBookingHistory() {
        try {
            HashMap<String, ArrayList<String>> viewers_history = ViewerDB.getViewersHistory();
            return viewers_history.get(this.email);
        } catch (Exception e) {
            System.out.println("IOException > " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Finds and returns user with the given email from the Viewer file
     * @param email
     * Email id of the required user
     * @return
     * Instance of the user, Or an empty user with the given email if no data on the user previously
     */
    public static Viewer pseudoLogin(String email){
        String data = ViewerDB.viewerDetails(email);
        if(!data.equals("")){
            String number, name, viewerId;
            StringTokenizer st = new StringTokenizer(data, "|");
            viewerId = st.nextToken().trim();
            name = st.nextToken().trim();
            st.nextToken();
            number = st.nextToken().trim();
            return new Viewer(email, name, number, viewerId);
        }else{
            // TODO: Implement adding of new users into the text file
            System.out.println("No viewer with emailID \"" + email + "\" registered in the system");
            return new Viewer(email, "John Doe", "00000000");
        }
    }

    /**
     * Creates and stores a new booking
     * @param transactionID
     * the transactionID of the booking
     * @param seatID
     * The seat in the cinema which was booked for the movie
     * @param movieName
     * the movie which was booked
     */
    public void createNewBookingLine(long transactionID, String seatID, String movieName){
        String tID = Long.toString(transactionID); // XXXYYYYMMDDHHMM
        String booking = tID + "-" + seatID + "-";
        String date = tID.substring(9, 11) + "/" + tID.substring(7, 9) + "/" + tID.substring(3, 7);
        this.addBookingToHistory(booking+date);
    }
}