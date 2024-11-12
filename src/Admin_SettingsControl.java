import javafx.scene.layout.Pane;

public class Admin_SettingsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Admin_SettingsPane pane;

    public Admin_SettingsControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Admin_SettingsPane(user, width, height);

        this.getChildren().addAll(pane);
    }
}
