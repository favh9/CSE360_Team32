import java.text.NumberFormat;
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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Admin_UsersPane extends BorderPane {

    private Button refreshButton;
    private VBox usersVBox;
    private Text quantityText;

    public Admin_UsersPane(User user, double width, double height) {

        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);
        Font quantityFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 24);

        Admin_NavigationControl navBarVBox = new Admin_NavigationControl(user,width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Users");
        titleLabel.setFont(titleFont);

        // set attributes for the quantity label
        Label quantityLabel = new Label("amount");
        quantityLabel.setFont(quantityFont);

        // set attribute for the quantity label
        quantityText = new Text("0");
        quantityText.setFont(quantityFont);

        // set attributes for the HBox that holds the quantity and quantity label
        HBox quantityHBox = new HBox(quantityText, quantityLabel);
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

        // set attributes for the header of the body
        // set attributes for the header label
        Label headerLabel1 = new Label("Username");
        headerLabel1.setFont(Font.font(20));
        headerLabel1.setPrefWidth(390);
        headerLabel1.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the header label
        Label headerLabel3 = new Label("Type");
        headerLabel3.setFont(Font.font(20));
        headerLabel3.setPrefWidth(150);
        headerLabel3.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header of the body
        HBox bodyheaderHBox = new HBox(headerLabel1,headerLabel3);

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
        VBox headerAndScrollPane = new VBox(bodyheaderHBox,lineSeparator,sp);

        // set attributes for the main VBox which excludes the navigation bar
        VBox mainVBox = new VBox(headerPane,headerAndScrollPane);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setSpacing(10);
        mainVBox.setPrefWidth(width - navBarVBox.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and page
        HBox navBarAndMainHBox = new HBox(navBarVBox,mainVBox);
        navBarAndMainHBox.setSpacing(20);

        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color

    }

    public void clearUsers() {
        usersVBox.getChildren().clear();
        quantityText.setText("0");
    }

    // returns a container where a user's information is displayed
    public void addUser(String username,String usertype) {

        // set attributes for the header label
        Label headerLabel1 = new Label(username);
        headerLabel1.setFont(Font.font(18));
        headerLabel1.setPrefWidth(390);
        headerLabel1.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the header label
        Label headerLabel3 = new Label(usertype);
        headerLabel3.setFont(Font.font(18));
        headerLabel3.setPrefWidth(150);
        headerLabel3.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header of the body
        HBox bodyheaderHBox = new HBox(headerLabel1,headerLabel3);

        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(690);
        lineSeparator.setHeight(1);
        lineSeparator.setFill(Color.GRAY);

        VBox vbox = new VBox(bodyheaderHBox,lineSeparator);

        usersVBox.getChildren().add(vbox);
        this.updateCount();
    }

    // modifies and updates the display amount text
    public void updateCount() {
        String str = quantityText.getText();
        int incr = Integer.parseInt(str) + 1;
        quantityText.setText(Integer.toString(incr));
    }

    public Button getRefreshButton() {
        return refreshButton;
    }

    public void setRefreshButton(Button refreshButton) {
        this.refreshButton = refreshButton;
    }

    public VBox getUsersVBox() {
        return usersVBox;
    }

    public void setUsersVBox(VBox usersVBox) {
        this.usersVBox = usersVBox;
    }

    public Text getQuantityText() {
        return quantityText;
    }

    public void setQuantityText(Text quantityText) {
        this.quantityText = quantityText;
    }

}
