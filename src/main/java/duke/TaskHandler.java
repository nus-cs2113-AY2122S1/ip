package duke;

import java.util.ArrayList;

public class TaskHandler {
    /* Success messages */
    private final String ADD_TASK_SUCCESS_MESSAGE = "As you command. Added: ";
    private final String DO_TASK_SUCCESS_MESSAGE = "Transcendent, my liege. You have completed: ";
    private final String DELETED_TASK_MESSAGE = "I have removed this unworthy task from your magnificent sight, my liege:";

    /* Error messages */
    private final String INPUT_OUT_OF_RANGE_ERROR_MESSAGE = "Have mercy, for that is beyond my knowledge.";
    private final String NONZERO_INPUT_IS_ZERO_ERROR_MESSAGE = "There are none, my liege.";

    /* Attributes */
    private Parser parser;
    private Storage storage;
    protected ArrayList<Task> tasks;

    public TaskHandler(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
        this.parser = new Parser();
    }


    public String handleTasks(String line) throws IllegalArgumentException, DukeException {
        //what I want is a switch-case thing here
        Command command = parser.returnCommand(line);
        String result = "";

        switch (command) {
        case LIST:
            result = listTasks();
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
            result = "Invalid command";
        }


        return result;
    }

    private String clearTasks() {
        this.tasks.clear();
        return storage.clearFileData();
    }

    public String addTodo(String line) throws IllegalArgumentException {
        String description = parser.parseTodo(line);

        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        storage.addTaskToFileData(newTodo.taskString());
        return ADD_TASK_SUCCESS_MESSAGE + newTodo.toString();
    }

    public String addDeadline(String line) throws IllegalArgumentException {
        String[] taskComponents = parser.parseDeadline(line);
        String description = taskComponents[0];
        String by = taskComponents[1];

        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        storage.addTaskToFileData(newDeadline.taskString());
        return ADD_TASK_SUCCESS_MESSAGE + newDeadline.toString();
    }

    public String addEvent(String line) throws IllegalArgumentException {
        String[] taskComponents = parser.parseEvent(line);
        String description = taskComponents[0];
        String at = taskComponents[1];

        Event newEvent = new Event(description, at);
        tasks.add(newEvent);
        storage.addTaskToFileData(newEvent.taskString());
        return ADD_TASK_SUCCESS_MESSAGE + newEvent.toString();
    }

    public String doTask(String line) {
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

    public String deleteTask(String line) {
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

    public String listTasks() {
        String out = "Your magnificent tasks:";
        for (int i = 1; i <= tasks.size(); i++) {
            out += System.lineSeparator() + i + "." + tasks.get(i - 1).toString();
        }
        return out;
    }

}
