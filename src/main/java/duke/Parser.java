package duke;

import duke.data.Storage;
import duke.exception.DukeInvalidInputException;
import duke.exception.DukeTimeFormatException;
import duke.exception.DukeParameterException;
import duke.exception.DukeCommandException;
import duke.exception.DukeTaskNotFoundException;

import java.util.ArrayList;

/**
 * This class contains methods that deal with the parsing of user input into its components (command and parameters)
 * and also with the handling of user input once the input has been broken down
 */
public class Parser {

    public static final String HELP = "help";
    public static final String DELETE = "delete";
    public static final String DONE = "done";
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String FIND = "find";

    /**
     * This function takes in the user input and returns the input broken into its constituent parameters
     * @param input the user input
     * @return the input broken up into the command and the parameters as an arrayList<String>, which is in
     * the order command, parameter, parameter (If it's a 3 argument command like deadline, event)
     */
    public static ArrayList<String> parseInput(String input) throws DukeParameterException {
        String trimmedInput = input.trim();
        ArrayList<String> arguments = new ArrayList<>();

        //First word of the input is command so we split at first space
        String command = trimmedInput.split(" ")[0];
        arguments.add(command);

        //Separate the command from user input for further parsing
        String parameters = trimmedInput.replaceFirst(command, "");

        //Check if is a 3 param or 2 param command (3 param commands contain a '/')
        int separatorIndex = parameters.indexOf('/');
        if (separatorIndex != -1 && separatorIndex != parameters.length() - 1) {
            String[] splitParams = parameters.split("/", 2);
            if (!splitParams[0].isBlank() && !splitParams[1].isBlank()) {
                arguments.add(splitParams[0].trim());
                arguments.add(splitParams[1].trim());
            } else {
                throw new DukeParameterException();
            }
        } else {
            if (!parameters.isBlank()) {
                arguments.add(parameters.trim());
            }
        }
        return arguments;
    }

    /**
     * This function given the arguments of the user input, then calls the appropriate functions based on the command
     * @param arguments an arrayList containing the user input string broken into its components
     * @throws DukeCommandException if the command given is invalid
     */
    public static void handleInput(ArrayList<String> arguments) throws DukeCommandException, DukeParameterException, DukeInvalidInputException, DukeTaskNotFoundException, DukeTimeFormatException {
        //arguments[0] is the command that was inputted by the user
        //arguments[1], arguments[2] (For the case of deadline, events) are the parameters that were given to the command
        switch (arguments.get(0).toLowerCase()) {
        case HELP:
            Ui.printHelpMessage();
            break;

        case FIND:
            Command.executeFind(arguments.get(1));
            break;

        case DELETE:
            Command.executeDelete(arguments.get(1));
            Storage.write();
            break;

        case DONE:
            Command.executeDone(arguments.get(1));
            Storage.write();
            break;

        case LIST:
            Command.executeList();
            break;

        case TODO:
            //Fallthrough
        case DEADLINE:
            //Fallthrough
        case EVENT:
            Command.executeAdd(arguments);
            Storage.write();
            break;
        default:
            throw new DukeCommandException();
        }
    }
}
