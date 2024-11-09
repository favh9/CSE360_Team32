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

public class NavigationBuyerPane extends VBox {

    private final Button profileButton;
    private final NavButton shopButton;
    private final NavButton purchaseHistoryButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public NavigationBuyerPane(User user, double height) {

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
        profileButton = new Button();
        profileButton.setGraphic(new StackPane(circle, letter));  // Stack the circle and text
        profileButton.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");  // Remove default button background and border

        shopButton = new NavButton(Main.buyIcon);
        purchaseHistoryButton = new NavButton(Main.transIcon);
        settButton = new NavButton(Main.settingsIcon);
        logoutButton = new NavButton(Main.logOutIcon);

        // set attributes for the buttons' labels
        Label profileButtonText = new Label("Profile");
        Label shopButtonText = new Label("Shop");
        Label purchaseHistoryButtonText = new Label("Purchase History");
        purchaseHistoryButtonText.setAlignment(Pos.CENTER);
        Label settButtonText = new Label("Settings");
        Label logoutButtonText = new Label("Logout");

        // set attributes for the NavigationAdminPane Bar Buttons
        // their respective images and/or descriptions are stored in VBoxes
        VBox profileButtonTextVBox = new VBox(profileButton,profileButtonText);
        profileButtonTextVBox.setAlignment(Pos.CENTER);
        profileButtonTextVBox.setSpacing(0);

        VBox shopButtonTextVBox = new VBox(shopButton,shopButtonText);
        shopButtonTextVBox.setAlignment(Pos.CENTER);
        shopButtonTextVBox.setSpacing(0);

        VBox purchaseHistoryButtonTextVBox = new VBox(purchaseHistoryButton,purchaseHistoryButtonText);
        purchaseHistoryButtonTextVBox.setAlignment(Pos.CENTER);
        purchaseHistoryButtonTextVBox.setSpacing(-10);

        VBox settButtonTextVBox = new VBox(settButton,settButtonText);
        settButtonTextVBox.setAlignment(Pos.CENTER);

        VBox logoutButtonTextVBox = new VBox(logoutButton,logoutButtonText);
        logoutButtonTextVBox.setAlignment(Pos.CENTER);
        logoutButtonTextVBox.setSpacing(-10);

        // set attributes for the navigation bar
        VBox navBarVBox = new VBox(asuIcon,profileButtonTextVBox,shopButtonTextVBox,purchaseHistoryButtonTextVBox,settButtonTextVBox,logoutButtonTextVBox);
        navBarVBox.setSpacing(3);
        navBarVBox.setPadding(new Insets(20,20,20,20));
        navBarVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        this.setPrefHeight(height);
        this.getChildren().addAll(navBarVBox);

    }

    public Button getProfileButton() {
        return profileButton;
    }

    public NavButton getShopButton() {
        return shopButton;
    }

    public NavButton getPurchaseHistoryButton() {
        return purchaseHistoryButton;
    }

    public NavButton getSettButton() {
        return settButton;
    }

    public NavButton getLogoutButton() {
        return logoutButton;
    }
}
