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
        for (TaskBase task : taskList) {
            task.printInfo();
            System.out.println("-------");
        }
    }


}

