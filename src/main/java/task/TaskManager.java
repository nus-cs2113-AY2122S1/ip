package task;

import java.util.ArrayList;
import java.util.Collections;

public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void setTaskList(ArrayList<Task> taskList) {
        if (taskList != null) {
            this.taskList = taskList;
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> savedTaskList) {
        taskList.addAll(savedTaskList);
    }

    public ArrayList<Task> getSortedList() {
        sortTaskList();
        return getTaskList();
    }

    public void sortTaskList() {
        ArrayList<TaskWithDate> taskWithDateList = filterTasksWithDateFromTaskList();
        sortByDate(taskWithDateList);
        ArrayList<Task> sortedTaskList = new ArrayList<>(taskWithDateList);
        for (Task t:taskList) {
            if (t instanceof Todo) {
                sortedTaskList.add(t);
            }
        }
        taskList = new ArrayList<>(sortedTaskList);
    }

    private void sortByDate(ArrayList<TaskWithDate> taskWithDateList) {
        int n = taskWithDateList.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (taskWithDateList.get(j).isAfter(taskWithDateList.get(j + 1))) {
                    Collections.swap(taskWithDateList, j, j + 1);
                }
            }
        }
    }



    private ArrayList<TaskWithDate> filterTasksWithDateFromTaskList() {
        ArrayList<TaskWithDate> taskWithDateList = new ArrayList<>();
        for (Task t : taskList) {
            if (t instanceof TaskWithDate) {
                taskWithDateList.add((TaskWithDate) t);
            }
        }
        return taskWithDateList;
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void deleteTask(Task t) {
        taskList.remove(t);
    }

    public void markTaskAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void clearHistory() {
        taskList.clear();
    }
}
