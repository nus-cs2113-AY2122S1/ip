package duke;

import Command.AddCommand;
import Command.Command;
import Command.DeleteCommand;
import Command.DoneCommand;
import Command.ExitCommand;
import Command.ListCommand;

public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";

    public static Command parse(String fullCommand) {
        if (fullCommand.equals(COMMAND_BYE)) {
            return new ExitCommand(fullCommand);
        }

        if (fullCommand.equals(COMMAND_LIST)) {
            return new ListCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_DONE)) {
            return new DoneCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_DELETE)) {
            return new DeleteCommand(fullCommand);
        }

        return new AddCommand(fullCommand);
    }
}
