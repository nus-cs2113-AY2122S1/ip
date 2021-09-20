package duke;

import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPARATOR = "------------------------------------" + System.lineSeparator();
    private static final String HELLO_MESSAGE = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE = "You need to specify the task type!";

    protected Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public void printMessage(String message) {
        String formattedMessage = LINE_SEPARATOR + message + System.lineSeparator() + LINE_SEPARATOR;
        System.out.print(formattedMessage);
    }

    public void printHelloMessage() {
        System.out.println("Hello from\n" + LOGO);
        printMessage(HELLO_MESSAGE);
    }

    public void printByeMessage() {
        printMessage(BYE_MESSAGE);
    }

    public void promptInvalidInput() {
        printMessage(ERROR_MESSAGE);
    }

    public void printAddedTask(Task addedTask, int taskCount) {
        String taskAddedConfirmation = "Got it! I've added the following task:";
        String promptTaskCount = "Now you have " + taskCount + " tasks in your list";
        String formattedMessage = taskAddedConfirmation + System.lineSeparator() +
                addedTask.getTaskInfo() +  System.lineSeparator() + promptTaskCount;
        printMessage(formattedMessage);
    }

    public void printCompletedTask(Task completedTask) {
        String taskCompletedConfirmation = "Nice! I have marked this task as done:";
        String formattedMessage = taskCompletedConfirmation +
                System.lineSeparator() + completedTask.getTaskInfo();
        printMessage(formattedMessage);
    }

    public void printDeletedTask(Task deletedTask, int taskCount) {
        String taskDeletedConfirmation = "Noted. I've removed this task:";
        String promptTaskCount = "Now you have " + taskCount + " tasks in your list";
        String formattedMessage = taskDeletedConfirmation + System.lineSeparator() +
                deletedTask.getTaskInfo() + System.lineSeparator() + promptTaskCount;
        printMessage(formattedMessage);
    }

}
