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
    private TextField booknameField;
    private TextField authorField;
    private ComboBox<String> yearComboBox;
    private ChoiceBox<String> categoryChoiceBox;
    private ChoiceBox<String> conditionChoiceBox;
    private TextField priceField;
    private TextField generatedpriceField;
    private Button listmybookButton;

    public Seller_PostBookControl(User user, double width, double height) {

        this.width = width;
        this.height = height;
        this.user = user;

        pane = new Seller_PostBookPane(user,width,height);
        booknameField = pane.getBooknameField();
        authorField = pane.getAuthorField();
        yearComboBox = pane.getYearComboBox();
        categoryChoiceBox = pane.getCategoryChoiceBox();
        conditionChoiceBox = pane.getConditionChoiceBox();
        priceField = pane.getPriceField();
        generatedpriceField = pane.getGeneratedpriceField();
        listmybookButton = pane.getListmybookButton();
        listmybookButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            if(emptyFields()) {
                System.out.println(user.getFirstName());
                displayEmptyFields();
            } else {
                // insert book??
            }
        }

        public boolean emptyFields() {

            if(booknameField.getText().isEmpty()) {
                return true;
            } else if (authorField.getText().isEmpty()) {
                return true;
            } else if(yearComboBox.getValue().equals("Select Year")) {
                return true;
            } else if (categoryChoiceBox.getValue().equals("Select Category")) {
                return true;
            } else if (conditionChoiceBox.getValue().equals("Select Condition")) {
                return true;
            } else return priceField.getText().isEmpty();
        }

        public void displayEmptyFields() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            String missingData = "Please check the following fields:\n";

            if(booknameField.getText().isEmpty())
                missingData += "\tBook Name\n";
            if(authorField.getText().isEmpty())
                missingData += "\tAuthor\n";
            if(yearComboBox.getValue().equals("Select Year"))
                missingData += "\tYear\n";
            if(categoryChoiceBox.getValue().equals("Select Category"))
                missingData += "\tCategory\n";
            if(conditionChoiceBox.getValue().equals("Select Condition"))
                missingData += "\tCondition\n";
            if(priceField.getText().isEmpty())
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
