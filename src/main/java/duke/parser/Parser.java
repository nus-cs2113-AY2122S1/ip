package duke.parser;

import duke.exception.DukeException;
import duke.command.*;

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
        int taskIndex;
        switch(command) {
        case "bye":
            c = new ExitCommand();
            break;
        case "list":
            c = new ListCommand();
            break;
        case "done":
            taskIndex = Integer.parseInt(inputWords[1]) - 1;
            c = new TaskDoneCommand(taskIndex);
            break;
        case "find":
            String keyword = inputWords[1];
            c = new FindCommand(keyword);
            break;
        case "delete":
            taskIndex = Integer.parseInt(inputWords[1]) - 1;
            c = new DeleteCommand(taskIndex);
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
