import UI.GUI.GUI;
import UI.GUI.ServePage.ServeGUI;
import userManagement.UserManager;

public class Duke {
    private static UserManager userManager = new UserManager();

    public static void main (String[] args) {

//        UserServer newUser = new UserServer(userManager);
//        newUser.run();
        new ServeGUI();

    }
}


