package Parser;

import commands.*;
import tasks.TaskList;
import exceptions.InvalidCommandException;
import exceptions.DukeException;
import exceptions.TaskIndexMissingException;



/**
 * A class to interpret user commands.
 */
public class Parser {

    /**
     * Interprets user input, and gets what type of command it is.
     *
     * @param command The user input
     * @param userTasks The TaskList object holds all tasks of this user.
     *
     * @return The command type
     * @throws DukeException Throws exception if the command is not exactly following the format.
     */
    public UserCommand parseCommand(String command, TaskList userTasks) throws DukeException {
        String[] inputSplits = command.split(" ");
        UserCommand input;

        switch (inputSplits[0]) {
        case "bye":
            input = new QuitCommand();
            break;

        case "list":
            input = new ListCommand(userTasks);
            break;

        case "done": case "delete":
            try {
                if (inputSplits[0].equals("done")) {
                    input = new DoneCommand(Integer.parseInt(inputSplits[1]), userTasks);
                } else {
                    input = new DeleteCommand(Integer.parseInt(inputSplits[1]), userTasks);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TaskIndexMissingException();
            }
            break;

        case "todo" : case "deadline" : case "event":
            input = new AddTaskCommand(command, userTasks);
            break;

        case "find":
            input = new FindTaskCommand(userTasks, inputSplits[1]);
            break;

        default:
            throw new InvalidCommandException();
        }
        return input;
    }
}
