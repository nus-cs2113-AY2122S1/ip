package duke;

import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui;

    public TaskList(Ui ui) {
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to task list represented by tasks array.
     *
     * @param task Task to add
     */
    public void addTasks(Task task, boolean toPrint) {
        tasks.add(task);
        if (toPrint) {
            ui.printAddTaskMessage(task, tasks);
        }
    }

    public void printTasks() {
        ui.printTaskList(tasks);
    }

    /**
     * Mark a task given by its index as done
     *
     * @param taskIndex Index of task in task list to mark as done
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public void markTaskAsDone(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please select a task in the task list!");
        }
        tasks.get(taskIndex).markAsDone();
        ui.printTaskDoneMessage(taskIndex, tasks);
    }

    /**
     * Deletes a task given by its index
     *
     * @param taskIndex Index of task in task list to delete
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public void deleteTask(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please select a task in the task list!");
        }
        ui.printDeleteTaskMessage(taskIndex, tasks);
        tasks.remove(taskIndex);
    }

    /**
     * Finds a task given by a keyword
     *
     * @param keyword Keyword to search for within each task
     */
    public void findTask(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> {
                    if (task.getDescription().toLowerCase().contains(keyword)) {
                        return true;
                    }
                    if (task instanceof Deadline) {
                        return (((Deadline) task).getDeadline().toLowerCase().contains(keyword) ||
                                task.getDescription().toLowerCase().contains(keyword));
                    }
                    if (task instanceof Events) {
                        return (((Events) task).getEventLocation().toLowerCase().contains(keyword) ||
                                task.getDescription().toLowerCase().contains(keyword));
                    }
                    return false;
                })
                .collect(Collectors.toList());
        ui.printFindTask(matchingTasks);
    }
}
