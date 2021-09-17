import userManagement.UserManager;
import userManagement.UserServer;

public class Duke {
    private static UserManager userManager = new UserManager();

    public static void main (String[] args) {
        UserServer newUser = new UserServer(userManager);
        newUser.run();
    }
}


