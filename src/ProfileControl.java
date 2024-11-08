import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ProfileControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button button;
    private ProfilePane pane;

    public ProfileControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new ProfilePane(user,width,height);

        button = pane.getButton();

        this.getChildren().addAll(pane);

    }

}
