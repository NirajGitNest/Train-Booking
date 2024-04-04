package irctc.entities;

import java.util.List;

public class User {
    private String name;

    private String userId;

    private String password;

    private String hashedpassword;

    private List<Ticket> ticketbooked;

    public User(String name, String password, String hashedpassword, List<Ticket> booked,String userId ) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.hashedpassword = hashedpassword;
        this.ticketbooked = booked;
    }

    public User() {
    }

    public String getnameString() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedpassword;
    }

    public List<Ticket> getbookedticket() {
        return ticketbooked;
    }

    public void printTicket() {
        for (int i = 0; i < ticketbooked.size(); i++) {
            System.err.println(ticketbooked.get(i).getTicketInfo());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedpassword = hashedPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketbooked = ticketsBooked;
    }

    public boolean hasTicket(String ticketId) {
        for (Ticket ticket : ticketbooked) {
            if (ticket.getTicketId().equals(ticketId)) {
                return true; // Ticket found
            }
        }
        return false; // Ticket not found
    }

    public boolean removeTicket(String ticketId) {
        // Ticket tempUser =user.getTicketId;
        for (Ticket ticket : ticketbooked) {
            if (ticket.getTicketId().equals(ticketId)) {
                ticketbooked.remove(ticket);
                return true; // Ticket removed
            }
        }
        return false; // Ticket not found
    }

}
