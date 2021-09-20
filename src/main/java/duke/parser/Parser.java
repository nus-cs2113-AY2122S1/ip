package duke.parser;

import duke.command.AddTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.DoneCommand;
import duke.command.QuitCommand;
import duke.command.ListTasksCommand;
import duke.command.FindTasksCommand;
import duke.command.Command;
import duke.ui.Ui;
import duke.exception.DukeException;

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
    private static final String PARAMETER_BY = "/by";
    private static final String PARAMETER_AT = "/at";

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
        case COMMAND_FIND:
            String rawDescription = parseDescription(line, command);
            return new FindTasksCommand(rawDescription);
        default:
            ui.printInvalidCommand();
            throw new DukeException("Please provide a valid command");
        }
    }

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
