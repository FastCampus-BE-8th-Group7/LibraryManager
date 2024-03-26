import db.Database;
import user.User;
import user.UserController;
import user.UserView;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Database.connect();

        UserController userController = new UserController();
        UserView userView = new UserView();

        // 사용자 등록
        User user = userController.registerUser("손민주", "010-1234-5678", "sonminju@example.com", "12345");
        System.out.println();

        // 사용자 정보 수정
        if (user != null) {
            userController.updateUser(user.getUserId(), "손민주", "010-1233-5678", "sonminju@example.com", "12345");
        }
        System.out.println();

        // 사용자 정보 조회
        user = userController.getUser(user.getUserId());
        if (user != null) {
            userView.viewUserInfo(user);
        }
        System.out.println();

        // 사용자 목록 조회
        List<User> users = userController.getAllUsers();
        userView.viewUserInfoList(users);
        System.out.println();

        // 사용자 삭제
        if (user != null) {
            userController.deleteUser(user.getUserId());
        }
        System.out.println();

        Database.disconnect();

    }
}