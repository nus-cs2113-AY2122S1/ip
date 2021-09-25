package duke.tasks;

import duke.exceptions.DukeInvalidTaskIndex;
import duke.exceptions.DukeTaskAlreadyCompletedException;

import java.util.ArrayList;

/**
 * Represents a list of all tasks in the form of a ArrayList<Task> and has methods to manipulate the Arraylist
 */
public class TaskList {
    private int numberOfTasks;
    private ArrayList<Task> tasks;

    /**
     * Default constructor
     */
    public TaskList() {
        numberOfTasks = 0;
        tasks = new ArrayList<>();
    }

    /**
     * Constructs the TaskList with data loaded from a stored file
     *
     * @param loadedData ArrayList of tasks loaded from a stored file
     */
    public TaskList(ArrayList<Task> loadedData) {
        tasks = loadedData;
        numberOfTasks = loadedData.size();
    }

    /**
     * adds task to the ArrayList
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        numberOfTasks++;
    }

    /**
     * @return number of tasks in ArrayList
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * @return ArrayList of tasks containing all current tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * @param taskNumber index of task to be set as done
     * @throws DukeInvalidTaskIndex              when the taskNumber is greater than number of tasks or lesser than 0
     * @throws DukeTaskAlreadyCompletedException when the task with index taskNumber is already completed
     */
    public void setTaskAsDone(int taskNumber) throws DukeInvalidTaskIndex,
            DukeTaskAlreadyCompletedException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new DukeInvalidTaskIndex();
        }
        if (tasks.get(taskNumber - 1).isDone()) {
            throw new DukeTaskAlreadyCompletedException();
        }

        tasks.get(taskNumber - 1).setDone();
    }

    /**
     * @param taskNumber index of task to be removed
     * @return task that was just removed
     * @throws DukeInvalidTaskIndex when the taskNumber is greater than number of tasks or lesser than 0
     */
    public Task removeTask(int taskNumber) throws DukeInvalidTaskIndex {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new DukeInvalidTaskIndex();
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        numberOfTasks--;
        return removedTask;
    }
}
