package book;

import db.Database;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// import java.util.HashMap;

public class BookController {
    /**
     * 책 등록
     * @param title
     * @param author
     * @param publicationYear
     * @param isbn
     * @param isAvailable
     * @param publisher
     * @param category
     */
    public void registerBook(String title, String author, LocalDate publicationYear, String isbn,
                             boolean isAvailable, String publisher, String category) {
        String sql = "INSERT INTO Book (title, author, publication_year, isbn, is_available, publisher, category) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setDate(3, Date.valueOf(publicationYear));
            pstmt.setString(4, isbn);
            pstmt.setBoolean(5, isAvailable);
            pstmt.setString(6, publisher);
            pstmt.setString(7, category);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(title + " 등록 완료");
            } else {
                System.out.println("책 등록 실패");
            }

        } catch (SQLException e) {
            System.out.println("책 등록 실패: " + e.getMessage());
        }
    }

    /**
     * 책 정보 수정
     * @param bookId
     * @param title
     * @param author
     * @param publicationYear
     * @param isbn
     * @param isAvailable
     * @param publisher
     * @param category
     */
    public void updateBook(int bookId, String title, String author, LocalDate publicationYear, String isbn,
                           boolean isAvailable, String publisher, String category) {
        String sql = "UPDATE Book SET title = ?, author = ?, publication_year = ?, isbn = ?, is_available = ?, publisher = ?, category = ? WHERE book_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setDate(3, Date.valueOf(publicationYear));
            pstmt.setString(4, isbn);
            pstmt.setBoolean(5, isAvailable);
            pstmt.setString(6, publisher);
            pstmt.setString(7, category);
            pstmt.setInt(8, bookId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(title + " 정보 수정 완료");
            } else {
                System.out.println("책 정보 수정 실패");
            }

        } catch (SQLException e) {
            System.out.println("책 정보 수정 실패: " + e.getMessage());
        }
    }

    /**
     * 책 삭제
     * @param bookId
     */
    public void deleteBook(int bookId) {
        String sql = "DELETE FROM Book WHERE book_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("책 삭제 완료");
            } else {
                System.out.println("책 삭제 실패");
            }

        } catch (SQLException e) {
            System.out.println("책 삭제 실패: " + e.getMessage());
        }
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("publication_year").toLocalDate(),
                        rs.getString("isbn"),
                        rs.getBoolean("is_available"),
                        rs.getString("publisher"),
                        rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            System.out.println("책 조회 실패: " + e.getMessage());
        }
        return books;
    }

    public List<Book> getSearchBooks(String searchQuery) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE REPLACE(title, ' ', '') LIKE ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchQuery.replace(" ", "") + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getDate("publication_year").toLocalDate(),
                            rs.getString("isbn"),
                            rs.getBoolean("is_available"),
                            rs.getString("publisher"),
                            rs.getString("category")
                    );
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            System.out.println("책 검색 실패: " + e.getMessage());
        }
        return books;
    }

}
