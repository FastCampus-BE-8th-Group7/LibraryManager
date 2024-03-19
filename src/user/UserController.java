package user;

public class UserController {
    public User registerUser(int userId, String name, String phone, String email, String password) {
        System.out.println(name + "님의 회원가입이 성공했습니다.");
        return new User(userId, name, phone, email, password);
    }

    public void updateUser(User user, String name, String phone, String email, String password) {
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        System.out.println("사용자 정보가 수정되었습니다.");
    }

    public void deleteUser(User user) {
        System.out.println(user.getName() + "님의 사용자 정보를 삭제합니다.");
    }
}
