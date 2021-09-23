import tasks.Tasks;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Handles the user interface, including the printing of the start and exit messages.
 */
public class Ui {

    public static void showStartMessage() {
        String line = "_______________________________________________________________________________________________";
        String logo =
                "      ____                U _____ u   ____      ____        _" + System.lineSeparator() +
                        "     / __\"| u      ___    \\| ___\"|/U |  _\"\\ uU |  _\"\\ u U  /\"\\  u" + System.lineSeparator() +
                        "    <\\___ \\/      |_\"_|    |  _|\"   \\| |_) |/ \\| |_) |/  \\/ _ \\/" + System.lineSeparator() +
                        "     u___) |       | |     | |___    |  _ <    |  _ <    / ___ \\" + System.lineSeparator() +
                        "     |____/>>    U/| |\\u   |_____|   |_| \\_\\   |_| \\_\\  /_/   \\_\\" + System.lineSeparator() +
                        "      )(  (__).-,_|___|_,-.<<   >>   //   \\\\_  //   \\\\_  \\\\    >>" + System.lineSeparator() +
                        "     (__)      \\_)-' '-(_/(__) (__) (__)  (__)(__)  (__)(__)  (__)";

        System.out.println("Hello from" + System.lineSeparator() + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Sierra!" + System.lineSeparator() + "What can I do for you?");
        System.out.println(line);
    }


    public static void executeTillExit() {
        Scanner scannerObj = new Scanner(System.in);
        ArrayList<Tasks> tasksAL = new ArrayList<>();

        while (Parser.getIsContinue()) {
            System.out.println("I can do the following: Echo, Tasks");
            String userChoice = scannerObj.nextLine().toLowerCase();
            Parser.executeUserChoice(userChoice, tasksAL);
        }
    }

    public static void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
