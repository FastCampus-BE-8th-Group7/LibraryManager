package reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public Connect(){
        // 클래스 로딩 확인하기 (드라이버)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("클래스 로딩 성공");
        } catch(
                ClassNotFoundException e)
        {
            System.out.println("클래스 로딩 실패");
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        // DB 연결 정보
        String url = "jdbc:mysql://localhost:3306/newdb";
        String username = "root";
        String password = "1234";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("연결 완료!");
        } catch (SQLException e) {
            System.err.println("연결 실패! " + e.getMessage());
        }
        return connection;
    }
}
