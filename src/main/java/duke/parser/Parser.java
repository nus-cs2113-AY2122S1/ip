package duke.parser;

import duke.commands.*;
import duke.commands.Command;
import duke.exception.DukeEmptyParaException;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param fullCommand full user input string
     * @return the command based on the input
     * @throws DukeException on input error
     */
    public static Command parse(String fullCommand) throws DukeException {
        /** Used for extracting the command word. */
        String command = fullCommand.contains(" ") ? fullCommand.split(" ")[0] : fullCommand;

        switch (command) {
            case "deadline":
            case "event":
            case "todo":
                return new AddCommand(parseTask(command, fullCommand));
            case "exit":
                return new ExitCommand();
            case "delete":
                return new DeleteCommand(parseIndex(command, fullCommand));
            case "done":
                return new DoneCommand(parseIndex(command, fullCommand));
            case "find":
                return new FindCommand(parseKeyword(fullCommand));
            case "help":
                return new HelpCommand();
            case "list":
                return new ListCommand();
            default:
                throw new InvalidInputException("I'm sorry, but I don't know what that means");
        }
    }

    /**
     * Extracts index from user input and Converts it into <code>int</code> type.
     */
    public static int parseIndex(String command, String fullCommand) throws DukeEmptyParaException {
        int i = fullCommand.indexOf(" ");
        if (i == -1) {
            throw new DukeEmptyParaException("There should be an index of task to " + command);
        }

        return Integer.parseInt(fullCommand.substring(i + 1));
    }

    public static String parseKeyword(String fullCommand) throws DukeEmptyParaException {
        int i = fullCommand.indexOf(" ");
        if (i == -1) {
            throw new DukeEmptyParaException("The keyword to find cannot be empty");
        }

        return fullCommand.substring(i + 1);
    }

    /**
     * Parses arguments in the context of the add task command.
     *
     * @param command command word "todo", "deadline" and "event"
     * @param fullCommand full user input string
     * @return parsed task to be added into list
     * @throws DukeException on Empty parameters and Invalid input
     */
    public static Task parseTask(String command, String fullCommand) throws DukeException {
        int i = fullCommand.indexOf(" ");
        String taskDetails = " ";
        if (i != -1) {
            taskDetails = fullCommand.substring(i + 1);
        }

        if (taskDetails.isBlank()) {
            // the string is empty or contains only white space
            throw new DukeEmptyParaException("The description of a " + command + " cannot be empty");
        } else {
            switch (command) {
                case "todo":
                    return new ToDos(taskDetails);
                case "deadline":
                    return parseDeadline(taskDetails);
                case "event":
                    return parseEvent(taskDetails);
            }
        }
        return null;
    }

    /**
     * Parses Task information into Deadline object.
     *
     * @param taskDetails Description and Date of a deadline task
     * @return A Deadline task created based on given tasks details
     * @throws InvalidInputException missing keyword "/by"
     */
    public static Deadline parseDeadline(String taskDetails) throws InvalidInputException {
        int i = taskDetails.indexOf(" /by ");
        if ( i == -1) {
            throw new InvalidInputException("There should be a \"/by\" in the deadline");
        }

        String description = getDescription(i, taskDetails);
        String by = getTime(i, taskDetails);
        return new Deadline(description, by);

    }

    /**
     * Parses Task information into Event object.
     *
     * @param taskDetails Description and Time of an event task
     * @return An Event task created based on given tasks details
     * @throws InvalidInputException missing keyword "/at"
     */
    public static Events parseEvent(String taskDetails) throws InvalidInputException {
        int i = taskDetails.indexOf(" /at ");
        if ( i == -1) {
            throw new InvalidInputException("There should be a \"/at\" in the event");
        }

        String description = getDescription(i, taskDetails);
        String at = getTime(i, taskDetails);
        return new Events(description, at);
    }

    /**
     * Extracts description of task.
     */
    public static String getDescription(int index, String taskDetails){
        return taskDetails.substring(0, index);
    }

    /**
     * Extracts date/time of a deadline/event task.
     */
    public static String getTime(int index, String taskDetails){
        final int KEYWORD_LENGTH = 5;
        return taskDetails.substring(index + KEYWORD_LENGTH);
    }


}
