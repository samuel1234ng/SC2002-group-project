import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SettingsDB {

    /**
     * The character that separates entries in the text file.
     */
    public static final String SEPARATOR = "|";

    /**
     *
     * @param filename
     * A string of the name of the text file to read from.
     * @return
     * An ArrayList of admin objects containing the admin details from the file.
     * @throws IOException
     * when the file  with name filename does not exist
     */
    public static ArrayList<Admin> readAdmin(String filename) throws IOException{
        ArrayList<String> stringArray = (ArrayList<String>) readFile(filename);
        ArrayList<Admin> al = new ArrayList<>();
        for (String s : stringArray) {
            StringTokenizer star = new StringTokenizer(s, SEPARATOR);

            String name = star.nextToken().trim();
            String username = star.nextToken().trim();
            String password = star.nextToken().trim();
            al.add(new Admin(name, username, password));
        }
        return al;
    }

    public static void writeFile(String fileName, ArrayList<String> data) throws IOException {
        File file = new File(fileName);
        try (FileWriter out = new FileWriter(file, false)) {
            for (String line : data) {
                out.write(line);
            }
        }
    }


    public static List<String> readFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        List<String> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        }
        return data;
    }

    public static HashMap<String, String> loginHelper() throws FileNotFoundException {
        HashMap<String, String> hm= new HashMap<>();
        ArrayList<String> stringArray = (ArrayList<String>) readFile("src/data/Admin.txt");
        stringArray.remove(stringArray.size()-1);
        for (String s : stringArray) {
            StringTokenizer star = new StringTokenizer(s, SEPARATOR);
            String name = star.nextToken().trim();
            String username = star.nextToken().trim();
            String password = star.nextToken().trim();
            hm.put(username, password);
        }
        return hm;
    }

}