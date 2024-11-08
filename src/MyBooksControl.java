import javafx.scene.layout.Pane;

public class MyBooksControl extends Pane {

    private User user;
    private double width;
    private double height;

    public MyBooksControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        MyBooksPane pane = new MyBooksPane(user, width,height);

        this.getChildren().addAll(pane);
    }
}
