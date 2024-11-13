import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Buyer_NavigationControl extends VBox {

    public double width;
    public double height;

    private User user;
    private final Button profileButton;
    private final NavButton shopButton;
    private final NavButton purchaseHistoryButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public Buyer_NavigationControl(User user, double width, double height) {

        this.width = width;
        this.height = height;
        this.user = user;
        Buyer_NavigationPane pane = new Buyer_NavigationPane(user,height);

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
                Buyer_ProfileControl profile = new Buyer_ProfileControl(user, width, height);
                Main.mainWindow.setScene(new Scene(profile));

            }

            if(a.getSource() == shopButton) {
                Buyer_ShopControl buyer = new Buyer_ShopControl(user, width, height);
                Main.mainWindow.setScene(new Scene(buyer));

            }

            if(a.getSource() == purchaseHistoryButton) {
                Buyer_OrdersControl purhcaseHistory = new Buyer_OrdersControl(user,width, height);
                Main.mainWindow.setScene(new Scene(purhcaseHistory));
            }

            if(a.getSource() == settButton) {
                Buyer_SettingsControl sett = new Buyer_SettingsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(sett));
            }


            if(a.getSource() == logoutButton) {
                User_LoginControl login = new User_LoginControl(width, height);
                Main.mainWindow.setScene(new Scene(login));

            }
        }
    }
}
