import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Buyer_ShopPane extends BorderPane {

    public Buyer_ShopPane(User user, double width, double height) {

        Buyer_NavigationControl navBarVBox = new Buyer_NavigationControl(user,width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Shop");
        titleLabel.setFont(Font.font(42));

        // set attributes for the header of the main page
        HBox headerHBox = new HBox(titleLabel);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for instruction label
        Label instructionLabel1 = new Label("Browse books by category and condition.");

        // set attributes for category label
        Label categoryLabel = new Label("Category");

        // set attributes for category check boxes
        CheckBox ch1 = new CheckBox("Natural Science");
        CheckBox ch2 = new CheckBox("Computer Science");
        CheckBox ch3 = new CheckBox("Math");
        CheckBox ch4 = new CheckBox("English");
        CheckBox ch5 = new CheckBox("Other");

        // set attributes for the VBox that holds these items
        VBox vbox1 = new VBox(categoryLabel,ch1,ch2,ch3,ch4,ch5);

        // set attributes for the condition label
        Label conditionLabel = new Label("Condition");

        // set attributes for condition check boxes
        CheckBox ch6 = new CheckBox("Used Like New");
        CheckBox ch7 = new CheckBox("Moderately Used");
        CheckBox ch8 = new CheckBox("Heavily Used");

        // set attributes for the VBox that holds these items
        VBox vbox2 = new VBox(conditionLabel,ch6,ch7,ch8);

        // set atributes for the VBox that holds all Left side items
        VBox leftVBox = new VBox(vbox1,vbox2);

        // set attributes for the title of the scrollpane
        Label spTitle = new Label("Available Books");

        // set attributes for the container inside the scrollpane
        GridPane gp = new GridPane();

        // set attributes for the scrollpane
        ScrollPane sp = new ScrollPane(gp);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.minViewportHeightProperty().set(10);
        sp.setPrefHeight(10);
        sp.setStyle("-fx-background: transparent;");

        // set attributes for the VBox that contains these items on the right
        VBox rightVBox = new VBox(spTitle,sp);

        // set attributes for the HBox where the right side displays the list of books
        HBox hbox = new HBox(leftVBox,rightVBox);

        // set attributes for the submain VBox
        VBox submainVBox = new VBox(instructionLabel1,hbox);
        submainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #FFC627;"
        );

        // set attributes for the main VBox
        VBox mainVBox = new VBox(headerHBox,submainVBox);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setPrefWidth(width - navBarVBox.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and the main VBox
        HBox navBarAndMainHBox = new HBox(navBarVBox,mainVBox);
        navBarAndMainHBox.setSpacing(20);

        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon

    }
}
