import java.io.IOException;
import java.util.HashMap;


/**
 * A class of objects that represent Admins of the Movie booking System
 */
public class Admin {

    private final String fullName;
    private String username;
    private String password;

    public Admin(String fullName, String username, String password){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static boolean login(String username, String password) throws IOException {
        HashMap<String, String> data = DbIO.loginHelper();
        try{
            if (password.equals(data.get(username))){
                return true;
            }
        }catch (Exception e) {
            System.out.println("Exception > " + e.getMessage());
        }
        return false;
    }

    public boolean changePasswordValidation(Admin admin){
        return (this.getFullName().equals(admin.getFullName())) && (this.getUsername().equals(admin.getUsername()));
    }

    public boolean changeUsernameValidation(Admin admin){
        return (this.getFullName().equals(admin.getFullName())) && (this.getPassword().equals(admin.getPassword()));

    }

    public void updateDb(){
        DbIO.updateAdminRecords(this);
    }
}
