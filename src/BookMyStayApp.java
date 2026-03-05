import java.util.HashMap;

abstract class Room {
    String roomType;
    int beds;
    double price;

    Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    void displayRoomDetails(int availableRooms) {
        if (availableRooms > 0) {
            System.out.println("Room Type: " + roomType);
            System.out.println("Beds: " + beds);
            System.out.println("Price per Night: $" + price);
            System.out.println("Available Rooms: " + availableRooms);
            System.out.println();
        }
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 100);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 180);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 300);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
    }

    void addRoom(String roomType, int count) {
        inventory.put(roomType, count);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class SearchService {
    private RoomInventory inventory;

    SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void showAvailableRooms(Room[] rooms) {
        System.out.println("Available Rooms for Booking:\n");
        for (Room room : rooms) {
            int available = inventory.getAvailability(room.roomType);
            room.displayRoomDetails(available);
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 5);
        inventory.addRoom("Double Room", 0);
        inventory.addRoom("Suite Room", 2);

        Room[] rooms = {new SingleRoom(), new DoubleRoom(), new SuiteRoom()};

        System.out.println("Welcome to the Hotel Booking Management System v4.0\n");

        SearchService search = new SearchService(inventory);
        search.showAvailableRooms(rooms);

        System.out.println("Room search completed successfully. Inventory remains unchanged.");
    }
}
