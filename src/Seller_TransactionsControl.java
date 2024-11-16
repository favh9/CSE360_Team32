import javafx.scene.layout.Pane;

public class Seller_TransactionsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Seller_TransactionsPane pane;

    public Seller_TransactionsControl(User user, double width, double height) {

        pane = new Seller_TransactionsPane(user,width,height);

        this.getChildren().addAll(pane);
    }

}

