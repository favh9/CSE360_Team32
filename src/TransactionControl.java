import javafx.scene.layout.Pane;

public class TransactionControl extends Pane {

    private User user;
    private double width;
    private double height;
    private TransactionPane pane;

    public TransactionControl(User user, double width, double height) {

        pane = new TransactionPane(user,width,height);

        this.getChildren().addAll(pane);
    }

}
