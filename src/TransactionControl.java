import javafx.scene.layout.Pane;

public class TransactionControl extends Pane {

    public TransactionControl(double width, double height) {

        TransactionsPane pane = new TransactionsPane(width,height);
        this.getChildren().addAll(pane);
    }

}
