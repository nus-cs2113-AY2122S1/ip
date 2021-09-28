package DukeClasses;

import Commands.*;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Exceptions.UnknownCommandException;

public class Parser {

    public static Command parse(String line) throws UnknownCommandException {
        boolean isTodoTask = line.startsWith("todo");
        boolean isDeadlineTask = line.startsWith("deadline");
        boolean isEventTask = line.startsWith("event");
        boolean isProperTask = isTodoTask || isDeadlineTask || isEventTask;
        Command command;
        if (line.equals("bye")) {
            command = new ExitCommand();
        } else if (line.equals("list")) {
            command = new ListCommand();
        } else if (line.startsWith("done ")) {
            command = new DoneCommand(Integer.parseInt(line.substring(5)) - 1);
        } else if (isProperTask) {
            if (isTodoTask) {
                command = new TodoCommand(line);
            } else if (isDeadlineTask) {
                command = new DeadlineCommand(line);
            } else {
                command = new EventCommand(line);
            }
        } else if (line.equals("help")) {
            command = new HelpCommand();
        } else if (line.startsWith("delete ")) {
            command = new DeleteCommand(Integer.parseInt(line.substring(7)) - 1);
        } else {
            throw new UnknownCommandException();
        }
        return command;
    }
}
