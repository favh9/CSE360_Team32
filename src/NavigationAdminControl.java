import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class NavigationAdminControl extends VBox {

    private User user;
    private double width;
    private double height;
    NavigationAdminPane pane;

    private final NavButton usersButton;
    private final NavButton statsButton;
    private final NavButton transButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public NavigationAdminControl(User user, double width, double height) {

        this.user = user;
        this.width = width;
        this.height = height;

        pane = new NavigationAdminPane(height);

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
                AdminControl users = new AdminControl(user, width, height);
                Main.mainWindow.setScene(new Scene(users));

            }

            if(a.getSource() == statsButton) {
                StatisticsControl stats = new StatisticsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(stats));

            }

            if(a.getSource() == transButton) {
                TransactionControl trans = new TransactionControl(user, width, height);
                Main.mainWindow.setScene(new Scene(trans));

            }

            if(a.getSource() == settButton) {
                SettingsAdminControl sett = new SettingsAdminControl(user,width, height);
                Main.mainWindow.setScene(new Scene(sett));
            }

            if(a.getSource() == logoutButton) {
                LoginControl login = new LoginControl(width, height);
                Main.mainWindow.setScene(new Scene(login));

            }
        }
    }
}
