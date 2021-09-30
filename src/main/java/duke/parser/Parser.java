package duke.parser;

import duke.exception.DukeException;
import duke.command.*;

/**
 * Represents the parsing of user commands
 */
public class Parser {

    /**
     * Parses the user input and returns the command to be executed
     * 
     * @param input The user input
     * @throws DukeException If the user input is not a valid command
     * @return The command to be executed
     */
    public static Command parse(String input) throws DukeException{

        String[] inputWords = input.split(" ");
        String command = inputWords[0].toLowerCase();
        Command c;
        switch(command) {
        case "bye":
            c = new ExitCommand();
            break;
        case "list":
            c = new ListCommand();
            break;
        case "done":
            c = new TaskDoneCommand(input);
            break;
        case "find":
            c = new FindCommand(input);
            break;
        case "delete":
            c = new DeleteCommand(input);
            break;
        case "todo":
            c = new AddTodoCommand(input);
            break;
        case "deadline":
            c = new AddDeadlineCommand(input);
            break;
        case "event":
            c = new AddEventCommand(input);
            break;
        default:
            throw new DukeException();
        }
        return c;
    }

}
