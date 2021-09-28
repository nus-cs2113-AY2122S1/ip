package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

/**
 * Class that contains the task list and operations to modify the task list.
 */
public class TaskList {
    protected static ArrayList<String> stringList = new ArrayList<>();
    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static ArrayList<String> formattedDueDateList = new ArrayList<>();
    protected static ArrayList<String> dueDateList = new ArrayList<>();

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Returns number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int getListCount() {
        return taskList.size();
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task Task to be added to the taskList.
     */
    public void addToTaskList(Task task) {
        taskList.add(task);
    }

    /**
     * Adds the task description in the form of a string to the stringList.
     *
     * @param string Task description to be added to the stringList.
     */
    public void addToStringList(String string) {
        stringList.add(string);
    }

    /**
     * Adds the due date and time in the formatted format to the formattedDueDateList.
     *
     * @param formattedDueDate Formatted due date and time to be added to the formattedDueDateList.
     */
    public void addToFormattedDueDateList(String formattedDueDate) {
        formattedDueDateList.add(formattedDueDate);
    }

    /**
     * Adds the due date and time in the unformatted format to the dueDateList.
     *
     * @param dueDate Unformatted due date and time to be added to the dueDateList.
     */
    public void addToDueDateList(String dueDate) {
        dueDateList.add(dueDate);
    }

    /**
     * Returns the task of the specific index.
     *
     * @param taskIndex Index of the task in taskList.
     * @return task of the specific index.
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    /**
     * Returns the list containing all of the task descriptions.
     *
     * @return stringList, the list containing all of the task description.
     */
    public static ArrayList<String> getStringList() {
        return stringList;
    }

    /**
     * Returns the list containing all of the unformatted due date and time.
     *
     * @return dueDateList, the list containing all of the unformatted due date and time.
     */
    public static ArrayList<String> getDueDateList() {
        return dueDateList;
    }

    /**
     * Mark the task of a specific index as completed.
     *
     * @param taskIndex Specific index of the task to be marked as completed.
     */
    public void markAsCompleted(int taskIndex) {
        taskList.get(taskIndex).markAsDone();
    }

    /**
     * Makes sense of the user command input, store the information accordingly in the different lists
     * and returns a Todo task.
     *
     * @param line User command input.
     * @return A task with the class Todo.
     */
    public static Todo getToDoTask(String line) {
        char taskType = line.toUpperCase().charAt(0);
        String taskDisplay = line.substring(5);

        stringList.add(taskList.size(), taskDisplay);
        dueDateList.add(taskList.size(), null);
        formattedDueDateList.add(taskList.size(), null);
        Todo todoTask = new Todo(taskDisplay, taskType);
        taskList.add(taskList.size(), todoTask);
        return todoTask;
    }

    /**
     * Makes sense of the user command input, store the information accordingly in the different lists
     * and returns a Deadline task.
     *
     * @param line User command input.
     * @return A task with the class Deadline.
     */
    public static Deadline getDeadlineTask(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(9, startingIndex);
        char taskType = line.toUpperCase().charAt(0);

        stringList.add(taskList.size(), taskDisplay);
        dueDateList.add(taskList.size(), line.substring(startingIndex + 1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime formatDateTime = LocalDateTime.parse(line.substring(startingIndex + 1), formatter);
        String formattedDateTime = formatDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        formattedDueDateList.add(taskList.size(), formattedDateTime);
        String doBy = "(" + formattedDateTime + ")";
        Deadline deadlineTask = new Deadline(taskDisplay, taskType, doBy);
        taskList.add(taskList.size(), deadlineTask);
        return deadlineTask;
    }

    /**
     * Makes sense of the user command input, store the information accordingly in the different lists
     * and returns a Event task.
     *
     * @param line User command input.
     * @return A task with the class Event.
     */
    public static Event getEventTask(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(6, startingIndex);
        char taskType = line.toUpperCase().charAt(0);

        stringList.add(taskList.size(), taskDisplay);
        dueDateList.add(taskList.size(), line.substring(startingIndex + 1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime formatDateTime = LocalDateTime.parse(line.substring(startingIndex + 1), formatter);
        String formattedDateTime = formatDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        formattedDueDateList.add(taskList.size(), formattedDateTime);
        String doBy = "(" + formattedDateTime + ")";
        Event eventTask = new Event(taskDisplay, taskType, doBy);
        taskList.add(taskList.size(), eventTask);
        return eventTask;
    }

    /**
     * Prints out the added task and total number of tasks in the list.
     *
     * @param task Specific task to be added.
     */
    public static void printTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        Ui.printLine();
    }

    /**
     * Prints out all of the task in taskList in order.
     */
    public static void printTaskList() {
        int taskCount = 1;
        for (Task task : taskList) {
            System.out.println(taskCount + "." + task);
            taskCount++;
        }
        Ui.printLine();
    }

    /**
     * Makes sense of the user command input and prints out the task after the user completes it.
     *
     * @param line User command input.
     */
    public static void printCompletedTask(String line) {
        String taskNumber = line.substring(5);
        int taskListElement = Integer.parseInt(taskNumber) - 1;
        taskList.get(taskListElement).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskList.get(taskListElement).getDescription());
        Ui.printLine();
    }

    /**
     * Prints out the tasks that matches with the keyword.
     *
     * @param keyword Specific keyword to search for in the taskList.
     */
    public void printMatchingTask(String keyword) {
        int matchingTaskCount = 0;
        for (int i = 0; i < getListCount(); i++) {
            if (stringList.get(i).contains(keyword)) {
                matchingTaskCount++;
                System.out.println(matchingTaskCount + "." + taskList.get(i));
            }
        }
        Ui.printLine();
        if (matchingTaskCount == 0) {
            System.out.println("No matching task found.");
            Ui.printLine();
        }
    }

    /**
     * Makes sense of the user command input and deletes the task according to the user input.
     *
     * @param line User command input.
     */
    public static void deleteTask(String line) {
        int taskIndex = Integer.parseInt(line.substring(7)) - 1;
        Task task = taskList.get(taskIndex);
        Ui.printLine();
        taskList.remove(taskIndex);
        dueDateList.remove(taskIndex);
        formattedDueDateList.remove(taskIndex);
        stringList.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        Ui.printLine();
    }
}
