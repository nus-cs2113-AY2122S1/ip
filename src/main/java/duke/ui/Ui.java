package duke.ui;

import duke.data.task.TaskList;
import duke.logic.commands.AddDeadlineCommand;
import duke.logic.commands.AddEventCommand;
import duke.logic.commands.AddTodoCommand;
import duke.logic.commands.ByeCommand;
import duke.logic.commands.CommandListCommand;
import duke.logic.commands.DeleteTaskCommand;
import duke.logic.commands.ListCommand;
import duke.logic.commands.MarkTaskAsDoneCommand;

import java.util.Scanner;

/**
 * This class handles all text UI to be displayed to user.
 */
public class Ui {

    //Commonly used message formats in UI
    public static final String DIVIDER = "_________________________________________________________________________________";
    public static final String LS = System.lineSeparator();
    public static final String QUOTATION = "\"";
    public static final String EMPTY = "";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";
    public static final String MESSAGE_WELCOME_DUDE = "Hello! I'm Dude ^__^";
    public static final String MESSAGE_BYE = "Bye! Hope to see you again soon! ~^u^~ ";
    public static final String MESSAGE_NO_TASKS_YET = "No tasks yet, add a task now!";
    public static final String MESSAGE_INTRODUCE_TASKS = "These are your current tasks:";
    public static final String MESSAGE_COMMAND_LIST = "Commands:" + LS
            + AddTodoCommand.MESSAGE_COMMAND_DESCRIPTION + LS
            + AddDeadlineCommand.MESSAGE_COMMAND_DESCRIPTION + LS
            + AddEventCommand.MESSAGE_COMMAND_DESCRIPTION + LS
            + ListCommand.MESSAGE_COMMAND_DESCRIPTION + " : See lists of tasks" + LS
            + MarkTaskAsDoneCommand.MESSAGE_COMMAND_DESCRIPTION + LS
            + DeleteTaskCommand.MESSAGE_COMMAND_DESCRIPTION + LS
            + CommandListCommand.MESSAGE_COMMAND_DESCRIPTION + LS
            + ByeCommand.MESSAGE_COMMAND_DESCRIPTION;
    public static final String MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST = "Command does not exist @_@";
    public static final String MESSAGE_ERROR_DATE_FORMAT_WRONG = "Please input the date and time in the format " +
            QUOTATION + DATE_TIME_FORMAT + QUOTATION + "!" + LS + "E.g: 12/12/2012 2359";
    private static final String MESSAGE_DATA_LOADED = "Your old data has been successfully loaded!" + LS
            + "You have %d tasks. Type " + ListCommand.MESSAGE_COMMAND_FORMAT + " to see current tasks!";



    private Scanner scanner;


    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Prints lines of messages. Can take in variable number of arguments.
     *
     * @param lines Strings to be printed, each on a new line
     */
    public void showMessage(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Prints lines of messages framed by divider. Can take in variable number of arguments.
     *
     * @param lines Strings to be printed, each on a new line
     */
    public void showMessageFramedWithDivider(String... lines) {
        System.out.println(DIVIDER);
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints Welcome message and list of commands.
     */
    public void showWelcome() {
        showMessageFramedWithDivider(MESSAGE_WELCOME_DUDE, DIVIDER, MESSAGE_COMMAND_LIST);
    }

    /**
     * Prints Goodbye message and exits the program.
     */
    public void showBye() {
        showMessageFramedWithDivider(MESSAGE_BYE);
        System.exit(0);
    }

    public void showTasksLoaded(TaskList tasks) {
        showMessage(String.format(MESSAGE_DATA_LOADED, tasks.getNumTasks()), DIVIDER);
    }

}
