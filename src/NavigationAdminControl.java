import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class NavigationAdminControl extends VBox {

    public double width;
    public double height;

    private final NavButton usersButton;
    private final NavButton statsButton;
    private final NavButton transButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public NavigationAdminControl(double width, double height) {

        this.width = width;
        this.height = height;

        NavigationAdminPane pane = new NavigationAdminPane(height);

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
                UserControl users = new UserControl(width, height);
                Main.mainWindow.setScene(new Scene(users));

            }

            if(a.getSource() == statsButton) {
                StatisticsControl stats = new StatisticsControl(width, height);
                Main.mainWindow.setScene(new Scene(stats));

            }

            if(a.getSource() == transButton) {
                TransactionControl trans = new TransactionControl(width, height);
                Main.mainWindow.setScene(new Scene(trans));

            }

            if(a.getSource() == settButton) {
                SettingsAdminControl sett = new SettingsAdminControl(width, height);
                Main.mainWindow.setScene(new Scene(sett));
            }

            if(a.getSource() == logoutButton) {
                LoginControl login = new LoginControl(width, height);
                Main.mainWindow.setScene(new Scene(login));

            }
        }
    }
}
