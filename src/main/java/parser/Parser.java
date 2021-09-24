package parser;

import commands.*;

import static common.Messages.*;

public class Parser {

    public Command parseCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString);
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
            String[] eventArgs = decodeTask(AddEventCommand.COMMAND_WORD, commandArgs);
            return prepareAddEvent(eventArgs);
        case AddDeadlineCommand.COMMAND_WORD:
            String[] deadlineArgs = decodeTask(AddDeadlineCommand.COMMAND_WORD, commandArgs);
            return prepareAddDeadline(deadlineArgs);
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

    private static String[] splitCommandWordsAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        if (split.length == 2) {
            return split;
        }
        return new String[]{split[0], ""};
    }

    private static String[] decodeTask(String commandType, String commandArgs) {
        if (commandType.equals(AddEventCommand.COMMAND_WORD)) {
            if (commandArgs.contains(AddEventCommand.KEYWORD)) {
                return commandArgs.trim().split(AddEventCommand.KEYWORD, 2);
            }
        } else if (commandType.equals(AddDeadlineCommand.COMMAND_WORD)) {
            if (commandArgs.contains(AddDeadlineCommand.KEYWORD)) {
                return commandArgs.trim().split(AddDeadlineCommand.KEYWORD, 2);
            }
        }
        return new String[0]; // empty string array
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

    public static Command prepareAddEvent(String[] eventArgs) {
        String description = eventArgs[0];
        String date = eventArgs[1];
        return new AddEventCommand(description, false, date);
    }

    public static Command prepareAddDeadline(String[] deadlineArgs) {
        String description = deadlineArgs[0];
        String date = deadlineArgs[1];
        return new AddDeadlineCommand(description, false, date);
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
