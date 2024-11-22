import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Buyer_ProfileControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button confirmButton;
    private Buyer_ProfilePane pane;

    public Buyer_ProfileControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_ProfilePane(user,width,height);

        confirmButton = pane.getConfirmButton();
        confirmButton.setOnAction(e -> {
            // Fetch updated data from the fields
            String firstName = pane.getFirstNameTextField().getText();
            String lastName = pane.getLastNameTextField().getText();
            String email = pane.getEmailTextField().getText();
            String username = pane.getUsernameTextField().getText();

            // Validate fields
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty()) {
                System.out.println("All fields must be filled!");
                return;
            }

            boolean success = DataBase.updateUserProfile(user.getUserID(), firstName, lastName, email, username);

            if (success) {
                System.out.println("Profile updated successfully!");

                // Update the user object with the new details
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setUsername(username);

                // Refresh the pane new stuff
                pane.getFirstNameTextField().setText(firstName);
                pane.getLastNameTextField().setText(lastName);
                pane.getEmailTextField().setText(email);
                pane.getUsernameTextField().setText(username);
            } else {
                System.out.println("Failed to update profile!");
            }
        });

        this.getChildren().addAll(pane);
    }
}
