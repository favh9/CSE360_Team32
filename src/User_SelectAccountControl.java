import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * User_SelectAccountControl handles the logic for the User_SelectAccountPane.
 * It reacts to button actions (Buy or Sell) and switches the scene based on the user type.
 */
public class User_SelectAccountControl extends Pane {

    private User user;             // The user object representing the current user
    private double width;          // The width of the pane
    private double height;         // The height of the pane
    private User_SelectAccountPane pane; // The pane displaying the user interface
    private Button buyButton;      // The button for the "Buy" action
    private Button sellButton;     // The button for the "Sell" action

    /**
     * Constructor to initialize the User_SelectAccountControl.
     * Sets up the layout and buttons, and associates event handlers with the buttons.
     *
     * @param user The user object associated with the current user.
     * @param width The width of the control pane.
     * @param height The height of the control pane.
     */
    public User_SelectAccountControl(User user, double width, double height) {
        this.user = user;
        this.width = width;
        this.height = height;

        // Initialize the pane (UI layout) that allows the user to select between "Buy" or "Sell"
        pane = new User_SelectAccountPane(user, width, height);

        // Get buttons from the pane
        buyButton = pane.getBuyButton();
        sellButton = pane.getSellButton();

        // Set the action handlers for the buttons
        buyButton.setOnAction(new ButtonHandler());
        sellButton.setOnAction(new ButtonHandler());

        // Add the pane to the control's children, so it can be displayed
        this.getChildren().addAll(pane);
    }

    /**
     * ButtonHandler class listens for the Buy and Sell button actions.
     * It performs actions based on whether the user is new or returning and navigates to the appropriate scene.
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {

        /**
         * Handles the button click events for both the "Buy" and "Sell" buttons.
         * Depending on the user type, the appropriate scene is displayed.
         *
         * @param a The ActionEvent triggered by the button click.
         */
        @Override
        public void handle(ActionEvent a) {
            // Handle Buy button click
            if (a.getSource().equals(buyButton)) {
                // If the user is new, update their user type and navigate to the Buyer_ShopControl
                if (user.getUserType().equals("new_user")) {
                    user.setUserType("returning_user");
                    DataBase.updateUserType(user.getUsername(), "returning_user");
                    Buyer_ShopControl buyer = new Buyer_ShopControl(user, width, height);
                    Main.mainWindow.setScene(new Scene(buyer)); // Set the scene for buying
                } else {
                    // If the user is returning, directly navigate to the Buyer_ShopControl
                    Buyer_ShopControl buyer = new Buyer_ShopControl(user, width, height);
                    Main.mainWindow.setScene(new Scene(buyer)); // Set the scene for buying
                }

                // Handle Sell button click
            } else if (a.getSource().equals(sellButton)) {
                // If the user is new, update their user type and navigate to the Seller_PostBookControl
                if (user.getUserType().equals("new_user")) {
                    user.setUserType("returning_user");
                    DataBase.updateUserType(user.getUsername(), "returning_user");
                    Seller_PostBookControl seller = new Seller_PostBookControl(user, width, height);
                    Main.mainWindow.setScene(new Scene(seller)); // Set the scene for selling
                } else {
                    // If the user is returning, directly navigate to the Seller_PostBookControl
                    Seller_PostBookControl seller = new Seller_PostBookControl(user, width, height);
                    Main.mainWindow.setScene(new Scene(seller)); // Set the scene for selling
                }
            }
        }
    }
}
