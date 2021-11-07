package task;

import ui.UI;
import exceptions.DukeInvalidArgumentException;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * Represents the list of tasks and basic functionalities
 * to be performed on the list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task to list and gives message to the user.
     *
     * @param task Task to be added to list
     */
    public void addToList(Task task) {
        tasks.add(task);
        UI.printAddTaskMessage(tasks);
    }

    /**
     * Deletes task from list gives message to the user.
     *
     * @param index Index of task to be deleted
     */
    public void deleteFromList(int index) {
        String deletedTask = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        int size = tasks.size();
        UI.printDeleteTaskMessage(deletedTask, size);
    }

    /**
     * Marks a task as done by setting isDone to true.
     *
     * @param index Index of the task to be marked as done
     * @throws DukeInvalidArgumentException If index is invalid
     */
    public void markTaskAsDone(int index) throws DukeInvalidArgumentException {
        try {
            tasks.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException();
        }
        String description = tasks.get(index - 1).toString();
        UI.printDoneMessage(description);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Compile a list of tasks that math "find" command keyword.
     *
     * @param keyword Keyword to be checked against list of tasks
     * @return ArrayList List of keyword-matching tasks
     */
    public ArrayList<Task> findTasks(String keyword){
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
