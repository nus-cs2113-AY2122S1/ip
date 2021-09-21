package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.text.Text;

import java.util.ArrayList;

public class TaskList extends Text {

    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    public TaskList(TaskList openFile) {
        taskList = (ArrayList<Task>) openFile.taskList.clone();
    }

    public int size() {
        return taskList.size();
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index > size() - 1) {
            throw new DukeException(INPUT_VALID_TASK);
        } else {
            return taskList.get(index);
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index > size() - 1) {
            throw new DukeException(INPUT_VALID_TASK);
        } else {
            taskList.remove(index);
        }
    }

    public void markTaskDone(int index) throws DukeException {
        if (index < 0 || index > size() - 1) {
            throw new DukeException(INPUT_VALID_TASK);
        } else {
            taskList.get(index).markDone();
        }
    }

    public String getList() {
        int taskNumber = 1;
        String listOfTask = "";
        for (Task task : taskList) {
            listOfTask = listOfTask.concat(taskNumber + "." + task.toString() + NEW_LINE);
            taskNumber++;
        }
        return listOfTask;
    }
}
