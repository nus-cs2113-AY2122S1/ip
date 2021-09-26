package duke.processes.utility;

import duke.processes.commands.*;

public class Parser {

    /**
     * parses through the user input to make sense of the different command
     * and inform the main class what command the user has inputted
     *
     * @param response the direct string message of the users response
     * @return returns the Command inputted by the user of type Command
     */
    public static Command parseCommand(String response) {
        String[] command = response.split(" ", 10);
        switch (command[0]) {
        case "list":
            return new ListCommand(command);
        case "done":
        case "undo":
            return new DoneUndoCommand(command);
        case "remove":
            return new RemoveCommand(command);
        case "add":
            return new AddCommand(command, response);
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "find":
            return new FindCommand(command, response);
        case "update":
            return new UpdateCommand(command);
        default:
            System.out.println("Im sorry i did not catch that maybe these instructions below will help"
                    + System.lineSeparator() + Interface.lineBreak);
            return new HelpCommand();
        }
    }
}
