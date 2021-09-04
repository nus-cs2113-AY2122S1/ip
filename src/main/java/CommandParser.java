public class CommandParser {

    private static final int COMMAND_KEYWORD = 0;

    private static final String LS = System.lineSeparator();
    private static final String S_TAB = "     ";
    private static final String NL = LS + S_TAB;

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_HELP = "help";

    private static final String FORMAT_DONE = "Command format: 'done <TASK_NUMBER>'";
    private static final String FORMAT_TODO = "Command format: 'todo <TASK_NAME>'";
    private static final String FORMAT_DEADLINE = "Command format: 'deadline <TASK_NAME> /by <TASK_DATE>'";
    private static final String FORMAT_EVENT = "Command format: 'event <TASK_NAME> /at <TASK_DATE>'";

    private static final String ERROR_LEADING_SPACE = "ERROR: Remove leading whitespaces in the command.";
    private static final String ERROR_DEFAULT = S_TAB + "Invalid command. Type 'help' to see a list of commands.";
    private static final String ERROR_BYE = S_TAB + "ERROR: Type 'bye' without additional parameters to exit.";
    private static final String ERROR_LIST = S_TAB + "ERROR: Type 'list' without additional parameters to print list.";
    private static final String ERROR_DONE_1 = S_TAB + "ERROR: Provide the task number of the task." + NL + FORMAT_DONE;
    private static final String ERROR_DONE_2 = S_TAB + "ERROR: Provide the task number of one task only." + NL + FORMAT_DONE;
    private static final String ERROR_TODO = S_TAB + "ERROR: Specify a todo task to be added." + NL + FORMAT_TODO;
    private static final String ERROR_DEADLINE_1 = S_TAB + "ERROR: Specify a task and due date." + NL + FORMAT_DEADLINE;
    private static final String ERROR_DEADLINE_2 = S_TAB + "ERROR: Specify due date after /by." + NL + FORMAT_DEADLINE;
    private static final String ERROR_DEADLINE_3 = S_TAB + "ERROR: Specify a due date." + NL + FORMAT_DEADLINE;
    private static final String ERROR_EVENT_1 = S_TAB + "ERROR: Specify a task and event date." + NL + FORMAT_EVENT;
    private static final String ERROR_EVENT_2 = S_TAB + "ERROR: Specify event date after /at." + NL + FORMAT_EVENT;
    private static final String ERROR_EVENT_3 = S_TAB + "ERROR: Specify an event date." + NL + FORMAT_EVENT;
    private static final String ERROR_HELP = "ERROR: Type 'help' without additional parameters to view all commands.";


    public static Command parse(String inputCommand) throws DukeException {
        checkLeadingSpace(inputCommand);
        String[] words = inputCommand.split(" ");
        switch (words[COMMAND_KEYWORD]) {
        case COMMAND_BYE:
            return parseByeCommand(inputCommand);
        case COMMAND_LIST:
            return parseListCommand(inputCommand);
        case COMMAND_DONE:
            return parseDoneCommand(inputCommand, words);
        case COMMAND_TODO:
            return parseTodoCommand(inputCommand);
        case COMMAND_DEADLINE:
            return parseDeadlineCommand(inputCommand);
        case COMMAND_EVENT:
            return parseEventCommand(inputCommand);
        case COMMAND_HELP:
            return parseHelpCommand(inputCommand);
        default:
            throw new DukeException(ERROR_DEFAULT);
        }
    }

    private static void checkLeadingSpace(String inputCommand) throws DukeException {
        if (inputCommand.charAt(0) == ' ') {
            throw new DukeException(ERROR_LEADING_SPACE);
        }
    }

    private static Command parseByeCommand(String inputCommand) throws DukeException {
        if (!inputCommand.equals(COMMAND_BYE)) {
            throw new DukeException(ERROR_BYE);
        }
        return new ExitCommand();
    }

    private static Command parseListCommand(String inputCommand) throws DukeException {
        if (!inputCommand.equals(COMMAND_LIST)) {
            throw new DukeException(ERROR_LIST);
        }
        return new ListCommand();
    }

    private static Command parseDoneCommand(String inputCommand, String[] words) throws DukeException {
        if (inputCommand.length() <= 5) {
            throw new DukeException(ERROR_DONE_1);
        }
        if (words.length > 2) {
            throw new DukeException(ERROR_DONE_2);
        }
        return new DoneCommand(Integer.parseInt(words[1]));
    }

    private static Command parseTodoCommand(String inputCommand) throws DukeException {
        if (inputCommand.length() <= 5) {
            throw new DukeException(ERROR_TODO);
        }
        String taskName = inputCommand.substring(5);
        return new AddCommand(taskName, null, TaskType.TODO);
    }

    private static Command parseDeadlineCommand(String inputCommand) throws DukeException {
        if (inputCommand.length() <= 9) {
            throw new DukeException(ERROR_DEADLINE_1);
        }
        if (!inputCommand.contains("/by")) {
            throw new DukeException(ERROR_DEADLINE_2);
        }
        int startIndexOfByIndicator = inputCommand.indexOf("/by");
        int startIndexOfDatetime = startIndexOfByIndicator + 4;
        if (inputCommand.length() <= startIndexOfDatetime) {
            throw new DukeException(ERROR_DEADLINE_3);
        }
        String taskName = inputCommand.substring(9, startIndexOfByIndicator - 1);
        String datetime = inputCommand.substring(startIndexOfDatetime);
        return new AddCommand(taskName, datetime, TaskType.DEADLINE);
    }

    private static Command parseEventCommand(String inputCommand) throws DukeException {
        if (inputCommand.length() <= 6) {
            throw new DukeException(ERROR_EVENT_1);
        }
        if (!inputCommand.contains("/at")) {
            throw new DukeException(ERROR_EVENT_2);
        }
        int startIndexOfAtIndicator = inputCommand.indexOf("/at");
        int startIndexOfDatetime = startIndexOfAtIndicator + 4;
        if (inputCommand.length() <= startIndexOfDatetime) {
            throw new DukeException(ERROR_EVENT_3);
        }
        String taskName = inputCommand.substring(6, startIndexOfAtIndicator - 1);
        String datetime = inputCommand.substring(startIndexOfDatetime);
        return new AddCommand(taskName, datetime, TaskType.EVENT);
    }

    private static Command parseHelpCommand(String inputCommand) throws DukeException {
        if (!inputCommand.equals(COMMAND_HELP)) {
            throw new DukeException(ERROR_HELP);
        }
        return new HelpCommand();
    }

}
