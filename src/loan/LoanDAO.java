package loan;

import book.Book;
import db.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LoanDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 대출 이력 생성
    public String createLoanHistory(String bookTitle, int userId) {
        String bookName = "";
        boolean loanState = true;

        try (Connection conn = Database.getConnection()){
            String checkUserLoan = "SELECT * FROM loan WHERE user_id=? AND return_date IS NULL;";
            pstmt = conn.prepareStatement(checkUserLoan);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return "-1";
            }

            String findBookId = "SELECT * FROM book WHERE title=?;";
            pstmt = conn.prepareStatement(findBookId);
            pstmt.setString(1, bookTitle);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                int bookId = rs.getInt("book_id");

                String checkLoanAble = "SELECT * FROM book WHERE book_id = ?";
                pstmt = conn.prepareStatement(checkLoanAble);
                pstmt.setInt(1, bookId);
                rs = pstmt.executeQuery();

                if(rs.next()) {
                    boolean isAvailable = rs.getBoolean("is_available");
                    bookName = rs.getString("title");
                    if (isAvailable) {
                        String insertSql = "INSERT INTO loan (book_id, user_id, start_date, end_date, return_date)" +
                                "VALUES (?, ?, now(), date_add(now(),INTERVAL 7 DAY), null);";

                        pstmt = conn.prepareStatement(insertSql);
                        pstmt.setInt(1, bookId);
                        pstmt.setInt(2, userId);
                        pstmt.executeUpdate();

                        String updateSql = "UPDATE book SET is_available = 0 WHERE book_id = ?;";
                        pstmt = conn.prepareStatement(updateSql);
                        pstmt.setInt(1, bookId);
                        pstmt.executeUpdate();

                    } else {
                        loanState = false;
                    }
                }

            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!loanState) {
            return bookName + "은 대출중인 도서입니다.\n";
        }

        return bookName + " 책을 대출하였습니다.\n";
    }

    // 대출도서 반납(update)
    public String updateLoanHistoryForReturn(int userId) {
        String bookName = "";
        int loanId;
        try (Connection conn = Database.getConnection()){
            String findLoanId = "SELECT * FROM loan WHERE user_id = ? AND return_date IS NULL;";
            pstmt = conn.prepareStatement(findLoanId);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                loanId = rs.getInt("loan_id");

                String updateLoan = "UPDATE loan set return_date = now() WHERE loan_id = ?;";
                pstmt = conn.prepareStatement(updateLoan);
                pstmt.setInt(1, loanId);
                pstmt.executeUpdate();

                String getBookId = "SELECT book.book_id, book.title FROM loan, book WHERE loan.book_id = book.book_id AND loan_id = ?;";
                pstmt = conn.prepareStatement(getBookId);
                pstmt.setInt(1, loanId);
                rs = pstmt.executeQuery();
                rs.next();
                int bookId = rs.getInt("book_id");

                String updateBook = "UPDATE book set is_available = 1 WHERE book_id = ?;";
                pstmt = conn.prepareStatement(updateBook);
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
                bookName = rs.getString("title");
            } else {
                return "대출중인 도서가 없습니다.\n";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookName + " 책을 반납 하였습니다.\n";
    }

    // user_id의 대출 이력 조회
    public HashMap<Book, LocalDate> getLoanHistoryForUser(int user_id) {
        HashMap<Book, LocalDate> loanHistory = new HashMap<Book, LocalDate>();
        try (Connection conn = Database.getConnection()){
            String getLoanHistory = "SELECT book.*, loan.return_date FROM book INNER JOIN loan ON Book.book_id = loan.book_id WHERE loan.user_id = ?;";
            pstmt = conn.prepareStatement(getLoanHistory);
            pstmt.setInt(1, user_id);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("publication_year").toLocalDate(),
                        rs.getString("ISBN"),
                        rs.getBoolean("is_available"),
                        rs.getString("publisher"),
                        rs.getString("category")
                );
                try {
                    loanHistory.put(book, rs.getDate("return_date").toLocalDate());
                } catch (NullPointerException e) {
                    loanHistory.put(book, LocalDate.of(3000, 1, 1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loanHistory;
    }

    // book_id의 대출 상태 조회
    public Book getLoanStatusForBook(String bookTitle) {
        Book book=null;
        try (Connection conn = Database.getConnection()){
            String getBookState = "SELECT * FROM book WHERE title=?;";
            pstmt = conn.prepareStatement(getBookState);
            pstmt.setString(1, bookTitle);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("publication_year").toLocalDate(),
                        rs.getString("isbn"),
                        rs.getBoolean("is_available"),
                        rs.getString("publisher"),
                        rs.getString("category"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }
}
