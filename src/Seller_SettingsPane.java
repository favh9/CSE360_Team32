import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Seller_SettingsPane extends BorderPane {

    // Declare buttons for settings actions
    private Button changePasswordButton;
    private Button addPaymentInfoButton;

    /**
     * Constructor to set up the Seller Settings Pane.
     * @param user User object containing user details.
     * @param width The width of the settings pane.
     * @param height The height of the settings pane.
     */
    public Seller_SettingsPane(User user, double width, double height) {

        // Create and initialize the navigation control (sidebar or navigation bar)
        Seller_NavigationControl navBarVBox = new Seller_NavigationControl(user, width, height);

        // Initialize and set properties for the title label
        Label titleLabel = new Label("Settings");
        titleLabel.setFont(Font.font(42)); // Set the font size for the title

        // Create and set up the header with the title label
        HBox headerHBox = new HBox(titleLabel);
        headerHBox.setAlignment(Pos.BASELINE_LEFT); // Align the title to the left

        // Initialize the action buttons
        changePasswordButton = new Button("Change Password");
        addPaymentInfoButton = new Button("Add Payment Info");

        // Style the buttons
        changePasswordButton.setFont(Font.font(20));
        addPaymentInfoButton.setFont(Font.font(20));

        // Add rounded corners to buttons
        changePasswordButton.setStyle("-fx-background-radius: 3em;");
        addPaymentInfoButton.setStyle("-fx-background-radius: 3em;");

        // Set preferred button sizes
        changePasswordButton.setPrefSize(300, 40);
        addPaymentInfoButton.setPrefSize(300, 40);

        // Store the buttons in a VBox container with spacing and padding
        VBox buttonVBox = new VBox(changePasswordButton, addPaymentInfoButton);
        buttonVBox.setSpacing(10); // Set space between buttons
        buttonVBox.setPadding(new Insets(100, 0, 0, 0)); // Add padding to top
        buttonVBox.setAlignment(Pos.CENTER); // Center align buttons

        // Create a main VBox to hold header and buttons
        VBox mainVBox = new VBox(headerHBox, buttonVBox);
        mainVBox.setPadding(new Insets(40, 40, 0, 40)); // Add padding around the content
        mainVBox.setPrefWidth(width - navBarVBox.getWidth() - 40); // Adjust the width based on navBar
        mainVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #ffffff;"); // Style background of the main VBox

        // Create a container to hold both the navigation bar and the main VBox
        HBox navBarAndMainHBox = new HBox(navBarVBox, mainVBox);
        navBarAndMainHBox.setSpacing(20); // Set spacing between navigation bar and main content

        // Set the container as the center of the BorderPane
        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20, 20, 20, 20)); // Add margin around the center

        // Set the preferred size of the settings pane and apply background color
        this.setPrefSize(width, height);
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // Apply a maroon background color
    }

    // Getter and setter methods for the buttons (in case they need to be accessed or modified later)

    public Button getChangePasswordButton() {
        return changePasswordButton;
    }

    public Button getAddPaymentInfoButton() {
        return addPaymentInfoButton;
    }

}
