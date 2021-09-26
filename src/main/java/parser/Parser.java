package parser;

import commands.*;
import common.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static common.Messages.*;
import static ui.Ui.DISPLAYED_INDEX_OFFSET;

public class Parser {
    public static final String DATE_KEYWORD = "d/";
    public static final String TIME_KEYWORD = "t/";

    public Command parseCommand(String userInputString) throws DukeException {
        try {
            final String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
            final String commandType = commandTypeAndParams[0].trim();
            final String commandArgs = commandTypeAndParams[1].trim();
            if (commandArgs.isEmpty()
                    && !commandType.equals(ListCommand.COMMAND_WORD)
                    && !commandType.equals(FindCommand.COMMAND_WORD)
                    && !commandType.equals(HelpCommand.COMMAND_WORD)
                    && !commandType.equals(ClearCommand.COMMAND_WORD)
                    && !commandType.equals(ExitCommand.COMMAND_WORD)) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }
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
                return prepareAddEvent(getTaskDateArgs(commandArgs));
            case AddDeadlineCommand.COMMAND_WORD:
                return prepareAddDeadline(getTaskDateArgs(commandArgs));
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
            case DeleteCommand.COMMAND_WORD:
                return prepareDeleteTask(commandArgs);
            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            default:
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
            }
        } catch (DukeException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private static String[] splitCommandWordsAndArgs(String input, String keyword) {
        final String[] split = input.trim().split(keyword, 2);
        if (split.length == 2) {
            return split;
        }
        return new String[]{split[0], ""};
    }

    public static String[] getTaskDateArgs(String commandArgs) throws DukeException {
        String[] descriptionAndDatetime = splitCommandWordsAndArgs(commandArgs, DATE_KEYWORD);
        String description = descriptionAndDatetime[0].trim();
        String[] dateAndTime = splitCommandWordsAndArgs(descriptionAndDatetime[1].trim(), TIME_KEYWORD);
        String date = dateAndTime[0].trim();
        String time = dateAndTime[1].trim();
        if (description.isEmpty()||date.isEmpty()) {
            throw new DukeException("Error. Unable to get task date arguments. ");
        }
        return new String[]{description, date, time};
    }

    public static Command prepareAddTodo(String description) {
        if (description.isEmpty()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new AddTodoCommand(description, false);
    }

    public static Command prepareAddEvent(String[] eventArgs) throws DukeException {
        String description = eventArgs[0].trim();
        if (description.isEmpty()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        LocalDate date = parseArgsAsDate(eventArgs[1].trim());
        LocalTime time = parseArgsAsTime(eventArgs[2].trim());
        LocalDateTime dateTime = date.atTime(time);
        return new AddEventCommand(description, false, dateTime);
    }

    public static Command prepareAddDeadline(String[] deadlineArgs) throws DukeException {
        String description = deadlineArgs[0];
        if (description.isEmpty()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        LocalDate date = parseArgsAsDate(deadlineArgs[1].trim());
        LocalTime time = parseArgsAsTime(deadlineArgs[2].trim());
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return new AddDeadlineCommand(description, false, dateTime);
    }

    public static LocalDate parseArgsAsDate(String date) throws DukeException {
        if (date.isEmpty()) {
            throw new DukeException("Could not find date to parse");
        }
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Error parsing date. ");
        }
    }

    public static LocalTime parseArgsAsTime(String time) throws DukeException {
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

    public static Command prepareDeleteTask(String displayIndex) throws DukeException {
        int taskIndex = parseArgsAsDisplayedIndex(displayIndex) - DISPLAYED_INDEX_OFFSET;
        return new DeleteCommand(taskIndex);
    }

    private static Command prepareMarkAsDone(String displayIndex) throws DukeException {
        int taskIndex = parseArgsAsDisplayedIndex(displayIndex) - DISPLAYED_INDEX_OFFSET;
        return new MarkAsDoneCommand(taskIndex);
    }
}
