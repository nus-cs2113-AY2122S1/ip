package Duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int tasksLength() {
        return tasks.size();
    }

    public void addTask(Task t) {
        tasks.add(t);
        Task.setTaskCount(1);
    }

    public void addTask(Task t, Ui ui) {
        tasks.add(t);
        Task.setTaskCount(1);
        ui.showAdd(t);
    }

    public void deleteTask(int taskNumber, Ui ui) {
        Task.setTaskCount(-1);
        Task task = tasks.get(taskNumber);
        ui.showDelete(task);
        tasks.remove(taskNumber);
    }

    public void doneTask(int taskNumber, Ui ui) {
        Task task = tasks.get(taskNumber);
        task.setDone(true);
        ui.showDone(task);
    }

    public void showList(Ui ui) {
        for (Task item : tasks) {
            int curr = tasks.indexOf(item) + 1;
            System.out.println(curr + "." + item);
        }
        ui.showToUser("");
    }

    public String formatTaskForFile(int taskNumber) {
        Task t = tasks.get(taskNumber);
        String description = "";
        String taskType = "";
        String status = getStringStatus(t.isDone);
        if (t instanceof Todo) {
            description = t.getDescription();
            taskType = "T";
        }
        else if (t instanceof Deadline) {
            description = String.format("%s | %s", t.getDescription(), t.getBy());
            taskType = "D";
        }
        else if (t instanceof Event) {
            description = String.format("%s | %s", t.getDescription(), t.getAt());
            taskType = "E";
        }
        String textToWrite = String.format("%s | %s | %s\n", taskType, status, description);
        return textToWrite;
    }

    public static String getStringStatus(boolean b) {
        String status;
        if (b) {
            status = "1";
        } else {
            status = "0";
        }
        return status;
    }
}
