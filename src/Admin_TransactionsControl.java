import javafx.scene.layout.Pane;

public class Admin_TransactionsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Admin_TransactionsPane pane;

    public Admin_TransactionsControl(User user, double width, double height) {

        pane = new Admin_TransactionsPane(user,width,height);

        this.getChildren().addAll(pane);
    }

}
