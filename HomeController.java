package com.example.databaseproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Hyperlink logOutLink;

    @FXML
    private ComboBox<String> departureCity;

    @FXML
    private ComboBox<String> arrivalCity;

    @FXML
    private DatePicker Date;

    @FXML
    private Label error;

    Stage stage;
    Scene scene;
    Parent root;
    String id;
    navBarController nav = new navBarController();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        departureCity.setItems(DBConnectivity.fetchTrainItems());
        arrivalCity.setItems(DBConnectivity.fetchTrainItems());

        Platform.runLater(()-> {
            setId(id);
            logOutLink.setAlignment(Pos.CENTER);
            if(id == null)
                logOutLink.setText("LOG IN");
            else
                logOutLink.setText("LOG OUT");
        });
    }


    @FXML
    void seeStations(ActionEvent event) throws IOException {
        nav.seeStations(event, id);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        nav.logOut(event);
    }

    @FXML
    void reserveTickets(ActionEvent event) throws IOException {
        nav.reservedTickets(event,id);
    }

    @FXML
    private int search(ActionEvent event) throws IOException {
        String deptCity = departureCity.getSelectionModel().getSelectedItem();
        String arrCity = arrivalCity.getSelectionModel().getSelectedItem();
        LocalDate date = Date.getValue();

        if(deptCity == null || arrCity == null || Date.getValue() == null){
            error.setText("Please fill all fields");
            error.setAlignment(Pos.CENTER_LEFT);
            error.setTextFill(Color.color(1,0,0));
            return 0;
        }

        LocalDate today = LocalDate.now();
        if(date.compareTo(today) < 0){
            error.setText("Please select a valid date");
            error.setAlignment(Pos.CENTER_LEFT);
            error.setTextFill(Color.color(1,0,0));
            return 0;
        }

        Train train = new Train(deptCity,arrCity,date);
        boolean result = DBConnectivity.checkAvailability(train);
        if(!result){
            error.setText("OOPS!No trains for your input");
            error.setAlignment(Pos.CENTER_LEFT);
            error.setTextFill(Color.color(1,0,0));
            return 0;
        }

        FXMLLoader fxmlLoader =  new FXMLLoader(this.getClass().getResource("BookTicket.fxml"));
        root = fxmlLoader.load();
        ReserveTicketsController bookTicketController = fxmlLoader.getController();
        bookTicketController.setUserId(id);
        bookTicketController.setTrainData(train);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return 0;
    }

    public void setId(String id){
        this.id = id;
    }

}

