import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavigationBuyerControl extends VBox {

    public double width;
    public double height;

    private User user;
    private final Button profileButton;
    private final NavButton sellButton;
    private final NavButton booksButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public NavigationBuyerControl(User user, double width, double height) {

        this.width = width;
        this.height = height;
        this.user = user;
        NavigationSellerPane pane = new NavigationSellerPane(user,height);

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
                ProfileControl profile = new ProfileControl(user, width, height);
                Main.mainWindow.setScene(new Scene(profile));

            }

            if(a.getSource() == sellButton) {
                SellerControl seller = new SellerControl(user, width, height);
                Main.mainWindow.setScene(new Scene(seller));

            }

            if(a.getSource() == booksButton) {
                MyBooksControl mybooks = new MyBooksControl(user,width, height);
                Main.mainWindow.setScene(new Scene(mybooks));
            }

            if(a.getSource() == settButton) {
                SettingsUserControl sett = new SettingsUserControl(user, width, height);
                Main.mainWindow.setScene(new Scene(sett));
            }


            if(a.getSource() == logoutButton) {
                LoginControl login = new LoginControl(width, height);
                Main.mainWindow.setScene(new Scene(login));

            }
        }
    }
}
