package duke.tasks;

import duke.exceptions.DukeInvalidTaskIndex;
import duke.exceptions.DukeTaskAlreadyCompletedException;
import java.util.ArrayList;

public class TaskList {
    private int numberOfTasks;
    private ArrayList<Task> tasks;

    public TaskList() {
        numberOfTasks = 0;
        tasks = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> loadedData) {
        tasks = loadedData;
        numberOfTasks = loadedData.size();
    }
    
    public void addTask(Task task) {
        tasks.add(task);
        numberOfTasks++;
    }
    
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
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

    public Task removeTask(int taskNumber) throws DukeInvalidTaskIndex {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new DukeInvalidTaskIndex();
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        numberOfTasks--;
        return removedTask;
    }
}
