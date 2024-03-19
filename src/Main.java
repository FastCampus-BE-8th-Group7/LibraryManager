import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ReservationControl reservationControl = new ReservationControl();
        reservationControl.makeReservation(1, 1,new Date());
        reservationControl.confirmReservation(1);
        reservationControl.cancelReservation(1);

    }
}