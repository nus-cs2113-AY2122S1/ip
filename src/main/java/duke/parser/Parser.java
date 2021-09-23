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
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.data.exception.IllegalCommandException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.util.DateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * parses given user input
     *
     * @param userInput String that should be parsed
     * @return Command object based on the parsed userInput
     */
    public static Command parseCommand(String userInput) {
        try {

            Matcher matcher = COMMAND_FORMAT.matcher(userInput.trim());

            if (!matcher.matches()) {
                throw new IllegalCommandException();
            }

            final String command = matcher.group("command").trim();
            final String arguments = matcher.group("arguments").trim();

            Command toReturn;

            switch (command) {
            case ListCommand.COMMAND_WORD:
                toReturn = prepareList(arguments);
                break;
            case ByeCommand.COMMAND_WORD:
                toReturn = new ByeCommand();
                break;
            case FindCommand.COMMAND_WORD:
                toReturn = new FindCommand(arguments);
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
                break;
            }

            return toReturn;
        } catch (IllegalCommandException e) {
            return new ErrorCommand(MESSAGE_INVALID_COMMAND_EXCEPTION);
        } catch (NullPointerException e) {
            return new ErrorCommand(MESSAGE_MISSING_ARGUMENTS_EXCEPTION);
        }
    }

    public static final Pattern COMMAND_EVENT_FORMAT = Pattern.compile(
            "(?<description>^.*(?=/at))/at(?<deadline>.*)");

    public static final Pattern COMMAND_DEADLINE_FORMAT = Pattern.compile(
            "(?<description>^.*(?=/by))/by(?<deadline>.*)");

    public static final Pattern COMMAND_DATE_TIME_FORMAT = Pattern.compile(
            "(?<date>\\d+[:/]\\d+[:/]\\d+)(?<time>.*)");

    private static Command prepareEvent(String args) throws IllegalCommandException {
        return prepareDeadlineEvent(args, Event.TASK_TYPE);
    }

    private static Command prepareDeadline(String args) throws IllegalCommandException {
        return prepareDeadlineEvent(args, Deadline.TASK_TYPE);
    }

    /**
     * Additional parsing and preparation for Event and Deadline commands
     *
     * @param args      arguments given after the command
     * @param eventType 'E' or 'D' depending on if it is an Event or Deadline
     * @return Event or Deadline command
     * @throws IllegalCommandException returns exception if an invalid eventtype was given or command arguments was
     *                                 malformed
     */
    private static Command prepareDeadlineEvent(String args, char eventType) throws IllegalCommandException {
        Pattern COMMAND_FORMAT;

        switch (eventType) {
        case Deadline.TASK_TYPE:
            COMMAND_FORMAT = COMMAND_DEADLINE_FORMAT;
            break;
        case Event.TASK_TYPE:
            COMMAND_FORMAT = COMMAND_EVENT_FORMAT;
            break;
        default:
            throw new IllegalCommandException();
        }

        final Matcher matcher = COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new IllegalCommandException();
        }

        final String description = matcher.group("description").trim();
        final String deadline = matcher.group("deadline").trim();

        DateTime dateTime = parseDateTime(deadline);

        LocalDate deadlineDate = dateTime.getDate();
        LocalTime deadlineTime = dateTime.getTime();

        if (eventType == Deadline.TASK_TYPE) {
            if (deadlineDate == null) {
                return new DeadlineCommand(description, deadline);
            } else {
                return new DeadlineCommand(description, deadlineDate, deadlineTime);
            }
        } else {
            if (deadlineDate == null) {
                return new EventCommand(description, deadline);
            } else {
                return new EventCommand(description, deadlineDate, deadlineTime);
            }
        }

    }

    /**
     * Parses string to find if any date or time exists
     *
     * @param dateTimeString String to be parsed
     * @return DateTime object containing LocalDate and LocalTime objects parsed from dateTimeString
     */
    public static DateTime parseDateTime(String dateTimeString) {
        final Matcher dateMatcher = COMMAND_DATE_TIME_FORMAT.matcher(dateTimeString);

        LocalDate deadlineDate = null;
        LocalTime deadlineTime = null;

        if (dateMatcher.matches()) {
            final String date = dateMatcher.group("date").trim();
            final String time = dateMatcher.group("time").trim();

            deadlineDate = DateParser.parseDate(date);

            if (!time.isEmpty()) {
                deadlineTime = DateParser.parseTime(time);
            }
        }

        return new DateTime(deadlineDate, deadlineTime);
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

    public static final Pattern COMMAND_LIST_DATE_FORMAT = Pattern.compile(
            "/on(?<date>.*)");

    private static Command prepareList(String args) {
        final Matcher matcher = COMMAND_LIST_DATE_FORMAT.matcher(args);

        if (!matcher.find()) {
            return new ListCommand();
        }

        final String dateString = matcher.group("date").trim();
        LocalDate date = DateParser.parseDate(dateString);

        return new ListCommand(date);
    }
}
