package console;

import commands.DeadlineCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ToDoCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkDoneCommand;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.TaskManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Parses the user command line input.
 */
public abstract class InputParser {

    /** Index position of where the task details begin from. */
    public static final int TASK_INDEX = 1;

    /** Index position of where the keyword should be in task details. */
    public static final int KEYWORD_INDEX = 1;

    /** Index position of where the command word should be in task details. */
    public static final int COMMAND_INDEX = 0;

    public static final int TASK_INFORMATION_NAME_INDEX = 0;
    public static final int TASK_INFORMATION_DATE_INDEX = 1;

    public static final int DATETIME_DATE_INDEX = 0;
    public static final int DATETIME_TIME_INDEX = 1;

    public static final String EMPTY_STRING = "";
    public static final String DATE_SEPARATOR = "/";
    public static final String SEPARATOR = " ";
    public static final String DATE_REGEX = "MMM dd yyyy";

    public static final int INDEX_OFFSET = 1;

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
        case FindCommand.COMMAND_WORD:
            command = new FindCommand(taskManager);
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
        return Integer.parseInt(commandComponents[TASK_INDEX]) - INDEX_OFFSET;
    }

    /**
     * Returns the task name provided by the user.
     *
     * @param taskName Name of task specified by user.
     * @return Task name specified by user.
     * @throws DukeTaskNameEmptyException If the task name specific is empty.
     */
    public static String getTaskName(String taskName) throws DukeTaskNameEmptyException {
        if (taskName.equals(EMPTY_STRING)) {
            throw new DukeTaskNameEmptyException();
        }
        return taskName;
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

    /**
     * Returns the keyword the user specified to filter the tasks by.
     *
     * @param commandComponents A string array containing the user input.
     * @return Keyword provided by user if it exists.
     */
    public static String getKeyword(String[] commandComponents) {
        if (commandComponents.length == 1) {
            return EMPTY_STRING;
        }

        return commandComponents[KEYWORD_INDEX];
    }

    /**
     * Extracts out the task name from task information.
     *
     * @param taskInformation Task details in the command line input.
     * @return Task name given by user.
     * @throws DukeTaskNameEmptyException If the task name provided is empty.
     */
    public static String getTaskNameComponent(String taskInformation) throws DukeTaskNameEmptyException {
        String[] taskComponents = getTaskWithDateComponents(taskInformation);
        return getTaskName(taskComponents[TASK_INFORMATION_NAME_INDEX]);
    }

    /**
     * Extracts out the datetime from task information.
     *
     * @param taskInformation Task details in the command line input.
     * @return The datetime information specified by user.
     */
    public static String getDateTimeStringComponent(String taskInformation) {
        String[] taskComponents = getTaskWithDateComponents(taskInformation);
        return taskComponents[TASK_INFORMATION_DATE_INDEX];
    }

    /**
     * Separates the datetime information into date and time respectively.
     *
     * @param dateTimeInformation The datetime information specified by user.
     * @return A string array containing the date and time information.
     */
    public static String[] separateDateAndTimeComponent(String dateTimeInformation) {
        return dateTimeInformation.split(SEPARATOR);
    }

    /**
     * Returns the date information specified by user.
     *
     * @param dateTimeInformation The datetime information specified by user.
     * @return Date information from the datetime information.
     */
    public static String getDateStringComponent(String dateTimeInformation) {
        return separateDateAndTimeComponent(dateTimeInformation)[DATETIME_DATE_INDEX];
    }

    /**
     * Returns the time information specified by user.
     *
     * @param dateTimeInformation The datetime information specified by user.
     * @return Time information from the datetime information.
     */
    public static String getTimeStringComponent(String dateTimeInformation) {
        return separateDateAndTimeComponent(dateTimeInformation)[DATETIME_TIME_INDEX];
    }

    /**
     * Returns the date information to be stored in the task object.
     *
     * @param taskInformation Task details in the command line input.
     * @return Date information as a LocalDate type.
     */
    public static LocalDate getDateComponent(String taskInformation) {
        String dateTimeInformation = getDateTimeStringComponent(taskInformation);
        return LocalDate.parse(getDateStringComponent(dateTimeInformation));
    }

    /**
     * Returns the time information to be stored in the task object.
     *
     * @param taskInformation Task details in the command line input.
     * @return Time information as a LocalTime type.
     */
    public static LocalTime getTimeComponent(String taskInformation) {
        String dateTimeInformation = getDateTimeStringComponent(taskInformation);
        return LocalTime.parse(getTimeStringComponent(dateTimeInformation));
    }

    /**
     * Extracts out the date information from saved task in storage file.
     *
     * @param savedDateTimeInformation The datetime information saved in storage file.
     * @return Date information from the saved task.
     */
    public static String getSavedDateStringComponent(String savedDateTimeInformation) {
        int lastSpaceIndex = savedDateTimeInformation.lastIndexOf(SEPARATOR);
        return savedDateTimeInformation.substring(DATETIME_DATE_INDEX, lastSpaceIndex);
    }

    /**
     * Extracts out the time information from saved task in storage file.
     *
     * @param savedDateTimeInformation The datetime information saved in storage file.
     * @return Time information from the saved task.
     */
    public static String getSavedTimeStringComponent(String savedDateTimeInformation) {
        int lastSpaceIndex = savedDateTimeInformation.lastIndexOf(SEPARATOR);
        return savedDateTimeInformation.substring(lastSpaceIndex + INDEX_OFFSET);
    }

    /**
     * Returns the saved date information to be stored in the task object.
     *
     * @param savedTaskInformation Task details in the storage file.
     * @return Saved date information as a LocalDate type.
     */
    public static LocalDate getSavedDateComponent(String savedTaskInformation) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_REGEX);
        String dateTimeInformation = getDateTimeStringComponent(savedTaskInformation);
        return LocalDate.parse(getSavedDateStringComponent(dateTimeInformation), dateTimeFormatter);
    }

    /**
     * Returns the saved time information to be stored in the task object.
     *
     * @param savedTaskInformation Task details in the storage file.
     * @return Saved date information as a LocalDate type.
     */
    public static LocalTime getSavedTimeComponent(String savedTaskInformation) {
        String dateTimeInformation = getDateTimeStringComponent(savedTaskInformation);
        return LocalTime.parse(getSavedTimeStringComponent(dateTimeInformation));
    }
}
