import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// this class initiates the gui app, loading the login page as the initial view
public class Main extends Application {

    // testing junit
    public int sum(int n, int n2) {
        return n + n2;
    }

    public static Stage mainWindow;

    // these should be included in all pages to keep the window the same size
    public final double WIDTH = 960;
    public final double HEIGHT = 600;

    // images are not ours, we get them from the following links
    // static images to later use for the rest of the project
    public static Image cartIcon = new Image("https://static-00.iconduck.com/assets.00/cart-icon-250x256-xgkwtkpu.png");
    public static Image profileIcon = new Image("https://i.pinimg.com/originals/57/00/c0/5700c04197ee9a4372a35ef16eb78f4e.png");
    public static Image buyIcon = new Image("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQaDLfkvJ_2GkXDrtsbWa3Cl6h34L8J1zYFmT0EQEzZev_jp2e5");
    public static Image sellIcon = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH44eEh0ZTrAQWWz7GQ2e34lAnpPa4cHkM0foB-CcNbmsnvAPO");
    public static Image usersIcon = new Image("https://www.shareicon.net/data/128x128/2015/11/29/679698_add_512x512.png");
    public static Image addUserIcon = new Image("https://www.shareicon.net/data/512x512/2015/11/29/679705_add_512x512.png");
    public static Image delUserIcon = new Image("https://cdn-icons-png.flaticon.com/512/3334/3334328.png");
    public static Image statsIcon = new Image("https://icons.veryicon.com/png/o/education-technology/passenger-flow-analysis-icon-library/activity-assessment.png");
    public static Image transIcon = new Image("https://static.thenounproject.com/png/4655171-200.png");
    public static Image settingsIcon = new Image("https://cdn.icon-icons.com/icons2/2779/PNG/512/gear_settings_icon_176980.png");
    public static Image logOutIcon = new Image("https://cdn4.iconfinder.com/data/icons/navigation-40/24/exit-512.png");
    public static Image confirmIcon = new Image ("https://getdrawings.com/free-icon-bw/correct-icon-23.png");
    public static Image cancelIcon = new Image("https://img.icons8.com/?size=100&id=14297&format=png&color=000000");
    public static Image backIcon = new Image("https://cdn2.iconfinder.com/data/icons/simple-circular-icons-filled/78/Left_Arrow_Filled-512.png");
    public static Image searchIcon = new Image("https://cdn-icons-png.flaticon.com/512/61/61088.png");
    public static Image refreshIcon = new Image("https://static-00.iconduck.com/assets.00/gui-refresh-icon-2048x2048-xgbnerm5.png");
    public static Image asuIcon = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTbPLkDfxyWiu0Qho5hOJdgOkseNLcxiscVg&s");
    public static Image successIcon = new Image("https://static-00.iconduck.com/assets.00/checkmark-running-icon-2048x2048-8081bf4v.png");
    public static Image infoIcon = new Image("https://uxwing.com/wp-content/themes/uxwing/download/web-app-development/more-info-icon.png");
    public static Image booksIcon = new Image("https://cdn0.iconfinder.com/data/icons/carbon-mobile-browser-1/48/reading_list-512.png");

    public void start(Stage primaryStage) {

        // the initial page to be displayed
        // instance variables
        User_LoginControl mainPane = new User_LoginControl(WIDTH, HEIGHT);

        // create new scene and assign children, and window dimensions
        Scene scene1 = new Scene(mainPane);
        scene1.setFill(null); // gets rid of annoying flashes from switching scenes

        // create stage
        mainWindow = new Stage();
        
        // assign scene to stage
        mainWindow.setScene(scene1);

        // set resizable to false
        mainWindow.setResizable(false);

        // show default/primary stage 
        mainWindow.show();
        
    }

    public static void main(String[] args) {

        launch(args);
    }

}
