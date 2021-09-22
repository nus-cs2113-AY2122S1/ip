package duke.parser;

import static duke.ui.Strings.MESSAGE_INVALID_COMMAND_EXCEPTION;
import static duke.ui.Strings.MESSAGE_MISSING_ARGUMENTS_EXCEPTION;
import static duke.ui.Strings.MESSAGE_NUMBER_FORMAT_EXCEPTION;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ErrorCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.data.exception.IllegalCommandException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    public static Command parseCommand(String userInput) {
        try {

            Matcher matcher = COMMAND_FORMAT.matcher(userInput.trim());

            if (!matcher.matches()) {
                throw new IllegalCommandException();
            }

            final String command = matcher.group("command");
            final String arguments = matcher.group("arguments");

            Command toReturn;

            switch (command) {
            case ListCommand.COMMAND_WORD:
                toReturn = new ListCommand();
                break;
            case ByeCommand.COMMAND_WORD:
                toReturn = new ByeCommand();
                break;
            case DoneCommand.COMMAND_WORD:
                toReturn = prepareDone(arguments);
                break;
            case TodoCommand.COMMAND_WORD:
                toReturn = prepareTodo(arguments);
                break;
            case DeadlineCommand.COMMAND_WORD:
                toReturn = prepareDeadline(arguments);
                break;
            case EventCommand.COMMAND_WORD:
                toReturn = prepareEvent(arguments);
                break;
            case DeleteCommand.COMMAND_WORD:
                toReturn = prepareDelete(arguments);
                break;
            default:
                toReturn = new ErrorCommand(MESSAGE_INVALID_COMMAND_EXCEPTION);
            }

            return toReturn;
        } catch (IllegalCommandException e) {
            return new ErrorCommand(MESSAGE_INVALID_COMMAND_EXCEPTION);
        } catch (NullPointerException e) {
            return new ErrorCommand(MESSAGE_MISSING_ARGUMENTS_EXCEPTION);
        }
    }

    public static final Pattern COMMAND_EVENT_FORMAT = Pattern.compile(
            "(?<description>^.*(?=/at))/at\\s(?<deadline>.*)");

    private static Command prepareEvent(String args) throws IllegalCommandException {
        final Matcher matcher = COMMAND_EVENT_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new IllegalCommandException();
        }

        final String description = matcher.group("description").trim();
        final String deadline = matcher.group("deadline").trim();

        return new EventCommand(description, deadline);
    }

    public static final Pattern COMMAND_DEADLINE_FORMAT = Pattern.compile(
            "(?<description>^.*(?=/by))/by\\s(?<deadline>.*)");

    private static Command prepareDeadline(String args) throws IllegalCommandException {
        final Matcher matcher = COMMAND_DEADLINE_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new IllegalCommandException();
        }

        final String description = matcher.group("description").trim();
        final String deadline = matcher.group("deadline").trim();

        return new DeadlineCommand(description, deadline);
    }

    private static Command prepareTodo(String args) {
        if (args.isEmpty()) {
            return new ErrorCommand(MESSAGE_MISSING_ARGUMENTS_EXCEPTION);
        } else {
            return new TodoCommand(args.trim());
        }
    }

    private static Command prepareDone(String args) {
        try {
            int index = Integer.parseInt(args.trim());
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            return new ErrorCommand(MESSAGE_NUMBER_FORMAT_EXCEPTION);
        }
    }

    private static Command prepareDelete(String args) {
        try {
            int index = Integer.parseInt(args.trim());
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new ErrorCommand(MESSAGE_NUMBER_FORMAT_EXCEPTION);
        }
    }
}
