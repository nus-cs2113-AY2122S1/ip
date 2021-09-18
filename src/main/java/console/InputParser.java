package console;

import commands.DeadlineCommand;
import commands.EventCommand;
import commands.HelpCommand;
import commands.ToDoCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkDoneCommand;
import error.Error;
import task.TaskManager;

import java.util.Scanner;

/**
 * Parses the user command line input.
 */
public abstract class InputParser {

    /** Index position of where the task details begin from. */
    public static final int TASK_INDEX = 1;

    /** Index position of the command word. */
    public static final int COMMAND_INDEX = 0;

    public static final String DATE_SEPARATOR = "/";
    public static final String SEPARATOR = " ";

    /**
     * Reads in user inputs from the command line.
     *
     * @param in The Scanner object that gets the user inputs.
     * @return User input.
     */
    public static String getUserCommand(Scanner in) {
        return in.nextLine();
    }

    /**
     * Parses the user inputs and get the command words.
     * Checks the command words and execute the appropriate command.
     *
     * @param commandComponents A string array containing the user input.
     * @param taskManager The TaskManager object to perform the operations on.
     * @return The type of command given.
     */
    public static Command parseCommand(String[] commandComponents, TaskManager taskManager) {
        Command command;
        switch (commandComponents[COMMAND_INDEX]) {
        case ListCommand.COMMAND_WORD:
            command = new ListCommand(taskManager);
            break;
        case MarkDoneCommand.COMMAND_WORD:
            command = new MarkDoneCommand(taskManager);
            break;
        case ToDoCommand.COMMAND_WORD:
            command = new ToDoCommand(taskManager);
            break;
        case DeadlineCommand.COMMAND_WORD:
            command = new DeadlineCommand(taskManager);
            break;
        case EventCommand.COMMAND_WORD:
            command = new EventCommand(taskManager);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand(taskManager);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand(taskManager);
            break;
        default:
            command = new HelpCommand(taskManager);
            Error.displayInvalidCommandError();
            break;
        }
        return command;
    }

    /**
     * Returns a string of the user inputs excluding the command word.
     *
     * @param commandComponents A string array containing the user input.
     * @return The task details as a string.
     */
    public static String getTaskDetails(String[] commandComponents) {
        StringBuilder taskName = new StringBuilder();
        for (int i = TASK_INDEX; i < commandComponents.length; i++) {
            taskName.append(commandComponents[i]);
            taskName.append(SEPARATOR);
        }
        return taskName.toString().trim();
    }

    /**
     * Splits the user input into individual words.
     * Trims any leading or trailing whitespaces in the user input.
     *
     * @param commandInput The user command line input.
     * @return All words in the command line input.
     */
    public static String[] getCommandComponents(String commandInput) {
        return commandInput.trim().split(SEPARATOR);
    }

    /**
     * Returns the task number that the user specified.
     * Converts the indexing from 1-indexing to 0-indexing.
     *
     * @param commandComponents A string array containing the user input.
     * @return The task number.
     */
    public static int getTaskNumber(String[] commandComponents) {
        return Integer.parseInt(commandComponents[TASK_INDEX]) - 1;
    }

    /**
     * Returns the task details separated by name and date.
     * Trims any leading or trailing whitespaces in each component.
     *
     * @param taskInformation A string containing all the task details.
     * @return A string array containing the task name and the task date details.
     */
    public static String[] getTaskWithDateComponents(String taskInformation) {
        String[] taskComponents = taskInformation.split(DATE_SEPARATOR);
        for (int i = 0; i < taskComponents.length; i++) {
            taskComponents[i] = taskComponents[i].trim();
        }
        return taskComponents;
    }
}
