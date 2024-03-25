package reservation;

import reservation.BookVO;
import reservation.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ReservationDAO() {
        Connect connect = new Connect();
        this.conn = connect.getConnection();
    }

    // 도서를 검색 메서드(test를 위해서 필요)
    public List<BookVO> searchBooks(String bookTitle) {
        List<BookVO> books = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Book WHERE title = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookTitle);
            rs = pstmt.executeQuery();
            if (rs.next()) { //db의 행을 한줄씩 읽으면서 수행하는 next()메서드
                do {  // 반드시 한번은 수행되어야 이후 while문에서 검색 결과가 없다는 else기능을 수행할수있다.
                    BookVO book = new BookVO(
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("publication_year"),
                            rs.getString("ISBN"),
                            rs.getBoolean("is_available"),
                            rs.getString("publisher"),
                            rs.getString("category")
                    );
                    books.add(book);
                } while (rs.next());
            } else {
                System.out.println("검색 결과가 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return books; //return된 값은 List에 담겨진다.
    }

    // 책을 예약하는 메서드
    public void makeReservation(int userId, int bookId) {
        try {
            // 해당 책의 예약 가능 여부를 확인
            String checkAvailabilitySql = "SELECT is_available FROM Book WHERE book_id = ?";
            pstmt = conn.prepareStatement(checkAvailabilitySql);
            pstmt.setInt(1, bookId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                boolean isAvailable = rs.getBoolean("is_available");
                if (isAvailable) {
                    // 예약 가능한 경우에만 예약 생성
                    String insertSql = "INSERT INTO Reservation (book_id, user_id, register_date) VALUES (?, ?, NOW());";
                    pstmt = conn.prepareStatement(insertSql);
                    pstmt.setInt(1, bookId);
                    pstmt.setInt(2, userId);
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        // 예약 성공 시 해당 책의 is_available 상태를 false로 업데이트
                        String updateSql = "UPDATE Book SET is_available = 0 WHERE book_id = ?;";
                        pstmt = conn.prepareStatement(updateSql);
                        pstmt.setInt(1, bookId);
                        pstmt.executeUpdate();
                        System.out.println(userId + " 님, 예약이 완료되었습니다.");
                    }
                } else {
                    System.out.println("이 책은 대출되어있어서 예약할수없습니다.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }


    // 예약을 취소하는 메서드
    public void cancelReservation(int reservationId) {
        try {
            String deleteSql = "DELETE FROM Reservation WHERE reservation_id = ?;";
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setInt(1, reservationId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // 예약 취소 성공 시 해당 책의 is_available 상태를 true로 업데이트
                String updateSql = "UPDATE Book SET is_available = 1 WHERE book_id IN (SELECT book_id FROM Reservation WHERE reservation_id = ?);";
                // 서브쿼리의 조건이 맞을때(IN)(=으로 대체가능) ~~~~
                pstmt = conn.prepareStatement(updateSql);
                pstmt.setInt(1, reservationId);
                pstmt.executeUpdate();
                System.out.println("예약이 취소되었습니다.");
            } else {
                System.out.println("예약 취소 실패!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // 예약 확인하는 메서드
    // 예약 확인하는 메서드 -> 수정해야함
    // 유저1이 2권 이상 예약한 상태를 리스트로 표시해줄수 있는 기능 추가
    public List<ReservationVO> confirmReservation(int userId) {
        List<ReservationVO> reservations = new ArrayList<>();
        try {
            String sql = "SELECT r.reservation_id, r.book_id, b.title, r.register_date FROM Reservation r JOIN Book b ON r.book_id = b.book_id WHERE r.user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ReservationVO reservation = new ReservationVO();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setBookId(rs.getInt("book_id"));
                reservation.setBookTitle(rs.getString("title"));
                reservation.setRegisterDate(rs.getDate("register_date").toLocalDate());
                reservations.add(reservation); // 리스트에 예약 정보 추가
            }

            // 예약 정보 출력
            if (!reservations.isEmpty()) {
                System.out.println("예약된 도서 목록:");
                for (ReservationVO reservation : reservations) {
                    System.out.println("예약 ID: " + reservation.getReservationId());
                    System.out.println("도서 ID: " + reservation.getBookId());
                    System.out.println("도서 제목: " + reservation.getBookTitle());
                    System.out.println("예약 일자: " + reservation.getRegisterDate());
                    System.out.println();
                }
            } else {
                System.out.println("예약내역이 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return reservations;
    }



    // 리소스 닫는 메서드
    private void closeResources() { // final 동작을 묶어주는 메서드
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
