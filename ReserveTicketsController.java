package com.example.databaseproject;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReserveTicketsController implements Initializable {

    @FXML
    private Hyperlink logOutLink;

    @FXML
    private ChoiceBox<String> Coach;

    @FXML
    private ComboBox<String> seats;

    @FXML
    private ChoiceBox<String> available;

    @FXML
    private Button BookTicket;

    @FXML
    private Button price;

    @FXML
    private TextField name;

    @FXML
    private Label error;

    @FXML
    private AnchorPane ticketBox;

    String userID;
    Train train;

    Stage stage;
    Scene scene;
    Parent root;
    navBarController nav = new navBarController();

    @FXML
    void seeStations(ActionEvent event) throws IOException {
        nav.seeStations(event,userID);
    }

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


    @FXML
    void Booked(ActionEvent event) throws IOException {
        String trainName = available.getValue();
        String passengerName = name.getText();
        String seatNo = seats.getValue();

        if(trainName == null || passengerName.equals("") || seatNo == null){
            error.setTextFill(Color.color(1,0,0));
            error.setAlignment(Pos.CENTER);
            error.setText("Please fill all fields");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reserve.fxml"));
        root = fxmlLoader.load();
        BookedTicketsController ticketController = fxmlLoader.getController();
        ticketController.setUserID(userID);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setUserId(String id){
        this.userID = id;
    }

    public void setTrainData(Train trains){
        train = new Train(trains.getDept(),trains.getArr(),trains.getDate());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            setUserId(userID);
            setTrainData(train);
            logOutLink.setAlignment(Pos.CENTER);
            if(userID == null)
                logOutLink.setText("LOG IN");
            else
                logOutLink.setText("LOG OUT");

            ArrayList<String> bus=new ArrayList<>();
            bus.add("Business");
            bus.add("Economy");
            bus.add("AC Class");
            ObservableList<String> C = FXCollections.observableArrayList(bus);
            Coach.setItems(C);

            available.setItems(DBConnectivity.searchTrain(train.getDept(),train.getArr(),train.getDate()));
            Coach.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
                String ava = available.getSelectionModel().getSelectedItem();
                price.setText(DBConnectivity.ticketPrice(t1, ava));
                seats.setItems(DBConnectivity.Seats(t1, ava));
                BookTicket.setVisible(true);
            });
        });
    }

    public void BackPage(MouseEvent mouseEvent) throws IOException {
        nav.goToHome((ActionEvent) mouseEvent, userID);
    }
}
