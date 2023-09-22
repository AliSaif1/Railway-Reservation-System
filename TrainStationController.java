package com.example.databaseproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrainStationController implements Initializable {

    @FXML
    private Hyperlink logOutLink;

    @FXML
    private TableColumn<Train, String> Arr;

    @FXML
    private TableColumn<Train, String> Dept;

    @FXML
    private TableView<Train> TrainTable;

    @FXML
    private TableColumn<Train, String> Time;

    @FXML
    private ComboBox<String> AllTrains;

    String userID;
    navBarController nav = new navBarController();

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        nav.goToHome(event,userID);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        nav.logOut(event);
    }

    @FXML
    void reserveTickets(ActionEvent event) throws IOException {
        nav.reservedTickets(event,userID);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        AllTrains.setItems(DBConnectivity.getAllTrains());
        AllTrains.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            Arr.setCellValueFactory(new PropertyValueFactory<>("arr"));
            Dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
            Time.setCellValueFactory(new PropertyValueFactory<>("time"));

            TrainTable.setItems(DBConnectivity.seeStations(t1));
        });

        Platform.runLater(()-> {
            setUserID(userID);
            logOutLink.setAlignment(Pos.CENTER);
            if(userID == null)
                logOutLink.setText("LOG IN");
            else
                logOutLink.setText("LOG OUT");
        });
    }

    public void setUserID(String userID){
        this.userID = userID;
    }
}
