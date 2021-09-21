package kate.tasklist;

import kate.task.Deadline;
import kate.task.Event;
import kate.task.Task;
import kate.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves task size of the array list
     *
     * @return The task size in integer
     */
    public int getTaskSize() {
        return tasks.size();
    }

    /**
     * Retrieves a Task given a task index
     *
     * @param taskIndex Index of task supplied by user
     * @return Task object of the current task
     */
    public Task getCurrentTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public boolean isEmptyTaskList() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves the task added most recently
     *
     * @return Most recently added Task object
     */
    public Task getMostRecentAddedTask() {
        int lastElementIndex = tasks.size() - 1;
        return tasks.get(lastElementIndex);
    }

    /**
     * Adds a todo task to be done
     *
     * @param taskDescription A general task that user wants to add
     */
    public void addToDo(String taskDescription) {
        tasks.add(new ToDo(taskDescription));
    }

    /**
     * Adds a todo task from file
     *
     * @param taskDescription Description of task
     * @param isDone          Task done status
     */
    public void addToDoFromFile(String taskDescription, boolean isDone) {
        tasks.add(new ToDo(taskDescription, isDone));
    }

    /**
     * Adds a task that has a deadline
     *
     * @param taskDescription Description for task with a deadline
     * @param date            Date parameter
     * @param time            Time parameter
     */
    public void addDeadline(String taskDescription, LocalDate date, LocalTime time) {
        tasks.add(new Deadline(taskDescription, date, time));
    }

    /**
     * Adds a task that has a deadline from file
     *
     * @param taskDescription Adds a task that has a deadline from file
     * @param isDone          Task done status
     * @param date            Date field for task with deadline
     * @param time            Time field for task with deadline
     */
    public void addDeadlineFromFile(String taskDescription, boolean isDone, LocalDate date, LocalTime time) {
        tasks.add(new Deadline(taskDescription, isDone, date, time));
    }

    /**
     * Adds a task that is an event
     *
     * @param taskDescription Description of task that is an event
     * @param date            Date field for an event
     * @param time            Time field for an event
     * @param duration        Duration of the event
     */
    public void addEvent(String taskDescription, LocalDate date, LocalTime time, int duration) {
        tasks.add(new Event(taskDescription, date, time, duration));
    }

    /**
     * Adds a task that is an event from file
     *
     * @param taskDescription Description of task
     * @param isDone          Task done status
     * @param date            Date field for an event
     * @param time            Time field for an event
     * @param duration        Duration of the event
     */
    public void addEventFromFile(String taskDescription, boolean isDone, LocalDate date, LocalTime time, int duration) {
        tasks.add(new Event(taskDescription, isDone, date, time, duration));
    }

    /**
     * Delete a task specified by a task number
     *
     * @param deletedTask Task object to be deleted
     */
    public void deleteTask(Task deletedTask) {
        tasks.remove(deletedTask);
    }

    /**
     * Filter tasks by a specific keyword
     *
     * @param keyword Keyword provided by user
     * @return ArrayList of tasks in String
     */
    public ArrayList<String> filterTasksByKeyword(String keyword) {
        return (ArrayList<String>) IntStream
                .range(0, getTaskSize())
                .mapToObj((index) -> String.format("%d. %s", index + 1, getCurrentTask(index).getTaskInfo()))
                .filter((task) -> (task).contains(keyword))
                .collect(Collectors.toList());
    }

}
