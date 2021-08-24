package task;

import java.util.ArrayList;

public class TaskManager {

    private ArrayList<TaskBase> taskList;

    public TaskManager() {
        taskList = new ArrayList<TaskBase>();
    }

    public void addTask(TaskBase task) {
        taskList.add(task);
    }

    /**
     * api for list command
     **/
    public void listAllTasks() {
        int label = 1;
        for (TaskBase task : taskList) {
            System.out.print(label++);
            System.out.print(".");
            task.printInfo();
            System.out.println("-------");
        }
    }

    /**
     * Mark task as done
     **/
    public void markTaskDone(int taskId) {
        taskList.get(taskId - 1).markDone();
        taskList.get(taskId - 1).printInfo();
    }

    /**
     * Delete a task
     **/
    public void deleteTask(int taskId) {
        // data structure is 0-indexed
        taskList.remove(taskId - 1);
    }


}

