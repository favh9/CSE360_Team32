import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Seller_TransactionsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Seller_TransactionsPane pane;
    private Button refreshButton;

    public Seller_TransactionsControl(User user, double width, double height) {

        pane = new Seller_TransactionsPane(user,width,height);

        refreshButton = pane.getRefreshButton();
        refreshButton.setOnAction(new ButtonHandler());

        pane.addAllTransactionsToPane();

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {

            if(a.getSource() == refreshButton) {

            }
        }
    }

}

