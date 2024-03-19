import java.time.LocalDate;
import java.util.Date;

public class ReservationDTO {
    private int reservation_id;
    private int book_id;
    private int user_id;
    private Date register_date;

    //생성자


    public int getReservation_id() {
        return reservation_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }
}
