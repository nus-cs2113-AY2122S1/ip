package duke;

import java.util.ArrayList;

public class Ui {

    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String LOGO =  "    #    ####### #          #     #####\n"
            + "   # #      #    #         # #   #     #\n"
            + "  #   #     #    #        #   #  #\n"
            + " #     #    #    #       #     #  #####\n"
            + " #######    #    #       #######       #\n"
            + " #     #    #    #       #     # #     #\n"
            + " #     #    #    ####### #     #  #####\n";
    private static final String WELCOME_MESSAGE = HORIZONTAL_LINE + "Hello! I'm Atlas!\n"
            + "What can I do for you today?\n" + HORIZONTAL_LINE;

    public static void printHorizontalLine() {
        System.out.print(HORIZONTAL_LINE);
    }

    public static void printAddedTaskMessage(Task task) {
        System.out.print(HORIZONTAL_LINE + "Understood. I've added this task: " + System.lineSeparator()
        + task + System.lineSeparator());
    }

    public static void printTotalNumberOfTasks(ArrayList<Task> tasks) {
        System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
        System.out.print(HORIZONTAL_LINE);
    }

    public static void printDoneTask(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.print(HORIZONTAL_LINE);
    }

    public static void printUndoneTask(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("I've undone this task for you:");
        System.out.println(task);
        System.out.print(HORIZONTAL_LINE);
    }
}
