import java.text.NumberFormat;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Buyer_OrdersPane extends BorderPane {

    private Button refreshButton;
    private VBox transactionsVBox;
    private Text transactionsAmountText;
    private User user;
    private List<Transaction> transactionList;
    private int transactionAmount;

    public Buyer_OrdersPane(User user, double width, double height) {

        this.user = user;

        transactionList = DataBase.returnTransactions(user.getUserID());


        Font titleFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 42);
        Font quantityFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 24);

        Buyer_NavigationControl navigationControl = new Buyer_NavigationControl(user, width, height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Orders");
        titleLabel.setFont(titleFont);

        // set attributes for the quantity label
        Label quantityLabel = new Label("amount");
        quantityLabel.setFont(quantityFont);

        // set attribute for the quantity label
        transactionsAmountText = new Text("0");
        transactionsAmountText.setFont(quantityFont);

        // set attributes for the HBox that holds the quantity and quantity label
        HBox quantityHBox = new HBox(transactionsAmountText, quantityLabel);
        quantityHBox.setSpacing(20);
        quantityHBox.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for container that holds Title and Quantity
        HBox leftHeaderPaneHBox = new HBox(titleLabel,quantityHBox);
        leftHeaderPaneHBox.setSpacing(40);
        leftHeaderPaneHBox.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the image
        ImageView image1 = new ImageView(Main.refreshIcon);
        image1.setFitWidth(25);
        image1.setFitHeight(25);

        // set attributes for the button
        refreshButton = new Button();
        refreshButton.setGraphic(image1);
        refreshButton.setBackground(Background.fill(Color.TRANSPARENT));
        refreshButton.setAlignment(Pos.CENTER);

        // set attributes for the container of two buttons
        HBox rightHeaderPaneHBox = new HBox(refreshButton);
        rightHeaderPaneHBox.setAlignment(Pos.CENTER_RIGHT);

        // set attributes for the header of the page
        BorderPane headerPane = new BorderPane();
        headerPane.setLeft(leftHeaderPaneHBox);
        headerPane.setRight(rightHeaderPaneHBox);

        // set attributes for the header label
        Label headerLabel1 = new Label("Book Title");
        headerLabel1.setPrefWidth(250);
        headerLabel1.setWrapText(true);
        headerLabel1.setFont(Font.font(20));
        headerLabel1.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel2 = new Label("Ordered on");
        headerLabel2.setPrefWidth(200);
        headerLabel2.setWrapText(false);
        headerLabel2.setFont(Font.font(20));
        headerLabel2.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel3 = new Label("Amount");
        headerLabel3.setPrefWidth(150);
        headerLabel3.setWrapText(false);
        headerLabel3.setFont(Font.font(20));
        headerLabel3.setAlignment(Pos.BASELINE_CENTER);

        // set attributes for the header label
        Label headerLabel4 = new Label("Rate Seller");
        headerLabel4.setPrefWidth(100);
        headerLabel4.setWrapText(true);
        headerLabel4.setFont(Font.font(20));
        headerLabel4.setAlignment(Pos.BASELINE_CENTER);

        // FOR DEBUGGING maybe?
        headerLabel1.setBorder(Border.stroke(Color.BLACK));
        headerLabel2.setBorder(Border.stroke(Color.BLACK));
        headerLabel3.setBorder(Border.stroke(Color.BLACK));
        headerLabel4.setBorder(Border.stroke(Color.BLACK));

        // set attributes for the header of the body
        HBox bodyheaderPane = new HBox(headerLabel1,headerLabel2,headerLabel3,headerLabel4);

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(700);
        lineSeparator.setHeight(2);
        lineSeparator.setFill(Color.BLACK);

        // set attributes for the VBox that holds the lists of users
        transactionsVBox = new VBox();

        // set attributes for the scrollpane
        ScrollPane sp = new ScrollPane(transactionsVBox);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.minViewportHeightProperty().set(400);
        sp.setPrefHeight(400);
        sp.setStyle("-fx-background: white;");

        // set attributes for the users box that includes their header and scrollpane
        VBox headerAndScrollPane = new VBox(bodyheaderPane,lineSeparator,sp);

        // set attributes for the main VBox which excludes the navigation bar
        VBox mainVBox = new VBox(headerPane,headerAndScrollPane);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setSpacing(10);
        mainVBox.setPrefWidth(width - navigationControl.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and page
        HBox navBarAndMainHBox = new HBox(navigationControl,mainVBox);
        navBarAndMainHBox.setSpacing(20);

        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color

    }

    public void clearTransactions() {
        transactionsVBox.getChildren().clear();
        transactionsAmountText.setText("0");
    }

    // modifies and updates the display amount text by one
    public void updateCount() {
        String str = transactionsAmountText.getText();
        int incr = Integer.parseInt(str) + 1;
        transactionsAmountText.setText(Integer.toString(incr));
    }

    // modifies and updates the display amount text by an amount
    public void updateCount(int num) {
        String str = transactionsAmountText.getText();
        int incr = Integer.parseInt(str) + num;
        transactionsAmountText.setText(Integer.toString(incr));
    }

    public Button getRefreshButton() {
        return refreshButton;
    }

    public void addAllOrdersToPane() {

        for(Transaction t : transactionList) {

            clearTransactions();


        }

    }

    public VBox transactionVBox(Transaction transaction) {

        NumberFormat nf = NumberFormat.getCurrencyInstance();


        Book book = transaction.getBook();

        // set attributes for the header label
        Label title = new Label(book.getTitle());
        title.setPrefWidth(250);
        title.setWrapText(true);
        title.setFont(Font.font(20));
        title.setAlignment(Pos.BASELINE_LEFT);
        title.setPadding(new Insets(5));

        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);

        // set attributes for the header label
        Label timestamp = new Label(transaction.getTimestamp());
        timestamp.setPrefWidth(200);
        timestamp.setWrapText(false);
        timestamp.setFont(Font.font(20));
        timestamp.setAlignment(Pos.BASELINE_CENTER);
        timestamp.setPadding(new Insets(5));

        VBox timestampBox = new VBox(timestamp);
        timestampBox.setAlignment(Pos.CENTER);

        // set attributes for the header label
        Label amount = new Label(nf.format(transaction.getAmount()));
        amount.setPrefWidth(150);
        amount.setWrapText(false);
        amount.setFont(Font.font(20));
        amount.setAlignment(Pos.BASELINE_CENTER);
        amount.setPadding(new Insets(5));

        VBox amountBox = new VBox(amount);
        amountBox.setAlignment(Pos.CENTER);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText("Rate");
        comboBox.getItems().addAll("1","2","3","4","5");

        // Add a listener to disable editing when a value is selected
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!comboBox.getValue().isEmpty()) {
                comboBox.setDisable(true);
            }
            if(transaction.seller_reviewed == 'Y'){
                comboBox.setDisable(true);
            }
        });

        if(transaction.seller_reviewed == 'Y'){
            comboBox.setDisable(true);
        }

        comboBox.setOnAction(event -> {
            String selectedRating = comboBox.getValue();
            DataBase.addSellerReview(transaction.sellerID, Integer.parseInt(selectedRating), transaction.transactionID); // Call your function here
        });

        VBox soldToBox = new VBox(comboBox);
        soldToBox.setPadding(new Insets(5));
        soldToBox.setPrefWidth(100);
        soldToBox.setAlignment(Pos.CENTER);

        HBox transactionHBox = new HBox(titleBox,timestampBox,amountBox,soldToBox);

        // set attributes for the line that separates the header and the scrollpane
        Rectangle lineSeparator = new Rectangle();
        lineSeparator.setWidth(700);
        lineSeparator.setHeight(1);
        lineSeparator.setFill(Color.BLACK);

        return new VBox(transactionHBox,lineSeparator);


    }

    public void displayNoTransactionFound() {

        Label noBooksFoundLabel = new Label("No Orders Found");
        noBooksFoundLabel.setPadding(new Insets(100,100,100,300));
        noBooksFoundLabel.setFont(Font.font(48));

        transactionsVBox.getChildren().clear();

        transactionsVBox.getChildren().add(noBooksFoundLabel);
    }

    public void displayTransactions() {

        transactionsVBox.getChildren().clear();
        transactionsAmountText.setText("0");
        transactionAmount = 0;

        if(transactionList.isEmpty()) {
            displayNoTransactionFound();
        } else {

            for (Transaction t : transactionList) {
                transactionsVBox.getChildren().add(transactionVBox(t));
                updateCount();
            }
        }

    }

}
