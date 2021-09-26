package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask (Task newTask) {
        this.list.add(newTask);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public Task markAsDone(String command) {
        int index = getIndex(command) - 1;
        Task completedTask = list.get(index);
        completedTask.markAsDone();
        list.get(index).markAsDone();
        return completedTask;
    }

    public Task deleteTask(int index) {
        Task targetTask = list.get(index - 1);
        list.remove(index - 1);
        return targetTask;
    }

    public static String getTodo (String command) {
        String item = command.substring(command.indexOf(" ") + 1);
        return item;
    }

    public String getDescription (String command) {
        String desc = command.substring(command.indexOf(" ") + 1, command.indexOf("/"));
        return desc;
    }

    public String getDate (String command) {
        String date = command.substring(command.indexOf("/") + 4);
        return date;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    private static int getIndex(String command) {
        String index = command.substring(command.indexOf(" ") + 1);
        return Integer.parseInt(index.trim());
    }
}
