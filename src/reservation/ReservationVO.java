package reservation;

import java.time.LocalDate;
import java.util.Date;

public class ReservationVO {
    //BookDB 에서 필요한 컬럼
    private String bookTitle;
    private boolean isAvailable;

    //LoanDB 에서 필요한 컬럼
    private LocalDate timedDate;
    //Reservation 에서 필료한 컬럼
    private int reservationId;
    private int bookId;
    private int userId;
    private LocalDate registerDate;

    public ReservationVO(){}

    public ReservationVO(String bookTitle, boolean isAvailable, LocalDate timedDate, int reservationId, int bookId, int userId, LocalDate registerDate) {
        this.bookTitle = bookTitle;
        this.isAvailable = isAvailable;
        this.timedDate = timedDate;
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.userId = userId;
        this.registerDate = registerDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDate getTimedDate() {
        return timedDate;
    }

    public void setTimedDate(LocalDate timedDate) {
        this.timedDate = timedDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "ReservationVO{" +
                "bookTitle='" + bookTitle + '\'' +
                ", isAvailable=" + isAvailable +
                ", timedDate=" + timedDate +
                ", reservationId=" + reservationId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", registerDate=" + registerDate +
                '}';
    }
}
