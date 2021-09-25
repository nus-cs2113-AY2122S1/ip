package parser;

import commands.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static common.Messages.*;

public class Parser {
    public static final String DATE_KEYWORD = "/d";
    public static final String TIME_KEYWORD = "/t";

    public Command parseCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
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
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + HelpCommand.MESSAGE_USAGE);
        }
    }

    private static String[] splitCommandWordsAndArgs(String rawInput, String keyword) {
        final String[] split = rawInput.trim().split(keyword, 2);
        if (split.length == 2) {
            return split;
        }
        return new String[]{split[0], ""};
    }

    private static int parseArgsAsDisplayedIndex(String displayIndex) throws ParseException, NumberFormatException {
        if (displayIndex.isEmpty()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(displayIndex.trim());
    }

    public static Command prepareAddTodo(String description) {
        return new AddTodoCommand(description, false);
    }

    public static String[] getTaskDateArgs(String commandArgs) {
        String[] descriptionAndDatetime = splitCommandWordsAndArgs(commandArgs, DATE_KEYWORD);
        String[] dateAndTime = splitCommandWordsAndArgs(descriptionAndDatetime[1].trim(),TIME_KEYWORD);
        return new String[]{descriptionAndDatetime[0],dateAndTime[0],dateAndTime[1]};
    }

    public static Command prepareAddEvent(String[] eventArgs) {
        try {
            String description = eventArgs[0];
            LocalDate date = parseArgsAsDate(eventArgs[1].trim());
            LocalTime time = parseArgsAsTime(eventArgs[2].trim());
            LocalDateTime dateTime = date.atTime(time);
            return new AddEventCommand(description, false, dateTime);
        } catch (ParseException e) {
            return new IncorrectCommand("could not parse date for event.");
        }
    }

    public static Command prepareAddDeadline(String[] deadlineArgs) {
        try {
            String description = deadlineArgs[0];
            LocalDate date = parseArgsAsDate(deadlineArgs[1].trim());
            LocalTime time = parseArgsAsTime(deadlineArgs[2].trim());
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            return new AddDeadlineCommand(description, false, dateTime);
        } catch (ParseException e) {
            return new IncorrectCommand("could not parse date for deadline.");
        }
    }

    public static LocalDate parseArgsAsDate(String str) throws DateTimeParseException, ParseException {
        if (str.trim().isEmpty()) {
            throw new ParseException("Could not find date to parse");
        }
        return LocalDate.parse(str.trim());
    }

    public static LocalTime parseArgsAsTime(String str) throws DateTimeParseException {
        if (str.trim().isEmpty()) {
            return LocalTime.MIN;
        }
        return LocalTime.parse(str.trim());
    }

    public static Command prepareDeleteTask(String taskNumber) {
        try {
            int displayIndex = parseArgsAsDisplayedIndex(taskNumber);
            return new DeleteCommand(displayIndex);
        } catch (ParseException e) {
            return new IncorrectCommand("could not parse display index.");
        }
    }

    private static Command prepareMarkAsDone(String taskNumber) {
        try {
            int displayIndex = parseArgsAsDisplayedIndex(taskNumber);
            return new MarkAsDoneCommand(displayIndex);
        } catch (ParseException e) {
            return new IncorrectCommand("could not parse display index.");
        }
    }

    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }
}
