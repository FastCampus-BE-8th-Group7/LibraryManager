package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {
    private static Connection con = null;
    private static final Properties prop = new Properties(); // db 관련된 개인 정보가 저장된 파일

    public static void connect() throws IOException {
        prop.load(new FileInputStream("config.properties"));
        String server = prop.getProperty("db.server"); // 서버 주소
        String database = prop.getProperty("db.database"); // 데이터베이스 이름
        String username = prop.getProperty("db.username"); // 접속자 id
        String password = prop.getProperty("db.password"); // 접속자 pw

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
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", username, password);
            //System.out.println("연결 완료");
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
        } catch (IOException e) {
            System.err.println("데이터베이스 연결 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
}