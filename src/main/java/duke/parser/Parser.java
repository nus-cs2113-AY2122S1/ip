package duke.parser;

import duke.command.DeleteTaskCommand;
import duke.command.DoneCommand;
import duke.command.QuitCommand;
import duke.command.ListTasksCommand;
import duke.command.FindTasksCommand;
import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.Command;
import duke.ui.Ui;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Parses user input to make sense of the input.
 */
public class Parser {
    private static final Ui ui = new Ui();

    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    /**
     * Returns the corresponding command object (to be executed) based
     * on user input.
     *
     * @param line The user input.
     * @return A command object corresponding to the user input.
     * @throws DukeException If the user input is not a valid command.
     */
    public Command execute(String line) throws DukeException {
        String command = parseCommand(line);
        switch (command) {
        case COMMAND_BYE:
            return new QuitCommand();
        case COMMAND_LIST:
            return new ListTasksCommand();
        case COMMAND_TODO:
            return new AddTodoCommand(line);
        case COMMAND_DEADLINE:
            return new AddDeadlineCommand(line);
        case COMMAND_EVENT:
            return new AddEventCommand(line);
        case COMMAND_DELETE:
            return new DeleteTaskCommand(line);
        case COMMAND_DONE:
            return new DoneCommand(line);
        case COMMAND_FIND:
            return new FindTasksCommand(line);
        default:
            ui.printInvalidCommand();
            throw new DukeException("Please provide a valid command");
        }
    }

    /**
     * Obtains and returns the command word from the user input.
     *
     * @param line The user input.
     * @return The user command (command word).
     * @throws DukeException If no command word was found.
     */
    private static String parseCommand(String line) throws DukeException {
        String[] words = line.split(" "); // Convert sentence into array of words
        String command;
        try {
            command = words[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidCommand();
            throw new DukeException("Please provide a valid command");
        }
        return command;
    }

    /**
     * Returns the description of the command.
     *
     * @param line    The user input.
     * @param command The user command.
     * @return The description of the command.
     * @throws DukeException If the command description is empty.
     */
    public static String parseDescription(String line, String command) throws DukeException {
        int descriptionIndex = line.indexOf(" ");
        String description;
        try {
            description = line.substring(descriptionIndex).strip();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmptyDescription(command);
            throw new DukeException("Please provide a description");
        }
        return description.strip();
    }

    /**
     * Splits the description and timing information. Then, returns
     * a String Array of length 2, containing the description and
     * timing information for Event and Deadline tasks.
     *
     * @param rawDescription The entire description (excluding the command).
     * @param command        The command (command word) from the user.
     * @param indexSplit     The index of "/at" or "/by"
     * @return A String Array of length 2, containing the description (task name)
     * and timing information.
     * @throws DukeException If the description format provided is invalid.
     */
    public static String[] splitTaskDetails(
            String rawDescription, String command, int indexSplit)
            throws DukeException {
        String[] description = new String[2];
        try {
            description[0] = rawDescription.substring(0, indexSplit - 1).strip();
            description[1] = rawDescription.substring(indexSplit + 4).strip();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printCorrectCommandFormat(command);
            throw new DukeException("Please try again!");
        }
        return description;
    }

    /**
     * Returns the task ID as an Integer from the user input.
     *
     * @param line The user input.
     * @return The task ID as an Integer.
     * @throws DukeException If the task ID from the user input is
     *                       not an integer, or if no task ID was provided.
     */
    public static Integer getTaskId(String line) throws DukeException {
        int spaceIndex = line.indexOf(" ");
        String taskId = line.substring(spaceIndex + 1).strip();
        int num;
        try {
            if (taskId.equals("") || taskId.equals(COMMAND_DONE) || taskId.equals(COMMAND_DELETE)) {
                throw new DukeException("Please provide task ID");
            }
            num = Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            ui.printInvalidTaskNumberFormat();
            throw new DukeException("Please provide an integer");
        }
        return num - 1;
    }

    private static LocalDate parseDate(String description) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(description);
        } catch (DateTimeParseException ignore) {
        }
        return date;
    }

    private static LocalTime parseTime(String description) {
        LocalTime time = null;
        try {
            time = LocalTime.parse(description);
        } catch (DateTimeParseException ignore) {
        }
        return time;
    }

    private static LocalTime getTime(String[] description) {
        LocalTime time;
        for (String s : description) {
            time = parseTime(s);
            if (time != null) {
                return time;
            }
        }
        return null;
    }

    private static LocalDate getDate(String[] description) {
        LocalDate date;
        for (String s : description) {
            date = parseDate(s);
            if (date != null) {
                return date;
            }
        }
        return null;
    }

    /**
     * Attempts to parse a given String and returns a
     * LocalDateTime object if successful.
     *
     * @param line Description String to be parsed.
     * @return A LocalDateTime object if successful, returns
     * null otherwise.
     */
    public static LocalDateTime parseDateAndTime(String line) {
        String[] description = line.split(" ");
        LocalDate date = getDate(description);
        LocalTime time = getTime(description);
        if (date != null && time != null) {
            return LocalDateTime.of(date, time);
        }
        return null;
    }
}
