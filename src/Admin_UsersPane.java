import java.text.NumberFormat;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Admin_UsersPane extends BorderPane {

    private Button refreshButton;
    private VBox usersVBox;
    private Text usersAmountText;

    public Admin_UsersPane(User user, double width, double height) {

        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);
        Font quantityFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 24);

        Admin_NavigationControl navigationControl = new Admin_NavigationControl(user, width, height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Users");
        titleLabel.setFont(titleFont);

        // set attributes for the quantity label
        Label quantityLabel = new Label("amount");
        quantityLabel.setFont(quantityFont);

        // set attribute for the quantity label
        usersAmountText = new Text("0");
        usersAmountText.setFont(quantityFont);

        // set attributes for the HBox that holds the quantity and quantity label
        HBox quantityHBox = new HBox(usersAmountText, quantityLabel);
        quantityHBox.setSpacing(20);
        quantityHBox.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for container that holds Title and Quantity
        HBox leftHeaderPaneHBox = new HBox(titleLabel,quantityHBox);
        leftHeaderPaneHBox.setSpacing(40);
        leftHeaderPaneHBox.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the image
        ImageView image1 = new ImageView(Main.refreshIcon);
        image1.setFitWidth(25);
        image1.setFitHeight(25);

        // set attributes for the button
        refreshButton = new Button();
        refreshButton.setGraphic(image1);
        refreshButton.setBackground(Background.fill(Color.TRANSPARENT));
        refreshButton.setAlignment(Pos.CENTER);

        // set attributes for the container of two buttons
        HBox rightHeaderPaneHBox = new HBox(refreshButton);
        rightHeaderPaneHBox.setAlignment(Pos.CENTER_RIGHT);

        // set attributes for the header of the page
        BorderPane headerPane = new BorderPane();
        headerPane.setLeft(leftHeaderPaneHBox);
        headerPane.setRight(rightHeaderPaneHBox);

        // set attributes for the header label
        Label headerLabel1 = new Label("User ID");
        headerLabel1.setPrefWidth(500);
        headerLabel1.setWrapText(true);
        headerLabel1.setFont(Font.font(20));
        headerLabel1.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel2 = new Label("User Type");
        headerLabel2.setPrefWidth(200);
        headerLabel2.setWrapText(false);
        headerLabel2.setFont(Font.font(20));
        headerLabel2.setAlignment(Pos.BASELINE_CENTER);

        // FOR DEBUGGING maybe?
        headerLabel1.setBorder(Border.stroke(Color.BLACK));
        headerLabel2.setBorder(Border.stroke(Color.BLACK));

        // set attributes for the header of the body
        HBox bodyheaderPane = new HBox(headerLabel1,headerLabel2);

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(700);
        lineSeparator.setHeight(2);
        lineSeparator.setFill(Color.BLACK);

        // set attributes for the VBox that holds the lists of users
        usersVBox = new VBox();

        // set attributes for the scrollpane
        ScrollPane sp = new ScrollPane(usersVBox);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.minViewportHeightProperty().set(400);
        sp.setPrefHeight(400);
        sp.setStyle("-fx-background: white;");

        // set attributes for the users box that includes their header and scrollpane
        VBox headerAndScrollPane = new VBox(bodyheaderPane,lineSeparator,sp);

        // set attributes for the main VBox which excludes the navigation bar
        VBox mainVBox = new VBox(headerPane,headerAndScrollPane);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setSpacing(10);
        mainVBox.setPrefWidth(width - navigationControl.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and page
        HBox navBarAndMainHBox = new HBox(navigationControl,mainVBox);
        navBarAndMainHBox.setSpacing(20);

        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color

    }

    public void clearTransactions() {
        usersVBox.getChildren().clear();
        usersAmountText.setText("0");
    }

    // modifies and updates the display amount text by one
    public void updateCount() {
        String str = usersAmountText.getText();
        int incr = Integer.parseInt(str) + 1;
        usersAmountText.setText(Integer.toString(incr));
    }

    // modifies and updates the display amount text by an amount
    public void updateCount(int num) {
        String str = usersAmountText.getText();
        int incr = Integer.parseInt(str) + num;
        usersAmountText.setText(Integer.toString(incr));
    }

    public Button getRefreshButton() {
        return refreshButton;
    }

    public void addAllUsersToPane() {



    }

    public void clearUsers() {
        usersVBox.getChildren().clear();
    }

    public void addUserToPane(String username, String usertype) {

        updateCount();

        // set attributes for the header label
//        Label sellerLabel = new Label(Integer.toString(id));
        Label usernameLabel = new Label(username);
        usernameLabel.setPrefWidth(500);
        usernameLabel.setWrapText(true);
        usernameLabel.setFont(Font.font(20));
        usernameLabel.setAlignment(Pos.BASELINE_LEFT);
        usernameLabel.setPadding(new Insets(5));

        VBox sellerVBox = new VBox(usernameLabel);
        sellerVBox.setAlignment(Pos.CENTER);

        // set attributes for the header label
        Label buyerLabel = new Label(usertype);
        buyerLabel.setPrefWidth(200);
        buyerLabel.setWrapText(true);
        buyerLabel.setFont(Font.font(20));
        buyerLabel.setAlignment(Pos.CENTER);
        buyerLabel.setPadding(new Insets(5));

        VBox buyerVBox = new VBox(buyerLabel);
        sellerVBox.setAlignment(Pos.CENTER);

        HBox transactionHBox = new HBox(sellerVBox,buyerVBox);

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(700);
        lineSeparator.setHeight(1);
        lineSeparator.setFill(Color.BLACK);

        usersVBox.getChildren().add(transactionHBox);
        usersVBox.getChildren().add(lineSeparator);

    }

}
