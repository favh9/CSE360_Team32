import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class NewUserControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button continueButton;

    public NewUserControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        NewUserPane pane = new NewUserPane(user,width,height);

        continueButton = pane.getButton();
        continueButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            SelectUserTypeControl selectUser = new SelectUserTypeControl(user,width,height);
            Main.mainWindow.setScene(new Scene(selectUser));

        }
    }
}
