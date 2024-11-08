import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StatisticsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private StatisticsPane pane;

    private final Button refreshButton;

    public StatisticsControl(User user, double width,double height) {

        pane = new StatisticsPane(user,width,height);

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
