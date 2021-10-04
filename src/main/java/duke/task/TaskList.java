package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.ui.Ui;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
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
     * Filters the task list according to the specified date.
     *
     * @param date the specified date
     * @return the filtered task list
     */
    public TaskList filterTaskByDate(String date) {
        return new TaskList(taskList.stream()
                .filter(task -> task instanceof GetSchedule)
                .filter(task -> ((GetSchedule) task).getSchedule().equals(date))
                .collect(Collectors.toList()));
    }

    /**
     * Filters the task list according to the specified keyword.
     *
     * @param keyword the specified keyword
     * @return the filtered task list
     */
    public TaskList filterTaskByKeyword(String keyword) {
        return new TaskList(taskList.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList()));
    }

    /**
     * Serializes task data.
     *
     * @return serialied task data
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
     * @param data stored data
     * @return the new task list after deserialization
     * @throws DukeException if data is in invalid format
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
