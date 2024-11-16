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

public class Seller_SettingsPane extends BorderPane {

    private Button changePasswordButton;
    private Button addPaymentInfoButton;

    public Seller_SettingsPane(User user, double width, double height) {

        Seller_NavigationControl navBarVBox = new Seller_NavigationControl(user,width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Settings");
        titleLabel.setFont(Font.font(42));

        // set attributes for the header of the main page
        HBox headerHBox = new HBox(titleLabel);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);

        // buttons to show
        changePasswordButton = new Button("Change Password");
        addPaymentInfoButton = new Button("Add Payment Info");
        changePasswordButton.setFont(Font.font(20));
        addPaymentInfoButton.setFont(Font.font(20));
        changePasswordButton.setStyle(
                "-fx-background-radius: 3em;"
        );
        addPaymentInfoButton.setStyle(
                "-fx-background-radius: 3em;"
        );
        changePasswordButton.setPrefSize(300,40);
        addPaymentInfoButton.setPrefSize(300,40);


        // store buttons in VBox
        VBox buttonVBox = new VBox(changePasswordButton, addPaymentInfoButton);
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

    public Button getChangePasswordButton() {
        return changePasswordButton;
    }

    public void setChangePasswordButton(Button changePasswordButton) {
        this.changePasswordButton = changePasswordButton;
    }

    public Button getAddPaymentInfoButton() {
        return addPaymentInfoButton;
    }

    public void setAddPaymentInfoButton(Button addPaymentInfoButton) {
        this.addPaymentInfoButton = addPaymentInfoButton;
    }
}
