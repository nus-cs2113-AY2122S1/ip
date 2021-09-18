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
import java.util.Scanner;

public abstract class InputParser {

    public static final int TASK_INDEX = 1;
    public static final int KEYWORD_INDEX = 1;
    public static final int COMMAND_INDEX = 0;

    public static final int TASK_INFORMATION_DATE_INDEX = 1;
    public static final int TASK_INFORMATION_NAME_INDEX = 0;

    public static final int DATETIME_DATE_INDEX = 0;
    public static final int DATETIME_TIME_INDEX = 1;
    public static final int INDEX_OFFSET = 1;

    public static final String EMPTY_KEYWORD = "";
    public static final String DATE_SEPARATOR = "/";
    public static final String SEPARATOR = " ";
    public static final String EMPTY_TASK_NAME = "";

    public static String getUserCommand(Scanner in) {
        return in.nextLine();
    }

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

    public static String getTaskDetails(String[] words) {
        StringBuilder taskName = new StringBuilder();
        for (int i = TASK_INDEX; i < words.length; i++) {
            taskName.append(words[i]);
            taskName.append(SEPARATOR);
        }
        return taskName.toString().trim();
    }

    public static String[] getCommandComponents(String commandInput) {
        return commandInput.trim().split(SEPARATOR);
    }

    public static int getTaskNumber(String[] commandComponents) {
        return Integer.parseInt(commandComponents[TASK_INDEX]) - INDEX_OFFSET;
    }

    public static String getTaskName(String taskName) throws DukeTaskNameEmptyException {
        if (taskName.equals(EMPTY_TASK_NAME)) {
            throw new DukeTaskNameEmptyException();
        }
        return taskName;
    }

    public static String[] getTaskWithDateComponents(String taskInformation) {
        String[] taskComponents = taskInformation.split(DATE_SEPARATOR);
        for (int i = 0; i < taskComponents.length; i++) {
            taskComponents[i] = taskComponents[i].trim();
        }
        return taskComponents;
    }

    public static String getKeyword(String[] words) {
        if (words.length == 1) {
            return EMPTY_KEYWORD;
        }

        return words[KEYWORD_INDEX];
    }

    public static String getTaskNameComponent(String taskInformation) throws DukeTaskNameEmptyException {
        String[] taskComponents = InputParser.getTaskWithDateComponents(taskInformation);
        return getTaskName(taskComponents[TASK_INFORMATION_NAME_INDEX]);
    }

    public static String getDateTimeStringComponent(String taskInformation) {
        String[] taskComponents = InputParser.getTaskWithDateComponents(taskInformation);
        return taskComponents[TASK_INFORMATION_DATE_INDEX];
    }

    public static String[] separateDateAndTimeComponent(String dateTimeInformation) {
        return dateTimeInformation.split(SEPARATOR);
    }

    public static String getDateStringComponent(String dateTimeInformation) {
        return separateDateAndTimeComponent(dateTimeInformation)[DATETIME_DATE_INDEX];
    }

    public static String getTimeStringComponent(String dateTimeInformation) {
        return separateDateAndTimeComponent(dateTimeInformation)[DATETIME_TIME_INDEX];
    }

    public static LocalDate getDateComponent(String taskInformation) {
        String dateTimeInformation = getDateTimeStringComponent(taskInformation);
        return LocalDate.parse(InputParser.getDateStringComponent(dateTimeInformation));
    }

    public static LocalTime getTimeComponent(String taskInformation) {
        String dateTimeInformation = getDateTimeStringComponent(taskInformation);
        return LocalTime.parse(InputParser.getTimeStringComponent(dateTimeInformation));
    }
}
