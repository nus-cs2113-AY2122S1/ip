package duke.processes;

import duke.processes.commands.*;

public class Parser {

    public static Command parseCommand(String response) {
        String [] command = response.split(" ", 10);
        switch (command[0]) {
        case "list":
            return new ListCommand();
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
        default:
            System.out.println("Im sorry i did not catch that maybe these instructions below will help"
                    + System.lineSeparator() + Interface.lineBreak);
            return new HelpCommand();
        }
    }
}
