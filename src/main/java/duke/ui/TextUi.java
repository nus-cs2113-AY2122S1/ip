package duke.ui;

import duke.common.Messages;
import duke.data.TaskList;
import duke.data.task.Task;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class TextUi {
    /** Decorative prefix at the beginning of lines. */
    public final String LINE_PREFIX = "|| ";
    public final String EMPTY_STRING = "";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static final String MESSAGE_SUCCESSFUL_ADD = "Got it! I've added this task: ";
    public static final String MESSAGE_TASK_MARK_DONE = "Nice! You did the following task:";
    public static final String MESSAGE_SUCCESSFUL_DELETE = "Got it. I've removed this task for you: ";

    public final String DASHES = "_____________________________________________________________________";
    private final String LOGO =
            "  ____        _        \n"
            + "||  _ \\ _   _| | _____ \n"
            + "|| | | | | | | |/ / _ \\\n"
            + "|| |_| | |_| |   <  __/\n"
            + "||____/ \\__,_|_|\\_\\___|";

    protected Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    /**
     * Generates and prints the welcome message at the start of the application.
     */
    public void showWelcomeMessage() {
        showToUser(DASHES, LOGO, DASHES, Messages.MESSAGE_WELCOME, DASHES);
    }

    /**
     * Format to show messages to the user.
     * @param message strings of messages
     */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m.replace("\n",LINE_SEPARATOR + LINE_PREFIX));
        }
    }

    /**
     * Prints a success message to the user after adding a task to the task list.
     *
     * @param tasks the task list
     */
    public void showSuccessfulAdd(TaskList tasks) {
        showToUser(MESSAGE_SUCCESSFUL_ADD,
                tasks.getTask(tasks.getSize() - 1).toString(),
                getSizeString(tasks));
    }

    /**
     * Returns a message that includes the size of the task list.
     *
     * @param tasks the task list
     */
    private String getSizeString(TaskList tasks) {
        return "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints a success message to the user after marking a task as done.
     *
     * @param currentTask the specified task
     */
    public void showSuccessfulComplete(Task currentTask) {
        showToUser(MESSAGE_TASK_MARK_DONE,
                "[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
    }

    /**
     * Prints a success message to the user after deleting a task from the task list.
     *
     * @param currentTask the specified task
     * @param tasks the task list
     */
    public void showSuccessfulDelete(Task currentTask, TaskList tasks) {
        showToUser(MESSAGE_SUCCESSFUL_DELETE,
                currentTask.toString(),
                getSizeString(tasks));
    }

    /**
     * Prompts the user to enter a command reads the input.
     * Ignores empty pure whitespace inputs.
     *
     * @return command entered by the user
     */
    public String getInput() {
        showToUser(Messages.MESSAGE_ENTER_COMMAND);
        System.out.print("|| ");
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    /**
     * Decorative method that prints a line of dashes to the user.
     */
    public void showLine() {
        showToUser(DASHES);
    }
}
