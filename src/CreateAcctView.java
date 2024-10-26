

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// this class will provide the user interface 
// for the create account page
public class CreateAcctView extends BorderPane {
    
    private LoginView loginViewPane;
    private final TextField firstnameTextField;
    private final TextField lastnameTextField;
    private final TextField usernameTextField;
    private final PasswordField passwordPasswordfield;
    private PasswordField confirmpasswordPasswordfield;
    private ImageView confirmImageView;
    private Alert acctCreatedAlert;

    // parameterized constructor
    public CreateAcctView(double width, double height) {
        
        // set attributes for the image to be used for the back button
        ImageView backImageView = new ImageView(Main.backIcon);
        backImageView.setFitWidth(40);
        backImageView.setFitHeight(40);

        // set attributes for the back button and add the back icon
        Button backButton = new Button();
        backButton.setGraphic(backImageView);
        backButton.setBackground(null);
            // this event handler will set the scene of the main window
            // to Create Account
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                loginViewPane = new LoginView(width, height);
                Main.mainWindow.setScene(new Scene(loginViewPane,width,height));
            }
            
        });

        // set attributes for the create account label
        Label createacctLabel = new Label("Create Account");
        createacctLabel.setFont(Font.font("Arial",FontWeight.NORMAL,36));
        createacctLabel.setTextFill(Color.BLACK);
        createacctLabel.setPrefHeight(40);

        // add the back button and create account label to this HBox
        HBox backbuttonCreateacctLabelHBox = new HBox(backButton, createacctLabel);
        backbuttonCreateacctLabelHBox.setAlignment(Pos.CENTER_LEFT);
        backbuttonCreateacctLabelHBox.setSpacing(20);

        // set attributes for the instructions label
        Label instructionLabel = new Label("Fill in the information below and click on the confirm button to create your account.");
        instructionLabel.setFont(Font.font("Arial",FontWeight.NORMAL,18));

        // set attributes for the first name label
        Label firstnameLabel = new Label("First Name:");
        firstnameLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the first name text field
        firstnameTextField = new TextField();
        firstnameTextField.setPromptText("First Name");
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
        lastnameTextField.setPromptText("Last Name");
        lastnameTextField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the first name label and first name text field into this hbox
        HBox lastnameTextfieldHBox = new HBox(lastnameLabel, lastnameTextField);
        lastnameTextfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        lastnameTextfieldHBox.setSpacing(10);
        
        // set attributes for the email label
        Label emailLabel = new Label("E-mail:");
        emailLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the email text field
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("E-mail");
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
        usernameTextField.setPromptText("Username");
        usernameTextField.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // add the username label and username text field into this hbox
        HBox usernameTextfieldHBox = new HBox(usernameLabel, usernameTextField);
        usernameTextfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        usernameTextfieldHBox.setSpacing(10);

        // set attributes for the password label
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the password password field
        passwordPasswordfield = new PasswordField();
        passwordPasswordfield.setPromptText("Password");
        passwordPasswordfield.setFont(Font.font("Arial",FontWeight.NORMAL,16));
            // this handler will find passwor mismatch
            passwordPasswordfield.setOnKeyReleased(e -> {
            // sleep the thread to find a password mismatch
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if(confirmpasswordPasswordfield.getText().isEmpty())
                return;
            if(passwordPasswordfield.getText().compareTo(confirmpasswordPasswordfield.getText()) != 0) {
                passwordPasswordfield.setBorder(Border.stroke(Color.RED));
                confirmpasswordPasswordfield.setBorder(Border.stroke(Color.RED));
            } else {
                passwordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
                confirmpasswordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
            }
        });

        // add the password label and password password field into this hbox
        HBox passwordPasswordfieldHBox = new HBox(passwordLabel, passwordPasswordfield);
        passwordPasswordfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        passwordPasswordfieldHBox.setSpacing(10);

        // set attributes for the confirm password label
        Label confirmpasswordLabel = new Label("Confirm Password:");
        confirmpasswordLabel.setFont(Font.font("Arial",FontWeight.NORMAL,16));

        // set attributes for the password password field
        confirmpasswordPasswordfield = new PasswordField();
        confirmpasswordPasswordfield.setPromptText("Confirm Password");
        confirmpasswordPasswordfield.setFont(Font.font("Arial",FontWeight.NORMAL,16));
            // this handler will find passwor mismatch
        confirmpasswordPasswordfield.setOnMouseExited(e -> {
            
            if(passwordPasswordfield.getText().compareTo(confirmpasswordPasswordfield.getText()) != 0) {
                passwordPasswordfield.setBorder(Border.stroke(Color.RED));
                confirmpasswordPasswordfield.setBorder(Border.stroke(Color.RED));
            } else {
                passwordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
                confirmpasswordPasswordfield.setBorder(Border.stroke(Color.TRANSPARENT));
            }
        });

        // add the password label and password password field into this hbox
        HBox confirmpasswordPasswordfieldHBox = new HBox(confirmpasswordLabel, confirmpasswordPasswordfield);
        confirmpasswordPasswordfieldHBox.setAlignment(Pos.CENTER_RIGHT);
        confirmpasswordPasswordfieldHBox.setSpacing(10);


        // set the attributes for the confirm button
        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font("Arial",FontWeight.NORMAL,16));
        confirmButton.setPrefSize(100,40);
            // this displays a pop up displaying account was successfully created
        confirmButton.setOnAction(e -> {
            
            acctCreatedAlert = new Alert(AlertType.INFORMATION);
            acctCreatedAlert.setTitle("");
            acctCreatedAlert.setHeaderText("Congratulations!");
            acctCreatedAlert.setContentText("Welcome " + firstnameTextField.getText() + " " + lastnameTextField.getText() + 
            ",\nYour account was successfully created." +
            "\nYour username is " + usernameTextField.getText() + ".");
            confirmImageView = new ImageView(Main.successIcon);
            confirmImageView.setFitHeight(40);
            confirmImageView.setFitWidth(100);
            acctCreatedAlert.setGraphic(confirmImageView);
                // this will ensure upon closing the login page appears
            acctCreatedAlert.setOnCloseRequest(new EventHandler<DialogEvent>() {

                @Override
                public void handle(DialogEvent arg0) {
                    // TODO Auto-generated method stub
                    loginViewPane = new LoginView(width, height);
                    Main.mainWindow.setScene(new Scene(loginViewPane,width,height));
                }
                
            });
            acctCreatedAlert.show();
        });
        

        // add the grey box elements which includes the instructions label and all HBoxes
        VBox greyVBox = new VBox(instructionLabel, firstnameTextfieldHBox, lastnameTextfieldHBox, emailTextFieldHBox, usernameTextfieldHBox, passwordPasswordfieldHBox, confirmpasswordPasswordfieldHBox, confirmButton);
            // readjust the width of the HBoxes inside the greyVBox
        VBox.setMargin(firstnameTextfieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(lastnameTextfieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(emailTextFieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(usernameTextfieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(passwordPasswordfieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(confirmpasswordPasswordfieldHBox, new Insets(0,450,0,0));
        VBox.setMargin(confirmButton, new Insets(0,0,0,400));
        greyVBox.setSpacing(20);
        greyVBox.setPadding(new Insets(20,20,20,20));
        greyVBox.setStyle(
            "-fx-background-radius: 2em;" +
            "-fx-background-color: #D9D9D9;"
        );

        // the white VBox is our main box
        VBox whiteVBox = new VBox(backbuttonCreateacctLabelHBox, greyVBox);
        whiteVBox.setSpacing(10);
        whiteVBox.setPadding(new Insets(40,40,40,40));
        whiteVBox.setStyle(
            "-fx-background-radius: 2em;" +
            "-fx-background-color: #ffffff;"
        );

        // set attributes for the Border Pane
        this.setCenter(whiteVBox);
        BorderPane.setMargin(whiteVBox, new Insets(20,20,20,20));
        
        this.setPrefSize(width, height);;
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon 
        
    }
}
