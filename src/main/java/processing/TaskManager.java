package processing;

import java.util.ArrayList; // import the ArrayList class
import java.util.Comparator;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

/*---------LOCAL IMPORT--------*/
import exceptions.CommandSyntaxException;
import org.jetbrains.annotations.NotNull;
import tasks.TaskType;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;
import exceptions.DukeException;

public class TaskManager {
    /*----------- PROCESSING CONSTANTS ---------- */
    public static final String DEADLINE_CLAUSE = "/by";
    public static final String EVENT_CLAUSE = "/at";
    public static final String FIND_CLAUSE = "find";

    private static final int DEADLINE_DESCRIPTION_IDX = 9;
    private static final int EVENT_DESCRIPTION_IDX = 6;


    /*----------- CONSOLE LOGGING ----------- */
    private static final String DONE_TASK = "Nice! I've marked this task as done: ";
    private static final String LIST_TASK = "Here are your scheduled tasks!";


    /*------------- PRIVATE VARIABLES ------------ */
    private final ArrayList<Task> tasks;
    private int taskSize;

    /*-------------- COMPARATOR ----------------- */

    /**
     * Comparator to sort the tasks in the task list :
     * >> Puts all TODOs ahead in the list
     * >> Sorts remaining deadlines/events by nearest
     */
    static class TaskComparator implements Comparator<Task> {
        @Override
        public int compare(@NotNull Task o1, Task o2) {
            if (o1.getTaskType() == TaskType.TODO) {
                return -1;
            } else if (o2.getTaskType() == TaskType.TODO) {
                return 1;
            }
            try {
                LocalDateTime t1Date = DateParser.parseDate(o1.getDate());
                LocalDateTime t2Date = DateParser.parseDate(o2.getDate());
                return t1Date.compareTo(t2Date);
            } catch (DukeException e) {
                return 0;
            }
        }
    }

    /*------------- CONSTRUCTOR -------------- */
    public TaskManager() {
        tasks = new ArrayList<Task>();
        taskSize = 0;
    }


    private List<Task> sortTasks() {
        return tasks
                .stream()
                .sorted(new TaskComparator())
                .collect(Collectors.toList());
    }

    /**
     * Adds a Task into a variable-size array of Tasks,
     *
     * @param command  input that has been parsed by Command Handler,
     * @param type     the type of task to be added : Todo / Event / Deadline
     * @param isDone   sets the task to be added as done or not done
     * @param isLogged if set to true, logs the addition of the task and displays it via UI
     * @throws DukeException if command parsing by clause is invalid due to empty descriptors
     * @see UI
     */
    public void addTask(CommandHandler command, @NotNull TaskType type, boolean isDone, boolean isLogged) throws DukeException {
        Task t = new Task("");
        switch (type) {
        case TODO:
            command.splitByClause("todo", 0, true);
            t = new Todo(command.descriptorAfterClause, isDone);
            break;
        case DEADLINE:
            command.splitByClause(DEADLINE_CLAUSE, DEADLINE_DESCRIPTION_IDX, false);
            t = new Deadline(command.descriptorBeforeClause,
                    DateParser.formatDate(command.descriptorAfterClause), isDone);
            break;
        case EVENT:
            command.splitByClause(EVENT_CLAUSE, EVENT_DESCRIPTION_IDX, false);
            t = new Event(command.descriptorBeforeClause,
                    DateParser.formatDate(command.descriptorAfterClause), isDone);
            break;
        }
        tasks.add(t);
        taskSize++;
        if (isLogged) {
            UI.showAddTask(t, taskSize);
        }
    }

    /**
     * Adds a Task that is not done into a variable-size array of Tasks,
     * Displays the added tasks and number of tasks currently in list
     *
     * @param command input that has been parsed by Command Handler
     * @param type    TaskType of the task to be added
     * @throws DukeException if command parsing by clause is invalid due to empty descriptors
     */
    public void addTask(CommandHandler command, TaskType type) throws DukeException {
        addTask(command, type, false, true);
    }

    /**
     * Removes a task from the task list by index, and displays the deleted command to user
     *
     * @param command input that has been parsed by Command Handler. Should be called with a
     *                valid integer
     * @throws DukeException if After Clause of command cannot be parsed as an Integer
     */
    public void deleteTask(@NotNull CommandHandler command) throws DukeException {
        command.splitByClause("delete", 0, true);
        try {
            int idx = Integer.parseInt(command.descriptorAfterClause) - 1;
            Task t = sortTasks().get(idx);
            tasks.remove(t);
            taskSize--;
            UI.showDeleteTask(t, taskSize);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            listTasks();
            throw new CommandSyntaxException("Please enter a valid task number to delete.");
        }
    }


    /**
     * Marks a task as done by its index in the tasklist
     *
     * @param command the input after it's been parsed by command handler
     * @throws DukeException if the command is not called with a valid integer bounded by the
     *                       number of the tasks in the task list
     */
    public void markTaskAsDone(@NotNull CommandHandler command) throws DukeException {
        command.splitByClause("done", 0, true);
        try {
            int idx = Integer.parseInt(command.descriptorAfterClause) - 1;
            updateTask(idx, true);
            System.out.println(DONE_TASK);
            printTask(idx);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new CommandSyntaxException("Please enter a valid task number to be marked as done!");
        } finally {
            listTasks();
        }
    }

    /**
     * Queries the task list by a regex term and displays filtered list of tasks
     *
     * @param command the query after it's been parsed by command handler
     * @throws DukeException if query is given with invalid syntax
     * @see QueryHandler for more details on valid queries
     */
    public void queryTasks(@NotNull CommandHandler command) throws DukeException {
        command.splitByClause(FIND_CLAUSE, 0, true);
        QueryHandler query = new QueryHandler(command.descriptorAfterClause.split(" "));
        query.queryOn(this);
    }

    /**
     * Iterates through the tasklist and prints out the tasks in order
     *
     * @param tasks task list managed by Taskmanager
     */
    public void listTasks(@NotNull List<Task> tasks) {
        int idx = 0;
        for (Task task : tasks) {
            idx++;
            System.out.printf("%d.", idx);
            System.out.println(task);
        }
    }

    /**
     * Overloaded method that list the tasks sorted by <b>TaskComparator</b>
     */
    public void listTasks() {
        System.out.println(LIST_TASK);
        listTasks(sortTasks());
    }

    /**
     * Updates a registered task to be set as done or otherwise
     * @param idx the index of the task on the list to be updated
     * @param isDone True if task is done, False otherwise
     */
    public void updateTask(int idx, boolean isDone) {
        Task t = sortTasks().get(idx);
        int taskIdx = tasks.indexOf(t);
        tasks.get(taskIdx).setDone(isDone);
    }

    private void printTask(int idx) {
        System.out.println(sortTasks().get(idx));
    }

    /**
     * Gets the list of tasks currently registered
     * @return List of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Getter for the number of tasks in the list
     * @return the number of tasks in the list
     */
    public int getTaskSize() {
        return taskSize;
    }
}

