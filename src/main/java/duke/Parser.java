package duke;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.exceptions.InvalidCommandException;

/**
 * Class for the purpose of collecting and interpreting input from the user.
 */
public class Parser {

    /**
     * Reads input from user and returns the appropriate command class for execution of command.
     * @param fullCommand input string from user
     * @return Command called by user
     * @throws InvalidCommandException If command by user is not recognised.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        if (fullCommand.trim().equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.trim().equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("done")) {
            return new DoneCommand(fullCommand);
        } else if (fullCommand.startsWith("todo")) {
            return new TodoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return new DeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return new EventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else {
            throw new InvalidCommandException();
        }
    }
}
