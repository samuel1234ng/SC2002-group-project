import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * Reads and writes from Settings file
 * @author Samuel Ng
 * @version 1.0
 * @since 2022-11-05
 *
 */
public class SettingsDB {

    /**
     * The character that separates entries in the text file.
     */
    public static final String SEPARATOR = "|";
    /**
     * Writes setting to file
     * @param fileName settings file location
     * @param data data to store
     * @throws IOException
     */
    public static void writeFile(String fileName, ArrayList<String> data) throws IOException {
        File file = new File(fileName);
        try (FileWriter out = new FileWriter(file, false)) {
            for (String line : data) {
                out.write(line);
            }
        }
    }

    /**
     * Reads settings from file
     * @param fileName settings file location
     * @return data from file
     * @throws FileNotFoundException
     */
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
