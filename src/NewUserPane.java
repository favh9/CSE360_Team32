import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NewUserPane extends BorderPane {

    private Button b1;

    public NewUserPane(User user, double width, double height) {

        NavigationUserControl navBarVBox = new NavigationUserControl(user,width,height);

        // load custom font for title
        Font arima = Font.loadFont(getClass().getResourceAsStream("fonts/Arima-Bold.ttf"), 65);

        Font bodyFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20);

        // set attributes for the title label
        Label titleLabel = new Label("ASU Bookstore");
        titleLabel.setFont(arima);
        titleLabel.setPadding(new Insets(0,0,0,0));
        titleLabel.setTextFill(Color.BLACK);

        // set attributes for the header of the main page
        HBox headerHBox = new HBox(titleLabel);
        headerHBox.setAlignment(Pos.CENTER);

        // set attributes for the body of the main page
        Text t1 = new Text();
        t1.setText("Hello, " + user.getFirstName() + " " + user.getLastName() + ".\n");
        Text t2 = new Text();
        t2.setText("""
                Here you can choose to either buy or sell. Once you've chosen the account
                you'd like, please utilize the navigation bar on the left of this screen
                for your convenience. Please click on continue to select your account type.

                Forks Up!""");
        t1.setFont(bodyFont);
        t2.setFont(bodyFont);

        // set attributes for the bodyVBox
        VBox bodyVBox = new VBox(t1,t2);
        bodyVBox.setPadding(new Insets(40,20,40,20));

        // set attributes for the button
        b1 = new Button("Continue");
        b1.setPrefSize(200,30);
        b1.setFont(bodyFont);

        // set attributes for the HBox
        HBox buttonHBox = new HBox(b1);
        buttonHBox.setAlignment(Pos.CENTER);

        // set attributes for the main VBox
        VBox mainVBox = new VBox(headerHBox,bodyVBox, buttonHBox);
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

    public Button getButton() {
        return b1;
    }
}
