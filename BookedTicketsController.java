package com.example.databaseproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookedTicketsController implements Initializable {

    @FXML
    private Hyperlink logOutLink;

    @FXML
    private TableColumn<BookedTickets,String> coachNo;

    @FXML
    private TableColumn<BookedTickets,String> seatNo;

    @FXML
    private TableColumn<BookedTickets,String> PassName;

    @FXML
    private TableColumn<BookedTickets,String> ticketFee;

    @FXML
    private TableView<BookedTickets> ReserveTable;

    String userID;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            setUserID(userID);
            logOutLink.setAlignment(Pos.CENTER);
            if(userID == null)
                logOutLink.setText("LOG IN");
            else
                logOutLink.setText("LOG OUT");
            coachNo.setCellValueFactory(new PropertyValueFactory<>("Coach"));
            PassName.setCellValueFactory(new PropertyValueFactory<>("PassN"));
            seatNo.setCellValueFactory(new PropertyValueFactory<>("SeatNo"));
            ticketFee.setCellValueFactory(new PropertyValueFactory<>("ticketFee"));
            ReserveTable.setItems(DBConnectivity.bookedTickets(userID));
        });
    }

    public void setUserID(String userID){
        this.userID = userID;
    }
}
