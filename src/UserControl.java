
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UserControl extends Pane {

    private final VBox userlistVBox;
    private final Button adduserButton;

    public UserControl(double width, double height) {

        UsersPane pane = new UsersPane(width, height);

        userlistVBox = pane.getUserListVBox();
        adduserButton = pane.getAdduserButton();
        adduserButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    public void addUserToList(String username, String balance, String type) {

        Text usernameText = new Text(username);
        usernameText.setFont(Font.font(20));

        Text balanceText = new Text(balance);
        balanceText.setFont(Font.font(20));

        Text typeText = new Text(type);
        typeText.setFont(Font.font(20));

        HBox userHBox = new HBox(usernameText,balanceText,typeText);

        userlistVBox.getChildren().add(userHBox);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent a) {

            if(a.getSource() == adduserButton) {

                addUserToList("sparky","0","0");
            }
        }
    }


}
