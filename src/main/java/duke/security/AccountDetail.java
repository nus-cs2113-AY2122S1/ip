package duke.security;

import java.util.Scanner;

public class AccountDetail {
    private static String username = "John Doe";
    private static String password = "password";
    private static int points = 0;

    public AccountDetail() {
        Scanner in = new Scanner(System.in);
        setupUsernamePassword(in);
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        if (username.isBlank()) {
            AccountDetail.username = "dukeBot";
        } else {
            AccountDetail.username = username;
        }
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        if (password.isBlank()) {
            AccountDetail.password = "bukeDot";
        } else {
            AccountDetail.password = password;
        }
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        AccountDetail.points = points;
    }

    private static void setupUsernamePassword(Scanner in) {
        AccountDetail user = new AccountDetail();
        System.out.print("Username [dukeBot]: ");
        AccountDetail.setUsername(in.nextLine());
        System.out.print("Password [bukeDot]: ");
        AccountDetail.setPassword(in.nextLine());
    }
}
