package duke.task;

import java.util.ArrayList;
import duke.ui.Ui;

/**
 * The TaskManager class manages the list of tasks, including adding and printing.
 */
public class TaskList {

    /* List of tasks */
    private ArrayList<Task> taskList;

    /**
     * Initialise a new list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialise using an existing list of tasks.
     *
     * @param taskList Existing list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Return a copy of the list of tasks.
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
        if(taskList.size() == 0) {
            Ui.printTaskListEmptyError();
            return;
        }
        Ui.printTaskListFormattedMessage(taskList);
    }

    /**
     * Adds the given task description to the list of tasks.
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
        if (taskIndex <= 0 || taskIndex > taskList.size()) {
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
        if (taskIndex <= 0 || taskIndex > taskList.size()) {
            Ui.printTaskListNotFoundError();
            return;
        }
        Task taskSelected = taskList.get(taskIndex - 1);
        taskList.remove(taskIndex - 1);
        Ui.printTaskListDeleteMessage(taskList, taskSelected);
    }
}
