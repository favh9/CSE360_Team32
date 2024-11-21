import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Buyer_ProfileControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button confirmButton;
    private Buyer_ProfilePane pane;

    public Buyer_ProfileControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_ProfilePane(user,width,height);

        confirmButton = pane.getConfirmButton();
        confirmButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            if(a.getSource().equals(confirmButton)) {

                if(pane.emptyFields()) {
                    pane.displayEmptyFields();
                } else { // update their information
                    pane.requestConfirmChanges();
                }

            }

        }
    }

}
