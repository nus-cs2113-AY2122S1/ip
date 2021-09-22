package duke.storage;

import duke.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList;
    private TaskStorage storage;
    private static TaskList instance = null;

    public TaskList() throws DukeException {
        try {
            storage = new TaskStorage();
            taskList = storage.readTasksFromMemory();
        } catch (IOException e) {
            taskList = new ArrayList<>();
            throw new DukeException("Cannot read tasks from memory!");
        }

    }

    public static TaskList getInstance() throws DukeException{
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    public void add(Task task) throws IOException {
        taskList.add(task);
        storage.writeTasksToMemory(taskList);
    }

    public Task delete(int taskNumber) throws IOException {
        Task removedTask = taskList.remove(taskNumber - 1);
        storage.writeTasksToMemory(taskList);
        return removedTask;
    }

    public void setDone(int taskNumber) throws IOException, IndexOutOfBoundsException {
        taskList.get(taskNumber - 1).setDone();
        storage.writeTasksToMemory(taskList);
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public String getTaskInfo(int taskIndex) throws DukeException{
        try {
            return taskList.get(taskIndex).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number not exist!");
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
