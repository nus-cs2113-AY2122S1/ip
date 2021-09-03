/**
 * The CommandExecutor class deals with the execution of supported commands.
 */
public class CommandExecutor {

    /* Indexes used in referencing results from command execution */
    private final static int ARGUMENT_VALUE_INDEX = 0;
    private final static int FLAG_VALUE_INDEX = 1;

    /* Supported commands */
    private final static String END_COMMAND = "bye";
    private final static String LIST_COMMAND = "list";
    private final static String DONE_COMMAND = "done";
    private final static String ADD_TODO_COMMAND = "todo";
    private final static String ADD_DEADLINE_COMMAND = "deadline";
    private final static String ADD_EVENT_COMMAND = "event";

    /* Names of supported arguments/flags */
    private final static String ARGUMENT_TASK_INDEX = "Task Index";
    private final static String ARGUMENT_TASK_DESCRIPTION = "Task Description";
    private final static String FLAG_TASK_TIMESTAMP = "Timestamp";
    private final static String FLAG_DEADLINE_OPTION = "by";
    private final static String FLAG_EVENT_OPTION = "at";

    /* Used to store tasks */
    private TaskManager taskManager;
    /* Used to store supported commands */
    private Command[] commandList;
    /* State of whether interaction has terminated. True if interaction has terminated. */
    private boolean isExit;

    /**
     * Instantiates a new handler for command executions.
     */
    public CommandExecutor() {
        isExit = false;
        taskManager = new TaskManager();
        commandList = new Command[] {
                new Command(END_COMMAND),
                new Command(LIST_COMMAND),
                new CommandWithArgument(DONE_COMMAND, ARGUMENT_TASK_INDEX),
                new CommandWithArgument(ADD_TODO_COMMAND, ARGUMENT_TASK_DESCRIPTION),
                new CommandWithFlag(ADD_DEADLINE_COMMAND, ARGUMENT_TASK_DESCRIPTION,
                        FLAG_DEADLINE_OPTION, FLAG_TASK_TIMESTAMP),
                new CommandWithFlag(ADD_EVENT_COMMAND, ARGUMENT_TASK_DESCRIPTION,
                        FLAG_EVENT_OPTION, FLAG_TASK_TIMESTAMP)
        };
    }

    public boolean isExit() {
        return isExit;
    }

    public void markAsExited() {
        isExit = true;
    }

    /**
     * Validates the given input string before executing it.
     *
     * @param inputLine Raw input line to check.
     */
    public void execute(String inputLine) {
        Command command;
        try {
            command = findCommand(inputLine);
            runCommandUsingInput(command, inputLine);
        } catch (CommandException err) {
            System.out.println("[X] " + err.getMessage());
        } catch (NumberFormatException err) {
            System.out.println("[X] Error parsing argument!");
        }
    }

    /**
     * Finds the correct command according to the given input string.
     *
     * @param inputLine Raw input line to search.
     * @return Command that user is trying to run.
     */
    private Command findCommand(String inputLine) throws CommandException {
        for (Command command : commandList) {
            if (command.isCommand(inputLine)) {
                return command;
            }
        }
        throw new CommandException("Command not found");
    }

    /**
     * Perform execution of the given command using the given input string.
     *
     * @param command   Command that user is trying to run.
     * @param inputLine Raw input line to read from.
     */
    private void runCommandUsingInput(Command command, String inputLine) throws CommandException {
        if (!command.isValidCommandLine(inputLine)) {
            throw new CommandException("Usage: " + command.getUsage());
        }

        String[] commandResults = command.parseCommand(inputLine);
        Task task;

        switch (command.getCommand()) {
        case LIST_COMMAND:
            taskManager.printTaskList();
            break;
        case DONE_COMMAND:
            taskManager.complete(Integer.parseInt(commandResults[ARGUMENT_VALUE_INDEX]));
            break;
        case ADD_TODO_COMMAND:
            task = new Todo(commandResults[ARGUMENT_VALUE_INDEX]);
            taskManager.addTask(task);
            break;
        case ADD_DEADLINE_COMMAND:
            task = new Deadline(commandResults[ARGUMENT_VALUE_INDEX], commandResults[FLAG_VALUE_INDEX]);
            taskManager.addTask(task);
            break;
        case ADD_EVENT_COMMAND:
            task = new Event(commandResults[ARGUMENT_VALUE_INDEX], commandResults[FLAG_VALUE_INDEX]);
            taskManager.addTask(task);
            break;
        case END_COMMAND:
            markAsExited();
            break;
        default:
            throw new CommandException("Illegal operation");
        }
    }
}

