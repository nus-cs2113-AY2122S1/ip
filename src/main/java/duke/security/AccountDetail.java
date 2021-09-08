package duke.security;

public class AccountDetail {
    private static String username = "John Doe";
    private static String password = "password";
    private static int points = 0;

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
}
