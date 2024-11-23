import com.mysql.cj.jdbc.exceptions.MySQLStatementCancelledException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.xml.crypto.Data;
import java.text.DecimalFormat;
import java.util.List;

public class Buyer_CartControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button backButton;
    private Button checkoutButton;
    private List<Book> cart;
    private Buyer_CartPane pane;

    public Buyer_CartControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_CartPane(user, width, height);

        backButton = pane.getBackButton();
        backButton.setOnAction(new ButtonHandler());

        checkoutButton = pane.getCheckOutButton();
        checkoutButton.setOnAction(new ButtonHandler());

        if(pane.getBookList().isEmpty()) {
            pane.displayNoBooksFound();
        } else {
            pane.displayCart();
        }

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            if (a.getSource() == backButton) {

                // go back to the shop
                Buyer_ShopControl buyerShop = new Buyer_ShopControl(user, width, height);
                Main.mainWindow.setScene(new Scene(buyerShop));

            } else if (a.getSource() == checkoutButton) {
                // gets the user's payment info
                PaymentInfo paymentInfo = DataBase.getPaymentInfo(user.getUserID());


                // base case payment info is needed
                if (paymentInfo == null) {
                    pane.displayNoPaymentMethodFound();
                    return;
                }

                pane.pushConfirm();
                // payment info verification - updates or confirms to proceed
                boolean isPaymentInfoCorrect = pane.verifyPaymentInfo(paymentInfo);

                if (isPaymentInfoCorrect) {

                    List<Book> cart = pane.getBookList();
                    for (Book book : cart) {
                        int sellerID = DataBase.getSellerID(book.getID());
                        DataBase.insertTransaction(user.getUserID(), sellerID, book.getID(),   pane.computeTotal());
                        DataBase.removeFromCart(user.getUserID(), book.getID());
                        DataBase.markBookAsSold(book.getID());
                    }
                    pane.displayTransactionSuccess();
                } else {
                    // Stay on cart page if payment info is incorrect
                    pane.cancelConfirm();
                }


            }

        }

    }

}
