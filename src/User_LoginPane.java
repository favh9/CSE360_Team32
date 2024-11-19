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

/**
 * User_LoginPane provides the user interface for the login page.
 * This includes fields for username, password, and buttons for signing in or creating a new account.
 */
class User_LoginPane extends BorderPane {

    private Boolean focusBoolean;
    private final TextField usernameTextField; // TextField for the username input
    protected final PasswordField passwordField; // PasswordField for the password input
    private final Hyperlink forgotpasswordHyperlink; // Hyperlink for "Forgot password?" link
    private final Button signinButton; // Button to sign in
    private final Button createAcctButton; // Button to create a new account

    /**
     * Parameterized constructor for the login view.
     * Initializes the layout, user interface components, and applies necessary styles.
     *
     * @param width The width of the login pane.
     * @param height The height of the login pane.
     */
    public User_LoginPane(double width, double height) {

        // Load custom font for the title
        Font arima = Font.loadFont(getClass().getResourceAsStream("fonts/Arima-Bold.ttf"), 65);

        // Create and style the title label
        Label titleLabel = new Label("ASU Bookstore");
        titleLabel.setFont(arima);
        titleLabel.setPadding(new Insets(20, 0, 0, 0));
        titleLabel.setTextFill(Color.WHITE);

        // Create and style the sign-in label
        Label signinLabel = new Label("Sign in");
        signinLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 36));
        signinLabel.setTextFill(Color.BLACK);
        signinLabel.setPrefHeight(80);

        // Create and style the ASU icon image view
        ImageView imageView = new ImageView(Main.asuIcon);
        imageView.setFitHeight(80);
        imageView.setFitWidth(120);

        // Create an HBox for the sign-in label and ASU icon
        HBox signinAsuiconHBox = new HBox(signinLabel, imageView);
        signinAsuiconHBox.setSpacing(20);
        signinAsuiconHBox.setPadding(new Insets(20, 20, 0, 20));

        // Create and style the username text field
        usernameTextField = new TextField("");
        usernameTextField.setPromptText("Username");
        usernameTextField.setPrefHeight(40);

        // Logic to unfocus the username field when first launched
        focusBoolean = true;
        usernameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && focusBoolean) {
                this.requestFocus();
                focusBoolean = false;
            }
        });

        // Create and style the password text field
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(40);
        passwordField.deselect();

        // Create a VBox for username and password fields
        VBox usernamePasswordVBox = new VBox(usernameTextField, passwordField);
        usernamePasswordVBox.setSpacing(20);
        usernamePasswordVBox.setPadding(new Insets(20, 20, 0, 20));

        // Create and style the "Forgot password?" hyperlink
        forgotpasswordHyperlink = new Hyperlink("Forgot password?");
        forgotpasswordHyperlink.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        forgotpasswordHyperlink.setTextFill(Color.valueOf("#4A8DD2"));
        forgotpasswordHyperlink.setPrefHeight(40);

        // Create an HBox for the "Forgot password?" hyperlink
        HBox forgotpasswordHBox = new HBox(forgotpasswordHyperlink);
        forgotpasswordHBox.setPadding(new Insets(0, 20, 0, 20));
        forgotpasswordHBox.setPrefWidth(300);

        // Create and style the sign-in button
        signinButton = new Button("Sign in");
        signinButton.setPrefSize(260, 40);
        signinButton.setStyle("-fx-background-radius: 5em; -fx-background-color: #ffc627;");

        // Create and style the "or" label
        Label orLabel = new Label("or");
        orLabel.setFont(Font.font("Arial", FontWeight.THIN, 15));
        orLabel.setTextFill(Color.BLACK);
        orLabel.setPrefWidth(300);
        orLabel.setAlignment(Pos.CENTER);

        // Create and style the "Create account" button
        createAcctButton = new Button("Create account");
        createAcctButton.setPrefSize(260, 40);
        createAcctButton.setStyle("-fx-background-radius: 5em;");

        // Create a VBox for the sign-in button, "or" label, and create account button
        VBox signinOrCreateacctVBox = new VBox(signinButton, orLabel, createAcctButton);
        signinOrCreateacctVBox.setSpacing(5);
        signinOrCreateacctVBox.setPadding(new Insets(0, 20, 20, 20));

        // Create the main VBox and set up the layout for the login page
        VBox mainVBox = new VBox(signinAsuiconHBox, usernamePasswordVBox, forgotpasswordHBox, signinOrCreateacctVBox);
        mainVBox.setMaxWidth(300);
        mainVBox.setMaxHeight(380);
        mainVBox.setBackground(Background.fill(Color.WHITE));

        // Set the top title label and the center of the BorderPane to the main VBox
        this.setTop(titleLabel);
        this.setCenter(mainVBox);

        // Adjust alignment and margins
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(mainVBox, new Insets(0, 0, 50, 0));

        // Set the preferred size and background color for the BorderPane
        this.setPrefSize(width, height);
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // Darker maroon color
    }

    /**
     * Accessor method for the username text field.
     *
     * @return The TextField for the username.
     */
    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    /**
     * Accessor method for the password field.
     *
     * @return The PasswordField for the password.
     */
    public PasswordField getPasswordField() {
        return passwordField;
    }

    /**
     * Accessor method for the sign-in button.
     *
     * @return The Button used for signing in.
     */
    public Button getSigninButton() {
        return signinButton;
    }

    /**
     * Accessor method for the create account button.
     *
     * @return The Button used for creating a new account.
     */
    public Button getCreateAcctButton() {
        return createAcctButton;
    }

    /**
     * Accessor method for the "Forgot password?" hyperlink.
     *
     * @return The Hyperlink used for navigating to the password recovery page.
     */
    public Hyperlink getForgotpasswordHyperlink() {
        return forgotpasswordHyperlink;
    }

    /**
     * Displays an alert when the username or password is incorrect.
     * This method is triggered when an invalid login attempt is made.
     */
    public void displayIncorrectPasswordOrUsername() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please verify your username or re-enter your password\n");
        alert.show();
    }
}
