package duke.system;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A system component responsible for interaction with the user.
 */
public class Ui {
    static final String LINE_SEPARATOR = "     ____________________________________________________________________\n";
    static final String SPACING = "     ";
    static final String CANNOT_IDENTIFY =
            "I am sorry, but I do not know what do you mean. " +
                    "Please key in a valid input.";
    static final String GREETINGS = LINE_SEPARATOR
            + SPACING + "Hello! I'm Duke\n"
            + SPACING + "What can I do for you?\n"
            + LINE_SEPARATOR;
    static final String NO_ARGUMENT_1 = "The";
    static final String NO_ARGUMENT_2 = " command is incomplete.";

    /**
     * Print greeting message when the program starts.
     */
    public void printGreetings() {
        System.out.println(GREETINGS);
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
     * @param taskList the task list holding tasks.
     */
    public void list(ArrayList<Task> taskList) {
        String listOutput = LINE_SEPARATOR;
        for (int i = 0; i < taskList.size(); i++) {
            listOutput += SPACING + (i + 1) + ". "
                    + taskList.get(i).toString() + "\n";
        }
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
