package duke;

import duke.DukeExceptions.InvalidValueException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    /**
     * Adds task to TaskList.
     *
     * @param newTask that user wants to add.
     */
    public void addTask (Task newTask) {
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
    public Task markAsDone(String command) {
        int index = getIndex(command) - 1;
        Task completedTask = list.get(index);
        completedTask.markAsDone();
        list.get(index).markAsDone();
        return completedTask;
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
    public static String getTodo (String command) {
        return command.substring(command.indexOf(" ") + 1);
    }

    /**
     * Retrieves the description of task in user input.
     *
     * @param command input from user.
     * @return description of task.
     * @throws InvalidValueException when missing </> demarcators, or missing description in command.
     */
    public String getDescription (String command) throws InvalidValueException {
        if (!command.contains("/"))
            throw new InvalidValueException("Missing detail demarcator: [/by ] or [/at ]");
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
    public String getMoreDetails(String command) throws InvalidValueException{
        String moreDetails = command.substring(command.indexOf("/") + 4);
        if (moreDetails.trim().equals(""))
            throw new InvalidValueException("Missing Required Extra Details");
        if (moreDetails.trim().contains("/")) {
            moreDetails = getCorrectFormat(moreDetails);
        }
        return moreDetails;
    }

    private String getCorrectFormat(String moreDetails) {

        StringBuilder toParse = new StringBuilder();
        String[] dateTime = moreDetails.split(" ");
        String[] tempDate = dateTime[0].trim().split("/");

        for (int i=2; i > 0; i--) {
            toParse.append(tempDate[i]).append("-");
        }
        LocalDate date = LocalDate.parse(toParse + tempDate[0]);
        moreDetails = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";

        if (dateTime.length > 1) {
            toParse = new StringBuilder();
            int missingDigits = 6 - dateTime[1].trim().length();
            for (int i = 0; i < dateTime[1].trim().length(); i++) {
                char c = dateTime[1].charAt(i);
                toParse.append((i != 0 && i % 2 == 0) ? ":" + c : c);
            }
            for (int i=0 ; i<missingDigits;i++){
                toParse.append((missingDigits % 2 == i % 2) ? ":0" : "0");
            }
            LocalTime time = LocalTime.parse(toParse.toString());
            moreDetails += time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        }

        return moreDetails;
    }

    /**
     * Retrieves the task numbered the same as index in the TaskList.
     *
     * @param index of task to retrieve.
     * @return task
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Retrieve list of items containing the keyword.
     *
     * @param keywaord Keyword input by user.
     * @return result List of items found with keyword.
     */
    public String find(String keyword) {
        int count = 0;
        String result = "";
        for (Task task : list) {
            count += 1;
            result += (task.getTask().contains(keyword)) ? String.format("\t%d. ", count) + task + "\n": "" ;
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
