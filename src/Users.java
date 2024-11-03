import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Users extends BorderPane {

    public double width;
    public double height;

    public Users(double width, double height) {

        this.width = width;
        this.height = height;

        // set attributes for the navigation bar buttons
        ImageView asuIcon = new ImageView(Main.asuIcon);
        asuIcon.setFitHeight(70);
        asuIcon.setFitWidth(80);
        NavButton usersButton = new NavButton(Main.usersIcon);
        NavButton statsButton = new NavButton(Main.statsIcon);
        NavButton transButton = new NavButton(Main.transIcon);
        NavButton settButton = new NavButton(Main.settingsIcon);
        NavButton logoutButton = new NavButton(Main.logOutIcon);

        // set attributes for the buttons' labels
        Label usersButtonText = new Label("Users");
        Label statsButtonText = new Label("Statistics");
        Label transButtonText = new Label("Transactions");
        Label settButtonText = new Label("Settings");
        Label logoutButtonText = new Label("Logout");


        // set attributes for the Navigation Bar Buttons
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

        // set attributes for the navigation bar
        VBox navBarVBox = new VBox(asuIcon,usersButtonTextVBox,statsButtonTextVBox,transButtonTextVBox,settButtonTextVBox,logoutButtonTextVBox);
        navBarVBox.setPadding(new Insets(20,20,20,20));
        navBarVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the Users Title Label
        Label usersLabel = new Label("Users");
        usersLabel.setFont(Font.font(42));

        // set attributes for the user quantity label
        Label userQuantityLabel = new Label("Users");
        userQuantityLabel.setFont(Font.font(20));

        // set attribute for the user display amount label
        Text userDisplayAmountText = new Text();
        userDisplayAmountText.setFont(Font.font(20));
        userDisplayAmountText.setText(Double.toString(getUserQuantity()));

        // set attributes for the HBox that holds the quantity and quantity label
        HBox quantityHBox = new HBox(userDisplayAmountText, userQuantityLabel);
        quantityHBox.setSpacing(10);
        quantityHBox.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for delete button image
        ImageView deleteImage = new ImageView(Main.delUserIcon);
        deleteImage.setFitHeight(30);
        deleteImage.setFitWidth(30);

        // set attributes for delete button button
        Button deleteButton = new Button();
        deleteButton.setGraphic(deleteImage);
        deleteButton.setBackground(Background.fill(Color.TRANSPARENT));
        deleteButton.setAlignment(Pos.CENTER);

        // set attributes for the header of the Users page
        HBox userHeaderHBox = new HBox(usersLabel,quantityHBox,deleteButton);
        userHeaderHBox.setAlignment(Pos.BASELINE_LEFT);
        HBox.setMargin(deleteButton,new Insets(0,0,20,0));
        userHeaderHBox.setSpacing(40);

        // set attributes for the header of the user list
        //
        // set attributes for the username header label
        Label usernameHeaderLabel = new Label("Username");
        usernameHeaderLabel.setFont(Font.font(20));
        usernameHeaderLabel.setPrefWidth(400);
        usernameHeaderLabel.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the balance header label
        Label balanceHeaderLabel = new Label("Balance");
        balanceHeaderLabel.setFont(Font.font(20));
        balanceHeaderLabel.setPrefWidth(200);
        balanceHeaderLabel.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the type header label
        Label typeHeaderLabel = new Label("Type");
        typeHeaderLabel.setFont(Font.font(20));
        typeHeaderLabel.setPrefWidth(200);
        typeHeaderLabel.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the header of the user list box
        HBox userlistHeaderHBox = new HBox(usernameHeaderLabel,balanceHeaderLabel,typeHeaderLabel);

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(800);
        lineSeparator.setHeight(2);
        lineSeparator.setFill(Color.BLACK);

        // set attributes for the scrollpane
        ScrollPane userlistScrollPane = new ScrollPane();
        userlistScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        userlistScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        userlistScrollPane.setPrefSize(800,Double.MAX_VALUE);
        userlistScrollPane.setFitToHeight(true);
        userlistScrollPane.setFitToWidth(true);
        userlistScrollPane.minViewportHeightProperty().set(400);
        userlistScrollPane.setStyle("-fx-background-color:transparent;");

        // set attributes for the users box that includes their header and scrollpane
        VBox headerAndScrollPane = new VBox(userlistHeaderHBox,lineSeparator,userlistScrollPane);

        // set attributes for the Users VBox which stores the list of users and such
        VBox usersVBox = new VBox(userHeaderHBox,headerAndScrollPane);
        usersVBox.setPadding(new Insets(40,40,0,40));
        usersVBox.setPrefWidth(width - navBarVBox.getWidth() - 40);
        usersVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and users page
        HBox navBarAndUsersHBox = new HBox(navBarVBox,usersVBox);
        navBarAndUsersHBox.setSpacing(20);

        this.setCenter(navBarAndUsersHBox);
        BorderPane.setMargin(navBarAndUsersHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon

    }

    // fetches how many users are in the database
    public int getUserQuantity() {

        return 0;
    }

}
