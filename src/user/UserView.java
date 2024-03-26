package user;

import java.util.List;

public class UserView {
    public void viewUserInfo(User user) {
        System.out.println("ID: " + user.getUserId());
        System.out.println("Name: " + user.getName());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Email: " + user.getEmail());
        System.out.println("RegisteredAt: " + user.getRegisteredAt());
    }

    public void viewUserInfoList(List<User> userList) {
        System.out.printf("%-6s|%-8s|%-16s|%-24s|%-24s\n", "ID", "Name", "Phone", "Email", "RegisteredAt");
        for (User user : userList) {
            System.out.printf("%-6d|%-8s|%-16s|%-24s|%-24s\n",
                    user.getUserId(),
                    user.getName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getRegisteredAt().toString());
        }
    }
}