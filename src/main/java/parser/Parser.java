package parser;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.ClearCommand;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;

import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkAsDoneCommand;
import commands.Command;
import commands.IncorrectCommand;

import common.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static common.Messages.MESSAGE_GET_HELP;
import static common.Messages.MESSAGE_INVALID_COMMAND;
import static ui.Ui.DISPLAYED_INDEX_OFFSET;

/**
 * Takes in raw data and parses it into parameters of commands
 */
public class Parser {
    public static final String DATE_KEYWORD = "d/";
    public static final String TIME_KEYWORD = "t/";

    /**
     * Parses commands
     * @param userInputString input string from user
     * @return Command type of command to be executed
     * @throws DukeException if unable to parse command
     */
    public Command parseCommand(String userInputString) throws DukeException {

        final String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
        final String commandType = commandTypeAndParams[0].trim();
        final String commandArgs = commandTypeAndParams[1].trim();

        switch (commandType) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommand(commandArgs);
        case MarkAsDoneCommand.COMMAND_WORD:
            return prepareMarkAsDone(commandArgs);
        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodo(commandArgs);
        case AddEventCommand.COMMAND_WORD:
            return prepareAddEvent(commandArgs);
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadline(commandArgs);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteTask(commandArgs);
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + MESSAGE_GET_HELP);
        }

    }

    private static String[] splitCommandWordsAndArgs(String input, String keyword) {
        final String[] split = input.trim().split(keyword, 2);
        if (split.length == 2) {
            return split;
        }
        return new String[]{split[0], ""};
    }

    private static String[] getTaskDateArgs(String commandArgs) throws DukeException {
        String[] descriptionAndDatetime = splitCommandWordsAndArgs(commandArgs, DATE_KEYWORD);
        String description = descriptionAndDatetime[0].trim();
        String[] dateAndTime = splitCommandWordsAndArgs(descriptionAndDatetime[1].trim(), TIME_KEYWORD);
        String date = dateAndTime[0].trim();
        String time = dateAndTime[1].trim();
        if (description.isEmpty()) {
            throw new DukeException("Error. Task description is empty. ");
        }
        if (date.isEmpty()) {
            throw new DukeException("Error. Unable to get task date arguments. ");
        }
        return new String[]{description, date, time};
    }

    private static Command prepareAddTodo(String description) {
        if (description.isEmpty()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + AddTodoCommand.MESSAGE_USAGE);
        }
        return new AddTodoCommand(description, false);
    }

    private static Command prepareAddEvent(String commandArgs) {
        try {
            String[] eventArgs = getTaskDateArgs(commandArgs);
            String description = eventArgs[0].trim();
            LocalDate date = parseArgsAsDate(eventArgs[1].trim());
            LocalTime time = parseArgsAsTime(eventArgs[2].trim());
            LocalDateTime dateTime = date.atTime(time);
            return new AddEventCommand(description, false, dateTime);
        } catch (DukeException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + AddEventCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareAddDeadline(String commandArgs) {
        try {
            String[] deadlineArgs = getTaskDateArgs(commandArgs);
            String description = deadlineArgs[0].trim();
            LocalDate date = parseArgsAsDate(deadlineArgs[1].trim());
            LocalTime time = parseArgsAsTime(deadlineArgs[2].trim());
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            return new AddDeadlineCommand(description, false, dateTime);
        } catch (DukeException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + AddDeadlineCommand.MESSAGE_USAGE);
        }
    }

    private static LocalDate parseArgsAsDate(String date) throws DukeException {
        if (date.isEmpty()) {
            throw new DukeException("Error. Empty date.");
        }
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Error parsing date. ");
        }
    }

    private static LocalTime parseArgsAsTime(String time) throws DukeException {
        if (time.isEmpty()) {
            return LocalTime.MIN;
        }
        try {
            return LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Error parsing time. ");
        }
    }

    private static int parseArgsAsDisplayedIndex(String displayIndex) throws DukeException {
        if (displayIndex.isEmpty()) {
            throw new DukeException("Error. Task index not found. ");
        }
        try {
            return Integer.parseInt(displayIndex.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Error. Invalid task index.");
        }
    }

    private static Command prepareDeleteTask(String displayIndex) throws DukeException {
        int taskIndex = parseArgsAsDisplayedIndex(displayIndex) - DISPLAYED_INDEX_OFFSET;
        return new DeleteCommand(taskIndex);
    }

    private static Command prepareMarkAsDone(String displayIndex) throws DukeException {
        int taskIndex = parseArgsAsDisplayedIndex(displayIndex) - DISPLAYED_INDEX_OFFSET;
        return new MarkAsDoneCommand(taskIndex);
    }
}
