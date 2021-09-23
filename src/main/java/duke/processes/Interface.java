package duke.processes;

import duke.Duke;
import duke.processes.tasks.Task;

import java.util.Scanner;

public class Interface {
    public static final String lineBreak = "..........................." +
            ".......................................";

    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Greet user
     */
    public static void introductoryMessage() {
        String logo = "  /\\ _ /\\\n"
                + " #  @ @  #    Welcome to IKAROS!\n"
                + " #   ^   #  Your one and only butler\n"
                + " #########";
        System.out.println(Interface.lineBreak);

        System.out.println(logo);
        System.out.println(Interface.lineBreak);
        System.out.println("Below is your current list of tasks."
                + System.lineSeparator()
                + "What further assistance do you require?");
        System.out.println(Interface.lineBreak);
    }

    /**
     * Bid Farewell to the user
     */
    public static void goodbyeMessage() {
        System.out.println("GoodBye, Ikaros awaits for future commands");
        System.out.println(Interface.lineBreak);
    }

    public static void printList() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : Duke.taskList) {
            System.out.println(i + ".[" + task.getTaskID() + "]" +
                    "[" + task.getStatusIcon() +
                    "] " + task.description + task.getDate());
            i++;
        }
    }
}
