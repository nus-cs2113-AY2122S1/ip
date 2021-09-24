package parser;

import command.AddDeadlineTaskCommand;
import command.AddEventTaskCommand;
import command.AddTodoTaskCommand;
import command.AgendaCommand;
import command.ClearTasksCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.FindTasksCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkAsDoneCommand;
import command.MarkAsNotDoneCommand;
import exception.AustinEmptyDescriptionException;
import exception.AustinEmptyKeywordException;
import exception.AustinEmptyTimeDetailsException;
import exception.AustinException;
import exception.AustinInvalidCommandException;
import exception.AustinInvalidCommandFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Parses and validates the user's command before returning the command to execute */
public class Parser {
    /**
     * Parses the command input by the user.
     * @param line User's command
     * @return Command to execute
     * @throws AustinException In case if the validation fails
     * @throws ArrayIndexOutOfBoundsException If some details are missing
     * @throws NumberFormatException If the task index is of different data type
     */
    public static Command parse(String line) throws AustinException,
            ArrayIndexOutOfBoundsException,
            NumberFormatException,
            DateTimeParseException {
        String command = getFirstWord(line);
        switch (command) {
        case (ListCommand.COMMAND_KEYWORD):
            return validateListCommand(line);
        case (HelpCommand.COMMAND_KEYWORD):
            return validateHelpCommand(line);
        case (FindTasksCommand.COMMAND_KEYWORD):
            return validateFindTasksCommand(line);
        case (AgendaCommand.COMMAND_KEYWORD):
            return validateAgendaCommand(line);
        case (AddTodoTaskCommand.COMMAND_KEYWORD):
            return validateAddTodoCommand(line);
        case (AddEventTaskCommand.COMMAND_KEYWORD):
            return validateAddEventCommand(line);
        case (AddDeadlineTaskCommand.COMMAND_KEYWORD):
            return validateAddDeadlineCommand(line);
        case (MarkAsDoneCommand.COMMAND_KEYWORD):
            return validateMarkAsDoneCommand(line);
        case (MarkAsNotDoneCommand.COMMAND_KEYWORD):
            return validateMarkAsNotDoneCommand(line);
        case (DeleteTaskCommand.COMMAND_KEYWORD):
            return validateDeleteTaskCommand(line);
        case (ClearTasksCommand.COMMAND_KEYWORD):
            return validateClearTasksCommand(line);
        case (ExitCommand.COMMAND_KEYWORD):
            return validateExitCommand(line);
        default:
            throw new AustinInvalidCommandException();
        }
    }

    /**
     * Validates the "find" command called by the user.
     * @param line User's command
     * @return "find" command to execute
     * @throws AustinEmptyKeywordException
     */
    private static FindTasksCommand validateFindTasksCommand(String line) throws
            AustinEmptyKeywordException {
        try {
            String search = removeFirstWord(line);
            return new FindTasksCommand(search);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AustinEmptyKeywordException();
        }
    }

    /**
     * Validates the "agenda" command called by the user.
     * @param line User's command
     * @return "agenda" command to execute
     * @throws AustinInvalidCommandFormatException If there are additional characters
     * apart from the command
     */
    private static AgendaCommand validateAgendaCommand(String line) throws
            AustinInvalidCommandFormatException {
        if (!line.equals(AgendaCommand.COMMAND_KEYWORD)) {
            throw new AustinInvalidCommandFormatException(AgendaCommand.COMMAND_KEYWORD);
        }
        return new AgendaCommand();
    }

    /**
     * Validates the "list" command called by the user.
     * @param line User's command
     * @return "list" command to execute
     * @throws AustinInvalidCommandFormatException If there are additional characters
     * apart from the command
     */
    private static ListCommand validateListCommand(String line) throws
            AustinInvalidCommandFormatException {
        if (!line.equals(ListCommand.COMMAND_KEYWORD)) {
            throw new AustinInvalidCommandFormatException(ListCommand.COMMAND_KEYWORD);
        }
        return new ListCommand();
    }

    /**
     * Validates the "help" command called by the user.
     * @param line User's command
     * @return "help" command to execute
     * @throws AustinInvalidCommandFormatException If there are additional characters
     * apart from the command
     */
    private static HelpCommand validateHelpCommand(String line) throws
            AustinInvalidCommandFormatException {
        if (!line.equals(HelpCommand.COMMAND_KEYWORD)) {
            throw new AustinInvalidCommandFormatException(HelpCommand.COMMAND_KEYWORD);
        }
        return new HelpCommand();
    }

    /**
     * Validates the "todo" command called by the user.
     * @param line User's command
     * @return "todo" command to execute
     * @throws AustinEmptyDescriptionException If the description of the task is empty
     */
    private static AddTodoTaskCommand validateAddTodoCommand(String line) throws
            AustinEmptyDescriptionException {
        try {
            String description = removeFirstWord(line);
            return new AddTodoTaskCommand(description);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AustinEmptyDescriptionException();
        }
    }

    /**
     * Validates the "event" command called by the user.
     * @param line User's command
     * @return "event" command to execute
     * @throws AustinInvalidCommandFormatException If the format of the command is invalid
     * @throws AustinEmptyTimeDetailsException If the time details are missing
     * @throws AustinEmptyDescriptionException If the task details are missing
     */
    private static AddEventTaskCommand validateAddEventCommand(String line) throws
            AustinInvalidCommandFormatException,
            AustinEmptyTimeDetailsException,
            AustinEmptyDescriptionException,
            DateTimeParseException {
        try {
            String details = removeFirstWord(line);
            if (!line.contains("|")) {
                throw new AustinInvalidCommandFormatException(
                        AddEventTaskCommand.COMMAND_KEYWORD);
            }
            int index = details.indexOf("|");
            String description = details.substring(0, index);
            String at = details.substring(index + 1).trim();
            if (at.isEmpty()) {
                throw new AustinEmptyTimeDetailsException();
            }
            LocalDateTime atDT = LocalDateTime.parse(at,
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new AddEventTaskCommand(description, atDT);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AustinEmptyDescriptionException();
        }
    }

    /**
     * Validates the "deadline" command called by the user.
     * @param line User's command
     * @return "deadline" command to execute
     * @throws AustinInvalidCommandFormatException If the format of the command is invalid
     * @throws AustinEmptyTimeDetailsException If the time details are missing
     * @throws AustinEmptyDescriptionException If the task details are missing
     */
    private static AddDeadlineTaskCommand validateAddDeadlineCommand(String line) throws
            AustinInvalidCommandFormatException,
            AustinEmptyTimeDetailsException,
            AustinEmptyDescriptionException,
            DateTimeParseException {
        try {
            String details = removeFirstWord(line);
            if (!line.contains("|")) {
                throw new AustinInvalidCommandFormatException(
                        AddDeadlineTaskCommand.COMMAND_KEYWORD);
            }
            int index = details.indexOf("|");
            String description = details.substring(0, index);
            String by = details.substring(index + 1).trim();
            if (by.isEmpty()) {
                throw new AustinEmptyTimeDetailsException();
            }
            LocalDateTime byDT = LocalDateTime.parse(by,
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new AddDeadlineTaskCommand(description, byDT);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AustinEmptyDescriptionException();
        }
    }

    /**
     * Validates the "done" command called by the user.
     * @param line User's command
     * @return "done" command to execute
     * @throws NumberFormatException If the task index is of different type
     * @throws ArrayIndexOutOfBoundsException If the task index is missing
     */
    private static MarkAsDoneCommand validateMarkAsDoneCommand(String line) throws
            NumberFormatException, ArrayIndexOutOfBoundsException {
        int index = Integer.parseInt(removeFirstWord(line)) - 1;
        return new MarkAsDoneCommand(index);
    }

    /**
     * Validates the "undo" command called by the user.
     * @param line User's command
     * @return "undo" command to execute
     * @throws NumberFormatException If the task index is of different type
     * @throws ArrayIndexOutOfBoundsException If the task index is missing
     */
    private static MarkAsNotDoneCommand validateMarkAsNotDoneCommand(String line) throws
            NumberFormatException, ArrayIndexOutOfBoundsException {
        int index = Integer.parseInt(removeFirstWord(line)) - 1;
        return new MarkAsNotDoneCommand(index);
    }

    /**
     * Validates the "delete" command called by the user.
     * @param line User's command
     * @return "delete" command to execute
     * @throws NumberFormatException If the task index is of different type
     * @throws ArrayIndexOutOfBoundsException If the task index is missing
     */
    private static DeleteTaskCommand validateDeleteTaskCommand(String line) throws
            NumberFormatException, ArrayIndexOutOfBoundsException {
        int index = Integer.parseInt(removeFirstWord(line)) - 1;
        return new DeleteTaskCommand(index);
    }

    /**
     * Validates the "clear" command called by the user.
     * @param line User's command
     * @return "clear" command to execute
     * @throws AustinInvalidCommandFormatException If there are additional characters
     * apart from the command
     */
    private static ClearTasksCommand validateClearTasksCommand(String line)
            throws AustinInvalidCommandFormatException {
        if (!line.equals(ClearTasksCommand.COMMAND_KEYWORD)) {
            throw new AustinInvalidCommandFormatException
                    (ClearTasksCommand.COMMAND_KEYWORD);
        }
        return new ClearTasksCommand();
    }

    /**
     * Validates the "bye" command called by the user.
     * @param line User's command
     * @return "bye" command to execute
     * @throws AustinInvalidCommandFormatException If there are additional characters
     * apart from the command
     */
    private static ExitCommand validateExitCommand(String line)
            throws AustinInvalidCommandFormatException {
        if (!line.equals(ExitCommand.COMMAND_KEYWORD)) {
            throw new AustinInvalidCommandFormatException
                    (ExitCommand.COMMAND_KEYWORD);
        }
        return new ExitCommand();
    }

    /**
     * Returns the first word of the user's command.
     * @param line User's command
     * @return The command keyword
     */
    private static String getFirstWord(String line) {
        return line.toLowerCase().split(" ")[0];
    }

    /**
     * Removes the first word of the user's command to extract details
     * like description and time.
     * @param line User's command
     * @return Command without the keyword
     * @throws ArrayIndexOutOfBoundsException If there are no details
     * apart from the keyword
     */
    private static String removeFirstWord(String line) throws
            ArrayIndexOutOfBoundsException {
        String[] inputs = line.split(" ", 2);
        return inputs[1];
    }
}