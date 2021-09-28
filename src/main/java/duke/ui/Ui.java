package duke.ui;

import duke.tasklist.TaskList;

public class Ui {
    protected final String DIVIDER = "-----------------------------------------";

    public void printWelcomeMessage() {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(DIVIDER);
    }

    public void printExitMessage() {
        System.out.println("Bye! Have a nice day.");
        System.out.println(DIVIDER);
    }

    public void printInvalidCommand() {
        System.out.println("User command is not valid, please try again!");
    }

    public static void printAddSuccess(TaskList taskList) {
        int lastTaskIndex = taskList.getTasks().size() - 1;
        System.out.println("Got it. I've added this task:\n  " + taskList.getTasks().get(lastTaskIndex));
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list");
    }

}
