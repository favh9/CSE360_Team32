import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Admin_StatisticsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Admin_StatisticsPane pane;

    private final Button refreshButton;

    public Admin_StatisticsControl(User user, double width, double height) {

        pane = new Admin_StatisticsPane(user,width,height);

        refreshButton = pane.getRefreshButton();

        this.getChildren().addAll(pane);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent a) {

            if(a.getSource() == refreshButton) {
                // do something with refresh button
            }
        }
    }

}
