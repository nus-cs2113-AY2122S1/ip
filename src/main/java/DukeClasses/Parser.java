package DukeClasses;

import Commands.*;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Exceptions.UnknownCommandException;

/**
 * Parses user input
 */
public class Parser {

    /**
     * Parses the user's whole input to extract the command and relevant details
     * @param line user's input
     * @return Command object of user
     * @throws UnknownCommandException if user does not enter an existing command
     */
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
        } else if (line.startsWith("find ")) {
            command = new FindCommand(line.substring(5));
        } else {
            throw new UnknownCommandException();
        }
        return command;
    }

    /**
     * Parses the time from event and deadline commands
     */
    public static String extractTiming(String line, int indexOfSlash) {
        return line.substring(indexOfSlash + 4);
    }

    /**
     * Parses the task description from event commands
     */
    public static String extractEventTask(String line, int indexOfSlash) {
        return line.substring(5, indexOfSlash - 1).trim();
    }

    /**
     * Parses the task description from deadline commands
     */
    public static String extractDeadlineTask(String line, int indexOfSlash) {
        return line.substring(8, indexOfSlash - 1).trim();
    }

    /**
     * Parses the task description from todo commands
     */
    public static String extractTodoTask(String line) {
        return line.substring(4).trim();
    }
}
