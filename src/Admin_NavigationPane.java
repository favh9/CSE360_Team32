import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Admin_NavigationPane extends VBox {

    private final NavButton profileButton;
    private final NavButton usersButton;
    private final NavButton statsButton;
    private final NavButton transButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public Admin_NavigationPane(double height) {

        // set attributes for the navigation bar buttons
        ImageView asuIcon = new ImageView(Main.asuIcon);
        asuIcon.setFitHeight(70);
        asuIcon.setFitWidth(80);

        double widthImage = 55;
        double heightImage = 55;

        profileButton = new NavButton(Main.profileIcon,widthImage,heightImage);
        usersButton = new NavButton(Main.usersIcon,widthImage,heightImage);
        statsButton = new NavButton(Main.statsIcon,widthImage,heightImage);
        transButton = new NavButton(Main.transIcon,widthImage,heightImage);
        settButton = new NavButton(Main.settingsIcon,widthImage,heightImage);
        logoutButton = new NavButton(Main.logOutIcon,widthImage,heightImage);

        // set attributes for the buttons' labels
        Label profileButtonText = new Label("Profile");
        Label usersButtonText = new Label("Users");
        Label statsButtonText = new Label("Statistics");
        Label transButtonText = new Label("Transactions");
        Label settButtonText = new Label("Settings");
        Label logoutButtonText = new Label("Logout");

        // set attributes for the NavigationAdminPane Bar Buttons
        // their respective images and/or descriptions are stored in VBoxes
        VBox profileButtonTextVBox = new VBox(profileButton,profileButtonText);
        profileButtonTextVBox.setAlignment(Pos.CENTER);
        profileButtonTextVBox.setSpacing(0);

        VBox usersButtonTextVBox = new VBox(usersButton,usersButtonText);
        usersButtonTextVBox.setAlignment(Pos.CENTER);
        usersButtonTextVBox.setSpacing(-20);

        VBox statsButtonTextVBox = new VBox(statsButton,statsButtonText);
        statsButtonTextVBox.setAlignment(Pos.CENTER);
        statsButtonTextVBox.setSpacing(-10);

        VBox transButtonTextVBox = new VBox(transButton,transButtonText);
        transButtonTextVBox.setAlignment(Pos.CENTER);
        transButtonTextVBox.setSpacing(-10);

        VBox settButtonTextVBox = new VBox(settButton,settButtonText);
        settButtonTextVBox.setAlignment(Pos.CENTER);

        VBox logoutButtonTextVBox = new VBox(logoutButton,logoutButtonText);
        logoutButtonTextVBox.setAlignment(Pos.CENTER);
        logoutButtonTextVBox.setSpacing(-10);

        // set attributes for the navigation bar
        VBox navBarVBox = new VBox(asuIcon,profileButtonTextVBox,usersButtonTextVBox,statsButtonTextVBox,transButtonTextVBox,settButtonTextVBox,logoutButtonTextVBox);
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

    public NavButton getUsersButton() {
        return usersButton;
    }

    public NavButton getStatsButton() {
        return statsButton;
    }

    public NavButton getTransButton() {
        return transButton;
    }

    public NavButton getSettButton() {
        return settButton;
    }

    public NavButton getLogoutButton() {
        return logoutButton;
    }
}
