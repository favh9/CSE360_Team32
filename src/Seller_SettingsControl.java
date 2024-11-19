import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class Seller_SettingsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Seller_SettingsPane pane;
    private Button changePasswordButton;
    private Button addPaymentInfoButton;

    public Seller_SettingsControl(User user, double width, double height) {

        pane = new Seller_SettingsPane(user,width, height);

        changePasswordButton = pane.getChangePasswordButton();
        changePasswordButton.setOnAction(new ButtonHandler());

        addPaymentInfoButton = pane.getAddPaymentInfoButton();
        addPaymentInfoButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            if(a.getSource() == changePasswordButton) {



            } else if(a.getSource() == addPaymentInfoButton) {

                Seller_PaymentInfoControl payment = new Seller_PaymentInfoControl(user, width, height);
                Main.mainWindow.setScene(new Scene(payment));

            }

        }
    }
}
