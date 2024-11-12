import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Seller_PostBookControl extends Pane {

    public double width;
    public double height;
    private Seller_PostBookPane pane;
    private User user;
    private String bookName;
    private String author;
    private int selectedYear;
    private String selectedCategory;
    private String selectedCondition;
    private double price;
    private double generatedPrice;
    private Button listBookButton;

    public Seller_PostBookControl(User user, double width, double height) {

        this.width = width;
        this.height = height;
        this.user = user;

        pane = new Seller_PostBookPane(user,width,height);

        listBookButton = pane.getListBookButton();
        listBookButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            if(emptyFields()) {
                System.out.println(user.getFirstName());
                displayEmptyFields();
            } else {

            }
        }

        public boolean emptyFields() {

            if(pane.getBookName().isEmpty()) {
                return true;
            } else if (pane.getAuthorName().isEmpty() ) {
                return true;
            } else if (pane.getSelectedCategory().compareTo("Select Category") == 0) {
                return true;
            } else if (pane.getSelectedYear() == -1) {
                return true;
            } else if (pane.getSelectedCondition().compareTo("Select Condition") == 0) {
                return true;
            } else return pane.getOriginalPrice() == -1;
        }

        public void displayEmptyFields() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            String missingData = "Please check the following fields:\n";

            if(pane.getBookName().isEmpty())
                missingData += "\tBook Name\n";
            if(pane.getAuthorName().isEmpty())
                missingData += "\tAuthor\n";
            if(pane.getSelectedYear() == -1)
                missingData += "\tYear\n";
            if(pane.getSelectedCategory().equals("Select Category"))
                missingData += "\tCategory\n";
            if(pane.getSelectedCondition().equals("Select Condition"))
                missingData += "\tCondition\n";
            if(pane.getOriginalPrice() == -1)
                missingData += "\tPrice\n";

            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText(missingData);
            alert.show();
        }

        public void displayBookPosted() {

            Alert acctCreatedAlert = new Alert(Alert.AlertType.INFORMATION);
            acctCreatedAlert.setTitle("");
            acctCreatedAlert.setHeaderText("Congratulations!");
            acctCreatedAlert.setContentText("You have posted your book successfully!");
            ImageView confirmImageView = new ImageView(Main.successIcon);
            confirmImageView.setFitHeight(40);
            confirmImageView.setFitWidth(100);
            acctCreatedAlert.setGraphic(confirmImageView);
            acctCreatedAlert.setOnCloseRequest(arg0 -> {
                // TODO Auto-generated method stub
                Seller_PostBookControl seller = new Seller_PostBookControl(user, width, height);
                Main.mainWindow.setScene(new Scene(seller));
            });
            acctCreatedAlert.show();
        }
    }
}
