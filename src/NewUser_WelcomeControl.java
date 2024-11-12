import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class NewUser_WelcomeControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button continueButton;

    public NewUser_WelcomeControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        NewUser_WelcomePane pane = new NewUser_WelcomePane(user,width,height);

        continueButton = pane.getButton();
        continueButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            NewUser_SelectUserTypeControl selectUser = new NewUser_SelectUserTypeControl(user,width,height);
            Main.mainWindow.setScene(new Scene(selectUser));

        }
    }
}
