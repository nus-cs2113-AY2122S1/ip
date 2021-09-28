package duke;

import duke.DukeExceptions.InvalidValueException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor to create TaskList object.
     *
     * @param list is the current list of Task.
     */
    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public int notDoneCount() {
        int unmarked = 0;
        for (Task task : list) {
            unmarked += (task.isDone()) ? 0 : 1;
        }
        return unmarked;
    }

    /**
     * Adds task to TaskList.
     *
     * @param newTask that user wants to add.
     */
    public void addTask(Task newTask) {
        this.list.add(newTask);
    }

    /**
     * Retrieves list of Tasks stored as ArrayList.
     *
     * @return list of Tasks stored in TaskList object.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Sets isDone of tasks specified to true.
     *
     * @param command input by user.
     * @return task specified as done.
     */
    public String markAsDone(String command) {
        int index = getIndex(command) - 1;
        Task completedTask = list.get(index);
        if (completedTask.isDone())
            return "Done Previously";
        completedTask.markAsDone();
        list.get(index).markAsDone();
        return completedTask.toString();
    }

    /**
     * Deletes tasks from TaskList.
     *
     * @param index number of the task in list.
     * @return deleted task.
     */
    public Task deleteTask(int index) {
        Task targetTask = list.get(index - 1);
        list.remove(index - 1);
        return targetTask;
    }

    /**
     * Retrieves the target items after user command
     *
     * @param command target items (Description or find target).
     * @return target items.
     */
    public static String getItem(String command) {
        return command.substring(command.indexOf(" ") + 1);
    }

    /**
     * Retrieves the description of task in user input.
     *
     * @param command input from user.
     * @return description of task.
     * @throws InvalidValueException when missing </> demarcators, or missing description in command.
     */
    public String getDescription(String command) throws InvalidValueException {
        if (!command.contains("/"))
            throw new InvalidValueException("Missing detail demarcator: </by > or </at >");
        String desc = command.substring(command.indexOf(" ") + 1, command.indexOf("/"));
        if (desc.trim().equals(""))
            throw new InvalidValueException("Missing Description in command");
        return desc;
    }

    /**
     * Retrieves extra details needed.
     *
     * @param command input from user.
     * @return extra details required.
     * @throws InvalidValueException when missing extra details required in command.
     */
    public String getMoreDetails(String command) throws InvalidValueException {
        String moreDetails = command.substring(command.indexOf("/") + 4);
        if (moreDetails.trim().equals("") | moreDetails.trim().contains("\t"))
            throw new InvalidValueException("Missing Required Extra Details");
        return moreDetails;
    }

    /**
     * Retrieve list of items containing the keyword.
     *
     * @param keyword Keyword input by user.
     * @return result List of items found with keyword.
     */
    public String find(String keyword) {
        int count = 0;
        String result = "";
        for (Task task : list) {
            count += 1;
            result += (task.getTask().contains(keyword)) ? String.format("\t%d. ", count) + task + "\n" : "";
        }
        return result;
    }

    /**
     * Retrieve user input item as integer.
     *
     * @param command input from user
     * @return index found in input
     */
    private static int getIndex(String command) {
        String index = command.substring(command.indexOf(" ") + 1);
        return Integer.parseInt(index.trim());
    }
}
