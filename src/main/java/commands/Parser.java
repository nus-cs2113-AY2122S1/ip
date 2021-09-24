package commands;

public class Parser {
    public static Command parseCommand(String input) {
        Command preparedCommand;
        switch(categoriseCommand(input)) {
        case BYE:
            preparedCommand = createBye();
            break;
        case TODO:
            preparedCommand = createToDo(input);
            break;
        default:
            preparedCommand = createUnknown();
        }
        return preparedCommand;
    }

    private static Command createBye() {
        return new ByeCommand();
    }

    private static Command createUnknown() {
        return new UnknownCommand();
    }

    private static ToDoCommand createToDo(String input) {
        return new ToDoCommand(input);
    }

    private static CommandType categoriseCommand(String input) {
        CommandType type;
        if (input.equals(ByeCommand.COMMAND_WORD)) {
            type = CommandType.BYE;
        } else if (input.equals("/list")) {
            type = CommandType.LIST;
        } else if (input.startsWith(ToDoCommand.COMMAND_WORD)) {
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
