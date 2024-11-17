import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;

public class Buyer_CartControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button backButton;

    public Buyer_CartControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        Buyer_CartPane pane = new Buyer_CartPane(user, width, height);

        backButton = pane.getBackButton();
        backButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            if(a.getSource() == backButton) {

                Buyer_ShopControl buyer_shop = new Buyer_ShopControl(user,width,height);
                Main.mainWindow.setScene(new Scene(buyer_shop));
            }

        }

    }

}
