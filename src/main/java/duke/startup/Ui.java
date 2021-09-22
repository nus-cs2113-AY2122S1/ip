package duke.startup;
import Type.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static void printLine() {
        System.out.println("                 ...                 ");
    }

    public static void sayHi(String username) {
        printLine();
        System.out.println("Hello " + username + "!" + "\n" + "I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you  again soon!");
        printLine();
    }


    public static boolean userStopAdd(ArrayList<Task> taskList, String userInput) {
        if (!userInput.equals("stop")) {
            Task taskToAdd = parseInputAsTask(userInput);
            taskList.add(taskToAdd);
        } else {
            return true;
        }
        return false;
    }

    public static String readInputEchoCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        System.out.println("    " + command);
        return command;
    }

    public void showLoadingError() {
        System.out.println("Loading Error");
    }
}
