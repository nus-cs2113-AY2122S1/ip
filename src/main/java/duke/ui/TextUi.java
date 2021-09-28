package duke.ui;

import duke.common.Messages;
import duke.data.TaskList;
import duke.data.task.Task;

import java.util.Scanner;

public class TextUi {
    public final String LINE_PREFIX = "|| ";
    public final String EMPTY_STRING = "";

    public static final String MESSAGE_SUCCESSFUL_ADD = "Got it! I've added this task: ";
    public static final String MESSAGE_TASK_MARK_DONE = "Nice! You did the following task:";
    public static final String MESSAGE_SUCCESSFUL_DELETE = "Got it. I've removed this task for you: ";

    public final String DASHES = "_____________________________________________________________________";
    private final String LOGO = " ____        _        \n"
            + "||  _ \\ _   _| | _____ \n"
            + "|| | | | | | | |/ / _ \\\n"
            + "|| |_| | |_| |   <  __/\n"
            + "||____/ \\__,_|_|\\_\\___|";

    protected Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        showToUser(DASHES, LOGO, DASHES, Messages.MESSAGE_WELCOME, DASHES);
    }

    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
    }

    public void showSuccessfulAdd(TaskList tasks) {
        showToUser(MESSAGE_SUCCESSFUL_ADD,
                tasks.getTask(tasks.getSize() - 1).toString(),
                getSizeString(tasks));
    }

    private String getSizeString(TaskList tasks) {
        return "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    public void showSuccessfulComplete(Task currentTask) {
        showToUser(MESSAGE_TASK_MARK_DONE,
                "[" + currentTask.getStatusIcon() + "] ",
                currentTask.getDescription());
    }

    public void showSuccessfulDelete(Task currentTask, TaskList tasks) {
        showToUser(MESSAGE_SUCCESSFUL_DELETE,
                currentTask.toString(),
                getSizeString(tasks));
    }

    public String getInput() {
        showToUser(Messages.MESSAGE_ENTER_COMMAND);
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    public void showLine() {
        showToUser(DASHES);
    }
}
