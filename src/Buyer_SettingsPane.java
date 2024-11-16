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

public class Buyer_SettingsPane extends BorderPane {

    private Button changePasswordButton;
    private Button paymentInfoButton;

    public Buyer_SettingsPane(User user, double width, double height) {

        Buyer_NavigationControl navBarVBox = new Buyer_NavigationControl(user,width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Settings");
        titleLabel.setFont(Font.font(42));

        // set attributes for the header of the main page
        HBox headerHBox = new HBox(titleLabel);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);

        // buttons to show
        changePasswordButton = new Button("Change Password");
        paymentInfoButton = new Button("Add/Change Payment Info");
        changePasswordButton.setFont(Font.font(20));
        paymentInfoButton.setFont(Font.font(20));
        changePasswordButton.setStyle(
                "-fx-background-radius: 3em;"
        );
        paymentInfoButton.setStyle(
                "-fx-background-radius: 3em;"
        );
        changePasswordButton.setPrefSize(300,40);
        paymentInfoButton.setPrefSize(300,40);


        // store buttons in VBox
        VBox buttonVBox = new VBox(changePasswordButton, paymentInfoButton);
        buttonVBox.setSpacing(10);
        buttonVBox.setPadding(new Insets(100,0,0,0));
        buttonVBox.setAlignment(Pos.CENTER);

        // set attributes for the main VBox
        VBox mainVBox = new VBox(headerHBox, buttonVBox);
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
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color

    }
}
