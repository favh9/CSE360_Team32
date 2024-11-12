import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Seller_NavigationPane extends VBox {

    private final Button button;
    private final NavButton sellButton;
    private final NavButton booksButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public Seller_NavigationPane(User user, double height) {

        // set attributes for the navigation bar buttons
        ImageView asuIcon = new ImageView(Main.asuIcon);
        asuIcon.setFitHeight(70);
        asuIcon.setFitWidth(80);

        // Create the circle with a specific radius
        double radius = 30;
        Circle circle = new Circle(radius);
        circle.setFill(Color.WHITE);  // Set the color of the circle
        circle.setStroke(Color.BLACK);  // Set the stroke (border) color

        // Create the text node with their initials
        StringBuilder sb = new StringBuilder();
        sb.append(user.getFirstName().charAt(0));
        sb.append(user.getLastName().charAt(0));
        Text letter = new Text(sb.toString().toUpperCase());
        letter.setFont(Font.font(40));  // Set the font size of the letter
        letter.setFill(Color.BLACK);  // Set the text color (white for contrast)

        // Create a button and set the graphic (circle + text) as the button content
        button = new Button();
        button.setGraphic(new StackPane(circle, letter));  // Stack the circle and text
        button.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");  // Remove default button background and border

        sellButton = new NavButton(Main.sellIcon);
        booksButton = new NavButton(Main.booksIcon);
        settButton = new NavButton(Main.settingsIcon);
        logoutButton = new NavButton(Main.logOutIcon);

        // set attributes for the buttons' labels
        Label profileButtonText = new Label("Profile");
        Label sellButtonText = new Label("Sell");
        Label booksButtonText = new Label("My Books");
        booksButtonText.setAlignment(Pos.CENTER);
        Label settButtonText = new Label("Settings");
        Label logoutButtonText = new Label("Logout");

        // set attributes for the NavigationAdminPane Bar Buttons
        // their respective images and/or descriptions are stored in VBoxes
        VBox profileButtonTextVBox = new VBox(button,profileButtonText);
        profileButtonTextVBox.setAlignment(Pos.CENTER);
        profileButtonTextVBox.setSpacing(0);

        VBox sellButtonTextVBox = new VBox(sellButton,sellButtonText);
        sellButtonTextVBox.setAlignment(Pos.CENTER);
        sellButtonTextVBox.setSpacing(0);

        VBox booksButtonTextVBox = new VBox(booksButton,booksButtonText);
        booksButtonTextVBox.setAlignment(Pos.CENTER);
        booksButtonTextVBox.setSpacing(-10);

        VBox settButtonTextVBox = new VBox(settButton,settButtonText);
        settButtonTextVBox.setAlignment(Pos.CENTER);

        VBox logoutButtonTextVBox = new VBox(logoutButton,logoutButtonText);
        logoutButtonTextVBox.setAlignment(Pos.CENTER);
        logoutButtonTextVBox.setSpacing(-10);

        // set attributes for the navigation bar
        VBox navBarVBox = new VBox(asuIcon,profileButtonTextVBox,sellButtonTextVBox,booksButtonTextVBox,settButtonTextVBox,logoutButtonTextVBox);
        navBarVBox.setSpacing(3);
        navBarVBox.setPadding(new Insets(20,20,20,20));
        navBarVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        this.setPrefHeight(height);
        this.getChildren().addAll(navBarVBox);

    }

    public Button getProfileButton() {
        return button;
    }

    public NavButton getSellButton() {
        return sellButton;
    }

    public NavButton getBooksButton() {
        return booksButton;
    }

    public NavButton getSettButton() {
        return settButton;
    }

    public NavButton getLogoutButton() {
        return logoutButton;
    }
}
