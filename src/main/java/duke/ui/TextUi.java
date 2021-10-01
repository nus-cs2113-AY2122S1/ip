package duke.ui;

import static duke.ui.Strings.DIVIDER;
import static duke.ui.Strings.INDENT;
import static duke.ui.Strings.LOGO;
import static duke.ui.Strings.MESSAGE_GOODBYE;
import static duke.ui.Strings.MESSAGE_TASK_ADDED;
import static duke.ui.Strings.MESSAGE_TASK_COUNT;
import static duke.ui.Strings.MESSAGE_TASK_COUNT_SINGULAR;
import static duke.ui.Strings.MESSAGE_WELCOME;
import static duke.ui.Strings.NL;

import java.util.Scanner;

public class TextUi {

    private final Scanner in;

    public TextUi() {
        this.in = new Scanner(System.in);
    }

    public void printGreeting() {
        printMessage(
                LOGO,
                MESSAGE_WELCOME,
                DIVIDER);
    }

    public void printGoodbye() {
        printMessage(MESSAGE_GOODBYE);
    }

    public void showLine() {
        printMessage(DIVIDER);
    }

    /**
     * Prints array of strings given one line at a time
     *
     * @param message Array of strings to be printed
     */
    public void printMessage(String... message) {
        for (String m : message) {
            System.out.println(m.replace("\n", NL));
        }
    }

    /**
     * Prints task added message
     *
     * @param task      Task that was added
     * @param taskCount total tasks in the taskList
     */
    public void printTaskAddedMessage(String task, int taskCount) {
        String taskCountMessage;

        if (taskCount == 1) {
            taskCountMessage = MESSAGE_TASK_COUNT_SINGULAR;
        } else {
            taskCountMessage = String.format(MESSAGE_TASK_COUNT, taskCount);
        }

        printMessage(MESSAGE_TASK_ADDED,
                INDENT + task,
                taskCountMessage);
    }

    /**
     * Reads in the next line of user input
     *
     * @return String that the user typed in
     */
    public String readCommand() {
        return in.nextLine();
    }
}
