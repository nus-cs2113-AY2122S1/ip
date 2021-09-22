package shima.command;

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
    public static String name = "";
    public static final String EMPTY_PERIOD_MSG = "Sorry, the date and period for the task \"" + name + "\" is missing!";
    public static final String EMPTY_DEADLINE_MSG = "Sorry, the deadline for the task \"" + name + "\" is missing!";
    public static final String EMPTY_TASK_MSG = "Sorry, the task is empty! I don't know how to record it :(";
    public static final String SLASH_MISSING_MSG = "Sorry, fail to create an Event, the time specific character '/' is missing";
    public static final String DASH_MISSING_MSG = "Sorry, fail to create an Event, the period specific character '-' is missing";

    /**
     * Adds a to-do to the list if there is no error
     *
     * @param tasks   The array list that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @throws IOException Throws this exception if there is error occurs during saving
     */
    public static void createToDo(ArrayList<Task> tasks, String command, String[] words) throws IOException {
        if (isCorrectToDo(words)) {
            tasks.add(new ToDo(command.replace(words[0], "").trim()));
            if (Shima.longestTaskDescription < command.replace(words[0], "").trim().length()) {
                Shima.longestTaskDescription = command.replace(words[0], "").trim().length();
            }
            Storage.saveTaskToFile(tasks);
            Task currentTask = tasks.get(tasks.size() - 1);
            Default.showMessage(" Class type [" + currentTask.getClassType() + "] \"" + currentTask + "\" has been added to the list!" +
                    "(" + tasks.size() + " tasks in total)");
        }
    }

    /**
     * Adds an event to the list if there is no error
     *
     * @param tasks   The array list that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @throws IOException Throws this exception if there is error occurs during saving
     */
    public static void createEvent(ArrayList<Task> tasks, String command, String[] words) throws IOException {
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        String taskName = command.split("/", 2)[0].trim();
        if (isCorrectEvent(command, words, time, taskName)) {
            tasks.add(new Event(taskName, time));
            if (Shima.longestTaskDescription < taskName.length() + time.length()) {
                Shima.longestTaskDescription = taskName.length() + "(at: )".length() + time.length();
            }
            Storage.saveTaskToFile(tasks);
            Task currentTask = tasks.get(tasks.size() - 1);
            Default.showMessage(" Class type [" + currentTask.getClassType() + "] \"" + currentTask + "\" has been added to the list!" +
                    "(" + tasks.size() + " tasks in total)");
        }
    }

    /**
     * Adds a deadline to the list if there is no error
     *
     * @param tasks   The array list that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     * @throws IOException Throws this exception if there is error occurs during saving
     */
    public static void createDeadline(ArrayList<Task> tasks, String command, String[] words) throws IOException {
        command = command.replaceFirst(words[0], "").trim();
        String time = command.substring(command.indexOf('/') + 1).trim();
        String taskName = command.split("/", 2)[0].trim();
        if (isCorrectDeadline(command, words, time, taskName)) {
            tasks.add(new Deadline(taskName, time));
            if (Shima.longestTaskDescription < taskName.length() + time.length()) {
                Shima.longestTaskDescription = taskName.length() + "(by: )".length() + time.length();
            }
            Storage.saveTaskToFile(tasks);
            Task currentTask = tasks.get(tasks.size() - 1);
            Default.showMessage(" Class type [" + currentTask.getClassType() + "] \"" + currentTask + "\" has been added to the list!" +
                    "(" + tasks.size() + " tasks in total)");
        }
    }

    /**
     * Checks the syntax for the command to create a new task, and add to the to-do list if the syntax is correct
     *
     * @param words The array of words that compose the input command
     * @return Returns true if an instance of the subclass is created and successfully stored in the to-do list
     */
    private static boolean isCorrectToDo(String[] words) {
        if (words.length == 1) {
            Default.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Event' instance, and add to the to-do list if the syntax is correct
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if an instance of the subclass Event is created and successfully stored in the to-do list
     */
    private static boolean isCorrectEvent(String command, String[] words, String time, String taskName) {
        if (time.toLowerCase().startsWith("at")) {
            time = time.replaceFirst("(?i)at", "").trim();
        }
        if (words.length == 1 || taskName.isEmpty()) {
            Default.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            name = taskName;
            Default.showMessage(EMPTY_PERIOD_MSG);
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage(SLASH_MISSING_MSG);
            return false;
        }
        if (!command.contains("-")) {
            Default.showMessage(DASH_MISSING_MSG);
            return false;
        }
        return true;
    }

    /**
     * Checks the syntax for the command to create an 'Deadline' instance, and add to the to-do list if the syntax is correct
     *
     * @param command The input command typed by the user
     * @param words   The array of words that compose the input command
     * @return Returns true if the subclass Deadline is created and successfully stored in the to-do list
     */
    private static boolean isCorrectDeadline(String command, String[] words, String time, String taskName) {
        if (time.toLowerCase().startsWith("by")) {
            time = time.replaceFirst("(?i)by", "").trim();
        }
        if (words.length == 1 || taskName.isEmpty()) {
            Default.showMessage(EMPTY_TASK_MSG);
            return false;
        }
        if (time.isEmpty()) {
            name = taskName;
            Default.showMessage(EMPTY_DEADLINE_MSG);
            return false;
        }
        if (!command.contains("/")) {
            Default.showMessage(SLASH_MISSING_MSG);
            return false;
        }
        return true;
    }
}
