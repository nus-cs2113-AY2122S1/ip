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
            arguments.add(parameters.split("/")[0].trim());
            arguments.add(parameters.split("/")[1].trim());
        } else {
            if(!parameters.isBlank())
                arguments.add(parameters.trim());
        }
        return arguments;
    }

    public static void handleInput(ArrayList<String> arguments) throws DukeCommandException {
        switch (arguments.get(0)) {
        case "help":
            Messages.helpMessage();
            break;

        case "delete":
            Command.executeDelete(arguments);
            DataFile.write();
            break;

        case "done":
            Command.executeDone(arguments);
            DataFile.write();
            break;

        case "list":
            Command.executeList();
            break;

        case "todo":
        case "deadline":
        case "event":
            Command.executeAdd(arguments);
            DataFile.write();
            break;
        default:
            throw new DukeCommandException();
        }
    }
}
