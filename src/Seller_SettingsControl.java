import javafx.scene.layout.Pane;

public class Seller_SettingsControl extends Pane {

    public Seller_SettingsControl(User user, double width, double height) {

        Seller_SettingsPane pane = new Seller_SettingsPane(user,width, height);
        this.getChildren().addAll(pane);
    }
}
