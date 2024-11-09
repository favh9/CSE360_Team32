import javafx.scene.layout.Pane;

public class PurchaseHistoryControl extends Pane {

    private User user;
    private double width;
    private double height;
    private PurchaseHistoryPane pane;

    public PurchaseHistoryControl(User user, double width, double height) {

        pane = new PurchaseHistoryPane(user,width,height);

        this.getChildren().addAll(pane);
    }

}
