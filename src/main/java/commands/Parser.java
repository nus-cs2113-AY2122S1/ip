package commands;

public class Parser {
    public static Command parseCommand(String input) {
        Command preparedCmd;
        switch(categoriseCommand(input)) {
        case BYE:
            preparedCmd = createBye();
            break;
        case LIST:
            preparedCmd = createList();
            break;
        case TODO:
            preparedCmd = createToDo(input);
            break;
        case DEADLINE:
            preparedCmd = createDeadline(input);
            break;
        case EVENT:
            preparedCmd = createEvent(input);
            break;
        default:
            preparedCmd = createUnknown();
        }
        return preparedCmd;
    }

    private static Command createBye() {
        return new ByeCommand();
    }

    private static Command createList() {
        return new ListCommand();
    }

    private static Command createUnknown() {
        return new UnknownCommand();
    }

    private static Command createToDo(String input) {
        return new ToDoCommand(input);
    }

    private static Command createDeadline(String input) {
        return new DeadlineCommand(input);
    }

    private static Command createEvent(String input) {
        return new EventCommand(input);
    }

    private static CommandType categoriseCommand(String input) {
        CommandType type;
        if (input.equals(ByeCommand.COMMAND_WORD)) {
            type = CommandType.BYE;
        } else if (input.equals(ListCommand.COMMAND_WORD)) {
            type = CommandType.LIST;
        } else if (input.startsWith(ToDoCommand.COMMAND_WORD)) {
            type = CommandType.TODO;
        } else if (input.startsWith(DeadlineCommand.COMMAND_WORD)) {
            type = CommandType.DEADLINE;
        } else if (input.startsWith(EventCommand.COMMAND_WORD)) {
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
