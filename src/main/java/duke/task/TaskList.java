package duke.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import duke.ui.Ui;

/**
 * The TaskManager class manages the list of tasks, including adding and printing.
 */
public class TaskList {

    /* List of tasks */
    private ArrayList<Task> taskList;

    /**
     * Initialises a new list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialises using an existing list of tasks.
     *
     * @param taskList Existing list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Returns a copy of the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return new ArrayList<>(taskList);
    }

    /**
     * Prints the list of tasks.
     */
    public void printTaskList() {
        boolean isEmpty = taskList.size() == 0;

        if (isEmpty) {
            Ui.printTaskListEmptyError();
            return;
        }
        Ui.printTaskListHeader();
        Ui.printTaskListFormatted(taskList);
    }

    /**
     * Adds the given task to the list of tasks.
     *
     * @param task New task.
     */
    public void addTask(Task task) {
        taskList.add(task);
        Ui.printTaskListAddMessage(taskList, task);
    }

    /**
     * Marks the task at the given index as completed.
     *
     * @param taskIndex 1-Based index of selected task.
     */
    public void complete(int taskIndex) {
        if (!isValidIndex(taskIndex)) {
            Ui.printTaskListNotFoundError();
            return;
        }

        Task taskSelected = taskList.get(taskIndex - 1);
        taskSelected.markAsDone();
        Ui.printTaskListCompleteMessage(taskSelected);
    }

    /**
     * Removes the task at the given index.
     *
     * @param taskIndex 1-Based index of selected task.
     */
    public void deleteTask(int taskIndex) {
        if (!isValidIndex(taskIndex)) {
            Ui.printTaskListNotFoundError();
            return;
        }
        Task taskSelected = taskList.get(taskIndex - 1);
        taskList.remove(taskIndex - 1);
        Ui.printTaskListDeleteMessage(taskList, taskSelected);
    }

    /**
     * Prints a list of tasks with descriptions containing the given keyword.
     *
     * @param keyword String to search by.
     */
    public void printFilteredTasks(String keyword) {
        ArrayList<Task> filteredTasks = (ArrayList<Task>) taskList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        boolean isEmpty = filteredTasks.size() == 0;

        if (isEmpty) {
            Ui.printTaskListEmptyError();
            return;
        }

        Ui.printTaskListFilterHeader();
        Ui.printTaskListFormatted(filteredTasks);
    }

    /**
     * Check if the given index is valid.
     *
     * @param taskIndex 1-Based index of selected task.
     * @return True if index is valid. False otherwise.
     */
    private boolean isValidIndex(int taskIndex) {
        boolean isNonPositiveIndex = taskIndex <= 0;
        boolean isNonExistentIndex = taskIndex > taskList.size();
        return !isNonPositiveIndex && !isNonExistentIndex;
    }
}
