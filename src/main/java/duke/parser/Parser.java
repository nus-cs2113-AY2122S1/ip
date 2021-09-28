package duke.parser;

import duke.command.*;
import duke.exception.InvalidCommandException;

/**
 * A class that parses each command from user input.
 */
public class Parser {

    /**
     * Returns Command object according to what user key in.
     *
     * @param userCommand the command user typed in.
     * @return Command object.
     * @throws InvalidCommandException if the userCommand is not a valid command.
     */
    public static Command parse(String userCommand) throws InvalidCommandException {
        Command c;
        if (userCommand.equals("bye")) {
            c = new Bye();
        } else if (userCommand.equals("list")) {
            c = new ListTask();
        } else if (userCommand.contains("done")) {
            c = new DoneTask(userCommand);
        } else if (userCommand.startsWith("todo")) {
            c = new AddTodoTask(userCommand);
        } else if (userCommand.startsWith("event")) {
            c = new AddEventTask(userCommand);
        } else if (userCommand.startsWith("deadline")) {
            c = new AddDeadlineTask(userCommand);
        } else if (userCommand.startsWith("delete")) {
            c = new DeleteTask(userCommand);
        } else if (userCommand.startsWith("find")) {
            c = new FindTask(userCommand);
        } else {
            throw new InvalidCommandException();
        }
        return c;
    }
}
