package user;

public class UserView {
    public void viewUserInfo(User user) {
        System.out.println("User ID: " + user.getUserId());
        System.out.println("Name: " + user.getName());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Registered At: " + user.getRegisteredAt());
    }
}