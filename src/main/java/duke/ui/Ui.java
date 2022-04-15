package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String LOGO = "    #    ####### #          #     #####\n"
            + "   # #      #    #         # #   #     #\n"
            + "  #   #     #    #        #   #  #\n"
            + " #     #    #    #       #     #  #####\n"
            + " #######    #    #       #######       #\n"
            + " #     #    #    #       #     # #     #\n"
            + " #     #    #    ####### #     #  #####\n";
    private static final String WELCOME_MESSAGE = HORIZONTAL_LINE + "Hello! I'm Atlas!\n"
            + "What can I do for you today?\n" + HORIZONTAL_LINE;
    private static final String DONE_TASK = "Nice! I've marked this task as done:";
    private static final String UNDONE_TASK = "I've undone this task for you:";
    private static final String REMOVE_TASK = "Noted. I've removed this task: ";
    private static final String DELETE_ALL_TASKS = "I've deleted all tasks!";
    private static final String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE = "I don't understand that. Please try again!";


    public static void greetUser() {
        System.out.print("Hello from\n" + LOGO + WELCOME_MESSAGE);
    }

    public static void printAddedTaskMessage(Task task, ArrayList<Task> tasks) {
        System.out.print(HORIZONTAL_LINE + "Understood. I've added this task: " + System.lineSeparator()
                + task + System.lineSeparator() + "Now you have " + tasks.size()
                + " tasks in the list." + System.lineSeparator() + HORIZONTAL_LINE);
    }


    public static void printDoneTask(Task task) {
        System.out.print(HORIZONTAL_LINE + DONE_TASK + task
                + System.lineSeparator() + HORIZONTAL_LINE);
    }

    public static void printUndoneTask(Task task) {
        System.out.print(HORIZONTAL_LINE + UNDONE_TASK + task
                + System.lineSeparator() + HORIZONTAL_LINE);
    }

    public static void printDeleteMessage(Task task, ArrayList<Task> tasks) {
        System.out.print(HORIZONTAL_LINE + REMOVE_TASK + task
                + System.lineSeparator() + "Now you have " + (tasks.size() - 1)
                + " tasks in the list." + System.lineSeparator() + HORIZONTAL_LINE);
    }

    public static void printDeleteAllMessage() {
        System.out.print(HORIZONTAL_LINE + DELETE_ALL_TASKS + System.lineSeparator()
                + HORIZONTAL_LINE);
    }

    public static void printFarewellMessage() {
        System.out.print(HORIZONTAL_LINE + FAREWELL_MESSAGE + System.lineSeparator()
                + HORIZONTAL_LINE);
    }

    public static void printErrorMessage() {
        System.out.print(HORIZONTAL_LINE + ERROR_MESSAGE + System.lineSeparator()
                + HORIZONTAL_LINE);
    }

    public static void printHorizontalLine() {
        System.out.print(HORIZONTAL_LINE);
    }

    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public static void printFilteredTaskList(ArrayList<Task> filteredTasks) {
        System.out.print(HORIZONTAL_LINE + "Here are the matching tasks I found: "
                + System.lineSeparator());
        printList(filteredTasks);
        System.out.print(HORIZONTAL_LINE);
    }
}
