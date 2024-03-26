import book.*;
import bookDto.BookDto;
import bookDto.PhysicalBookDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static Connection con = null;
    public static void main(String[] args) {
        connect();

    }
    public static void connect() {
        String server = "localhost"; // 서버 주소
        String user_name = "root"; // 접속자 id
        String password = "test123"; // 접속자 pw

        // JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
            e.printStackTrace();
            return;
        }

        // 데이터베이스 접속
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + "?useSSL=false", user_name, password);
            System.out.println("연결 완료");
        } catch(SQLException e) {
            System.err.println("연결 오류" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        // 접속 종료
        try {
            if(con != null && !con.isClosed()) {
                con.close();
                System.out.println("연결 종료");
            }
        } catch (SQLException e) {
            System.err.println("연결 종료 오류" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            System.err.println("데이터베이스 연결 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
}