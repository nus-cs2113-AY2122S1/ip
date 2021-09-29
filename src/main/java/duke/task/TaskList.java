package duke.task;

import duke.task.exception.InvalidTaskException;
import java.util.ArrayList;
import static duke.ui.ErrorMessage.TASK_DOES_NOT_EXIST_MESSAGE;

public class TaskList {


    /* List of tasks */
    private final ArrayList<Task> taskList;

    /* Constructor for task manager */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /* Constructor for task manager */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public int getSize(){
        return taskList.size();
    }

    /**
     * Check if task exist and returns index
     *
     * @param taskNumber Task Number given by user
     * @return Corresponding task index fpr task number
     * @throws InvalidTaskException If task index does not exist in the current task list
     */
    public int getIndexOfTask(int taskNumber) throws InvalidTaskException {
        boolean isNotWithinSizeLimit = taskNumber < 0 || taskNumber >= taskList.size();
        if (isNotWithinSizeLimit) {
            throw new InvalidTaskException(TASK_DOES_NOT_EXIST_MESSAGE);
        }
        return taskNumber;
    }

    /**
     * Set the task to be completed by marking it done.
     *
     * @param taskNumber task number given by user
     * @return Task marked as done
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
