import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Admin_SettingsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Button changePasswordButton;
    private Button adjustpriceButton;
    private Admin_SettingsPane pane;

    public Admin_SettingsControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Admin_SettingsPane(user, width, height);

        changePasswordButton = pane.getChangePasswordButton();
        changePasswordButton.setOnAction(new ButtonHandler());

        adjustpriceButton = pane.getAdjustpriceButton();
        adjustpriceButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent a) {


            if(a.getSource().equals(changePasswordButton)) {

                Admin_ChangePasswordControl changePassword = new Admin_ChangePasswordControl(user,width,height);
                Main.mainWindow.setScene(new Scene(changePassword));

            } else if(a.getSource().equals(adjustpriceButton)) {

                Admin_AdjustPriceControl adjustPriceControl = new Admin_AdjustPriceControl(user,width,height);
                Main.mainWindow.setScene(new Scene(adjustPriceControl));

            }
        }
    }
}
