package duke.parser;

import duke.command.AddTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.DoneCommand;
import duke.command.QuitCommand;
import duke.command.ListTasksCommand;
import duke.command.Command;
import duke.ui.Ui;
import duke.exception.DukeException;

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
    private static final String PARAMETER_BY = "/by";
    private static final String PARAMETER_AT = "/at";

    /**
     * Returns the corresponding command based on user input.
     *
     * @param line The user input.
     * @return A command corresponding to the user input.
     * @throws DukeException If the user input is not a valid command.
     */
    public Command execute(String line) throws DukeException {
        String[] words = line.split(" "); // Convert sentence into array of words
        String command;
        try {
            command = words[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidCommand();
            throw new DukeException("Please provide a valid command");
        }
        switch (command) {
        case COMMAND_BYE:
            return new QuitCommand();
        case COMMAND_LIST:
            return new ListTasksCommand();
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            return executeAdd(command, line);
        case COMMAND_DELETE:
        case COMMAND_DONE:
            return executeTaskId(line, command);
        default:
            ui.printInvalidCommand();
            throw new DukeException("Please provide a valid command");
        }
    }

    /**
     * Returns the corresponding AddTaskCommand based on user input.
     *
     * @param command The user command corresponding to the type of
     *                task to be added.
     * @param line    The user input.
     * @return The corresponding AddTaskCommand.
     * @throws DukeException If the command is invalid.
     */
    public static AddTaskCommand executeAdd(String command, String line) throws DukeException {
        String[] details;
        String rawDescription = parseDescription(line, command);
        switch (command) {
        case COMMAND_TODO:
            return new AddTaskCommand(command, rawDescription);
        case COMMAND_DEADLINE:
            int indexBy = rawDescription.indexOf(PARAMETER_BY);
            details = parseTaskDetails(rawDescription, command, indexBy);
            return new AddTaskCommand(command, details);
        case COMMAND_EVENT:
            int indexAt = rawDescription.indexOf(PARAMETER_AT);
            details = parseTaskDetails(rawDescription, command, indexAt);
            return new AddTaskCommand(command, details);
        default:
            throw new DukeException("Invalid Command!");
        }
    }

    /**
     * Returns the description of the command.
     *
     * @param line    The user input.
     * @param command The user command.
     * @return The description of the command.
     * @throws DukeException If the command description is empty.
     */
    private static String parseDescription(String line, String command) throws DukeException {
        int descPos = line.indexOf(" ");
        String description;
        try {
            description = line.substring(descPos).strip();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmptyDescription(command);
            throw new DukeException("Please provide a description");
        }
        return description.strip();
    }

    /**
     * Returns a String Array of size 2, containing the description and
     * timing information for Event and Deadline tasks.
     *
     * @param rawDescription The entire description.
     * @param command        The command from the user.
     * @param paramPos       The index of "/at" or "/by"
     * @return A String Array of size 2, containing the description (task name)
     * and timing information.
     * @throws DukeException If the description format provided is invalid.
     */
    public static String[] parseTaskDetails(
            String rawDescription, String command, int paramPos)
            throws DukeException {
        String[] description = new String[2];
        try {
            description[0] = rawDescription.substring(0, paramPos - 1).strip();
            description[1] = rawDescription.substring(paramPos + 4).strip();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printCorrectCommandFormat(command);
            throw new DukeException("Please try again!");
        }
        return description;
    }

    /**
     * Returns the command with the correct task ID.
     *
     * @param line    The user input.
     * @param command The command from the user.
     * @return The command to be executed with the correct task ID.
     * @throws DukeException If the command is invalid.
     */
    private static Command executeTaskId(String line, String command) throws DukeException {
        Integer taskId = getTaskId(line);
        switch (command) {
        case "delete":
            return new DeleteTaskCommand(taskId - 1);
        case "done":
            return new DoneCommand(taskId - 1);
        default:
            throw new DukeException("Invalid Command!");
        }
    }

    /**
     * Returns the task ID as an Integer from the user input.
     *
     * @param line The user input.
     * @return The task ID as an Integer.
     * @throws DukeException If the task ID from the user input is
     *                       not an integer, or if no task ID was provided.
     */
    private static Integer getTaskId(String line) throws DukeException {
        int spacePos = line.indexOf(" ");
        String taskId = line.substring(spacePos + 1).strip();
        int num;
        try {
            if (taskId.equals("") || taskId.equals("done") || taskId.equals("delete")) {
                throw new DukeException("Please provide task ID");
            }
            num = Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            ui.printInvalidTaskNumberFormat();
            throw new DukeException("Please provide an integer");
        }
        return num;
    }
}
