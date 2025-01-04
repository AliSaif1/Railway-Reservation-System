package com.example.databaseproject;

import java.sql.*;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnectivity {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static final String databaseName = "jdbc:mysql://localhost/RailwayTicketReservation";
    private static final String userName = "";
    private static final String password = "";

    public DBConnectivity() {
    }

    public static ObservableList<String> fetchTrainItems() {
        ObservableList<String>  list = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("SELECT * FROM TrainStations");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String StationName=resultSet.getString("StationName");
                list.add(StationName);
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        return list;
    }


    public  static  ObservableList<String> searchTrain(String fCity,String tcity,LocalDate date){
        System.out.println("my city " +fCity);
        ObservableList<String> available =FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select * from TrainStations\n" +
                    "inner join TrainSchedule on TrainSchedule.TrainID = TrainStations.TrainID\n" +
                    "inner join Train on Train.TrainID = TrainStations.TrainID\n"+
                    "where  StationName='"+fCity+"'  and departureDate = '"+date+"'\n" +
                    "having TrainStations.stationNo < (select stationNo from TrainStations\n" +
                    "where StationName = '"+tcity+"')");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String StationName=resultSet.getString("TrainName");
                available.add(StationName);
            }

        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return  available;
    }

    public static ObservableList<String> Seats(String Coach, String Train){

        ObservableList<String> seats = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select *, (count(CoachSeats.SeatNo) - count(Reservation.SeatNo)) from CoachSeats\n" +
                    "inner join TrainCoach on TrainCoach.CoachNo = CoachSeats.CoachNo\n" +
                    "inner join Train on Train.TrainID = TrainCoach.TrainID\n" +
                    "left join Reservation on Reservation.SeatNo = CoachSeats.SeatNo and Reservation.CoachNo = CoachSeats.CoachNo\n" +
                    "where Reservation.SeatNo is null\n" +
                    "group by TrainCoach.TrainID,TrainCoach.CoachType,CoachSeats.SeatNo\n" +
                    "having TrainCoach.CoachType = '"+Coach+"' and Train.TrainName = '"+Train+"'\n");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String availableSeats = resultSet.getString("SeatNo");
                seats.add(availableSeats);
            }

        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return seats;
    }

    public static String ticketPrice(String Coach, String Train) {

        String fee = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select * from TrainStations\n" +
                    "inner join TrainCoach on TrainCoach.TrainID = TrainStations.TrainID\n" +
                    "inner join Train on Train.TrainID = TrainStations.TrainID\n" +
                    "where  Train.TrainName = '"+Train+"' and TrainCoach.CoachType = '"+Coach+"'\n" +
                    "group by TicketFee\n");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                fee = resultSet.getString("TicketFee");
                System.out.println(fee);
            }

        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return fee;
    }

    public static ObservableList<Train> seeStations(String Train){

        ObservableList<Train> stations = FXCollections.observableArrayList();
        System.out.println(Train);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select * from TrainStations\n" +
                    "inner join Train on Train.TrainID = TrainStations.TrainID\n" +
                    "where Train.TrainName = '"+Train+"'");
            ResultSet resultSet = preparedStatement.executeQuery();

            String arr = null;
            String time = null;
            while(resultSet.next()) {

                String dept = resultSet.getString("StationName");
                if(arr == null){
                    arr = dept;
                    time = resultSet.getString("departureTime");
                    continue;
                }
                Train trains = new Train(arr,dept,time);
                time = resultSet.getString("departureTime");
                arr = dept;
                stations.add(trains);
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return stations;
    }

    public  static ObservableList<String> getAllTrains(){
        ObservableList<String > train = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select * from Train");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String Name=resultSet.getString("TrainName");
                train.add(Name);
            }

        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return  train;
    }

    public static ObservableList<BookedTickets> bookedTickets(String userID){
        ObservableList<BookedTickets > tickets = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select SeatNo,TrainCoach.CoachNo,PassengerName,TicketFee from Reservation\n" +
                    "inner join TrainCoach on TrainCoach.CoachNo = Reservation.CoachNo\n" +
                    "where UserID = '"+userID+"'");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String SeatNo=resultSet.getString("SeatNo");
                String CoachNo =resultSet.getString("TrainCoach.CoachNo");
                String Name =resultSet.getString("PassengerName");
                String ticketFee = resultSet.getString("TicketFee");

                System.out.println(ticketFee);
                BookedTickets bookedTickets = new BookedTickets(CoachNo,Name,SeatNo,ticketFee);
                tickets.add(bookedTickets);
            }

        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return  tickets;
    }

    public static int setReservation(String userID, String trainName, String passengerName, String seatNo, String dept, String arr,LocalDate date){
        int status = 0;
        String coachNo = coachNo(seatNo, trainName);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            if(userID == null){
                preparedStatement = connection.prepareStatement("insert into Reservation" +
                        "(UserID,CoachNo,SeatNo,PassengerName,reservationDate,derpartCity,ArrivalCity)\n" +
                        "values("+ null +",'"+coachNo+"','"+seatNo+"','"+passengerName+"','"+date+"','"+dept+"','"+arr+"')");
            }
            else {
                preparedStatement = connection.prepareStatement("insert into Reservation" +
                        "(UserID,CoachNo,SeatNo,PassengerName,reservationDate,derpartCity,ArrivalCity)\n" +
                        "values('" + userID + "','" + coachNo + "','" + seatNo + "','" + passengerName + "','" + date + "','" + dept + "','" + arr + "')");
            }
            status = preparedStatement.executeUpdate();
        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return status;
    }

    public static String coachNo(String seatNo,String trainName) {

        String coachNo = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select CoachSeats.CoachNo from CoachSeats\n" +
                    "inner join TrainCoach on TrainCoach.CoachNo = CoachSeats.CoachNo\n" +
                    "inner join Train on Train.TrainID = TrainCoach.TrainID\n" +
                    "where seatNo = '"+seatNo+"' and TrainName = '"+trainName+"'");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                coachNo = resultSet.getString("CoachNo");
                System.out.println(coachNo);
            }

        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return coachNo;
    }

    public static Authentication authentication(String userID, String userPassword){
        Authentication authentication = null;
        String id,userPass;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select * from Authentication\n" +
                    "where AuthID = '"+userID+"' and UserPassword = '"+userPassword+"'");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getString("AuthID");
                userPass = resultSet.getString("UserPassword");
                authentication = new Authentication(id,userPass);
            }

        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return authentication;
    }

    public static boolean checkAvailability(Train trains){
        boolean available = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select * from TrainStations\n" +
                    "inner join TrainSchedule on TrainSchedule.TrainID = TrainStations.TrainID\n" +
                    "inner join Train on Train.TrainID = TrainStations.TrainID\n"+
                    "where  StationName='"+trains.getDate()+"'  and departureDate = '"+trains.getDate()+"'\n" +
                    "having TrainStations.stationNo < (select stationNo from TrainStations\n" +
                    "where StationName = '"+trains.getArr()+"')");
            available = preparedStatement.execute();

        } catch (Exception var14) {
            var14.printStackTrace();
        }
        System.out.println(available);
        return  available;
    }

    public static String validateID(String id){
        String available = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseName, userName, password);
            preparedStatement = connection.prepareStatement("select * from Users\n" +
                    "where PhNo = '"+id+"'");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.println(resultSet.getString("phNo"));
                available = resultSet.getString("phNo");
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }
        return  available;
    }

}
