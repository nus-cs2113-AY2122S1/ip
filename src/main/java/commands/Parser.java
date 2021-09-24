package commands;

public class Parser {
    public static Command parseCommand(String input) {
        Command preparedCommand;
        switch(categoriseCommand(input)) {
        case BYE:
            preparedCommand = prepareBye();
            break;
        default:
            preparedCommand = prepareUnknown();
        }
        return preparedCommand;
    }

    private static Command prepareBye() {
        return new ByeCommand();
    }

    private static Command prepareUnknown() {
        return new UnknownCommand();
    }

    private static CommandType categoriseCommand(String input) {
        CommandType type;
        if (input.startsWith(ByeCommand.COMMAND_WORD)) {
            type = CommandType.BYE;
        } else if (input.equals("/list")) {
            type = CommandType.LIST;
        } else if (input.startsWith("/todo")) {
            type = CommandType.TODO;
        } else if (input.startsWith("/deadline")) {
            type = CommandType.DEADLINE;
        } else if (input.startsWith("/event")) {
            type = CommandType.EVENT;
        } else if (input.startsWith("/done")) {
            type = CommandType.DONE;
        } else if (input.startsWith("/delete")) {
            type = CommandType.DELETE;
        } else {
            type = CommandType.UNKNOWN;
        }
        return type;
    }
}
