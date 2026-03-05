import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;
    int nights;

    Reservation(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
    }

    void displayRequest() {
        System.out.println("Guest: " + guestName + ", Room Type: " + roomType + ", Nights: " + nights);
    }
}

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added for guest: " + reservation.guestName);
    }

    void displayAllRequests() {
        System.out.println("\nCurrent Booking Requests in Queue:");
        for (Reservation r : requestQueue) {
            r.displayRequest();
        }
        System.out.println();
    }

    boolean isEmpty() {
        return requestQueue.isEmpty();
    }
}


public class BookMyStayApp {
    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Single Room", 2);
        Reservation r2 = new Reservation("Bob", "Double Room", 3);
        Reservation r3 = new Reservation("Charlie", "Suite Room", 1);

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        bookingQueue.displayAllRequests();

    }
}
