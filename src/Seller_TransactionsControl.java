import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Seller_TransactionsControl extends Pane {

    private User user;
    private double width;
    private double height;
    private Seller_TransactionsPane pane;
    private Button refreshButton;
    private VBox transactionsVBox;
    private Text transactionsAmountText;

    public Seller_TransactionsControl(User user, double width, double height) {

        pane = new Seller_TransactionsPane(user,width,height);

        refreshButton = pane.getRefreshButton();
        transactionsVBox = pane.getTransactionsVBox();
        transactionsAmountText = pane.getTransactionsAmountText();

        this.getChildren().addAll(pane);
    }

    public void addTransaction(Transaction transaction) {

        // set attributes for the header label
        Label headerLabel1 = new Label("book title");
        headerLabel1.setFont(Font.font(20));
        headerLabel1.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the header label
        Label headerLabel2 = new Label(transaction.getTimestamp());
        headerLabel2.setFont(Font.font(20));
        headerLabel2.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel3 = new Label("$" +"0");
        headerLabel3.setFont(Font.font(20));
        headerLabel3.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel4 = new Label("buyer");
        headerLabel4.setFont(Font.font(20));
        headerLabel4.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header of the body
        GridPane bodyheaderPane = new GridPane();
        for(int i = 0; i < 4; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(25);
            bodyheaderPane.getColumnConstraints().add(columnConstraints);
        }
        bodyheaderPane.add(headerLabel1,0,0);
        bodyheaderPane.add(headerLabel2,1,0);
        bodyheaderPane.add(headerLabel3,2,0);
        bodyheaderPane.add(headerLabel4,3,0);
    }

}

