package user;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserView {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void viewUserInfo(User user) {
        System.out.println("ID: " + user.getUserId());
        System.out.println("Name: " + user.getName());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Email: " + user.getEmail());
        System.out.println("RegisteredAt: " + user.getRegisteredAt().format(formatter));
    }

    public void viewUserInfoList(List<User> userList) {
        System.out.printf("%-6s|%-8s|%-16s|%-24s|%-20s\n", "ID", "Name", "Phone", "Email", "RegisteredAt");
        for (User user : userList) {
            System.out.printf("%-6d|%-8s|%-16s|%-24s|%-20s\n",
                    user.getUserId(),
                    user.getName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getRegisteredAt().format(formatter));
        }
    }
}