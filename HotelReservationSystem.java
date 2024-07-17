//program for hotel reservation system

import java.util.ArrayList;
import java.util.List;

class Room {
    private int roomId;
    private String roomType;
    private boolean available;

    public Room(int roomId, String roomType) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.available = true;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Reservation {
    private int reservationId;
    private Room room;
    private String guestName;

    public Reservation(int reservationId, Room room, String guestName) {
        this.reservationId = reservationId;
        this.room = room;
        this.guestName = guestName;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int nextReservationId;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        nextReservationId = 1;

        rooms.add(new Room(1, "Single"));
        rooms.add(new Room(2, "Double"));
        rooms.add(new Room(3, "Suite"));
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(Room room, String guestName) {
        if (room.isAvailable()) {
            Reservation reservation = new Reservation(nextReservationId++, room, guestName);
            reservations.add(reservation);
            room.setAvailable(false); 
            return reservation;
        } else {
            return null; 
        }
    }

    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
        reservation.getRoom().setAvailable(true); // Mark room as available again
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        // Example
        Room roomToBook = hotel.getAvailableRooms().get(0); // Assuming first available room
        String guestName = "Swayam More";
        Reservation reservation = hotel.makeReservation(roomToBook, guestName);

        if (reservation != null) {
            System.out.println("Reservation successful! Details:");
            System.out.println("Reservation ID: " + reservation.getReservationId());
            System.out.println("Room Type: " + reservation.getRoom().getRoomType());
            System.out.println("Guest Name: " + reservation.getGuestName());
        } else {
            System.out.println("Sorry, the selected room is not available.");
        }
    }
}