package duke.task;

import static duke.ui.ErrorMessage.TASK_DOES_NOT_EXIST_MESSAGE;

import duke.task.exception.InvalidTaskException;
import java.util.ArrayList;

public class TaskList {


    /* List of tasks */
    private final ArrayList<Task> taskList;

    /* Constructor for task manager */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /* Constructor for task manager with saved task list */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    /**
     * Add task as request by user
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Check if task exist and returns index
     *
     * @param taskNumber Task Number given by user
     * @return Corresponding task index for task number
     * @throws InvalidTaskException If task index does not exist in the current task list
     */
    public int getIndexOfTask(int taskNumber) throws InvalidTaskException {
        boolean isNotWithinSizeLimit = taskNumber < 1 || taskNumber > taskList.size();
        if (isNotWithinSizeLimit) {
            throw new InvalidTaskException(TASK_DOES_NOT_EXIST_MESSAGE);
        }
        return --taskNumber;
    }

    /**
     * Set the task to be completed by marking it done.
     *
     * @param taskNumber task number given by user
     * @return Task marked as done
     * @throws InvalidTaskException When an invalid task is selected to be mark as completed
     */
    public Task completeTask(int taskNumber) throws InvalidTaskException {
        try {
            int taskNumberIndex = getIndexOfTask(taskNumber);
            Task task = taskList.get(taskNumberIndex);
            task.markAsDone();
            return task;
        } catch (InvalidTaskException e) {
            throw new InvalidTaskException(e.getMessage());
        }
    }

    /**
     * Delete task as request by user.
     *
     * @param taskNumber task number given by user
     * @throws InvalidTaskException When an invalid task is selected to be deleted
     */
    public Task deleteTask(int taskNumber) throws InvalidTaskException {
        try {
            int taskNumberIndex = getIndexOfTask(taskNumber);
            Task task = taskList.get(taskNumberIndex);
            taskList.remove(taskNumberIndex);
            return task;
        } catch (InvalidTaskException e) {
            throw new InvalidTaskException(e.getMessage());
        }
    }


}
