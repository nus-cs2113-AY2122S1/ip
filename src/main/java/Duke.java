import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

import java.util.ArrayList;

public class Duke {
    public static Ui ui;
    private static Storage storage;
    /**
     * Error Messages
     */
    private static final String INVALID_TASK_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "That is invalid... Please use the syntax - "
            + "[taskType]" + Ui.SPACE_PREFIX + "[taskName] ([/by dateTime] or [/at dateTime] depending on taskType)"
            + Ui.LINE_BREAK + Ui.CONSOLE_LINE_PREFIX;
    private static final String UNKNOWN_COMMAND_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "Hey, I don't quite understand this command. Please install a new CPU for me ;D"
            + Ui.LINE_BREAK + Ui.SPACE_PREFIX + "Just kidding, it's too expensive, just try again..." + Ui.LINE_BREAK
            + Ui.CONSOLE_LINE_PREFIX;
    private static final String MISSING_INDEX_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "Excuse me Sir/Madam, which task number? Where is it? Under the Sea?" + Ui.LINE_BREAK
            + Ui.CONSOLE_LINE_PREFIX;
    private static final String NO_TASK_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "Woah woah, you can't just mark something when your list of tasks is empty"
            + Ui.LINE_BREAK + Ui.CONSOLE_LINE_PREFIX;
    private static final String TODO_EMPTY_MESSAGE = Ui.CONSOLE_LINE_PREFIX + Ui.LINE_BREAK
            + Ui.SPACE_PREFIX + "Excuse you? The description for todo can NEVER be empty!" + Ui.LINE_BREAK
            + Ui.CONSOLE_LINE_PREFIX;

    // Command Prefixes for checking type of command
    private static final String COMMAND_BYE = "Bye";
    private static final String COMMAND_DONE = "Done";
    private static final String COMMAND_LIST = "List";

    // Prefixes meant for processing data parameters for Task
    private static final String BY_WHEN_PREFIX = "/by";
    private static final String AT_WHEN_PREFIX = "/at";
    private static final String TASK_TODO_PREFIX = "Todo";
    private static final String TASK_DEADLINE_PREFIX = "Deadline";
    private static final String TASK_EVENT_PREFIX = "Event";
    private static final String COMMAND_DELETE = "Delete";

    /**
     * These variables are responsible for the management of Tasks
     */
    private static ArrayList<Task> tasks;

    /**
     * Initializes the list of Tasks and Task Counter
     */
    private static void initTasks() {
        ui = new Ui();
        storage = new Storage();
        tasks = new ArrayList<>();
        storage.instantiateTasksFromFile(tasks);
    }

    /**
     * Creates a TodoObject from task name given by the user
     * and returns it to be added to Tasks
     *
     * @param taskName name of the Todo_Task to be created
     * @return TodoObject
     */
    private static Todo createNewToDo(String taskName) throws DukeException {
        if (taskName.equals("")) {
            throw new DukeException(TODO_EMPTY_MESSAGE);
        }
        return new Todo(taskName);
    }

    /**
     * Processes the unprocessed task name given by user, to get the actual task name
     * and the date to be completed ("byWhen").
     * Lastly, creates a Deadline Object before returning it to be added to Tasks
     *
     * @param unprocessedTaskName task name given by user before removing non-taskName relevant info
     * @return DeadlineObject
     */
    private static Deadline createNewDeadline(String unprocessedTaskName) throws DukeException {
        if (!unprocessedTaskName.contains(BY_WHEN_PREFIX)) {
            throw new DukeException(INVALID_TASK_MESSAGE);
        }
        try {
            String byWhen = unprocessedTaskName.split(BY_WHEN_PREFIX)[1].trim();
            String actualTaskName = unprocessedTaskName.replace(BY_WHEN_PREFIX, "").replace(byWhen, "");
            actualTaskName = actualTaskName.trim();
            return new Deadline(actualTaskName, byWhen);
        } catch (ArrayIndexOutOfBoundsException arrError) {
            throw new DukeException("TOBEREPLACED WITH DEADLINE_EMPTY_MESSAGE");
        }
    }

    /**
     * Processes the unprocessed task name given by user, to get the actual task name
     * and the date of the event ("atWhen").
     * Lastly, creates an Event Object before returning it to be added to Tasks
     *
     * @param unprocessedTaskName task name given by user before removing non-taskName relevant info
     * @return EventObject
     */
    private static Event createNewEvent(String unprocessedTaskName) throws DukeException {
        if (!unprocessedTaskName.contains(AT_WHEN_PREFIX)) {
            throw new DukeException(INVALID_TASK_MESSAGE);
        }
        try {
            String atWhen = unprocessedTaskName.split(AT_WHEN_PREFIX)[1].trim();
            String actualTaskName = unprocessedTaskName.replace(AT_WHEN_PREFIX, "").replace(atWhen, "");
            actualTaskName = actualTaskName.trim();
            return new Event(actualTaskName, atWhen);
        } catch (ArrayIndexOutOfBoundsException arrError) {
            throw new DukeException("TOBEREPLACED WITH EVENT_EMPTY_MESSAGE");
        }
    }

    /**
     * Adds a new Task to list of Tasks.
     *
     * @param userInput The type of task and relevant details
     */
    private static void addToTasks(String userInput) {
        String taskType = userInput.split(Ui.SPACE_PREFIX)[0];
        // Remove the Type of Task from the user input
        String taskName = userInput.replace(taskType, "").trim();
        try {
            Task newTask;
            if (taskType.equalsIgnoreCase(TASK_TODO_PREFIX)) {
                newTask = createNewToDo(taskName);
            } else if (taskType.equalsIgnoreCase(TASK_DEADLINE_PREFIX)) {
                newTask = createNewDeadline(taskName);
            } else if (taskType.equalsIgnoreCase(TASK_EVENT_PREFIX)) {
                newTask = createNewEvent(taskName);
            } else {
                System.out.println(UNKNOWN_COMMAND_MESSAGE);
                return;
            }
            tasks.add(newTask);
            storage.saveTasksToFile(tasks);
            ui.printAddedTaskMessage(newTask.getTaskName());
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Mark the task as done and print out marked as done message.
     *
     * @param index task index of task that user wants to mark as done in the list
     */
    private static void markTaskAsDone(int index) throws DukeException{
        try {
            if (tasks.size() == 0) {
                throw new DukeException(NO_TASK_MESSAGE);
            } else if (tasks.size() <= index) {
                throw new DukeException("OVERFLOWED INDEX PLEASE REPLACE ME");
            }
            Task task = tasks.get(index);
            task.setDone();
            ui.printMarkedTaskDoneMessage(task);
            storage.saveTasksToFile(tasks);
        } catch (NullPointerException e) {
            throw new DukeException("TOBECHANGED");
        }
    }

    private static void deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        // Print deleted message
        ui.printDeletedTaskMessage(deletedTask, tasks.size());
        storage.saveTasksToFile(tasks);
    }

    private static void parseTaskCommands(String userInput) {
        try {
            String[] userParams = userInput.split(Ui.SPACE_PREFIX);
            if (userParams[0].equalsIgnoreCase(COMMAND_DONE)) {
                int index = Integer.parseInt(userParams[1]);
                markTaskAsDone(index - 1);
            } else if (userParams[0].equalsIgnoreCase(COMMAND_DELETE)) {
                int index = Integer.parseInt(userParams[1]);
                deleteTask(index - 1);
            } else {
                addToTasks(userInput);
            }
        } catch (ArrayIndexOutOfBoundsException arrError) {
            System.out.println(MISSING_INDEX_MESSAGE);
        } catch (DukeException dukeError) {
            ui.printErrorMessage(dukeError.getMessage());
        }
    }

    public static void main(String[] args) {
        initTasks();
        ui.printLogo();
        ui.printGreeting();
        String userInput;
        while (true) {
            userInput = ui.getUserInput();
            if (userInput.equalsIgnoreCase(COMMAND_BYE)) {
                break;
            }
            if (userInput.equalsIgnoreCase(COMMAND_LIST) || userInput.equals("")) {
                ui.printTasks(tasks);
            } else {
                parseTaskCommands(userInput);
            }
        }
        ui.printFarewell();
    }
}