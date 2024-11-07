import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class NavigationUserPane extends VBox {

    private final NavButton profileButton;
    private final NavButton sellButton;
    private final NavButton booksButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public NavigationUserPane(double height) {

        // set attributes for the navigation bar buttons
        ImageView asuIcon = new ImageView(Main.asuIcon);
        asuIcon.setFitHeight(70);
        asuIcon.setFitWidth(80);

        profileButton = new NavButton(Main.profileIcon);
        sellButton = new NavButton(Main.sellIcon);
        booksButton = new NavButton(Main.booksIcon);
        settButton = new NavButton(Main.settingsIcon);
        logoutButton = new NavButton(Main.logOutIcon);

        // set attributes for the buttons' labels
        Label profileButtonText = new Label("Profile");
        Label sellButtonText = new Label("Sell");
        Label booksButtonText = new Label("Your Books");
        booksButtonText.setAlignment(Pos.CENTER);
        Label settButtonText = new Label("Settings");
        Label logoutButtonText = new Label("Logout");

        // set attributes for the NavigationAdminPane Bar Buttons
        // their respective images and/or descriptions are stored in VBoxes
        VBox profileButtonTextVBox = new VBox(profileButton,profileButtonText);
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

    public NavButton getProfileButton() {
        return profileButton;
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
