package duke;

import duke.data.Storage;
import duke.exception.DukeCommandException;

import java.util.ArrayList;

public class Parser {

    public static final String HELP = "help";
    public static final String DELETE = "delete";
    public static final String DONE = "done";
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    /**
     * This function takes in the user input and returns the input broken into its constituent parameters
     * @param input the user input
     * @return the input broken up into the command and the parameters as an arrayList<String>, which is in
     * the order command, parameter, parameter (If it's a 3 argument command like deadline, event)
     */
    public static ArrayList<String> parseInput(String input) {
        String trimmedInput = input.trim();
        ArrayList<String> arguments = new ArrayList<>();
        String command = trimmedInput.split(" ")[0];
        arguments.add(command);
        String parameters = trimmedInput.replaceFirst(command, "");
        int separatorIndex = parameters.indexOf('/');
        if(separatorIndex != -1 && separatorIndex != parameters.length() - 1) {
            String[] splitParams = parameters.split("/",2);
            if(!splitParams[0].isBlank() && !splitParams[1].isBlank()) {
                arguments.add(splitParams[0].trim());
                arguments.add(splitParams[1].trim());
            } else {
                Ui.printParameterErrorMessage();
            }
        } else {
            if(!parameters.isBlank()) {
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
    public static void handleInput(ArrayList<String> arguments) throws DukeCommandException {
        //arguments(0) is the command that was inputted by the user
        switch (arguments.get(0)) {
        case HELP:
            Ui.printHelpMessage();
            break;

        case DELETE:
            Command.executeDelete(arguments);
            Storage.write();
            break;

        case DONE:
            Command.executeDone(arguments);
            Storage.write();
            break;

        case LIST:
            Command.executeList();
            break;

        case TODO:
        case DEADLINE:
        case EVENT:
            Command.executeAdd(arguments);
            Storage.write();
            break;
        default:
            throw new DukeCommandException();
        }
    }
}
