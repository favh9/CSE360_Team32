import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Seller_PostBookControl extends Pane {

    public double width;
    public double height;
    private final Seller_PostBookPane pane;
    private User user;

    public Seller_PostBookControl(User user, double width, double height) {

        this.width = width;
        this.height = height;
        this.user = user;

        pane = new Seller_PostBookPane(user,width,height);
        Button listmybookButton = pane.getListmybookButton();
        listmybookButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            if(pane.emptyFields()) {

                pane.displayEmptyFields();

            } else if (!(pane.isPriceValid())) {

                pane.displayInvalidPrice();

            } else {

                pane.computeGeneratedPrice();

                if(pane.confirmPost()) {

                    pane.displayBookPosted();

                }

            }
        }
    }
}
