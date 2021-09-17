package tasks;

import java.util.ArrayList;

public class TaskList {
    private static final String todoError = "☹ OOPS!!! The description of a todo cannot be empty.";
    private static final String addTaskMessage = "Got it. I've added this task:\n%1$s\nNow you have"
            + " %2$o tasks in the list.";
    private static final String byNotProvided = "I could not find '/by' in your command!";
    private static final String deadlineTitleNotProvided = "I could not find the title of your deadline!";
    private static final String atNotProvided = "I could not find '/at' in your command!";
    private static final String eventTitleNotProvided = "I could not find the title of your event!";
    private static final String removeTaskMessage = "Noted. I've removed this task:\n%1$s\nNow you have %2$o tasks"
            + " in the list.";
    private static final String taskDoesNotExist = "The task ID does not exist!";
    private static final String taskAlreadyCompleted = "You have already completed the task [%1$s]!";
    private static final String taskMarkedDone = "Nice! I've marked this task as done:\n%1$s";
    private static final String noTasks = "You have no tasks in your list!";
    private static final String hereAreYourTasks = "Here are the tasks in your list:\n";

    public static ArrayList<Task> tasks;

    /**
     * Adds a Todo to the Task list
     *
     * @param description Task description entered by user.
     * @return Success/error message to be printed in console.
     */
    public static String addTodo(String description) {
        if (description.equals("")) {
            return todoError;
        } else {
            Todo todo = new Todo(description);
            tasks.add(todo);
            return String.format(addTaskMessage, todo, tasks.size());
        }
    }

    /**
     * Adds a Deadline to the Task list
     *
     * @param description Task description entered by user.
     * @param dateline Task deadline entered by user.
     * @return Success/error message to be printed in console.
     */
    public static String addDeadline(String description, String dateline) {
        // Handle case it user did not provide date
        if (dateline == null) {
            return byNotProvided;
        } else if (description.equals("")) {
            return deadlineTitleNotProvided;
        } else {
            Deadline deadline = new Deadline(description, dateline);
            tasks.add(deadline);
            return String.format(addTaskMessage, deadline, tasks.size());
        }
    }

    /**
     * Adds an Event to the Task list
     *
     * @param eventTitle Event title entered by user.
     * @param date Event date entered by user.
     * @return Success/error message to be printed in console.
     */
    public static String addEvent(String eventTitle, String date) {
        // Handle case it user did not provide date
        if (date == null) {
            return atNotProvided;
        } else if (eventTitle.equals("")) {
            return eventTitleNotProvided;
        } else {
            Event event = new Event(eventTitle, date);
            tasks.add(event);
            return String.format(addTaskMessage, event, tasks.size());
        }
    }

    /**
     * Removes a Task from the Task list
     *
     * @param id Task id entered by user.
     * @return Success/error message to be printed in console.
     */
    public static String deleteTask(String id) {
        if (!isValidTaskId(id)) { // Invalid task id
            return taskDoesNotExist;
        }

        int taskId = Integer.parseInt(id) - 1;
        Task removedTask = tasks.get(taskId);
        tasks.remove(taskId);
        return String.format(removeTaskMessage, removedTask, tasks.size());
    }

    /**
     * Concatenates all the tasks into a string.
     *
     * @return Concatenated string of tasks.
     */
    public static String listTasks() {
        String taskString = "";
        // Checks if tasks exists
        if (tasks.size() == 0) {
            taskString += noTasks;
        } else {
            taskString += hereAreYourTasks;
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            taskString += (i + 1) + "." + currentTask.toString() + "\n";
        }
        return taskString;
    }

    /**
     * Marks a task as done.
     *
     * @param id String ID of task to be marked as completed.
     * @return Success/error message to be printed in console.
     */
    public static String markDone(String id) {
        if (!isValidTaskId(id)) { // Task id is invalid
            return taskDoesNotExist;
        }
        int taskId = Integer.parseInt(id) - 1; // -1 as array index starts from 0

        // Checks if task has been completed
        Task currentTask = tasks.get(taskId);
        if (currentTask.isDone()) {
            return String.format(taskAlreadyCompleted, currentTask.getDescription());
        } else {
            currentTask.markAsDone(); // Mark current task as done
            return String.format(taskMarkedDone, currentTask);
        }
    }

    /**
     * Checks if a task id is valid.
     *
     * @param id String ID of task to be checked.
     * @return a boolean value indicating if a task was valid.
     * @throws NumberFormatException If id was not a number or < 1 or > tasks.size()
     */
    private static boolean isValidTaskId(String id) {
        int taskId;
        try {
            taskId = Integer.parseInt(id);
            if (taskId < 1 || taskId > tasks.size()) { //invalid task ID
                throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
