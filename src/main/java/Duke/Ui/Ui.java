package Duke.Ui;

import Duke.Tasks.TaskList;
import Duke.Tasks.Task;
import Duke.Command.*;

import java.util.Scanner;

public class Ui {
    public Ui() {

    }

    public static final String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static final void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        showLine();
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?\n");
        showLine();
    }

    public static final void showLine() {
        System.out.println("____________________________________________________________");
    }

    public static final void printSuccessfulLoading() {
        System.out.println("Tasks have been loaded.");
    }

    public static final void printListEmptyMessage() {
        System.out.println("The current list is empty~");
    }

    public static final void printListMessage() {
        System.out.println("Here are the tasks in your list: ");
    }

    public static final void printClearMessage() {
        System.out.println("The tasks in your list have been cleared. ");
    }

    public static final void printDoneMessage(Task taskDone) {
        System.out.println("Nice, I have marked this task as done: \n"
                + taskDone.toString());
    }

    public static final void printFindMessage() {
        System.out.println("Here are the matching tasks in your list: ");
    }

    public static final void printNoMatchingTaskMessage() {
        System.out.println("There is no matching task in your list :-(");
    }

    public static final void printDeleteMessage() {
        System.out.println("Got it. I have deleted the task in your list! ");
    }

    public static final void printInvalidCommandMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "Please give me a valid command.");
    }

    public static final void printAddTaskMessage(Task task) {
        System.out.println("Got it. I have added the task to your list.\n"
                + task.toString());
    }

    public static final void printNumOfTasks(TaskList tasks) {
        System.out.println("Now you have " + tasks.getNumOfSize() + " tasks in your list.");
    }

    public static final void showHelpMessage() {
        System.out.println(AddDeadlineCommand.COMMAND_DESCRIPTION
                + "\n\n" + AddEventCommand.COMMAND_DESCRIPTION
                + "\n\n" + AddTodoCommand.COMMAND_DESCRIPTION
                + "\n\n" + ClearCommand.COMMAND_DESCRIPTION
                + "\n\n" + DeleteCommand.COMMAND_DESCRIPTION
                + "\n\n" + DoneCommand.COMMAND_DESCRIPTION
                + "\n\n" + ExitCommand.COMMAND_DESCRIPTION
                + "\n\n" + FindCommand.COMMAND_DESCRIPTION
                + "\n\n" + HelpCommand.COMMAND_DESCRIPTION
                + "\n\n" + ListCommand.COMMAND_DESCRIPTION);
    }

    public static final void showLoadingError() {
        showLine();
        System.out.println("No task list can be loaded.");
        showLine();
    }

    public static final void showError(String message) {
        System.out.println(message);
    }

    public static final void showByeMessage() {
        System.out.println("Bye. Hope to see you soon~");
    }
}
