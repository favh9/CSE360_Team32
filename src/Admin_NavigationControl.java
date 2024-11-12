import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class Admin_NavigationControl extends VBox {

    private User user;
    private double width;
    private double height;
    Admin_NavigationPane pane;

    private final NavButton usersButton;
    private final NavButton statsButton;
    private final NavButton transButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public Admin_NavigationControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new Admin_NavigationPane(height);

        usersButton = pane.getUsersButton();
        usersButton.setOnAction(new ButtonHandler());

        statsButton = pane.getStatsButton();
        statsButton.setOnAction(new ButtonHandler());

        transButton = pane.getTransButton();
        transButton.setOnAction(new ButtonHandler());

        settButton = pane.getSettButton();
        settButton.setOnAction(new ButtonHandler());

        logoutButton = pane.getLogoutButton();
        logoutButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent a) {

            if(a.getSource() == usersButton) {
                Admin_UsersControl users = new Admin_UsersControl(user, width, height);
                Main.mainWindow.setScene(new Scene(users));

            }

            if(a.getSource() == statsButton) {
                Admin_StatisticsControl stats = new Admin_StatisticsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(stats));

            }

            if(a.getSource() == transButton) {
                Admin_TransactionsControl trans = new Admin_TransactionsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(trans));

            }

            if(a.getSource() == settButton) {
                Admin_SettingsControl sett = new Admin_SettingsControl(user,width, height);
                Main.mainWindow.setScene(new Scene(sett));
            }

            if(a.getSource() == logoutButton) {
                User_LoginControl login = new User_LoginControl(width, height);
                Main.mainWindow.setScene(new Scene(login));

            }
        }
    }
}
