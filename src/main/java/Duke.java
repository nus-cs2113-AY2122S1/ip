import userManagement.UserManager;
import userManagement.UserServerCLI;
import userManagement.UserServerGUI;

import java.util.Scanner;

public class Duke {
    private static final int CLI = 0;
    private static final int GUI = 1;

    private static UserManager userManager = new UserManager();

    public static void main (String[] args) {

        if (selectMode() == CLI) {
            UserServerCLI newUser = new UserServerCLI(userManager);
            newUser.run();
        } else {
            new UserServerGUI(userManager);
        }
    }


    private static int selectMode() {
        System.out.println("    Hello! I am Duke, and I have CLI and GUI modes. Please type 0 if you want to try CLI" +
                " or 1 if you want to use GUI");
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                int mode = sc.nextInt();
                if (mode == 0 || mode == 1) {
                    return mode;
                }
            } else {
                sc.next();
            }
            System.out.println("    Ooops!!! I don't understand it! Please type 0 if you want to try CLI " +
                    "or 1 if you want to use GUI");
        }
    }
}


