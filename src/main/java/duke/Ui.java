package duke;

import duke.exception.DukeException;
import duke.task.Task;

import static duke.TaskList.count;
import static duke.TaskList.list;

/**
 * To print messages and details to deal with user interactions
 */
public class Ui {
    public static void printLineSeparator() {
        String line = "________________________________________________________";
        System.out.println(line);
    }

    public static void printWelcomeMessage() {
        //logo
        String logo = " _____  ________    _        ______  ___  ___  ______\n"
                + "|_   _||  _   _ |  / \\    .' ___  | |_  ||  _||__   _|\n"
                + "  | |     | |     / _ \\  / .'   \\_   | |__| |    | |\n"
                + "  | |     | |    / ___ \\ | |         |  __  |    | |\n"
                + " _| |_   _| |_ _/ /     \\ \\ `.___.'\\_| |  | |   _| |_\n"
                + "|_____| |_____|____| |____ `.____ .|____||____||_____|\n";

        System.out.println("THE GREATEST SHINOBI\n" + logo
                + "\nWelcome! You have entered my illusion where I will be your partner\n"
                + "and I will make you productive in order for you to reach your goals\n"
                + "so that I can fulfill my dream of making someone great.\n"
                + "Go ahead, give your command\n");

        printLineSeparator();
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    public static void printByeMessage() {
        printLineSeparator();
        System.out.println("Farewell. Come back when you need more help");
        printLineSeparator();
    }

    public static void printErrorMessage(DukeException e) {
        printLineSeparator();
        System.out.println(e.getMessage());
        printLineSeparator();
    }

    public static void printAddTaskMessage(Task task) {
        printLineSeparator();
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + count + " tasks in the list.");
        printLineSeparator();
    }

    public static void printDeleteTaskMessage(int taskIndex) {
        Ui.printLineSeparator();
        System.out.println("Noted. I've deleted this task:\n" + list.get(taskIndex) + "\nNow you have " + (count - 1) + " tasks in the list.");
        Ui.printLineSeparator();
    }

    public static void printDoneTaskMessage(int taskIndex) {
        Ui.printLineSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(taskIndex));
        Ui.printLineSeparator();
    }

    public static void printTaskList() {
        Ui.printLineSeparator();
        System.out.println("Here are the tasks in your list:");

        //Lists down all the tasks added along with its status
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        Ui.printLineSeparator();
    }

    public static void printMatchingTasks(String keyword) throws DukeException {
        int numOfTasksFound = 0;

        Ui.printLineSeparator();
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            String taskDescription = list.get(i).getDescription().toLowerCase();
            if (taskDescription.contains(keyword)) {
                System.out.println((i + 1) + ". " + list.get(i));
                numOfTasksFound++;
            }
        }

        if (numOfTasksFound == 0) {
            throw new DukeException("No matching tasks found, try another keyword");
        }

        Ui.printLineSeparator();
    }

    public static void printEmptyListMessage() {
        Ui.printLineSeparator();
        System.out.println("There are no more tasks in the list!");
        Ui.printLineSeparator();
    }
}
