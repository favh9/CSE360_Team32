
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class UserControl extends Pane {

    public UserControl(double width, double height) {

        UsersPane pane = new UsersPane(width, height);

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent a) {

        }
    }


}
