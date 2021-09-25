package duke.system;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void printGreetings() {
        System.out.println(GREETINGS);
    }

    public void printMarked(String taskDetail) {
        String update = LINE_SEPARATOR
                + SPACING + "Nice! I've marked this task as done: \n"
                + SPACING + taskDetail + "\n"
                + LINE_SEPARATOR;
        System.out.println(update);
    }

    public void printErrorMessage(String errorMessage) {
        String error = LINE_SEPARATOR
                + SPACING + errorMessage + "\n"
                + LINE_SEPARATOR;
        System.out.println(error);
    }

    public void printAdded(String taskDetail) {
        String response = LINE_SEPARATOR
                + SPACING + "added: " + taskDetail + "\n"
                + LINE_SEPARATOR;
        System.out.println(response);
    }

    public void printDeleted(String taskDetail) {
        String update = LINE_SEPARATOR
                + SPACING + "Noted! I've removed this task: \n"
                + SPACING + taskDetail + "\n"
                + LINE_SEPARATOR;
        System.out.println(update);
    }

    public void printBye() {
        String Bye = LINE_SEPARATOR
                + SPACING + "Bye. Hope to see you again soon!\n"
                + LINE_SEPARATOR;
        System.out.println(Bye);
    }

    public void list(ArrayList<Task> taskList) {
        String listOutput = LINE_SEPARATOR;
        for (int i = 0; i < taskList.size(); i++) {
            listOutput += SPACING + (i + 1) + ". "
                    + taskList.get(i).toString() + "\n";
        }
        listOutput += LINE_SEPARATOR;
        System.out.println(listOutput);

    }

    public String readCommand() {
        String fullCommand;
        Scanner in = new Scanner(System.in);
        fullCommand = in.nextLine();
        return fullCommand;
    }
}
