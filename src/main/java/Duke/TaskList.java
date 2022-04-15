package Duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Class constructor for TaskList
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns number of tasks in TaskList
     *
     * @return Number of tasks
     */
    public int tasksLength() {
        return tasks.size();
    }

    /**
     * Adds Task to TaskList from file
     *
     * @param t Task to be added
     */
    public void addTask(Task t) {
        tasks.add(t);
        Task.setTaskCount(1);
    }

    /**
     * Adds Task to TaskList from user input
     *
     * @param t Task to be added
     * @param ui User interface
     */
    public void addTask(Task t, Ui ui) {
        tasks.add(t);
        Task.setTaskCount(1);
        ui.showAdd(t);
    }

    /**
     * Deletes Task from TaskList
     *
     * @param taskNumber Numbering of task presented in the list
     * @param ui User interface
     */
    public void deleteTask(int taskNumber, Ui ui) {
        Task.setTaskCount(-1);
        Task task = tasks.get(taskNumber);
        ui.showDelete(task);
        tasks.remove(taskNumber);
    }

    /**
     * Marks Task as done
     *
     * @param taskNumber Numbering of task presented in the list
     * @param ui User interface
     */
    public void doneTask(int taskNumber, Ui ui) {
        Task task = tasks.get(taskNumber);
        task.setDone(true);
        ui.showDone(task);
    }

    /**
     * Shows user the list of all tasks with numbering
     *
     * @param ui User interface
     */
    public void showTaskList(Ui ui) {
        showList(tasks, ui);
    }

    /**
     * Shows user the list of all tasks that contains the keyword
     *
     * @param search Keyword to search for
     * @param ui User interface
     */
    public void showFilteredTaskList(String search, Ui ui) {
        ArrayList<Task> filteredtasks = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(search))
                .collect(Collectors.toList());
        showList(filteredtasks, ui);
    }

    /**
     * Iterates through an ArrayList to print out all tasks within
     *
     * @param tasks Arraylist of tasks
     * @param ui User interface
     */
    private void showList(ArrayList<Task> tasks, Ui ui) {
        for (Task item : tasks) {
            int curr = tasks.indexOf(item) + 1;
            System.out.println(curr + "." + item);
        }
        ui.showToUser("");
    }

    /**
     * Shows user the list of all deadlines that occur before or on Date dateTime
     *
     * @param dateTime Date that deadlines should be within
     * @param ui
     */
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

    /**
     * Format Task to be saved into the file
     *
     * @param taskNumber Index of task in tasks
     * @return Text to be written into file
     */
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

    private static String getStringStatus(boolean b) {
        String status;
        if (b) {
            status = "1";
        } else {
            status = "0";
        }
        return status;
    }
}
