import javafx.scene.layout.Pane;

public class Seller_MyBooksControl extends Pane {

    private User user;
    private double width;
    private double height;

    public Seller_MyBooksControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        Seller_MyBooksPane pane = new Seller_MyBooksPane(user, width,height);

        this.getChildren().addAll(pane);
    }
}
