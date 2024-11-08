import javafx.scene.layout.Pane;

public class SettingsAdminControl extends Pane {

    private User user;
    private double width;
    private double height;
    private SettingsAdminPane pane;

    public SettingsAdminControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new SettingsAdminPane(user, width, height);

        this.getChildren().addAll(pane);
    }
}
