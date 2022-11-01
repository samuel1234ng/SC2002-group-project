import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * A class which reads data from the Admin text file
 */
public class ViewerDB {

    /**
     * The character that separates entries in the text file
     */
    public static final String SEPARATOR = "|";

    /**
     * Reads data from a file and returns its contents in an ArrayList
     * @param fileName
     * the path of the file(from the content root)
     * @return
     * an ArrayList of strings containing lines of the read file
     */
    public static ArrayList<String> readFile(String fileName) {
        File f = new File(fileName);
        ArrayList<String> file_data = new ArrayList<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                file_data.add(scanner.nextLine());
            }
        }catch (Exception e){
            System.out.println("Exception >> " + e.getMessage());
        }
        return file_data;
    }

    /**
     * Writes data from an Arraylist into a file
     * @param fileName
     * Name of the file to write into
     * @param data
     * An arraylist of String data
     */
    public static void writeFile(String fileName, ArrayList<String> data){
        try {
            File f = new File(fileName);
            FileWriter out = new FileWriter(f, false);
            for (String line : data) {
                out.write(line);
            }
        }catch (Exception e){
            System.out.println("Exception >> " + e.getMessage());

        }
    }

    /**
     * Reads the Viewer text file and returns a hashmap of email id's corresponding to the viewer's booking history
     * @return
     * A Hashmap of email ids corresponding to an Arraylist of the viewer's booking history
     */
    public static HashMap<String, ArrayList<String>> getViewersHistory(){
        String fileName = "src/data/Viewer.txt";
        ArrayList<String> string_data = readFile(fileName);
        HashMap<String, ArrayList<String>> viewers_history = new HashMap<>();
        for (String line : string_data) {
            ArrayList<String> history = new ArrayList<>();
            StringTokenizer star = new StringTokenizer(line, SEPARATOR);
            star.nextToken();
            star.nextToken();
            String email = star.nextToken().trim();
            star.nextToken();
            while (star.hasMoreTokens()){
                if(line.equals("NA")){
                    history.add("NA");
                    break;
                }
                history.add(star.nextToken().trim());
            }
            viewers_history.put(email, history);
        }
        return viewers_history;
    }

    /**
     * reads the Viewer text file and returns the viewerID corresponding to the emailID
     * @param email
     * The email of the viewer
     * @return
     * The Viewer ID of the viewer
     */
    public static int getViewerID(String email){
        String fileName = "src/data/Viewer.txt";
        ArrayList<String> string_data = readFile(fileName);
        for (String line : string_data) {
            ArrayList<String> history = new ArrayList<>();
            StringTokenizer star = new StringTokenizer(line, SEPARATOR);
            String id = star.nextToken();
            star.nextToken();
            String data_email= star.nextToken().trim();
            if(email.equals(data_email)){
                return Integer.parseInt(id);
            }
        }
        return 0;
    }

    /**
     * Find the ID value of the last viewer to be created
     * @return
     * The id if found, else -1
     */
    public static int getLastViewerID(){
        try{
            String fileName = "src/data/Viewer.txt";
            File f = new File(fileName);
            String id;
            String line = "1021|";
            try (Scanner scanner = new Scanner(f)) {
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                }
                StringTokenizer st = new StringTokenizer(line, SEPARATOR);
                id = st.nextToken().trim();
            }
            return Integer.parseInt(id);
        }catch(Exception e){
            System.out.println("Exception >> " + e.getMessage());
        }
        return -1;
    }

    /**
     * Reads the viewer text file and returns the viewer with the given email
     * @param email
     * emailID of the viewer
     * @return
     * instance of the viewer, or "" of the viewer does not exist
     */
    public static String viewerDetails(String email){
        try{
            String fileName = "src/data/Viewer.txt";
            File f = new File(fileName);
            Scanner scanner = new Scanner(f);
            String line,emailID;
            StringTokenizer st;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                st = new StringTokenizer(line, SEPARATOR);
                st.nextToken();
                st.nextToken();
                emailID = st.nextToken().trim();
                if(email.equals(emailID)){
                    return line;
                }
            }
        }catch (Exception e){
            System.out.println("Exception >> " + e.getMessage());
        }
        return "";
    }
}
