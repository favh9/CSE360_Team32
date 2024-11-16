import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Seller_ProfileControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button button;
    private Seller_ProfilePane pane;

    public Seller_ProfileControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Seller_ProfilePane(user,width,height);

//        button = pane.getButton();

        this.getChildren().addAll(pane);

    }

}
