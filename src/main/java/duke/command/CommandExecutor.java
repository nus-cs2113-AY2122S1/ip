package duke.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.TaskList;
import duke.file.Storage;
import duke.exception.CommandException;
import duke.ui.Ui;

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
    private final static String DELETE_COMMAND = "delete";
    private final static String ADD_TODO_COMMAND = "todo";
    private final static String ADD_DEADLINE_COMMAND = "deadline";
    private final static String ADD_EVENT_COMMAND = "event";

    /* Names of supported arguments/flags */
    private final static String ARGUMENT_TASK_INDEX = "Task Index";
    private final static String ARGUMENT_TASK_DESCRIPTION = "Task Description";
    private final static String FLAG_TASK_TIMESTAMP = "Timestamp";
    private final static String FLAG_DEADLINE_OPTION = "by";
    private final static String FLAG_EVENT_OPTION = "at";

    /* Names of used files and directories */
    private final static String FILE_PATH = "duke.txt";
    private final static String DATA_PATH = "data";

    /* Used to store tasks */
    private TaskList taskManager;
    /* Used to store supported commands */
    private Command[] commandList;
    /* Used to perform parsing operations */
    private Parser parser;
    /* Used to save tasks to file system */
    private Storage fileManager;
    /* State of whether interaction has terminated. True if interaction has terminated. */
    private boolean isExit;

    /**
     * Instantiates a new handler for command executions.
     */
    public CommandExecutor() {
        isExit = false;
        fileManager = new Storage(DATA_PATH);
        commandList = new Command[] {
                new Command(END_COMMAND),
                new Command(LIST_COMMAND),
                new CommandWithArgument(DONE_COMMAND, ARGUMENT_TASK_INDEX),
                new CommandWithArgument(DELETE_COMMAND, ARGUMENT_TASK_INDEX),
                new CommandWithArgument(ADD_TODO_COMMAND, ARGUMENT_TASK_DESCRIPTION),
                new CommandWithFlag(ADD_DEADLINE_COMMAND, ARGUMENT_TASK_DESCRIPTION,
                        FLAG_DEADLINE_OPTION, FLAG_TASK_TIMESTAMP),
                new CommandWithFlag(ADD_EVENT_COMMAND, ARGUMENT_TASK_DESCRIPTION,
                        FLAG_EVENT_OPTION, FLAG_TASK_TIMESTAMP)
        };
        parser = new Parser(commandList);
        try {
            taskManager = fileManager.readTaskManagerFromFile(FILE_PATH);
        } catch (IOException | DateTimeParseException err) {
            Ui.printFileReadError();
            taskManager = new TaskList();
        }
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
        try {
            runCommandUsingInput(inputLine);
        } catch (CommandException err) {
            Ui.printError(err.getMessage());
        } catch (NumberFormatException | DateTimeParseException err) {
            Ui.printConvertError();
        } catch (IOException err) {
            Ui.printFileUpdateError();
        }
    }

    /**
     * Perform execution of the given command using the given input string. After every successful command execution,
     * state of the task manager is saved to a file.
     *
     * @param inputLine Raw input line to read from.
     * @throws CommandException If an illegal command is executed or there is an error parsing the user's command.
     * @throws IOException      If a file-related operation has errors.
     */
    private void runCommandUsingInput(String inputLine) throws CommandException, IOException {
        Command command = parser.findCommand(inputLine);
        String[] commandLineValues = parser.parseCommandLineValues(command, inputLine);
        Task task;
        int taskIndex;
        String taskDescription;
        String taskDateTime;

        switch (command.getCommand()) {
        case LIST_COMMAND:
            taskManager.printTaskList();
            break;
        case DONE_COMMAND:
            taskIndex = Integer.parseInt(commandLineValues[ARGUMENT_VALUE_INDEX]);
            taskManager.complete(taskIndex);
            break;
        case DELETE_COMMAND:
            taskIndex = Integer.parseInt(commandLineValues[ARGUMENT_VALUE_INDEX]);
            taskManager.deleteTask(taskIndex);
            break;
        case ADD_TODO_COMMAND:
            taskDescription = commandLineValues[ARGUMENT_VALUE_INDEX];
            task = new Todo(taskDescription);
            taskManager.addTask(task);
            break;
        case ADD_DEADLINE_COMMAND:
            taskDescription = commandLineValues[ARGUMENT_VALUE_INDEX];
            taskDateTime = commandLineValues[FLAG_VALUE_INDEX];
            task = new Deadline(taskDescription, taskDateTime);
            taskManager.addTask(task);
            break;
        case ADD_EVENT_COMMAND:
            taskDescription = commandLineValues[ARGUMENT_VALUE_INDEX];
            taskDateTime = commandLineValues[FLAG_VALUE_INDEX];
            task = new Event(taskDescription, taskDateTime);
            taskManager.addTask(task);
            break;
        case END_COMMAND:
            markAsExited();
            break;
        default:
            throw new CommandException("Illegal operation");
        }

        fileManager.writeTaskManagerToFile(taskManager, FILE_PATH);
    }
}

