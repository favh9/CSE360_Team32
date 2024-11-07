import javafx.scene.layout.Pane;

public class SettingsAdminControl extends Pane {

    public SettingsAdminControl(double width, double height) {

        SettingsAdminPane pane = new SettingsAdminPane(width, height);
        this.getChildren().addAll(pane);
    }
}
