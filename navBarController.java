package com.example.databaseproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class navBarController {

    private Stage stage;
    private Parent root;
    private Scene scene;

    public navBarController(){

    }

    public void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Create-Account.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void seeStations(ActionEvent event, String id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Train Stations.fxml"));
        root = fxmlLoader.load();
        TrainStationController stationController = fxmlLoader.getController();
        stationController.setUserID(id);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reservedTickets(ActionEvent event, String id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reserve.fxml"));
        root = fxmlLoader.load();
        BookedTicketsController controller = fxmlLoader.getController();
        controller.setUserID(id);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setUserData(id);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToHome(ActionEvent event, String id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
        root = fxmlLoader.load();
        HomeController homeController = fxmlLoader.getController();
        homeController.setId(id);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
