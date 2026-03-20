import java.util.*;

// ---------------- Reservation ----------------
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// ---------------- BookingHistory ----------------
class BookingHistory {

    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

// ---------------- BookingReportService ----------------
class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("Booking History Report");

        for (Reservation r : history.getConfirmedReservations()) {
            System.out.println("Guest: " + r.guestName + ", Room Type: " + r.roomType);
        }
    }
}

// ---------------- MAIN CLASS ----------------
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting\n");

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from UC6)
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history);
    }
}