import java.io.IOException;
import java.util.HashMap;


/**
 * Represent Admins of the Movie booking System. Each Cineplex has two admins.
 * Each admin may only work under one cineplex
 */
public class Admin {

    /**
     * The first, middle and last name of an admin
     */
    private final String fullName;

    /**
     * The username of an admin.
     * used for log in to perform administrative functions
     */
    private final String username;

    /**
     * password of the admin
     * used for log in to perform administrative functions
     */
    private final String password;

    /**
     * Creates a new admin with the given fullName, username and password
     * @param fullName
     * name of the admin
     * @param username
     * username of the admin
     * @param password
     * password of the admin
     */
    public Admin(String fullName, String username, String password){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    /**
     * Verifies the username and password against the usernames and passwords
     * against the data in the Admin text file
     * @param username
     * username used for login
     * @param password
     * password corresponding to the username used
     * @return
     * True if the login is successful, else false
     */
    public static boolean login(String username, String password){
        String fileName = "src/data/Admin.txt";
        HashMap<String, String> data = AdminDB.getAdminData(fileName);
        try{
            if (password.equals(data.get(username))){
                return true;
            }
        }catch (Exception e) {
            System.out.println("Exception > " + e.getMessage());
        }
        return false;
    }
}
