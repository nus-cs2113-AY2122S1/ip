package Parser;

import Commands.*;
import Exceptions.InsufficientParametersException;
import Exceptions.UnknownCommandException;

/**
 * Interprets user input into commands
 */
public class Parser {

    public Parser() {
    }

    /**
     * Processes the user input into the command type and the parameters involved. Returns the command to be executed
     *
     * @param userInput the input string of the user
     * @return the command to be executed
     * @throws InsufficientParametersException when required parameters are not detected
     * @throws UnknownCommandException         when the input string is not a supported command
     */
    public Command parse(String userInput) throws InsufficientParametersException, UnknownCommandException {
        String[] input = userInput.split(" ", 2);
        String commandName = input[0];
        switch (commandName) {
        case "exit":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(input[1]));
        case "todo":
            if (input.length < 2) {
                throw new InsufficientParametersException();
            }
            return new TodoCommand(input[1]);
        case "deadline":
            if (!input[1].contains("/by")) {
                throw new InsufficientParametersException();
            }
            return new DeadlineCommand(input[1]);
        case "event":
            if (!input[1].contains("/at")) {
                throw new InsufficientParametersException();
            }
            return new EventCommand(input[1]);
        case "help":
            return new HelpCommand();
        case "delete":
            return new DeleteCommand(Integer.parseInt(input[1]));
        case "find":
            return new FindCommand(input[1]);
        default:
            throw new UnknownCommandException();
        }
    }
}
