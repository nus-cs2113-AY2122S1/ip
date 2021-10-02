package duke.common;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements all the commands that Duke can execute. This class holds the ArrayList object 'tasks' to be used by Duke,
 * hence all the commands that require access to 'tasks' are in this class.
 */
public class TaskList {

    protected static final ArrayList<Task> tasks = new ArrayList<>();

    /** Prints the current task list. */
    public static void executeList() {
        Ui.printList(tasks);
    }

    /**
     * Adds a todo task to the tasklist.
     *
     * @param description the description of the task to be added
     * @throws IOException if an error occurred while writing to the data file
     */
    public static void addTodo(String description) throws IOException {
        tasks.add(Task.getTaskCount(), new Todo(description));
        Ui.printAddMessage(tasks);
        Storage.saveData();
    }

    /**
     * Adds a deadline task to the tasklist. Deadlines must be entered in the format "dd/mm/yyyy hhmm" with the time
     * being in 24-Hour format. For example: 22/12/2021 1800
     *
     * @param description the description of the task to be added
     * @param by the description of the task to be added
     * @throws IOException if an error occurred while writing to the data file
     */
    public static void addDeadline(String description, LocalDateTime by) throws IOException {
        tasks.add(Task.getTaskCount(), new Deadline(description, by));
        Ui.printAddMessage(tasks);
        Storage.saveData();
    }

    /**
     * Adds an event task to the tasklist. Event times must be entered in the format "dd/mm/yyyy hhmm" with the time
     * being in 24-Hour format. For example: 22/12/2021 1800
     *
     * @param description the description of the task to be added
     * @param at the date and time of the task to be added
     * @throws IOException if an error occurred while writing to the data file
     */
    public static void addEvent(String description, LocalDateTime at) throws IOException {
        tasks.add(Task.getTaskCount(), new Event(description, at));
        Ui.printAddMessage(tasks);
        Storage.saveData();
    }

    /**
     * Toggles whether a task is marked as completed or not.
     *
     * @param taskIndex the number of the task to be marked completed (or incomplete)
     * @throws IOException if an error occurred while writing to the data file
     */
    public static void doneTask(int taskIndex) throws IOException {
        try {
            Task givenTask = tasks.get(taskIndex - 1);
            boolean isDone = givenTask.getStatusIcon().equals(Task.DONE_ICON);
            givenTask.setDone(!isDone);
            Ui.printDoneMessage(taskIndex, tasks, !isDone);
            Storage.saveData();
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsMessage();
        }
    }

    /**
     * Deletes a given task from the tasklist.
     *
     * @param taskIndex the number of the task to be marked completed
     * @throws IOException if an error occurred while writing to the data file
     */
    public static void deleteTask(int taskIndex) throws IOException {
        try {
            Task deleted = tasks.remove(taskIndex - 1);
            Task.decreaseTaskCount();
            Ui.printDeleteMessage(deleted);
            Storage.saveData();
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsMessage();
        }
    }

    /**
     * Searches the tasklist for any tasks that contain the given keywords, and displays them to the user.
     *
     * @param keywords the keywords to search for in tasks
     */
    public static void findTaskByDesc(String... keywords) {
        for (String keyword : keywords) {
            List<Task> searchResults = tasks.stream().filter(task -> task.getDescription().contains(keyword))
                    .collect(Collectors.toList());
            Ui.printResults(tasks, searchResults, keyword, false);
        }
        Ui.printDivider();
    }

    public static void findTaskByDate(LocalDate date) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getType().equals(Task.TODO_ICON)) {
                continue;
            }
            boolean isMatch = task.getDateTime().toLocalDate().isEqual(date);
            if (isMatch) {
                searchResults.add(task);
            }
        }
        Ui.printResults(tasks, searchResults, date.format(Task.commandFormat), true);
        Ui.printDivider();
    }
}
