import java.util.Scanner;

public class Duke {
    private static UserManager userManager = new UserManager();

    public static void main (String[] args) {
        String userName = userManager.getUserName();
        User newUser = new User(userName, userManager.loadUser(userName));
        newUser.startServe();
    }
}


