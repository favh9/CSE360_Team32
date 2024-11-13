

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// this class provides the user interface for the login page
class User_LoginPane extends BorderPane {

    private Boolean focusBoolean;
    private final TextField usernameTextField;
    protected final PasswordField passwordField;
    private final Hyperlink forgotpasswordHyperlink;
    private final Button signinButton;
    private final Button createAcctButton;

    // parameterized constructor for login view
    public User_LoginPane(double width, double height) {

        // load custom font
        Font arima = Font.loadFont(getClass().getResourceAsStream("fonts/Arima-Bold.ttf"), 65);

        // set attributes for the title label
        Label titleLabel = new Label("ASU Bookstore");
        titleLabel.setFont(arima);
        titleLabel.setPadding(new Insets(20,0,0,0));
        titleLabel.setTextFill(Color.WHITE);

        // set attributes for the sign in label that sits next to the asu icon
        Label signinLabel = new Label("Sign in");
        signinLabel.setFont(Font.font("Arial",FontWeight.NORMAL,36));
        signinLabel.setTextFill(Color.BLACK);
        signinLabel.setPrefHeight(80);
        
        // set attributes for the asu icon
        ImageView imageView = new ImageView(Main.asuIcon);
        imageView.setFitHeight(80);
        imageView.setFitWidth(120);

        // add the sign in label and asu icon, adjust their position in the HBox
        HBox signinAsuiconHBox = new HBox(signinLabel, imageView);
        signinAsuiconHBox.setSpacing(20);
        signinAsuiconHBox.setPadding(new Insets(20,20,0,20));

        // set attributes for the username text field
        usernameTextField = new TextField("");
        usernameTextField.setPromptText("Username");
        usernameTextField.setPrefHeight(40);
            // this helps us unfocus the username text field when first launched
        focusBoolean = true;
        usernameTextField.focusedProperty().addListener((observable,oldValue, newValue) -> {

            if(newValue && focusBoolean) {
                this.requestFocus();
                focusBoolean = false;
            }
        });

        // set attributes for the password text field
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(40);
        passwordField.deselect();

        // add the username and password text fields to a VBox
        VBox usernamePasswordVBox = new VBox(usernameTextField, passwordField);
        usernamePasswordVBox.setSpacing(20);
        usernamePasswordVBox.setPadding(new Insets(20,20,0,20));

        forgotpasswordHyperlink = new Hyperlink("Forgot password?");
        forgotpasswordHyperlink.setFont(Font.font("Arial",FontWeight.NORMAL,14));
        forgotpasswordHyperlink.setTextFill(Color.valueOf("#4A8DD2"));
        forgotpasswordHyperlink.setPrefHeight(40);

        HBox forgotpasswordHBox = new HBox(forgotpasswordHyperlink);
        forgotpasswordHBox.setPadding(new Insets(0,20,0,20));
        forgotpasswordHBox.setPrefWidth(300);

        // set attributes of sign in button
        signinButton = new Button();
        signinButton.setText("Sign in");
        signinButton.setPrefSize(260, 40);
        signinButton.setStyle(
            "-fx-background-radius: 5em;" +
            "-fx-background-color: #ffc627;"
        );

        // set attributes of the or label
        Label orLabel = new Label("or");
        orLabel.setFont(Font.font("Arial", FontWeight.THIN, 15));
        orLabel.setTextFill(Color.BLACK);
        orLabel.setPrefWidth(300);
        orLabel.setAlignment(Pos.CENTER);

        // set attributes of create account button
        createAcctButton = new Button();
        createAcctButton.setText("Create account");
        createAcctButton.setPrefSize(260, 40);
        createAcctButton.setStyle(
            "-fx-background-radius: 5em;"
        );

        // add the sign in button, or label, and create account button to this VBox
        VBox signinOrCreateacctVBox = new VBox(signinButton, orLabel, createAcctButton);
        signinOrCreateacctVBox.setSpacing(5);
        signinOrCreateacctVBox.setPadding(new Insets(0,20,20,20));

        // add the boxes to this VBox to make it into one
        VBox mainVBox = new VBox(signinAsuiconHBox, usernamePasswordVBox,forgotpasswordHBox,signinOrCreateacctVBox);
        mainVBox.setMaxWidth(300);
        mainVBox.setMaxHeight(380);
        mainVBox.setBackground(Background.fill(Color.WHITE));

        // set the attirbutes for this Border Pane 
        // add label and main VBox 
        this.setTop(titleLabel);
        this.setCenter(mainVBox);
        
        // adjust the title and main VBox
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(mainVBox, new Insets(0,0,50,0));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon colors
    }

    // accessor for username text field
    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    // accessor for password text field
    public PasswordField getPasswordField() {
        return passwordField;
    }

    // accessor for sign in button
    public Button getSigninButton() {
        return signinButton;
    }

    // accessor for create account button
    public Button getCreateAcctButton() {
        return createAcctButton;
    }

    public Hyperlink getForgotpasswordHyperlink() {
        return forgotpasswordHyperlink;
    }


}