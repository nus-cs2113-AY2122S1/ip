package task;

import exception.AustinClearListException;
import exception.AustinEmptyListException;
import exception.AustinTaskAlreadyCompletedException;
import exception.AustinTaskAlreadyNotCompletedException;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class holds all the tasks which user has added and has methods
 * to manipulate the task list.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs a new task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list by adding the loaded tasks from the file.
     *
     * @param loadedTasks Tasks loaded from the file
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        tasks = loadedTasks;
    }

    /**
     * @return ArrayList containing all the tasks
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * @return The number of tasks in the task list
     */
    public static int tasksCount() {
        return tasks.size();
    }

    /**
     * Extracts the task object from the array list.
     *
     * @param taskIndex Index of the selected task
     * @return Selected Task object
     */
    public static Task getTaskItem(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Extracts the selected task and creates a string by adding all its details
     * to make it printable.
     *
     * @param taskIndex Index of the selected task
     * @return String containing all the task details
     */
    public static String getTaskItemInString(int taskIndex) {
        return getTaskItem(taskIndex).toString();
    }

    /**
     * Extracts the selected task and creates a string by in a specific format
     * in order to store it in the file.
     *
     * @param taskIndex Index of the selected task
     * @return String containing all the task details which is used
     *          to store it in the file
     */
    public static String getTaskItemInFileFormat(int taskIndex) {
        return getTaskItem(taskIndex).toFileFormat();
    }

    /**
     * Prints all the tasks in the list along with the category and status.
     * Error message is printed if there are no tasks in the list.
     * This method is executed once the "list" command is called.
     *
     * @throws AustinEmptyListException If either there are no items in the list or if
     *         there are additional keywords in the command
     */
    public void printTaskList() throws AustinEmptyListException {
        if (tasks.size() == 0) {
            throw new AustinEmptyListException();
        }
        System.out.println("Below are the list of tasks in your list:");
        Ui.printList(tasks);
        Ui.printCurrentStatus(tasks.size());
    }

    /**
     * Marks the status of a specific task as done by identifying the task index
     * in the command.
     *
     * @throws AustinTaskAlreadyCompletedException If the task is already marked as done
     * @throws IndexOutOfBoundsException If the task index is out of range
     */
    public void markAsDone(int taskIndex) throws AustinTaskAlreadyCompletedException,
            IndexOutOfBoundsException {
        if (tasks.get(taskIndex).isDone()) {
            throw new AustinTaskAlreadyCompletedException();
        }
        tasks.get(taskIndex).setDone(true);
    }

    /**
     * Marks the status of a specific task as done by identifying the task index
     * in the command.
     *
     * @throws AustinTaskAlreadyNotCompletedException If the task is already marked as
     *                                                incomplete
     * @throws IndexOutOfBoundsException If the task index is out of range
     */
    public void markAsNotDone(int taskIndex) throws
            AustinTaskAlreadyNotCompletedException,
            IndexOutOfBoundsException {
        if (tasks.get(taskIndex).isDone()) {
            tasks.get(taskIndex).setDone(false);
        } else {
            throw new AustinTaskAlreadyNotCompletedException();
        }
    }


    /**
     * Adds a todo task into the list.
     *
     * @param description Description of the task
     */
    public void addTodoTask(String description) {
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
    }

    /**
     * Adds a task of "event" type into the list.
     *
     * @param description Description of the task
     * @param at Event date and time
     */
    public void addEventTask(String description, LocalDateTime at)  {
        Event newEvent = new Event(description, at);
        tasks.add(newEvent);
    }

    /**
     * Adds the deadline task into the list.
     *
     * @param description Description of the task
     * @param by Deadline date and time
     */
    public void addDeadlineTask(String description, LocalDateTime by) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
    }

    /**
     * Removes a task from the list.
     *
     * @param taskIndex Index of the task to be deleted
     * @throws IndexOutOfBoundsException If the task index is out of range
     */
    public Task deleteTask(int taskIndex) throws IndexOutOfBoundsException {
        Task removedTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return removedTask;
    }

    /**
     * Removes all the tasks from the list and in the file.
     *
     * @throws AustinClearListException It happens if
     */
    public void clearTasks() throws AustinClearListException {
        if (tasks.size() == 0) {
            throw new AustinClearListException();
        }
        tasks.clear();
    }

    /**
     * Filters out the tasks based on the keyword input by the user.
     *
     * @param keyword Keyword input by the user
     * @throws AustinEmptyListException If there are no tasks in the list
     */
    public ArrayList<Task> findTasks(String keyword) throws AustinEmptyListException {
        if (tasks.size() == 0) {
            throw new AustinEmptyListException();
        }
        ArrayList<Task> result = (ArrayList<Task>) tasks.stream()
                .filter((task) -> task.getDescription().toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return result;
    }
}
