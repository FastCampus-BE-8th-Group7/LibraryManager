package reservation;

import reservation.Reservation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ReservationControl implements Reservation {
    private Map<Integer, ReservationDTO> reservations = new HashMap<>();
    private int currentId = 1;

    @Override
    public void makeReservation(int book_id, int user_id, Date register_date) {
//        ReservationDTO reservation = new ReservationDTO(currentId, book_id, user_id, register_date);// 오류처리 x
        ReservationDTO reservation = new ReservationDTO();
        reservations.put(currentId, reservation);
        System.out.println("예약번호 " + currentId + "로 예약되었습니다.");
        currentId++;
    }

    @Override
    public void confirmReservation(int reservation_id) {
        ReservationDTO reservation = reservations.get(reservation_id);
        if (reservation != null) {
            System.out.println("예약번호: " + reservation.getReservation_id() + ", 예약일: " + reservation.getRegister_date());
        } else {
            System.out.println("예약번호 " + reservation_id + "는 존재하지 않습니다.");
        }
    }

    @Override
    public void cancelReservation(int reservation_id) {
        if (reservations.containsKey(reservation_id)) {
            reservations.remove(reservation_id);
            System.out.println("예약번호 " + reservation_id + "의 예약이 취소되었습니다.");
        } else {
            System.out.println("예약번호 " + reservation_id + "는 존재하지 않습니다.");
        }
    }
}