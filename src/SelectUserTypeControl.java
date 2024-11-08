import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javax.xml.crypto.Data;

public class SelectUserTypeControl extends Pane {

    private User user;
    private double width;
    private double height;
    private SelectUserTypePane pane;
    private Button buyButton;
    private Button sellButton;

    public SelectUserTypeControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new SelectUserTypePane(user, width, height);

        buyButton = pane.getBuyButton();
        buyButton.setOnAction(new ButtonHandler());

        sellButton = pane.getSellButton();
        sellButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            System.out.println("foo");

            if (a.getSource().equals(buyButton)) {
                user.setUserType("buyer");
                DataBase.updateUserType(user.getUsername(), "buyer");

            } else if (a.getSource().equals(sellButton)) {
                user.setUserType("seller");
                DataBase.updateUserType(user.getUsername(), "seller");
                SellerControl seller = new SellerControl(user,width,height);
                Main.mainWindow.setScene(new Scene(seller));
            }
        }
    }
}
