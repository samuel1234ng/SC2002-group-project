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

    public static void writeFile(String fileName, ArrayList<String> data) throws IOException {
        File file = new File(fileName);
        try (FileWriter out = new FileWriter(file, false)) {
            for (String line : data) {
                out.write(line);
            }
        }
    }


    public static ArrayList<String> readFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        ArrayList<String> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        }
        return data;
    }
}