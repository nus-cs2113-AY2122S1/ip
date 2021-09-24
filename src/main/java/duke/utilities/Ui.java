package duke.utilities;

import duke.commands.ByeCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Logo of the bot
     */
    private static final String LOGO = " _ __ _   _  __ _ _ __\n"
            + "| '__| | | |/ _` | '_ \\\n"
            + "| |  | |_| | (_| | | | |\n"
            + "|_|   \\__, |\\__,_|_| |_|\n"
            + "       __/ |\n"
            + "      |___/";

    /**
     * A decorative spacer between user inputs and outputs by the bot
     */
    private static final String DIVIDER = "____________________________________________________________";


    private static final String MESSAGE_WELCOME = "Hello from\n"
            + LOGO
            + "\nHow can I assist you? Type something below! :D";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_NO_INPUT = "No input found! Please type <mode> + item";


    private static final String TASK_PLURAL = "tasks";
    private static final String TASK_SINGLE = "task";
    private static final String PRINT_TASK_MESSAGE_FRONT = "Now you have ";
    private static final String PRINT_TASK_MESSAGE_BACK = " in the list.";
    private static final String SPACING = " ";
    private final String PREFIX = ">";

    public void print(String... input) {
        for (String m : input) {
            System.out.println(m);
        }
    }

    /**
     * Prints the goodbye message
     */
    public void printByeMessage() {
        System.out.println(MESSAGE_GOODBYE);
    }

    /**
     * Prints the welcome message
     */
    public void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
    }

    /**
     * Prints a divider
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints the list of tasks stored
     */
    public void printList(ArrayList<Task> tasks) {
        int counter = 1;
        for (Task t : tasks) {
            System.out.print(counter + ". ");
            System.out.println(t);
            counter++;
        }
        printTaskNumber(tasks);
        return;
    }

    /**
     * Prints the number of tasks
     */
    public void printTaskNumber(ArrayList<Task> tasks) {
        String task = TASK_PLURAL;
        if (tasks.size() == 1) {
            task = TASK_SINGLE;
        }
        System.out.println(PRINT_TASK_MESSAGE_FRONT + tasks.size() + SPACING + task + PRINT_TASK_MESSAGE_BACK);
    }

    public void printNoInput() {
        System.out.println(MESSAGE_NO_INPUT);
    }

    public void printHelp() {
        System.out.println("Commands available : ");
        print(ByeCommand.COMMAND_WORD, DeadlineCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD,
                DoneCommand.COMMAND_WORD, EventCommand.COMMAND_WORD, ListCommand.COMMAND_WORD,
                ToDoCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD);
    }
}
