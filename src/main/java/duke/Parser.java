package duke;

import duke.data.Storage;
import duke.exception.DukeCommandException;

import java.util.ArrayList;

public class Parser {

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

    public static void handleInput(ArrayList<String> arguments) throws DukeCommandException {
        switch (arguments.get(0)) {
        case "help":
            Ui.printHelpMessage();
            break;

        case "delete":
            Command.executeDelete(arguments);
            Storage.write();
            break;

        case "done":
            Command.executeDone(arguments);
            Storage.write();
            break;

        case "list":
            Command.executeList();
            break;

        case "todo":
        case "deadline":
        case "event":
            Command.executeAdd(arguments);
            Storage.write();
            break;
        default:
            throw new DukeCommandException();
        }
    }
}
