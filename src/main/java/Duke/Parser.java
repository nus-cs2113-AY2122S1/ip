package Duke;

import Duke.Commands.*;

public class Parser {

    /**
     * Interprets the input given by the user and returns a command type based on the first word from the input.
     * If the first word is not a recognised command, an invalid command message would be thrown.
     *
     * @param input The entire line of command entered by the user.
     * @return Returns a particular command type depending on the first word of the input.
     * @throws DukeException If the first word of the input is not a recognised command.
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        final String COMMAND = splittedInput[0];

        switch (COMMAND.toLowerCase()) {
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(input);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(input);
        case EventCommand.COMMAND_WORD:
            return new EventCommand(input);
        case ListCommand.COMMAND_WORD:
            return new ListCommand(input);
        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(input);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(input);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(input);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand(input);
        default:
            return new InvalidCommand();
        }
    }
}
