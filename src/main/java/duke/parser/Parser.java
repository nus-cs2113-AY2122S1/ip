package duke.parser;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.exception.DukeException;
import duke.command.*;

public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    public static String getCommandType(String command) throws DukeException{
        if (command.contains("done")) {
            return "DONE";
        } else if (command.contains("delete")) {
            return "DELETE";
        } else if (command.contains("bye")) {
            return "BYE";
        } else if (command.contains("list")) {
            return "LIST";
        } else if (command.contains("todo")) {
            return "T";
        } else if (command.contains("deadline")) {
            return "D";
        } else if (command.contains("event")) {
            return "E";
        } else {
            throw new DukeException();
        }
    }

    public static Command parse(String command) throws DukeException {
        try {
            String commandType = getCommandType(command);
            switch (commandType) {
            case "DELETE":
                return new DeleteCommand(command);
            case "DONE":
                return new DoneCommand(command);
            case "BYE":
                return new ByeCommand();
            case "LIST":
                return new ListCommand();
            default:
                return new AddCommand(command);
            }
        } catch (DukeException e) {
            throw new DukeException();
        }
    }
}
