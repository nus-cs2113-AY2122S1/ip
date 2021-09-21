package Duke;

import Duke.Commands.*;

public class Parser {

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
