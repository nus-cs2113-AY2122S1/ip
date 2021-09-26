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
        final String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
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
    }

    private static String[] splitCommandWordsAndArgs(String input, String keyword) {
        final String[] split = input.trim().split(keyword, 2);
        if (split.length == 2) {
            return split;
        }
        return new String[]{split[0], ""};
    }

    public static String[] getTaskDateArgs(String commandArgs) {
        String[] descriptionAndDatetime = splitCommandWordsAndArgs(commandArgs, DATE_KEYWORD);
        String[] dateAndTime = splitCommandWordsAndArgs(descriptionAndDatetime[1].trim(), TIME_KEYWORD);
        return new String[]{descriptionAndDatetime[0], dateAndTime[0], dateAndTime[1]};
    }

    public static Command prepareAddTodo(String description) {
        return new AddTodoCommand(description, false);
    }

    public static Command prepareAddEvent(String[] eventArgs) throws DukeException {
        String description = eventArgs[0];
        LocalDate date = parseArgsAsDate(eventArgs[1].trim());
        LocalTime time = parseArgsAsTime(eventArgs[2].trim());
        LocalDateTime dateTime = date.atTime(time);
        return new AddEventCommand(description, false, dateTime);
    }

    public static Command prepareAddDeadline(String[] deadlineArgs) throws DukeException {

        String description = deadlineArgs[0];
        LocalDate date = parseArgsAsDate(deadlineArgs[1].trim());
        LocalTime time = parseArgsAsTime(deadlineArgs[2].trim());
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return new AddDeadlineCommand(description, false, dateTime);
    }

    public static LocalDate parseArgsAsDate(String str) throws DateTimeParseException, DukeException {
        if (str.trim().isEmpty()) {
            throw new DukeException("Could not find date to parse");
        }
        return LocalDate.parse(str.trim());
    }

    public static LocalTime parseArgsAsTime(String str) throws DateTimeParseException {
        if (str.trim().isEmpty()) {
            return LocalTime.MIN;
        }
        return LocalTime.parse(str.trim());
    }

    private static int parseArgsAsDisplayedIndex(String displayIndex) throws DukeException, NumberFormatException {
        if (displayIndex.isEmpty()) {
            throw new DukeException("You did not enter a task index. Try again.");
        }
        return Integer.parseInt(displayIndex.trim());
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
