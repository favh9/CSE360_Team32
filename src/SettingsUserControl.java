import javafx.scene.layout.Pane;

public class SettingsUserControl extends Pane {

    public SettingsUserControl(User user, double width, double height) {

        SettingsUserPane pane = new SettingsUserPane(user,width, height);
        this.getChildren().addAll(pane);
    }
}
