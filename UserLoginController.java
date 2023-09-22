package com.example.databaseproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginController {

    @FXML
    private TextField loginID;

    @FXML
    private PasswordField Password;

    @FXML
    private Label loginError;

    Stage stage;
    Scene scene;
    Parent root;

    @FXML
    public int login(ActionEvent event) throws IOException {

        String Mobile = loginID.getText();
        String pass = Password.getText();

        if(Mobile.equals("") || pass.equals("")){
            loginError.setText("No fields can be empty. ");
            loginError.setAlignment(Pos.CENTER);
            loginError.setTextFill(Color.color(1,0,0));
            return  0;
        }

        Authentication authentication = DBConnectivity.authentication(Mobile,pass);
        if(authentication == null){
            loginID.setText("");
            Password.setText("");
            loginError.setText("Invalid  UserId or Password");
            loginError.setAlignment(Pos.CENTER);
            loginError.setTextFill(Color.color(1,0,0));
            return 0;
        }
        else if(Mobile.equals(authentication.getUserId())||pass.equals(authentication.getPassword())){
            if (Mobile.equals(DBConnectivity.validateID(Mobile))) {
                FXMLLoader fxmlLoader =  new FXMLLoader(this.getClass().getResource("Home.fxml"));
                root = fxmlLoader.load();
                HomeController homeController = fxmlLoader.getController();
                homeController.setId(Mobile);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                loginError.setText("Please Enter valid Credentials");
                loginError.setAlignment(Pos.CENTER);
                loginError.setTextFill(Color.color(1,0,0));
            }
            return 0;
        }
        return 0;
    }

    @FXML
    void guest(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(this.getClass().getResource("Home.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
