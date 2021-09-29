package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.MissingParameterException;
import duke.exception.WrongCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An object to parse user input to the format Duke can understand
 */
public class Parser {

    public static String parseInput(String command) {
        return command.toLowerCase().trim();
    }

    /**
     * Parses a number in the command given by the user
     *
     * @param command the command given by the user
     * @return the task number that user specified
     * @throws EmptyDescriptionException when the command is of the wrong format
     * @throws NumberFormatException when no number is found in the command
     */
    public static int parseNumber(String command) throws EmptyDescriptionException, NumberFormatException {
        if (command.split(" ").length < 2) {
            throw new EmptyDescriptionException();
        }
        return Integer.parseInt(command.split(" ")[1]);
    }

    public static String parseKeyword(String command) {
        return command.substring(command.indexOf("find") + 5);
    }


    /**
     * Parses the task from the command to the recognized format
     *
     * @param command the command given by the user
     * @param taskType the type of task that the parser recognized this command belong to
     * @return the descriptions of the given task
     * @throws EmptyDescriptionException when the command is empty or of wrong format
     * @throws MissingParameterException when some needed part of the description is missing
     */
    public static String[] parseTask(String command, Action taskType) throws EmptyDescriptionException, MissingParameterException {
        String[] parameters = new String[2];
        //Check if the description for is empty
        if (parseInput(command).split(" ").length < 2) {
            throw new EmptyDescriptionException();
        }
        switch (taskType) {
        case TO_DO:
            parameters[0] = command.substring(command.indexOf("todo") + 5);
            parameters[1] = "N/A";
            break;
        case EVENT:
            if (command.indexOf("/at") == -1) {
                throw new MissingParameterException();
            }
            parameters[0] = command.substring(command.indexOf("event") + 6, command.indexOf("/at"));
            parameters[1] = command.substring(command.indexOf("/at") + 3);
            break;
        case DEADLINE:
            if (command.indexOf("/by") == -1) {
                throw new MissingParameterException();
            }
            parameters[0] = command.substring(command.indexOf("deadline") + 9, command.indexOf("/by"));
            parameters[1] = command.substring(command.indexOf("/by") + 3);
            break;
        default:
            parameters[0] = "N/A";
            parameters[1] = "N/A";

        }
        parameters[0] = parameters[0].trim();
        parameters[1] = parameters[1].trim();
        return parameters;
    }

    /**
     * Gets the intended action from the user's command
     *
     * @param command The command given by the user
     * @return the action that this command specified
     * @throws WrongCommandException when the command is not recognizable
     */
    public static Action translateAction(String command) throws WrongCommandException {
        String normalizedCommand = parseInput(command).split(" ")[0];
        if (normalizedCommand.equals("list")) {
            return Action.LIST;
        } else if (normalizedCommand.equals("bye")) {
            return Action.QUIT;
        } else if (normalizedCommand.equals("done")) {
            return Action.MARK_DONE;
        } else if (normalizedCommand.equals("todo")) {
            return Action.TO_DO;
        } else if (normalizedCommand.equals("deadline")) {
            return Action.DEADLINE;
        } else if (normalizedCommand.equals("event")) {
            return Action.EVENT;
        } else if (normalizedCommand.equals("delete")) {
            return Action.DELETE;
        }else if (normalizedCommand.equals("find")) {
            return Action.FIND;
        } else {
            throw new WrongCommandException();
        }
    }
}
