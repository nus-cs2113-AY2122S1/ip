package duke;

import duke.DukeExceptions.InvalidValueException;
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

    public String getDescription (String command) throws InvalidValueException {
        if (!command.contains("/"))
            throw new InvalidValueException("Missing detail demarcator: [/by ] or [/at ]");
        String desc = command.substring(command.indexOf(" ") + 1, command.indexOf("/"));
        if (desc.trim().equals(""))
            throw new InvalidValueException("Missing Description in command");
        return desc;
    }

    public String getMoreDetails(String command) throws InvalidValueException{
        String moreDetails = command.substring(command.indexOf("/") + 4);
        if (moreDetails.trim().equals(""))
            throw new InvalidValueException("Missing Required Extra Details");
        return moreDetails;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    private static int getIndex(String command) {
        String index = command.substring(command.indexOf(" ") + 1);
        return Integer.parseInt(index.trim());
    }
}
