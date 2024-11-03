import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class NavigationPane extends VBox {

    private final NavButton usersButton;
    private final NavButton statsButton;
    private final NavButton transButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public NavigationPane(double height) {

        // set attributes for the navigation bar buttons
        ImageView asuIcon = new ImageView(Main.asuIcon);
        asuIcon.setFitHeight(70);
        asuIcon.setFitWidth(80);

        usersButton = new NavButton(Main.usersIcon);
        statsButton = new NavButton(Main.statsIcon);
        transButton = new NavButton(Main.transIcon);
        settButton = new NavButton(Main.settingsIcon);
        logoutButton = new NavButton(Main.logOutIcon);

        // set attributes for the buttons' labels
        Label usersButtonText = new Label("Users");
        Label statsButtonText = new Label("Statistics");
        Label transButtonText = new Label("Transactions");
        Label settButtonText = new Label("Settings");
        Label logoutButtonText = new Label("Logout");

        // set attributes for the NavigationPane Bar Buttons
        // their respective images and/or descriptions are stored in VBoxes
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
        VBox navBarVBox = new VBox(asuIcon,usersButtonTextVBox,statsButtonTextVBox,transButtonTextVBox,settButtonTextVBox,logoutButtonTextVBox);
        navBarVBox.setSpacing(3);
        navBarVBox.setPadding(new Insets(20,20,20,20));
        navBarVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        this.setPrefHeight(height);
        this.getChildren().addAll(navBarVBox);

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
