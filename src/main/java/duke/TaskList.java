package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds Task to task list and saves addition to data file
     *
     * @param task Task object to be added
     */
    public static void addTask(Task task) {
        tasks.add(task);
        Storage.exportData();
    }

    /**
     * Adds a todo to task list, saves changes to data file and returns newly added todo
     *
     * @param todoName Name of todo to be added to task list
     * @return Newly added deadline
     * @throws DukeException If todo was empty
     */
    public static Task addTodo(String todoName) throws DukeException {
        if (todoName.isEmpty()) {
            throw new DukeException("Todo cannot be empty");
        }
        Todo todo = new Todo(todoName);
        addTask(todo);
        return todo;
    }

    /**
     * Adds a deadline to task list, saves changes to data file and returns newly added deadline
     *
     * @param deadlineName Name of deadline to be added to task list
     * @param deadlineDue Due date of event to be added to task list
     * @return Newly added deadline
     */
    public static Task addDeadline(String deadlineName, LocalDate deadlineDue) {
        Deadline deadline = new Deadline(deadlineName, deadlineDue);
        addTask(deadline);
        return deadline;
    }

    /**
     * Adds an event to task list, saves changes to data file and returns newly added event
     *
     * @param eventName Name of event to be added to task list
     * @param eventTime Time of event to be added to task list
     * @return Newly added event
     */
    public static Task addEvent(String eventName, LocalDate eventTime) {
        Event event = new Event(eventName, eventTime);
        addTask(event);
        return event;
    }

    /**
     * Prints list of Tasks in task list in a user-friendly format
     */
    public static void listTasks() {
        Ui.printTaskList(tasks);
    }

    /**
     * Marks Task at specified index as done, changes to data file and returns Task
     *
     * @param taskNo Index of completed task
     * @return Returns completed Task
     */
    public static Task markTaskNoAsDone(int taskNo) {
        Task task = tasks.get(taskNo);
        task.markAsDone();
        Storage.exportData();
        return task;
    }

    /**
     * @return Size of task list
     */
    public static int getTasklistSize() {
        return tasks.size();
    }

    /**
     * Returns task list containing all tasks in memory
     *
     * @return Task list
     */
    public static ArrayList<Task> getTasklist() {
        return tasks;
    }


    /**
     * Deletes Task at specified index, saves changes to data file and returns Task
     *
     * @param taskNo Index of task in task list
     * @return Task that has been deleted
     */
    public static Task deleteTask(int taskNo) {
        Task task = tasks.remove(taskNo);
        Storage.exportData();
        return task;
    }
}
