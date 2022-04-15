package Duke;

import java.util.Scanner;

import static Duke.Messages.*;
import static Duke.Constants.*;

public class Ui {
    /**
     * Reads user input
     *
     * @return User input
     */
    public String readCommand() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        return line;
    }

    /**
     * Shows user welcome message
     */
    public void showWelcome() {
        showToUser(GREETING);
    }

    /**
     * Shows user bye message
     */
    public void showBye() {
        showToUser(BYE);
    }

    /**
     * Shows user that task has been added
     *
     * @param task Task that has been added
     */
    public void showAdd(Task task) {
        showToUser(TASK_ADD +
                "\n  " + task +
                "\nYou now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    /**
     * Shows user that task has been marked as done
     *
     * @param task Task that has been marked as done
     */
    public void showDone(Task task) {
        showToUser(TASK_DONE +
                "\n  " + task);
    }

    /**
     * Shows user that task has been deleted
     *
     * @param task Task that has been deleted
     */
    public void showDelete(Task task) {
        showToUser(TASK_DELETE +
                "\n  " + task +
                "\nYou now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    /**
     * Shows user the task with numbering
     *
     * @param curr Numbering of the task
     * @param task Task to be shown
     */
    public void showTask(int curr, Task task) {
        System.out.print(curr + "." + task + "\n");
    }

    /**
     * Shows user the error message according to the error type
     *
     * @param errorType Type of error
     */
    public void showError(String errorType) {
        switch (errorType) {
        case COMMAND_TODO:
            showToUser(TODO_EMPTY);
            break;
        case COMMAND_DEADLINE:
            showToUser(DEADLINE_EMPTY);
            break;
        case COMMAND_EVENT:
            showToUser(EVENT_EMPTY);
            break;
        case COMMAND_DONE:
        case COMMAND_DELETE:
            showToUser(NUMBER_INVALID);
            break;
        case COMMAND_FIND:
            showToUser(FIND_EMPTY);
        case COMMAND_INCORRECT:
            showToUser(INPUT_INVALID);
            break;
        case FILE:
            showToUser(FILE_CORRUPT);
            break;
        case DATEANDTIME:
            showToUser(DATE_INVALID);
        }
    }

    /**
     * Shows user the message
     *
     * @param text Message to be shown
     */
    public void showToUser(String text) {
        if (!text.equals("")) {
            System.out.println(text);
        }
        System.out.println(DIVIDER);
    }

}
