import java.time.LocalDate;
import java.util.Date;

public interface Reservation {
    void makeReservation(int book_id, int user_id, Date register_date);
    void confirmReservation(int reservation_id);
    void cancelReservation(int reservation_id);
}