import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * User_SelectAccountPane is a UI component that allows the user to select between
 * "Buy" or "Sell" actions by displaying corresponding images and buttons.
 * This pane also includes a navigation bar and a customizable layout.
 */
public class User_SelectAccountPane extends BorderPane {

    private final Button b1; // Button for "Buy"
    private final Button b2; // Button for "Sell"

    /**
     * Constructor to initialize the User_SelectAccountPane.
     * Sets up the layout, styles, and actions for the "Buy" and "Sell" options.
     *
     * @param user The user object, which contains user-related information (not used directly in the pane).
     * @param width The width of the pane. Defines the horizontal size of the pane.
     * @param height The height of the pane. Defines the vertical size of the pane.
     */
    public User_SelectAccountPane(User user, double width, double height) {

        // Initialize navigation bar using a custom control (assumes NewUser_NavigationControl is implemented elsewhere)
        NewUser_NavigationControl navBarVBox = new NewUser_NavigationControl(user, width, height);

        // Load custom font for title and body text
        Font arima = Font.loadFont(getClass().getResourceAsStream("fonts/Arima-Bold.ttf"), 65); // Font for title
        Font bodyFont = Font.font("Arial", 20); // Font for body text

        // Set up the title label with custom font and styling
        Label titleLabel = new Label("Please select either...");
        titleLabel.setFont(arima);
        titleLabel.setPadding(new Insets(0, 0, 0, 0)); // No padding
        titleLabel.setTextFill(Color.BLACK); // Set text color

        // Set the dimensions for the images (Buy and Sell)
        double imageWidth = 250;
        double imageHeight = 280;

        // Set up the header HBox (to center the title label)
        HBox headerHBox = new HBox(titleLabel);
        headerHBox.setAlignment(Pos.CENTER);

        // Create Buy button (with icon)
        ImageView imageView1 = new ImageView(Main.buyIcon);
        imageView1.setFitWidth(imageWidth); // Set width of the image
        imageView1.setFitHeight(imageHeight); // Set height of the image

        b1 = new Button();
        b1.setGraphic(imageView1); // Set icon for the button
        b1.setBackground(Background.fill(Color.TRANSPARENT)); // Transparent background

        // Create text for the "Buy" button
        Text t1 = new Text("Buy");
        t1.setFont(arima); // Set font for text

        // VBox for the "Buy" button and its label
        VBox vbox1 = new VBox(b1, t1);
        vbox1.setAlignment(Pos.CENTER); // Center the contents of the VBox
        vbox1.setSpacing(-15); // Adjust spacing between button and label

        // Create Sell button (with icon)
        ImageView imageView2 = new ImageView(Main.sellIcon);
        imageView2.setFitWidth(imageWidth); // Set width of the image
        imageView2.setFitHeight(imageHeight); // Set height of the image

        b2 = new Button();
        b2.setGraphic(imageView2); // Set icon for the button
        b2.setBackground(Background.fill(Color.TRANSPARENT)); // Transparent background

        // Create text for the "Sell" button
        Text t2 = new Text("Sell");
        t2.setFont(arima); // Set font for text

        // VBox for the "Sell" button and its label
        VBox vbox2 = new VBox(b2, t2);
        vbox2.setAlignment(Pos.CENTER); // Center the contents of the VBox
        vbox2.setSpacing(-15); // Adjust spacing between button and label

        // HBox for the body of the pane (containing "Buy" and "Sell" options)
        HBox bodyHBox = new HBox(vbox1, vbox2);
        bodyHBox.setSpacing(100); // Space between the two VBoxes
        bodyHBox.setPadding(new Insets(0, 20, 20, 20)); // Padding for the body area

        // VBox for the main layout (header + body)
        VBox mainVBox = new VBox(headerHBox, bodyHBox);
        mainVBox.setSpacing(20); // Space between header and body
        mainVBox.setPadding(new Insets(40, 40, 0, 40)); // Padding for the main container
        mainVBox.setPrefWidth(width - navBarVBox.getWidth() - 40); // Adjust width to fit within the available space
        mainVBox.setStyle("-fx-background-radius: 2em; -fx-background-color: #ffffff;");

        // HBox for combining navigation bar and main VBox content
        HBox navBarAndMainHBox = new HBox(navBarVBox, mainVBox);
        navBarAndMainHBox.setSpacing(20); // Space between the navigation bar and main content

        // Set the center of the BorderPane to the combined HBox
        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20, 20, 20, 20)); // Set margins for the container

        // Set the default size and background color for the entire pane
        this.setPrefSize(width, height);
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // Dark maroon color for background

    }

    /**
     * Gets the "Buy" button for the user to interact with.
     *
     * @return The "Buy" button.
     */
    public Button getBuyButton() {
        return b1;
    }

    /**
     * Gets the "Sell" button for the user to interact with.
     *
     * @return The "Sell" button.
     */
    public Button getSellButton() {
        return b2;
    }
}
