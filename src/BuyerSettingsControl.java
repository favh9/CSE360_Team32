import javafx.scene.layout.Pane;

public class BuyerSettingsControl extends Pane {

    public BuyerSettingsControl(User user, double width, double height) {

        BuyerSettingsPane pane = new BuyerSettingsPane(user,width, height);
        this.getChildren().addAll(pane);
    }
}
