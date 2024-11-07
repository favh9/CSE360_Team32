import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class NavigationUserControl extends VBox {

    public double width;
    public double height;

    private final NavButton profileButton;
    private final NavButton sellButton;
    private final NavButton booksButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public NavigationUserControl(double width, double height) {

        this.width = width;
        this.height = height;

        NavigationUserPane pane = new NavigationUserPane(height);

        profileButton = pane.getProfileButton();
        profileButton.setOnAction(new ButtonHandler());

        sellButton = pane.getSellButton();
        sellButton.setOnAction(new ButtonHandler());

        booksButton = pane.getBooksButton();
        booksButton.setOnAction(new ButtonHandler());

        settButton = pane.getSettButton();
        settButton.setOnAction(new ButtonHandler());

        logoutButton = pane.getLogoutButton();
        logoutButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent a) {

            if(a.getSource() == profileButton) {
//                UserControl users = new UserControl(width, height);
//                Main.mainWindow.setScene(new Scene(users));

            }

            if(a.getSource() == sellButton) {
//                StatisticsControl stats = new StatisticsControl(width, height);
//                Main.mainWindow.setScene(new Scene(stats));

            }

            if(a.getSource() == settButton) {
//                SettingsAdminControl sett = new SettingsAdminControl(width, height);
//                Main.mainWindow.setScene(new Scene(sett));
            }

            if(a.getSource() == booksButton) {
//                SettingsAdminControl sett = new SettingsAdminControl(width, height);
//                Main.mainWindow.setScene(new Scene(sett));
            }

            if(a.getSource() == logoutButton) {
                LoginControl login = new LoginControl(width, height);
                Main.mainWindow.setScene(new Scene(login));

            }
        }
    }
}
