package commands;

/**
 * Contains methods to process a complete line of user-input commands
 */
public class Parser {
    /**
     * Based on category of command, create corresponding command object
     * @param input Complete line of user-input command, including both
     *              command keywords and additional arguments (if applicable)
     * @return Command object of the relevant type
     */
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
        case DONE:
            preparedCmd = createDone(input);
            break;
        case DELETE:
            preparedCmd = createDelete(input);
            break;
        case FIND:
            preparedCmd = createFind(input);
            break;
        case TODAY:
            preparedCmd = createToday();
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

    private static Command createDone(String input) {
        return new DoneCommand(input);
    }

    private static Command createDelete(String input) {
        return new DeleteCommand(input);
    }

    private static Command createFind(String input) {
        return new FindCommand(input);
    }

    private static Command createToday() {
        return new TodayCommand();
    }

    /**
     * Compares the input entered by users and checks for the
     * valid presence of command keywords of each supported command,
     * returning the type of command detected
     * @param input Complete line of user input
     * @return CommandType Detected type of command
     */
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
        } else if (input.startsWith(DoneCommand.COMMAND_WORD)) {
            type = CommandType.DONE;
        } else if (input.startsWith(DeleteCommand.COMMAND_WORD)) {
            type = CommandType.DELETE;
        } else if (input.startsWith(FindCommand.COMMAND_WORD)) {
            type = CommandType.FIND;
        } else if (input.equals(TodayCommand.COMMAND_WORD)) {
            type = CommandType.TODAY;
        } else {
            type = CommandType.UNKNOWN;
        }
        return type;
    }
}
