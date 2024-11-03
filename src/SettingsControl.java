import javafx.scene.layout.Pane;

public class SettingsControl extends Pane {

    public SettingsControl(double width, double height) {

        SettingsPane pane = new SettingsPane(width, height);
        this.getChildren().addAll(pane);
    }
}
