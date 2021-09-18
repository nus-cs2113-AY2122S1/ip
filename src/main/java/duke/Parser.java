package duke;

import duke.command.*;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.exceptions.DukeException;

public class Parser {

    protected Ui ui;
    protected Storage storage;
    protected TaskList tasks;
    Task newTask;

    public static final String TODO = "todo";
    public static final String TODO_EXCEPTION = "Description of todo cannot be empty.";
    public static final String DEADLINE = "deadline";
    public static final String DEADLINE_EXCEPTION = "Deadline task requires a /by property.";
    public static final String DEADLINE_EMPTY_DESCRIPTION = " /by property cannot be empty.";
    public static final String EVENT = "event";
    public static final String EVENT_EXCEPTION = "Event task requires an /at property.";
    public static final String EVENT_EMPTY_DESCRIPTION = " /at property cannot be empty.";
    public static final String INVALID_TASK_TYPE = "Invalid task type. Try a todo, deadline or event.";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String BYE = "bye";
    public static final String INVALID_INPUT = "Your input is invalid.";


    /**
     * Parser constructor with its associated UI, Storage and TaskList
     * @param ui UI to be used by Parser
     * @param storage Storage to be used by Parser
     * @param tasks TaskList to be used by Parser
     */
    public Parser(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Parses user's raw input and initiates a new task to be added according to its type.
     * @param input string of user's raw input
     */
    public void trimInput(String input) {

        if (input.startsWith(DEADLINE)) {
            String description =
                    input.substring(0, input.indexOf("/by")).replaceFirst(DEADLINE, "").trim();
            String by = input.substring(input.indexOf("/by") + "/by".length()).trim();
            newTask = new Deadline(description, by);

        } else if (input.startsWith(EVENT)) {
            String description =
                    input.substring(0, input.indexOf("/at")).replaceFirst(EVENT, "").trim();
            String at = input.substring(input.indexOf("/at") + "/at".length()).trim();
            newTask = new Event(description, at);

        } else if (input.startsWith(TODO)) {
            String description = input.replaceFirst(TODO, "").trim();
            newTask = new ToDo(description);
        }
    }

    /**
     * Dissects user's raw input and returns an appropriate AddCommand object.
     * @param input string of user's raw input
     * @return an appropriate AddCommand object
     * @throws DukeException
     */
    public Command addTask(String input) throws DukeException {

        if (input.startsWith(TODO)) {
            if (input.substring(4).isEmpty()) {
                throw new DukeException(TODO_EXCEPTION);
            }
            trimInput(input);

        } else if (input.startsWith(DEADLINE)) {
            if (!input.contains("/by")) {
                throw new DukeException(DEADLINE_EXCEPTION);
            } else if (input.substring(input.indexOf("/by") + "/by".length()).isEmpty()) {
                throw new DukeException(DEADLINE_EMPTY_DESCRIPTION);
            }
            trimInput(input);

        } else if (input.startsWith(EVENT)) {
            if (!input.contains("/at")) {
                throw new DukeException(EVENT_EXCEPTION);
            } else if (input.substring(input.indexOf("/at") + "/at".length()).isEmpty()) {
                throw new DukeException(EVENT_EMPTY_DESCRIPTION);
            }
            trimInput(input);

        } else {
            throw new DukeException(INVALID_TASK_TYPE);
        }

        return new AddCommand(ui, tasks, newTask);
    }

    /**
     * Dissects user's raw input and returns an appropriate SetDoneCommand
     * @param input string of user's raw input
     * @return a SetDoneCommand object
     */
    public Command markTaskDone(String input) {
        int taskNumber = Integer.parseInt(input.replace("done ", "")) - 1;

        return new SetDoneCommand(ui, tasks, taskNumber);
    }

    /**
     * Dissects user's raw input and returns an appropriate DeleteCommand
     * @param input string of user's raw input
     * @return a DeleteCommand object
     */
    public Command deleteTask(String input) {
        int taskNumber = Integer.parseInt(input.replaceFirst("delete", "").trim()) - 1;

        return new DeleteCommand(ui, tasks, taskNumber);
    }

    /**
     * Dissects user's raw input beforehand and then calls appropriate command methods.
     * @param input string of user's raw input
     * @return a Command object
     * @throws DukeException
     */
    public Command selectCommand(String input) throws DukeException {

        String trimmedInput = input.trim().split(" ")[0];
        Command commandFromInput = null;

        switch (trimmedInput){
        case TODO: case DEADLINE: case EVENT:
            commandFromInput = addTask(input);
            break;
        case LIST:
            commandFromInput = new ListCommand(ui, tasks);
            break;
        case DONE:
            commandFromInput = markTaskDone(input);
            break;
        case DELETE:
            commandFromInput = deleteTask(input);
            break;
        case BYE:
            ui.printByeMessage();
            System.exit(0);
            break;
        default:
            throw new DukeException(INVALID_INPUT);
        }
        return commandFromInput;
    }
}