package duke;

import duke.tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task inputTask) {
        tasks.add(inputTask);
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public Task updateTask(int taskIndex) {
        tasks.get(taskIndex - 1).setDone();
        return tasks.get(taskIndex - 1);
    }

    public Task deleteTask(int index) {
        Task deletedTask = tasks.remove(index - 1);
        return deletedTask;
    }

    public String listTaskList(TaskList tasks) {
        String listMessage = "Here is the list you requested!";
        for (int i = 1; i <= tasks.getSize(); i++) {
            listMessage += "\n";
            listMessage += String.valueOf(i) + ".";
            listMessage += tasks.getTask(i);
        }
        return listMessage;
    }

    public int getSize() {
        return tasks.size();
    }

}
