package duke;

import duke.exception.DukeCommandException;

import java.util.ArrayList;

public class InputParser {

    public static ArrayList<String> parseInput(String input) {
        ArrayList<String> arguments = new ArrayList<>();
        String command = input.split(" ")[0];
        arguments.add(command);
        String parameters = input.replaceFirst(command, "");
        int separatorIndex = parameters.indexOf('/');
        if(separatorIndex != -1 && separatorIndex != parameters.length() - 1) {
            arguments.add(parameters.split("/")[0]);
            arguments.add(parameters.split("/")[1]);
        } else {
            if(!parameters.isBlank())
                arguments.add(parameters);
        }
        return arguments;
    }

    public static void handleInput(ArrayList<String> arguments) throws DukeCommandException {
        switch (arguments.get(0)) {
        case "delete":
            Command.executeDelete(arguments);
            break;

        case "done":
            Command.executeDone(arguments);
            break;

        case "list":
            Command.executeList();
            break;

        case "todo":
        case "deadline":
        case "event":
            Command.executeAdd(arguments);
            break;
        default:
            throw new DukeCommandException();
        }
    }
}
