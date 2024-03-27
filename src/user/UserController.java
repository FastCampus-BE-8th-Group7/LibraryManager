package user;

import db.Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    /**
     * 사용자 등록
     * @param name
     * @param phone
     * @param email
     * @param password
     * @return
     */
    public User registerUser(String name, String phone, String email, String password) {
        String sql = "INSERT INTO User (name, phone, email, password, registered_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("사용자 등록 실패");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println(name + " 사용자 등록 완료");
                    int userId = generatedKeys.getInt(1);
                    return new User(userId, name, phone, email, LocalDateTime.now(), password);
                } else {
                    throw new SQLException("사용자 등록 실패: ID 획득 실패");
                }
            }
        } catch (SQLException e) {
            System.out.println("사용자 등록 실패: " + e.getMessage());
            return null;
        }
    }

    /**
     * 사용자 정보 수정
     * @param userId
     * @param name
     * @param phone
     * @param email
     * @param password
     */
    public void updateUser(int userId, String name, String phone, String email, String password) {
        String sql = "UPDATE User SET name = ?, phone = ?, email = ?, password = ? WHERE user_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setInt(5, userId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(name + " 사용자 정보 수정 완료");
            } else {
                System.out.println(name + " 사용자 정보 수정 실패");
            }
        } catch (SQLException e) {
            System.out.println("사용자 정보 수정 실패: " + e.getMessage());
        }
    }

    /**
     * 사용자 삭제
     * @param userId
     */
    public void deleteUser(int userId) {
        String sql = "DELETE FROM User WHERE user_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("사용자 정보 삭제 완료");
            } else {
                System.out.println("사용자 정보 삭제 실패");
            }
        } catch (SQLException e) {
            System.out.println("사용자 정보 삭제 실패: " + e.getMessage());
        }
    }

    /**
     * 데이터베이스에서 주어진 userId에 해당하는 사용자 정보 조회
     * @param userId
     * @return User 객체
     */
    public User getUser(int userId) {
        String sql = "SELECT * FROM User WHERE user_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                LocalDateTime registeredAt = rs.getTimestamp("registered_at").toLocalDateTime();
                String password = rs.getString("password");
                return new User(userId, name, phone, email, registeredAt, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 데이터베이스에서 모든 사용자 정보 조회
     * @return User 객체 리스트
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, name, phone, email, password, registered_at FROM User";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                LocalDateTime registeredAt = rs.getTimestamp("registered_at").toLocalDateTime();

                users.add(new User(userId, name, phone, email, registeredAt, password));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }
}