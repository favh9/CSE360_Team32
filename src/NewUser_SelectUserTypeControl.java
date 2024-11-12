import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class NewUser_SelectUserTypeControl extends Pane {

    private User user;
    private double width;
    private double height;
    private NewUser_SelectUserTypePane pane;
    private Button buyButton;
    private Button sellButton;

    public NewUser_SelectUserTypeControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new NewUser_SelectUserTypePane(user, width, height);

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
                Buyer_ShopControl buyer = new Buyer_ShopControl(user,width,height);
                Main.mainWindow.setScene(new Scene(buyer));

            } else if (a.getSource().equals(sellButton)) {
                user.setUserType("seller");
                DataBase.updateUserType(user.getUsername(), "seller");
                Seller_PostBookControl seller = new Seller_PostBookControl(user,width,height);
                Main.mainWindow.setScene(new Scene(seller));
            }
        }
    }
}
