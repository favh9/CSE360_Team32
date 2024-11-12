import javafx.scene.layout.Pane;

public class Buyer_ShopControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Buyer_ShopPane pane;

    public Buyer_ShopControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_ShopPane(user,width,height);

        this.getChildren().addAll(pane);

    }
}
