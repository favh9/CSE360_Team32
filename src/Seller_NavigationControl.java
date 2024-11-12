import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Seller_NavigationControl extends VBox {

    public double width;
    public double height;

    private User user;
    private final Button profileButton;
    private final NavButton sellButton;
    private final NavButton booksButton;
    private final NavButton settButton;
    private final NavButton logoutButton;

    public Seller_NavigationControl(User user, double width, double height) {

        this.width = width;
        this.height = height;
        this.user = user;
        Seller_NavigationPane pane = new Seller_NavigationPane(user,height);

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
                Seller_ProfileControl profile = new Seller_ProfileControl(user, width, height);
                Main.mainWindow.setScene(new Scene(profile));

            }

            if(a.getSource() == sellButton) {
                Seller_PostBookControl seller = new Seller_PostBookControl(user, width, height);
                Main.mainWindow.setScene(new Scene(seller));

            }

            if(a.getSource() == booksButton) {
                Seller_MyBooksControl mybooks = new Seller_MyBooksControl(user,width, height);
                Main.mainWindow.setScene(new Scene(mybooks));
            }

            if(a.getSource() == settButton) {
                Seller_SettingsControl sett = new Seller_SettingsControl(user, width, height);
                Main.mainWindow.setScene(new Scene(sett));
            }


            if(a.getSource() == logoutButton) {
                User_LoginControl login = new User_LoginControl(width, height);
                Main.mainWindow.setScene(new Scene(login));

            }
        }
    }
}
