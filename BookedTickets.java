package com.example.databaseproject;

public class BookedTickets {

    private String Coach;
    private String PassN;
    private String SeatNo;
    private String ticketFee;


    public BookedTickets(String coach, String passN, String seatNo, String ticketFee) {
        setCoach(coach);
        setPassN(passN);
        setSeatNo(seatNo);
        setTicketFee(ticketFee);
    }

    public String getCoach() {
        return Coach;
    }

    public void setCoach(String coach) {
        Coach = coach;
    }

    public String getPassN() {
        return PassN;
    }

    public void setPassN(String passN) {
        PassN = passN;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String seatNo) {
        SeatNo = seatNo;
    }
    public String getTicketFee() {
        return ticketFee;
    }

    public void setTicketFee(String ticketFee) {
        this.ticketFee = ticketFee;
    }
}
