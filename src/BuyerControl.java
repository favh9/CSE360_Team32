import javafx.scene.layout.Pane;

public class BuyerControl extends Pane {

    private User user;
    private double width;
    private double height;
    private BuyerPane pane;

    public BuyerControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new BuyerPane(user,width,height);

        this.getChildren().addAll(pane);

    }
}
