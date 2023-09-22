package com.example.databaseproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TicketReservation extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicketReservation.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Railway Ticket Reservation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
