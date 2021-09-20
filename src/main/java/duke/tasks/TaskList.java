package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public static String listDateTaskList(ArrayList<Task> tasksDateList) {
        String listDateMessage = "Here is the list you requested!";
        for (int i = 0; i < tasksDateList.size(); i++) {
            int j = i + 1;
            listDateMessage += "\n";
            listDateMessage += String.valueOf(j) + ".";
            listDateMessage += tasksDateList.get(i);
        }
        return listDateMessage;
    }

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

    public static ArrayList<Task> findByDate(LocalDate keyword,TaskList tasks) {
        ArrayList<Task> tasksDateList = new ArrayList<Task>();
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task currTask = tasks.getTask(i);
            if (currTask instanceof Todo) {
                continue;
            }
            String taskDateInString = currTask.getDate().toString();
            if (taskDateInString.equals(keyword.toString())) {
                tasksDateList.add(currTask);
            }
        }
        return tasksDateList;
    }

    public int getSize() {
        return tasks.size();
    }

}
