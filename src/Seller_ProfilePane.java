import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Seller_ProfilePane extends BorderPane {

    private TextField firstnameTextField;
    private TextField lastnameTextField;
    private TextField emailTextField;
    private TextField usernameTextField;
    private Button confirmButton;

    public Seller_ProfilePane(User user, double width, double height) {

        Seller_NavigationControl navBarVBox = new Seller_NavigationControl(user,width,height);

        //confirm button :)
        confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        confirmButton.setPrefHeight(35);
        confirmButton.setPrefWidth(150);

        // set attributes for the Title Label
        Label titleLabel = new Label("Profile");
        titleLabel.setFont(Font.font(42));

        // set attributes for the header of the main page
        HBox headerHBox = new HBox(titleLabel);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);

        // set attributes for the instructions label
        Label instructionLabel = new Label("Your account's information.");
        instructionLabel.setFont(Font.font("Arial", FontWeight.NORMAL,18));

        // set attributes for the first name label
        Label firstnameLabel = new Label("First Name:");
        firstnameLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the first name text field
        firstnameTextField = new TextField();
        firstnameTextField.setText(user.getFirstName());
        firstnameTextField.setPromptText("First Name");
        firstnameTextField.setStyle("-fx-text-fill: #000000;");
        firstnameTextField.setEditable(true);
        firstnameTextField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the first name label and first name text field into this hbox
        HBox firstnameTextfieldHBox = new HBox(firstnameLabel, firstnameTextField);
        firstnameTextfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        firstnameTextfieldHBox.setSpacing(10);

        // set attributes for the last name label
        Label lastnameLabel = new Label("Last Name:");
        lastnameLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the last name text field
        lastnameTextField = new TextField();
        lastnameTextField.setText(user.getLastName());
        lastnameTextField.setPromptText("Last Name");
        lastnameTextField.setStyle("-fx-text-fill: #000000;");
        lastnameTextField.setEditable(true);
        lastnameTextField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the first name label and first name text field into this hbox
        HBox lastnameTextfieldHBox = new HBox(lastnameLabel, lastnameTextField);
        lastnameTextfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        lastnameTextfieldHBox.setSpacing(10);

        // set attributes for the email label
        Label emailLabel = new Label("E-mail:");
        emailLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the email text field
        emailTextField = new TextField();
        emailTextField.setText(user.getEmail());
        emailTextField.setPromptText("E-mail");
        emailTextField.setStyle("-fx-text-fill: #000000;");
        emailTextField.setEditable(true);
        emailTextField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the email label and email text field into this hbox
        HBox emailTextFieldHBox = new HBox(emailLabel, emailTextField);
        emailTextFieldHBox.setAlignment(Pos.CENTER_RIGHT);
        emailTextFieldHBox.setSpacing(10);

        // set attributes for the username label
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the username text field
        usernameTextField = new TextField();
        usernameTextField.setText(user.getUsername());
        usernameTextField.setPromptText("Username");
        usernameTextField.setStyle("-fx-text-fill: #000000;");
        usernameTextField.setEditable(false);
        usernameTextField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the username label and username text field into this hbox
        HBox usernameTextfieldHBox = new HBox(usernameLabel, usernameTextField);
        usernameTextfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        usernameTextfieldHBox.setSpacing(10);


        // add the grey box elements which includes the instructions label and all HBoxes
        VBox greyVBox = new VBox(instructionLabel, firstnameTextfieldHBox, lastnameTextfieldHBox, emailTextFieldHBox, usernameTextfieldHBox, confirmButton);
        // readjust the width of the HBoxes inside the greyVBox
        VBox.setMargin(firstnameTextfieldHBox, new Insets(0,350,0,0));
        VBox.setMargin(lastnameTextfieldHBox, new Insets(0,350,0,0));
        VBox.setMargin(emailTextFieldHBox, new Insets(0,350,0,0));
        VBox.setMargin(usernameTextfieldHBox, new Insets(0,350,0,0));
        VBox.setMargin(confirmButton, new Insets(0,0,0,160)); // Position the button in the VBox
        greyVBox.setSpacing(20);
        greyVBox.setPrefHeight(height-160);
        greyVBox.setPadding(new Insets(20,20,20,20));
        greyVBox.setStyle(
                "-fx-background-radius: 2em;" +
                        "-fx-background-color: #D9D9D9;"
        );

        // set attributes for the main VBox
        VBox mainVBox = new VBox(headerHBox, greyVBox);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setPrefWidth(width - navBarVBox.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and the main VBox
        HBox navBarAndMainHBox = new HBox(navBarVBox,mainVBox);
        navBarAndMainHBox.setSpacing(20);

        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon color

    }

    public Button getConfirmButton() { return confirmButton; }

    public TextField getFirstNameTextField() { return firstnameTextField; }

    public TextField getLastNameTextField() { return lastnameTextField; }

    public TextField getEmailTextField() { return emailTextField; }

    public TextField getUsernameTextField() { return usernameTextField; }
}




