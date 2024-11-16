import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Buyer_ProfileControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Buyer_ProfilePane pane;

    public Buyer_ProfileControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Buyer_ProfilePane(user,width,height);

        this.getChildren().addAll(pane);

    }

}
