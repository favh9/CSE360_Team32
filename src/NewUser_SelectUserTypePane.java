import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class NewUser_SelectUserTypePane extends BorderPane {

    private final Button b1;
    private final Button b2;

    public NewUser_SelectUserTypePane(User user, double width, double height) {

        NewUser_NavigationControl navBarVBox = new NewUser_NavigationControl(user,width,height);

        // load custom font for title
        Font arima = Font.loadFont(getClass().getResourceAsStream("fonts/Arima-Bold.ttf"), 65);

        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 65);
        Font bodyFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20);

        // set attributes for the title label
        Label titleLabel = new Label("Select Account Type");
        titleLabel.setFont(titleFont);
        titleLabel.setPadding(new Insets(0,0,0,0));
        titleLabel.setTextFill(Color.BLACK);

        // set attributes for image width
        double imageWidth = 280;

        // set attributes for image height
        double imageHeight = 300;

        // set attributes for the header of the main page
        HBox headerHBox = new HBox(titleLabel);
        headerHBox.setAlignment(Pos.CENTER);

        // set attributes for the imageview
        ImageView imageView1 = new ImageView(Main.buyIcon);
        imageView1.setFitWidth(imageWidth);
        imageView1.setFitHeight(imageHeight);

        // set attributes for the button
        b1 = new Button();
        b1.setGraphic(imageView1);
        b1.setBackground(Background.fill(Color.TRANSPARENT));

        // set attributes for the text
        Text t1 = new Text("Buy");
        t1.setFont(arima);

        // set attributes for the VBox
        VBox vbox1 = new VBox(b1,t1);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setSpacing(-15);

        // set attributes for the imageview
        ImageView imageView2 = new ImageView(Main.sellIcon);
        imageView2.setFitWidth(imageWidth);
        imageView2.setFitHeight(imageHeight);

        // set attributes for the button
        b2 = new Button();
        b2.setGraphic(imageView2);
        b2.setBackground(Background.fill(Color.TRANSPARENT));

        // set attributes for the text
        Text t2 = new Text("Sell");
        t2.setFont(arima);

        // set attributes for the VBox
        VBox vbox2 = new VBox(b2,t2);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(-15);

        // set attributes for the bodyHBox
        HBox bodyHBox = new HBox(vbox1,vbox2);
        bodyHBox.setSpacing(100);
        bodyHBox.setPadding(new Insets(0,20,20,20));

        // set attributes for the main VBox
        VBox mainVBox = new VBox(headerHBox,bodyHBox);
        mainVBox.setSpacing(20);
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

    public Button getBuyButton() {
        return b1;
    }

    public Button getSellButton() {
        return b2;
    }
}
