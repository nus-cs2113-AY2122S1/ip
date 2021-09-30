package duke.parser;

import duke.command.*;
import duke.data.exception.*;
import duke.data.task.Task;
import duke.data.task.ToDo;
import duke.data.task.Event;
import duke.data.task.Deadline;

public class Parser {

    /**
     * Determine the command type of the command given by the user
     *
     * @param fullCommand  The input by the user
     * @return a string that represents the command type
     * @throws InvalidInputException if the command does not belong to any knowing type
     */
    public static String getType(String fullCommand) throws InvalidInputException {
        if (fullCommand.contains("done")) {
            return "DONE";
        } else if (fullCommand.contains("find")) {
            return "FIND";
        }else if (fullCommand.contains("help")) {
            return "HELP";
        } else if (fullCommand.contains("delete")) {
            return "DELETE";
        } else if (fullCommand.contains("bye")) {
            return "EXIT";
        } else if (fullCommand.contains("list")) {
            return "LIST";
        } else if (fullCommand.contains("todo")) {
            return "T";
        } else if (fullCommand.contains("deadline")) {
            return "D";
        } else if (fullCommand.contains("event")) {
            return "E";
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Parses the full command given by the user as input.
     *
     * @param fullCommand The input by the user that is to be parsed.
     * @return A <code>Command</code> that corresponds to the user input.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String commandType = getType(fullCommand);
        String[] commandParts = fullCommand.trim().split(" ", 2);

        switch (commandType) {
        case "HELP":
            return getHelpCommand();
        case "EXIT":
            return getExitCommand();
        case "LIST":
            return getListCommand();
        case "FIND":
            return getFindCommand(commandParts);
        case "DONE":
            return getDoneCommand(commandParts);
        case "DELETE":
            return getDeleteCommand(commandParts);
        default:
            return getAddCommand(commandType, commandParts);

        }
    }


    private static ExitCommand getExitCommand() {
        return new ExitCommand();
    }

    private static HelpCommand getHelpCommand() {
        return new HelpCommand();
    }

    private static ListCommand getListCommand() {
        return new ListCommand();
    }
    private static FindCommand getFindCommand(String[] commandParts) throws MissingParaException {
        if (commandParts.length < 2) {
            throw new MissingParaException("in Find Task");
        }
        return new FindCommand(commandParts[1].trim());
    }

    private static DoneCommand getDoneCommand(String[] commandParts)
            throws MissingParaException, InvalidInputException {
        if (commandParts.length < 2) {
            throw new MissingParaException("in Done Task");
        }
        try {
            int Index = Integer.parseInt(commandParts[1].trim()) ;
            return new DoneCommand(Index);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("in Done Task");
        }
    }

    private static DeleteCommand getDeleteCommand(String[] commandParts) throws DukeException {
        if (commandParts.length < 2) {
            throw new MissingParaException("in Delete Task");
        }
        try {
            int Index = Integer.parseInt(commandParts[1].trim());
            return new DeleteCommand(Index);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("in Delete Task");
        }
    }

    private static AddCommand getAddCommand(String commandType, String[] commandParts) throws DukeException {
        return new AddCommand(parseTask(commandType, commandParts));
    }

    /**
     * Returns the task parameters that will be added from the given inputs
     *
     * @param commandType  The task type "todo", "deadline", or "event"
     * @param commandParts The array of string that contains information about at/by time for event/deadline task
     * @return The task that will be created from the given inputs
     */
    private static Task parseTask(String commandType, String[] commandParts) throws DukeException {
        if (commandParts.length < 2) {
            throw new EmptyTaskException();
        }

        String taskDetails = commandParts[1].trim();
        switch (commandType) {
        case "T":
            return new ToDo(taskDetails);
        case "D":
            if (!commandParts[1].contains("/by")) {
                throw new MissingParaException();
            }
            String[] deadlineDetails = taskDetails.split("/by", 2);
            String deadlineDescription = deadlineDetails[0].trim();
            String by = deadlineDetails[1].trim();
            return new Deadline(deadlineDescription, by);
        case "E":
            if (!commandParts[1].contains("/at")) {
                throw new MissingParaException();
            }
            String[] eventDetails = taskDetails.split("/at", 2);
            String eventDescription = eventDetails[0].trim();
            String at = eventDetails[1].trim();
            return new Event(eventDescription, at);
        default:
            throw new DukeException("Unknown error");
        }
    }
}
