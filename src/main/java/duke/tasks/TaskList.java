package duke.tasks;

import java.time.LocalDate;
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

    public String listTaskList(TaskList taskList) {
        String listMessage = "Here is the full list!";
        for (int i = 1; i <= taskList.getSize(); i++) {
            listMessage += "\n";
            listMessage += String.valueOf(i) + ".";
            listMessage += taskList.getTask(i);
        }
        return listMessage;
    }


    public ArrayList<Task> findByDate(LocalDate keyword) {
        ArrayList<Task> tasksDateList = new ArrayList<Task>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
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

    public static String listDateTaskList(ArrayList<Task> tasksDateList) {
        if (tasksDateList.isEmpty()) {
            return "Hmm there does not seem to be any dates that matches your search request";
        }
        String listDateMessage = "Here are the matching dates in your list:";
        for (int i = 1; i <= tasksDateList.size(); i++) {
            listDateMessage += "\n";
            listDateMessage += String.valueOf(i) + ".";
            listDateMessage += tasksDateList.get(i - 1);
        }
        return listDateMessage;
    }

    public static String listQueryTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "Hmm there does not seem to be any tasks that matches your search request";
        }
        String listQueryMessage = "Here are the matching tasks in your list:";
        for (int i = 1; i <= taskList.size(); i++) {
            listQueryMessage += "\n";
            listQueryMessage += String.valueOf(i) + ".";
            listQueryMessage += taskList.get(i - 1);
        }
        return listQueryMessage;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> findTask(String key) {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            String TaskDescription = currTask.getDescription();
            if (TaskDescription.contains(key)) {
                tasksFound.add(currTask);
            }
        }
        return tasksFound;
    }

}
