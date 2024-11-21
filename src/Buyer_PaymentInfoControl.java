import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Buyer_PaymentInfoControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button backButton;
    private Button confirmButton;
    private Buyer_PaymentInfoPane pane;

    /**
     * Constructor to initialize the payment information control for buyers.
     * This pane allows us to control input payment details such as card number, expiration date, and CVC.
     * It also sets the size and layout of the pane based on the provided parameters.
     *
     * @param user   The user whose payment information will be displayed in the pane.
     * @param width  The width of the pane.
     * @param height The height of the pane.
     */
    public Buyer_PaymentInfoControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_PaymentInfoPane(user, width, height);

        // Load payment info for the user
        PaymentInfo paymentInfo = DataBase.getPaymentInfo(user.getUserID());
        if (paymentInfo != null) {
            pane.setNameOnCardField(paymentInfo.getNameOnCard());
            pane.setCardNumberField(paymentInfo.getCardNumber());
            pane.setExpDateField(paymentInfo.getExpirationDate());
            pane.setCvcField(paymentInfo.getCvc());
        }

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
                // Navigate back to the Buyer Settings page
                Buyer_SettingsControl settings = new Buyer_SettingsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(settings));

            } else if (a.getSource() == confirmButton) {

                if (pane.emptyFields()) {
                    // Display a warning if any fields are empty
                    pane.displayEmptyFields();

                } else if (!pane.isCardValid()) {
                    // Display a warning if the card details are invalid
                    pane.displayInValidCard();

                } else {
                    // Attempt to save the payment info to the database
                    boolean success = DataBase.savePaymentInfo(
                            user.getUserID(),
                            pane.getNameOnCardField().getText(),
                            pane.getCardNumberField().getText(),
                            pane.getExpDateField().getText(),
                            pane.getCvcField().getText()
                    );

                    if (success) {
                        pane.displayPaymentUpdated();
                    } else {
                        // Show failure alert if saving to the database failed
                        pane.displayInValidCard();
                    }
                }
            }
        }
    }
}
