import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Admin_AdjustPriceControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button backButton;
    private Button confirmButton;
    private Admin_AdjustPricePane pane;

    /**
     * Constructor to initialize the payment information control.
     * This pane allows us to control input payment details such as card number, expiration date, and CVC.
     * It also sets the size and layout of the pane based on the provided parameters.
     *
     * @param user   The user whose payment information will be displayed in the pane. This may include their details.
     * @param width  The width of the pane. This defines the horizontal size of the pane.
     * @param height The height of the pane. This defines the vertical size of the pane.
     */
    public Admin_AdjustPriceControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Admin_AdjustPricePane(user, width, height);

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
                // Navigate back to the Seller Settings page
                Admin_SettingsControl settings = new Admin_SettingsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(settings));

            } else if (a.getSource() == confirmButton) {

                if (pane.emptyFields()) {
                    // Display a warning if any fields are empty
                    pane.displayEmptyFields();

                } else {
                    DataBase.setMarkdown(1, Admin_AdjustPricePane.getUsedLikeNewFee() / 100);
                    DataBase.setMarkdown(2, Admin_AdjustPricePane.getModeratelyUsedFee() / 100);
                    DataBase.setMarkdown(3, Admin_AdjustPricePane.getHeavilyUsedFee() / 100);
                    pane.displayAdjustmentPricesUpdated();
                }
            }
        }
    }
}
