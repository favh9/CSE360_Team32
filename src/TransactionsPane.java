import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TransactionsPane extends BorderPane {

    public TransactionsPane(double width, double height) {

        NavigationControl navBarVBox = new NavigationControl(width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Transactions");
        titleLabel.setFont(Font.font(42));

        // set attributes for the quantity label
        Label quantityLabel = new Label("Transaction");
        quantityLabel.setFont(Font.font(20));

        // set attribute for the display amount label
        Text displayAmountText = new Text("0");
        displayAmountText.setFont(Font.font(20));

        // set attributes for the HBox that holds the quantity and quantity label
        HBox quantityHBox = new HBox(displayAmountText, quantityLabel);
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

        // set attributes for the header of the page
        HBox headerHBox = new HBox(titleLabel,quantityHBox,deleteButton);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);
        headerHBox.setSpacing(40);
        HBox.setMargin(deleteButton,new Insets(0,0,20,0));

        // set attributes for the header of the body
        //
        // set attributes for the header label
        Label headerLabel1 = new Label("Transaction");
        headerLabel1.setFont(Font.font(20));
        headerLabel1.setPrefWidth(390);
        headerLabel1.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the header label
        Label headerLabel2 = new Label("Timestamp");
        headerLabel2.setFont(Font.font(20));
        headerLabel2.setPrefWidth(150);
        headerLabel2.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the header label
        Label headerLabel3 = new Label("Amount");
        headerLabel3.setFont(Font.font(20));
        headerLabel3.setPrefWidth(150);
        headerLabel3.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the header of the body
        HBox bodyHeaderHBox = new HBox(headerLabel1,headerLabel2,headerLabel3);

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(690);
        lineSeparator.setHeight(2);
        lineSeparator.setFill(Color.BLACK);

        // set attributes for the scrollpane
        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.minViewportHeightProperty().set(400);
        sp.setStyle("-fx-background-color:transparent;");

        // set attributes for the box that includes the header and scrollpane
        VBox headerAndScrollPane = new VBox(bodyHeaderHBox,lineSeparator,sp);

        // set attributes for the main VBox
        VBox mainVBox = new VBox(headerHBox,headerAndScrollPane);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setPrefWidth(width - navBarVBox.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and main VBox
        HBox navBarAndMainHBox = new HBox(navBarVBox,mainVBox);
        navBarAndMainHBox.setSpacing(20);

        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon

    }
}
