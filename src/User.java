import java.io.Serializable;

public class User implements Serializable {

    private int userID;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String username;
    private transient String password;
    private int rating; // needs constructor
    private String userType;

    public User() {

        userID = 0;
        firstName = "";
        lastName = "";
        dob = "";
        email = "";
        username = "";
        password = "";
        userType = "";
    }

    public User(String firstName, String lastName, String dob, String email, String username, String password, String userType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;

    }

    public User(int userID, String firstName, String lastName, String dob, String email, String username, String password, String userType) {

        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserID(int id) {
        userID = id;
    }

    public void setFirstName(String string) {
        firstName = string;
    }

    public void setLastName(String string) {
        lastName = string;
    }

    public void setDob(String string) {
        dob = string;
    }

    public void setEmail(String string) {
        email = string;
    }

    public void setUsername(String string) {
        username = string;
    }

    public void setPassword(String string) {
        password = string;
    }

    public void setUserType(String string) {
        userType = string;
    }

}
