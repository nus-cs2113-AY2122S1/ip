package shima.task.action;

import shima.Shima;
import shima.design.Default;
import shima.storage.Storage;
import shima.task.Deadline;
import shima.task.Event;
import shima.task.Task;
import shima.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

public class AddTask {
    /**
     * Checks the syntax for the command to create a new task, and add to the to-do list if the syntax is correct
     *
     * @param words   The array of words that compose the input command
     * @return Returns true if an instance of the subclass is created and successfully stored in the to-do list
     */
    public static boolean isCorrectToDo(String[] words)  {
        if (!words[0].equalsIgnoreCase("TODO")) {
            return false;
        }
        if (words.length == 1) {
            Default.showMessage("Sorry, the task is empty! I don't know how to record it :(");
            return false;
        }
        return true;
    }

    public static void createToDo(ArrayList<Task> tasks, String command, String[] words) throws IOException {
        tasks.add(new ToDo(command.replace(words[0], "").trim()));
        if (Shima.longestTaskDescription < command.replace(words[0], "").trim().length()) {
            Shima.longestTaskDescription = command.replace(words[0], "").trim().length();
        }
        Storage.saveTaskToFile(tasks);
        Default.showMessage(" Class type [T] \"" + tasks.get(tasks.size() - 1) + "\" has been added to the list!" + " " +
                "(" + tasks.size() + " tasks in total)");
    }

    /**
     * Checks the syntax for the command to create an 'Event' instance, and add to the to-do list if the syntax is correct
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if an instance of the subclass Event is created and successfully stored in the to-do list
     */
    public static boolean isCorrectEvent(String command, String[] words, String time, String taskName) {
        if (!words[0].equalsIgnoreCase("EVENT")) {
            return false;
        }
        if (time.toLowerCase().startsWith("at")) {
            time = time.replaceFirst("(?i)at", "").trim();
        }
        if (words.length == 1 || taskName.isEmpty()) {
            Default.showMessage("Sorry, the task is empty! I don't know how to record it :(");
            return false;
        }
        if (time.isEmpty()) {
            Default.showMessage("Sorry, the date and period for the task \"" + taskName + "\" is missing!");
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage("Sorry, fail to create an Event, the time specific character '/' is missing");
            return false;
        }
        if (!command.contains("-")) {
            Default.showMessage("Sorry, fail to create an Event, the period specific character '-' is missing");
            return false;
        }
        return true;
    }

    public static void createEvent(ArrayList<Task> tasks, String time, String taskName) throws IOException {
        tasks.add(new Event(taskName, time));
        if (Shima.longestTaskDescription < taskName.length() + time.length()) {
            Shima.longestTaskDescription = taskName.length() + "(at: )".length() + time.length();
        }
        Storage.saveTaskToFile(tasks);
        Default.showMessage(" Class type [E] \"" + tasks.get(tasks.size() - 1) + "\" has been added to the list!" + " " +
                "(" + tasks.size() + " tasks in total)");
    }

    /**
     * Checks the syntax for the command to create an 'Deadline' instance, and add to the to-do list if the syntax is correct
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if the subclass Deadline is created and successfully stored in the to-do list
     */
    public static boolean isCorrectDeadline(String command, String[] words, String time, String taskName) {
        if (!words[0].equalsIgnoreCase("DEADLINE")) {
            return false;
        }
        if (time.toLowerCase().startsWith("by")) {
            time = time.replaceFirst("(?i)by", "").trim();
        }
        if (words.length == 1 || taskName.isEmpty()) {
            Default.showMessage("Sorry, the task is empty! I don't know how to record it :(");
            return false;
        }
        if (time.isEmpty()) {
            Default.showMessage("Sorry, the deadline for the task \"" + taskName + "\" is missing!");
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage("Sorry, fail to create an Event, the time specific character '/' is missing");
            return false;
        }
        return true;
    }

    public static void createDeadline(ArrayList<Task> tasks, String time, String taskName) throws IOException {
        tasks.add(new Deadline(taskName, time));
        if (Shima.longestTaskDescription < taskName.length() + time.length()) {
            Shima.longestTaskDescription = taskName.length() + "(by: )".length() + time.length();
        }
        Storage.saveTaskToFile(tasks);
        Default.showMessage(" Class type [D] \"" + tasks.get(tasks.size() - 1) + "\" has been added to the list!" + " " +
                "(" + tasks.size() + " tasks in total)");
    }
}
