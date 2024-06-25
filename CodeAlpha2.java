import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeAlpha2 {
    private List<Room> rooms;
    private List<Booking> bookings;

    public CodeAlpha2() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();

        rooms.add(new Room("Single", 1, 1000));
        rooms.add(new Room("Double", 2, 2500));
        rooms.add(new Room("Suite", 3, 5000));
        rooms.add(new Room("Deluxe", 4, 10000));
    }

    public void searchRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println(room.toString());
            }
        }
    }

    public void makeReservation() {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc);
        System.out.println("enter room type: ");
        String roomType = sc.nextLine();
        System.out.println("enter number of guests: ");
        int numGuests = sc.nextInt();
        sc.nextLine();

        Room room = findRoom(roomType, numGuests);
        if (room != null) {
            System.out.println("enter guest name: ");
            String guestName = sc.nextLine();
            System.out.println("enter credit card number: ");
            int creditCardNumber = sc.nextInt();
            sc.nextLine();

            Booking booking = new Booking(room, guestName, creditCardNumber);
            bookings.add(booking);
            room.setBooked(true);
            System.out.println("Reservation successful!");
        } else {
            System.out.println("No available rooms of that type.");
        }
    }

    public void viewBookingDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc);
        System.out.println("Enter booking ID: ");
        int bookingId = sc.nextInt();
        sc.nextLine();

        for (Booking booking : bookings) {
            if (booking.getId() == bookingId) {
                System.out.println(booking.toString());
                return;
            }
        }
        System.out.println("booking not found.");
    }

    private Room findRoom(String roomType, int numGuests) {
        for (Room room : rooms) {
            if (room.getType().equals(roomType) && room.getCapacity() >= numGuests && !room.isBooked()) {
                return room;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CodeAlpha2 system = new CodeAlpha2();
        Scanner sc = new Scanner(System.in);
        System.out.println(sc);

        while (true) {
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.println("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    system.searchRooms();
                    break;
                case 2:
                    system.makeReservation();
                    break;
                case 3:
                    system.viewBookingDetails();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

class Room {
    private String type;
    private int capacity;
    private double rate;
    private boolean booked;

    public Room(String type, int capacity, double rate) {
        this.type = type;
        this.capacity = capacity;
        this.rate = rate;
        this.booked = false;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRate() {
        return rate;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Room{" +
                "type='" + type + '\'' +
                ", capacity=" + capacity +
                ", rate=" + rate +
                '}';
    }
}

class Booking {
    private int id;
    private Room room;
    private String guestName;
    private int creditCardNumber;

    private static int nextId = 1;

    public Booking(Room room, String guestName, int creditCardNumber) {
        this.id = nextId++;
        this.room = room;
        this.guestName = guestName;
        this.creditCardNumber = creditCardNumber;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }
    @Override
public String toString() {
    return "Booking{" +
            "id=" + id +
            ", room=" + room.toString() +
            ", guestName='" + guestName + '\'' +
            ", creditCardNumber=" + creditCardNumber +
            '}';
}
}
