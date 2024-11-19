import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Seller_PaymentInfoControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button backButton;
    private Button confirmButton;
    private Seller_PaymentInfoPane pane;

    public Seller_PaymentInfoControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Seller_PaymentInfoPane(user, width, height);

        backButton = pane.getBackButton();
        backButton.setOnAction(new ButtonHandler());

        confirmButton = pane.getConfirmButton();
        confirmButton.setOnAction(new ButtonHandler());

        this.getChildren().add(pane);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            if (a.getSource() == backButton) {

                Seller_SettingsControl settings = new Seller_SettingsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(settings));

            } else if (a.getSource() == confirmButton) {

                if(pane.emptyFields()) {
                    pane.displayEmptyFields();
                } else if(!pane.isCardValid()) {
                    pane.displayInValidCard();
                } else {
                    pane.displayPaymentUpdated();
                }


            }

        }
    }
}
