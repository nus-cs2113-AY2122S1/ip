package duke;

import java.util.ArrayList;

public class TaskHandler {
    /** Success messages */
    private final String ADD_TASK_SUCCESS_MESSAGE = "As you command. Added: ";
    private final String DO_TASK_SUCCESS_MESSAGE = "Transcendent, my liege. You have completed: ";
    private final String DELETED_TASK_MESSAGE = "I have removed this unworthy task from your magnificent sight, my liege:";
    private final String CLEAR_SUCCESS_MESSAGE = "A clean slate, my liege!";

    /** Error messages */
    private final String INPUT_OUT_OF_RANGE_ERROR_MESSAGE = "Have mercy, for that is beyond my knowledge.";
    private final String NONZERO_INPUT_IS_ZERO_ERROR_MESSAGE = "There are none, my liege.";

    /** Attributes */
    private Parser parser;
    private Storage storage;
    protected ArrayList<Task> tasks;

    public TaskHandler(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
        this.parser = new Parser();
    }

    /**
     * Handles the commands relating to Tasks and returns a String of the
     * output to be formatted and printed on the standard output.
     * @param line the line containing the command
     * @return the output to be formatted and printed
     * @throws IllegalArgumentException if the parameters are missing or invalid
     * @throws InvalidCommandException if the command is not recognised
     */
    public String handleTasks(String line) throws IllegalArgumentException, InvalidCommandException {
        Command command = parser.returnCommand(line);
        String result = "";

        switch (command) {
        case LIST:
            result = listTasks();
            break;
        case FIND:
            result = find(line);
            break;
        case CLEAR:
            result = clearTasks();
            break;
        case ADD_TODO:
            result = addTodo(line);
            break;
        case ADD_DEADLINE:
            result = addDeadline(line);
            break;
        case ADD_EVENT:
            result = addEvent(line);
            break;
        case DO_TASK:
            result = doTask(line);
            break;
        case DELETE:
            result = deleteTask(line);
            break;
        default:
            throw new InvalidCommandException(parser.COMMAND_UNKNOWN_ERROR_MESSAGE);
        }

        return result;
    }

    /**
     * Deletes all Tasks.
     * @return a successful clear message
     */
    private String clearTasks() {
        this.tasks.clear();
        storage.clearFileData();
        return CLEAR_SUCCESS_MESSAGE;
    }

    /**
     * Adds the input line as a Todo.
     * @param line the command to be converted into a Todo and added
     * @return a successful add message
     * @throws IllegalArgumentException if there is no description
     */
    public String addTodo(String line) throws IllegalArgumentException {
        String description = parser.parseTodo(line);

        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        storage.addTaskToFileData(newTodo);
        return ADD_TASK_SUCCESS_MESSAGE + newTodo.toString();
    }

    /**
     * Adds the input line as a Deadline.
     * @param line the command to be converted into a Deadline and added
     * @return a successful add message
     * @throws IllegalArgumentException if there is no description or by
     */
    public String addDeadline(String line) throws IllegalArgumentException {
        String[] taskComponents = parser.parseDeadline(line);
        String description = taskComponents[0];
        String by = taskComponents[1];

        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        storage.addTaskToFileData(newDeadline);
        return ADD_TASK_SUCCESS_MESSAGE + newDeadline.toString();
    }

    /**
     * Adds the input line as an Event.
     * @param line the command to be converted into an Event and added
     * @return a successful add message
     * @throws IllegalArgumentException if there is no description or at
     */
    public String addEvent(String line) throws IllegalArgumentException {
        String[] taskComponents = parser.parseEvent(line);
        String description = taskComponents[0];
        String at = taskComponents[1];

        Event newEvent = new Event(description, at);
        tasks.add(newEvent);
        storage.addTaskToFileData(newEvent);
        return ADD_TASK_SUCCESS_MESSAGE + newEvent.toString();
    }

    /**
     * Does the Task whose ID is specified in the given line.
     * @param line the command containing the ID
     * @return a successful do task message
     * @throws IllegalArgumentException if the ID is missing or invalid
     */
    public String doTask(String line) throws IllegalArgumentException {
        if (tasks.size() <= 0) {
            throw new IllegalArgumentException(NONZERO_INPUT_IS_ZERO_ERROR_MESSAGE);
        }
        int inputNum = parser.parseDoTask(line);
        if (!(inputNum > 0 && inputNum <= tasks.size())) {
            throw new IllegalArgumentException(INPUT_OUT_OF_RANGE_ERROR_MESSAGE);
        }
        int id = inputNum - 1;
        tasks.get(id).setDone();
        storage.setDone(id);
        return DO_TASK_SUCCESS_MESSAGE + System.lineSeparator()
                + Formatter.returnOutputStart() + tasks.get(id).toString();
    }

    /**
     * Deletes the Task whose ID is specified in the given line.
     * @param line the command containing the ID
     * @return a successful delete task message
     * @throws IllegalArgumentException if the ID is missing or invalid
     */
    public String deleteTask(String line) throws IllegalArgumentException {
        if (tasks.size() <= 0) {
            throw new IllegalArgumentException(NONZERO_INPUT_IS_ZERO_ERROR_MESSAGE);
        }
        int inputNum = parser.parseDeleteTask(line);
        if (!(inputNum > 0 && inputNum <= tasks.size())) {
            throw new IllegalArgumentException(INPUT_OUT_OF_RANGE_ERROR_MESSAGE);
        }
        int id = inputNum - 1;
        Task deletedTask = tasks.remove(id);
        storage.deleteLineFromFileData(id);
        return DELETED_TASK_MESSAGE + System.lineSeparator()
                + Formatter.returnOutputStart() + deletedTask.toString();
    }

    /**
     * Lists all the Tasks.
     * @return a String containing all the Tasks
     */
    public String listTasks() {
        String out = "Your magnificent tasks:";
        for (int i = 1; i <= tasks.size(); i++) {
            out += System.lineSeparator() + i + "." + tasks.get(i - 1).toString();
        }
        return out;
    }

    /**
     * Finds the Tasks which match the given search term.
     * @param line the command containing the search term
     * @return a String containing all the found Tasks
     * @throws IllegalArgumentException if the ID is missing or invalid
     */
    public String find(String line) throws IllegalArgumentException {
        String search = parser.parseFind(line);
        String out = "Here are the marvellously matching tasks in your list:";
        for (int i = 1; i <= tasks.size(); i++) {
            Task currentTask = tasks.get(i - 1);
            if (currentTask.contains(search)) {
                out += System.lineSeparator() + i + "." + currentTask.toString();
            }
        }
        return out;
    }
}
