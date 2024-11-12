import javafx.scene.layout.Pane;

public class Buyer_PurchaseHistoryControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Buyer_PurchaseHistoryPane pane;

    public Buyer_PurchaseHistoryControl(User user, double width, double height) {

        pane = new Buyer_PurchaseHistoryPane(user,width,height);

        this.getChildren().addAll(pane);
    }

}
