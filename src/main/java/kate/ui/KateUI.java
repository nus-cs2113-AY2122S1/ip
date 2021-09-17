package kate.ui;

import kate.task.Task;
import kate.tasklist.TaskList;

import java.util.Scanner;

public class KateUI {
    private static final String TEXT_INDENTATION = "    ";
    private static final String LOGO_INDENTATION = "                    ";
    private static final String TEXT_WRAPPER = "================================="
            + "====================================\n";

    /**
     * A custom ASCII art logo for Project Kate
     */
    private static final String LOGO_KATE = LOGO_INDENTATION + " _  __     _\n"
            + LOGO_INDENTATION + "| |/ /__ _| |_ ___\n"
            + LOGO_INDENTATION + "| ' </ _` |  _/ -_)\n"
            + LOGO_INDENTATION + "|_|\\_\\__,_|\\__\\___|\n";

    private static final String GREET_MESSAGE = TEXT_INDENTATION
            + "This is Kate, your personal assistant ;)\n"
            + TEXT_INDENTATION + "How can I help you?\n";
    private static final String BYE_MESSAGE = TEXT_INDENTATION
            + "Leaving already? Oh well see you again soon!\n";

    private static final String FAILURE_MESSAGE_INVALID_COMMAND = TEXT_INDENTATION
            + "Please enter a valid command!\n"
            + TEXT_INDENTATION + "Type <help> for the list of commands\n";

    /**
     * Specific description on how to use the action commands
     * User inputs should be in location with square brackets
     */
    private static final String COMMAND_TODO = "todo [description]";
    private static final String COMMAND_DEADLINE = "deadline [description] /by [deadline]";
    private static final String COMMAND_EVENT = "event [description] /at [time frame]";
    private static final String COMMAND_DONE = "done [task number shown in list]";
    private static final String COMMAND_DELETE = "delete [task number]";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";

    private Scanner in;

    public KateUI() {
        in = new Scanner(System.in);
    }

    public String getInput() {
        return in.nextLine();
    }

    /**
     * Greet message from Kate
     */
    public void printGreetMessage() {
        System.out.println(LOGO_KATE);
        printMessage(GREET_MESSAGE);
    }

    /**
     * Prints a general message that is nicely formatted
     *
     * @param message A string input to be formatted and printed
     */
    public void printMessage(String message) {
        String formattedMsg = TEXT_WRAPPER + message + TEXT_WRAPPER;
        System.out.println(formattedMsg);
    }

    /**
     * Bye message from Kate
     */
    public void printByeMessage() {
        printMessage(BYE_MESSAGE);
    }

    /**
     * Prints a confirmation that a task has been added
     *
     * @param addedTask Task object of the newly added Task
     * @param taskSize  Number of tasks
     */
    public void printAddedTask(Task addedTask, int taskSize) {
        String formattedMsg = TEXT_INDENTATION + "Okay, I have added this task!\n"
                + TEXT_INDENTATION + "  " + addedTask.getTaskInfo() + "\n"
                + TEXT_INDENTATION + "You currently have (" + taskSize
                + ") tasks in your list :)\n";
        printMessage(formattedMsg);
    }

    /**
     * Prints the list of all available commands
     * Only prints when user key in an invalid command
     */
    public void printHelpPage() {
        String helpText = TEXT_INDENTATION + "Please enter only the following commands: \n"
                + TEXT_INDENTATION + "- " + COMMAND_TODO + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_DEADLINE + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_EVENT + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_DONE + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_DELETE + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_LIST + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_BYE + "\n";
        printMessage(helpText);
    }

    /**
     * Prints all the tasks entered by the user
     *
     * @param tasks TaskList object of all the tasks
     */
    public void printTasks(TaskList tasks) {
        StringBuilder allTasks = new StringBuilder();
        String taskHeading = TEXT_INDENTATION + "Here are the tasks in your list:\n";
        allTasks.append(taskHeading);
        for (int i = 0; i < tasks.getTaskSize(); ++i) {
            int numberedBullets = i + 1;
            Task curTask = tasks.getCurrentTask(i);
            String taskRow = TEXT_INDENTATION + numberedBullets + ". " + curTask.getTaskInfo() + "\n";
            allTasks.append(taskRow);
        }
        printMessage(String.valueOf(allTasks));
    }

    /**
     * Prints the error message for an invalid command
     */
    public void printInvalidCommandMessage() {
        printMessage(FAILURE_MESSAGE_INVALID_COMMAND);
    }

}
