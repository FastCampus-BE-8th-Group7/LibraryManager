package loan;

import book.Book;
import database.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public LoanDAO() {
        Connect connect = new Connect();
        this.conn = connect.getConnection();
    }

    // 대출 이력 생성
    public String createLoanHistory(int bookId, int userId) {
        String bookName = "";
        boolean loanState = true;
        try {
            String checkLoanAble = "SELECT * FROM book WHERE book_id = ?";
            pstmt = conn.prepareStatement(checkLoanAble);
            pstmt.setInt(1, bookId);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                boolean isAvailable = rs.getBoolean("is_available");
                bookName = rs.getString("title");
                if (isAvailable) {
                    String insertSql = "INSERT INTO loan (book_id, user_id, start_date, end_date)" +
                            "VALUES (?, ?, now(), date_add(now(),INTERVAL 7 DAY));";

                    pstmt = conn.prepareStatement(insertSql);
                    pstmt.setInt(1, bookId);
                    pstmt.setInt(2, userId);
                    int rowsAffected = pstmt.executeUpdate();

                    if(rowsAffected > 0) {
                        String updateSql = "UPDATE book SET is_available = 0 WHERE book_id = ?;";
                        pstmt = conn.prepareStatement(updateSql);
                        pstmt.setInt(1, bookId);
                        pstmt.executeUpdate();
                    }
                } else {
                    loanState = false;
                }
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
        try {
            String findLoanId = "SELECT * FROM loan WHERE user_id = ? AND return_date IS NULL;";
            pstmt = conn.prepareStatement(findLoanId);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            rs.next();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookName + " 책을 반납 하였습니다.\n";
    }

    // user_id의 대출 이력 조회
    public List<Book> getLoanHistoryForUser(int user_id) {
        List<Book> books = new ArrayList<>();
        try{
            String getLoanHistory = "SELECT Book.* FROM Book INNER JOIN loan ON Book.book_id = loan.book_id WHERE loan.user_id = ?;";
            pstmt = conn.prepareStatement(getLoanHistory);
            pstmt.setInt(1, user_id);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublicationYear(rs.getDate("publication_year"));
                book.setIsbn(rs.getString("ISBN"));
                book.setPublisher(rs.getString("publisher"));
                book.setCategory(rs.getInt("category"));

                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
