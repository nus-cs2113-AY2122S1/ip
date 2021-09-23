package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.ui.Ui;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<String> data) throws DukeException {
        taskList = deserialize(data);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void markTaskAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Filters the task list according to the specified keyword.
     *
     * @param keyword The keyword.
     * @return The filtered task list.
     */
    public TaskList filterTaskByKeyword(String keyword) {
        TaskList filteredTaskList = new TaskList();
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                filteredTaskList.addTask(task);
            }
        }
        return filteredTaskList;
    }

    /**
     * Serializes task data.
     *
     * @return Serialied task data.
     */
    public String serialize() {
        StringBuilder serializedData = new StringBuilder();
        for (Task task : taskList) {
            serializedData.append(task.serialize()).append(System.lineSeparator());
        }
        return serializedData.toString();
    }

    /**
     * Deserializes stored data and returns a new list.
     *
     * @param data Stored data.
     * @return The new task list after deserialization.
     * @throws DukeException If data is in invalid format.
     */
    public static List<Task> deserialize(List<String> data) throws DukeException {
        List<Task> taskList = new ArrayList<>();
        for (String line : data) {
            taskList.add(Task.deserialize(line));
        }
        return taskList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            s.append(Ui.PADDING).append(i + 1).append(". ").append(task).append(System.lineSeparator());
        }
        return s.toString();
    }
}
