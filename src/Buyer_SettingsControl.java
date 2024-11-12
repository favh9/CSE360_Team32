import javafx.scene.layout.Pane;

public class Buyer_SettingsControl extends Pane {

    public Buyer_SettingsControl(User user, double width, double height) {

        Buyer_SettingsPane pane = new Buyer_SettingsPane(user,width, height);
        this.getChildren().addAll(pane);
    }
}
