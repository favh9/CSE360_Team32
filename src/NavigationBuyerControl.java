import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavigationBuyerControl extends VBox {

    public double width;
    public double height;

    private User user;
    private final Button profileButton;
    private final NavButton shopButton;
    private final NavButton purchaseHistoryButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public NavigationBuyerControl(User user, double width, double height) {

        this.width = width;
        this.height = height;
        this.user = user;
        NavigationBuyerPane pane = new NavigationBuyerPane(user,height);

        profileButton = pane.getProfileButton();
        profileButton.setOnAction(new ButtonHandler());

        shopButton = pane.getShopButton();
        shopButton.setOnAction(new ButtonHandler());

        purchaseHistoryButton = pane.getPurchaseHistoryButton();
        purchaseHistoryButton.setOnAction(new ButtonHandler());

        settButton = pane.getSettButton();
        settButton.setOnAction(new ButtonHandler());

        logoutButton = pane.getLogoutButton();
        logoutButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent a) {

            if(a.getSource() == profileButton) {
                BuyerProfileControl profile = new BuyerProfileControl(user, width, height);
                Main.mainWindow.setScene(new Scene(profile));

            }

            if(a.getSource() == shopButton) {
                BuyerControl buyer = new BuyerControl(user, width, height);
                Main.mainWindow.setScene(new Scene(buyer));

            }

            if(a.getSource() == purchaseHistoryButton) {
                PurchaseHistoryControl purhcaseHistory = new PurchaseHistoryControl(user,width, height);
                Main.mainWindow.setScene(new Scene(purhcaseHistory));
            }

            if(a.getSource() == settButton) {
                BuyerSettingsControl sett = new BuyerSettingsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(sett));
            }


            if(a.getSource() == logoutButton) {
                LoginControl login = new LoginControl(width, height);
                Main.mainWindow.setScene(new Scene(login));

            }
        }
    }
}
