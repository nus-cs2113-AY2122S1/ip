package Duke;

import Commands.*;
import Exceptions.InsufficientParametersException;
import Exceptions.UnknownCommandException;

public class Parser {

    public Parser() {
    }

    public Command parse(String userInput) throws InsufficientParametersException, UnknownCommandException {
        String[] input = userInput.trim().split(" ", 2);
        String commandName = input[0];
        String commandParameters = input[1].trim();
        switch (commandName) {
        case "exit":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(commandParameters));
        case "todo":
            return new TodoCommand(commandParameters);
        case "deadline":
            if (!input[1].contains("/by")) {
                throw new InsufficientParametersException();
            }
            return new DeadlineCommand(commandParameters);
        case "event":
            if (!input[1].contains("/at")) {
                throw new InsufficientParametersException();
            }
            return new EventCommand(commandParameters);
        case "help":
            return new HelpCommand();
        case "delete":
            return new DeleteCommand(Integer.parseInt(commandParameters));
        default:
            throw new UnknownCommandException();
        }
    }
}
