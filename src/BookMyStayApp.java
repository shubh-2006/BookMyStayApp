import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Suite", 1);
    }

    boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    void decrease(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.roomType;

        if (!inventory.isAvailable(roomType)) {
            System.out.println("No rooms available for " + reservation.guestName);
            return;
        }

        String roomId = generateRoomId(roomType);

        allocatedRoomIds.add(roomId);

        assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
        assignedRoomsByType.get(roomType).add(roomId);

        inventory.decrease(roomType);

        System.out.println("Booking confirmed for Guest: "
                + reservation.guestName
                + ", Room ID: " + roomId);
    }

    private String generateRoomId(String roomType) {

        int count = assignedRoomsByType.getOrDefault(roomType, new HashSet<>()).size() + 1;
        String roomId;

        do {
            roomId = roomType + "-" + count;
            count++;
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        RoomAllocationService service = new RoomAllocationService();
        RoomInventory inventory = new RoomInventory();

        // FIFO Queue (as required)
        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Abhi", "Single"));
        queue.add(new Reservation("Subha", "Single"));
        queue.add(new Reservation("Vanmathi", "Suite"));

        while (!queue.isEmpty()) {
            Reservation r = queue.poll();
            service.allocateRoom(r, inventory);
        }
    }
}