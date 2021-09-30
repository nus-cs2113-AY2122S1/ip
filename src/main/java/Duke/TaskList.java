package Duke;

import java.time.LocalDateTime;
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
        for (Task t : tasks) {
            int curr = tasks.indexOf(t) + 1;
            ui.showTask(curr, t);
        }
        ui.showToUser("");
    }

    public void showListForDate(LocalDateTime dateTime, Ui ui) {
        int curr = 1;
        for (Task t: tasks) {
            if (t instanceof Deadline) {
                curr = showDeadlineForDate((Deadline)t, dateTime, curr, ui);
            }
        }
    }

    private int showDeadlineForDate(Deadline d, LocalDateTime dateTime, int curr, Ui ui) {
        if (isWithinDate(d, dateTime)) {
            ui.showTask(curr, d);
            return curr + 1;
        }
        return curr;
    }

    private boolean isWithinDate(Deadline d, LocalDateTime dateTime) {
        LocalDateTime deadlineDateTime = d.getBy();
        if (deadlineDateTime.isBefore(dateTime) | deadlineDateTime.isEqual(dateTime)) {
            if (!d.isDone) {
                return true;
            }
        }
        return false;
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
            description = String.format("%s | %s", t.getDescription(), ((Deadline) t).getUnformattedBy());
            taskType = "D";
        }
        else if (t instanceof Event) {
            description = String.format("%s | %s", t.getDescription(), ((Event) t).getUnformattedAt());
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
