package duke.parser;

import duke.command.*;
import duke.exception.InvalidCommandException;

public class Parser {

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
        } else {
            throw new InvalidCommandException();
        }
        return c;
    }
}
