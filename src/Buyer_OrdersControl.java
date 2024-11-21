import javafx.scene.layout.Pane;

public class Buyer_OrdersControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Buyer_OrdersPane pane;

    public Buyer_OrdersControl(User user, double width, double height) {

        pane = new Buyer_OrdersPane(user,width,height);

        pane.addAllOrdersToPane();

        this.getChildren().addAll(pane);
    }

}
