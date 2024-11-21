import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class Buyer_SettingsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Buyer_SettingsPane pane;
    private Button changePasswordButton;
    private Button addPaymentInfoButton;

    public Buyer_SettingsControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_SettingsPane(user,width, height);

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

                Seller_ChangePasswordControl forgotpassword = new Seller_ChangePasswordControl(user,width,height);
                Main.mainWindow.setScene(new Scene(forgotpassword));

            } else if(a.getSource() == addPaymentInfoButton) {

                Buyer_PaymentInfoControl payment = new Buyer_PaymentInfoControl(user, width, height);
                Main.mainWindow.setScene(new Scene(payment));

            }

        }
    }
}
