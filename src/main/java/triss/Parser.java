package triss;

import triss.command.*;

public class Parser {

    public Parser() {
        // Parser has no dependencies to be initialised
    }

    /**
     * Parse the user input and return the word in the index the user wants.
     * @param userInput The user input to be parsed.
     * @param i The index of the word in the user input to be returned.
     * @return Parsed string from user input.
     */
    public String parseUserInput(String userInput, int i) {
        return userInput.split(" ")[i];
    }

    public String getDeadlineOrTiming(String userInput) {
        return userInput.substring(userInput.indexOf("/") + 1).trim();
    }

    public String getTaskName(String userInput, int lengthOfCommandWord) {
        return userInput.substring(lengthOfCommandWord, userInput.indexOf("/")).trim();
    }

    /**
     * Returns appropriate Command Type based on user's command.
     * @param userInput User's input.
     * @return Command that can execute user's input.
     */
    public Command parseUserCommand(String userInput) {
        String userCommand = parseUserInput(userInput, 0);

        // Perform actions based on user's command
        switch (userCommand) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand();
        case "delete":
            return new DeleteCommand();
        case "deadline":
            return new AddDeadlineCommand();
        case "event":
            return new AddEventCommand();
        case "todo":
            return new AddTodoCommand();
        default:
            return new InvalidCommand();
        }
    }
}
