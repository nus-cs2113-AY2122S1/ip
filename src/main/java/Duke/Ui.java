package Duke;

import java.util.Scanner;

import static Duke.Messages.*;
import static Duke.Constants.*;

public class Ui {
    public String readCommand() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        return line;
    }

    public void showWelcome() {
        showToUser(GREETING);
    }

    public void showBye() {
        showToUser(BYE);
    }

    public void showAdd(Task task) {
        showToUser(TASK_ADD +
                "\n  " + task +
                "\nYou now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    public void showDone(Task task) {
        showToUser(TASK_DONE +
                "\n  " + task);
    }

    public void showDelete(Task task) {
        showToUser(TASK_DELETE +
                "\n  " + task +
                "\nYou now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    public void showTask(int curr, Task task) {
        System.out.print(curr + "." + task + "\n");
    }

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

    public void showToUser(String text) {
        if (!text.equals("")) {
            System.out.println(text);
        }
        System.out.println(DIVIDER);
    }

}
