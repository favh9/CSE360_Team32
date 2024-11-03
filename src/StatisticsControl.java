import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StatisticsControl extends Pane {

    private final Button refreshButton;

    public StatisticsControl(double width,double height) {

        StatisticsPane pane = new StatisticsPane(width,height);
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
