package Tasks;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private static final String NO_TASK_MESSAGE = "No tasks\n";

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public String addTask(Task task) {
        taskList.add(task);
        return task.toString();
    }

    public String printAllTasks() {
        if (taskList.isEmpty()) {
            return NO_TASK_MESSAGE;
        }
        String listOfTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            listOfTasks += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return listOfTasks;
    }

    public String markTaskAsDone(int index) {
        taskList.get(index - 1).markAsDone();
        return taskList.get(index - 1).toString();
    }

    public String deleteTask(int index) {
        String removedTask = taskList.get(index - 1).toString();
        taskList.remove(index - 1);
        return removedTask;
    }

    public String printNewestTask() {
        return taskList.get(taskList.size() - 1).toString();
    }
}
