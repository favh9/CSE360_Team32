import java.io.Serializable;

public class User implements Serializable {

    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private transient String password;
    private String userType;

    public User() {

        userID = Long.getLong("");
        firstName = "";
        lastName = "";
        email = "";
        username = "";
        password = "";
        userType = "";
    }

    public User(String firstName, String lastName, String email, String username, String password, String userType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;

    }

    public User(Long userID, String firstName, String lastName, String email, String username, String password, String userType) {

        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public Long getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public void setUserID(Long id) {
        userID = id;
    }

    public void setFirstName(String string) {
        firstName = string;
    }

    public void setLastName(String string) {
        lastName = string;
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
