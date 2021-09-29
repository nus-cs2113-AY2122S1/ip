package duke.system;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A system component responsible for interaction with the user.
 */
public class Ui {
    static final String DUKE_LOGO =
            "      ____        _        \n" +
            "     |  _ \\ _   _| | _____ \n" +
            "     | | | | | | | |/ / _ \\\n" +
            "     | |_| | |_| |   <  __/\n" +
            "     |____/ \\__,_|_|\\_\\___|\n";
    static final String LINE_SEPARATOR = "     __________________________________________" +
            "______________________________________________________________\n";
    static final String SPACING = "     ";
    static final String CANNOT_IDENTIFY =
            "I am sorry, but I do not know what do you mean. " +
                    "Please key in a valid input.";
    static final String GREETINGS = LINE_SEPARATOR
            + SPACING + "Hello! I'm Duke\n"
            + SPACING + "What can I do for you?\n"
            + LINE_SEPARATOR;
    static final String COUNT_NUMBER = " tasks in total";

    /**
     * Print greeting message when the program starts.
     */
    public void printGreetings() {
        System.out.println(DUKE_LOGO + GREETINGS);
    }

    /**
     * Print messages after marking a task as done.
     * @param taskDetail the detail of the task marked as done
     */
    public void printMarked(String taskDetail) {
        String update = LINE_SEPARATOR
                + SPACING + "Nice! I've marked this task as done: \n"
                + SPACING + taskDetail + "\n"
                + LINE_SEPARATOR;
        System.out.println(update);
    }

    /**
     * Print error messages when there are errors.
     * @param errorMessage error message to be printed
     */
    public void printErrorMessage(String errorMessage) {
        String error = LINE_SEPARATOR
                + SPACING + errorMessage + "\n"
                + LINE_SEPARATOR;
        System.out.println(error);
    }

    /**
     * Print messages after adding a task.
     * @param taskDetail the detail of the task added
     */
    public void printAdded(String taskDetail) {
        String response = LINE_SEPARATOR
                + SPACING + "added: " + taskDetail + "\n"
                + LINE_SEPARATOR;
        System.out.println(response);
    }

    /**
     * Print messages after deleting a task.
     * @param taskDetail the detail of the task deleted
     */
    public void printDeleted(String taskDetail) {
        String update = LINE_SEPARATOR
                + SPACING + "Noted! I've removed this task: \n"
                + SPACING + taskDetail + "\n"
                + LINE_SEPARATOR;
        System.out.println(update);
    }

    /**
     * Print goodbye to the user before exiting.
     */
    public void printBye() {
        String Bye = LINE_SEPARATOR
                + SPACING + "Bye. Hope to see you again soon!\n"
                + LINE_SEPARATOR;
        System.out.println(Bye);
    }

    /**
     * List all the tasks in the list.
     * @param tasks the task list holding tasks.
     */
    public void list(ArrayList<Task> tasks) {
        String listOutput = LINE_SEPARATOR;
        for (int i = 0; i < tasks.size(); i++) {
            listOutput += SPACING + (i + 1) + ". "
                    + tasks.get(i).toString() + "\n";
        }
        listOutput += "\n" + SPACING + tasks.size() + COUNT_NUMBER + "\n";
        listOutput += LINE_SEPARATOR;
        System.out.println(listOutput);

    }

    /**
     * Read the command from the user.
     * @return the user's command as String.
     */
    public String readCommand() {
        String fullCommand;
        Scanner in = new Scanner(System.in);
        fullCommand = in.nextLine();
        return fullCommand;
    }
}
