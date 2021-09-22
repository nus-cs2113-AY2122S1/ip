import java.util.ArrayList;

/**
 * Contains all task list related methods.
 * Deals the user's input commands.
 */
public class TaskList {

    /**
     * Prints a task list.
     *
     * @param tasks Array list of tasks.
     */
    public static void printTaskList(ArrayList<Task> tasks) {
        String message = " Here are the tasks in your list:\n";
        int taskIndex = 1;
        for (Task task : tasks) {
            message = message + " " + taskIndex + "." + task + "\n";
            taskIndex++;
        }
        Ui.printMessage(message);
    }

    /**
     * Marks a task from a task list as done based on the task number entered.
     *
     * @param tasks Array list of tasks.
     * @param taskNumber Task number of the task to be marked as done.
     */
    public static void markAsDone(ArrayList<Task> tasks, int taskNumber) {
        try{
            tasks.get(taskNumber).setDone();
            String message = " Nice! I've marked this task as done:\n"
                    + "  " + tasks.get(taskNumber) + "\n";
            Ui.printMessage(message);
        } catch (IndexOutOfBoundsException e) {
            DukeException.printIndexError();
        }
    }

    /**
     * Deletes a task from a task list based on the task number entered.
     *
     * @param tasks Array list of tasks.
     * @param taskNumber Task number of the task to be deleted.
     */
    public static void deleteTask(ArrayList<Task> tasks, int taskNumber) {
        try{
            Task deletedTask = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            String message = " Noted. I've removed this task:\n"
                    + "  " + deletedTask + "\n"
                    + " Now you have " + tasks.size() +" tasks in the list.\n";
            Ui.printMessage(message);
        } catch (IndexOutOfBoundsException e) {
            DukeException.printIndexError();
        }
    }

    /**
     * Creates a new task based on the user's input.
     *
     * @param parsedUserInput Parsed user input.
     * @return New task.
     */
    public static Task createTask(String[] parsedUserInput) {
        String category = parsedUserInput[0];
        String description = parsedUserInput[1];
        String details = parsedUserInput[2];
        Task newTask = new Task("initialize");
        switch (category) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            newTask = new Deadline(description, details);
            break;
        case "event":
            newTask = new Event(description, details);
            break;
        default:
            break;
        }
        return newTask;
    }

    /**
     * Adds a new task to a task list.
     *
     * @param tasks Array list of tasks.
     * @param newTask Task to be added to the task list.
     */
    public static void addTask(ArrayList<Task> tasks, Task newTask) {
        tasks.add(newTask);
        String message = " Got it. I've added this task:\n"
                + "  " + newTask + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n";
        Ui.printMessage(message);
    }

    /**
     * Checks whether a keyword is in a task's string format.
     *
     * @param wordsInTask List of words of a task's string format.
     * @param keyword Word that is used to check.
     * @return Boolean representing whether a keyword is in a task's string format.
     */
    public static boolean keywordChecker(String[] wordsInTask, String keyword) {
        for (String word : wordsInTask) {
            if (word.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints a matching tasks list.
     *
     * @param tasks Array list of tasks.
     */
    public static void printMatchingTasksList(ArrayList<Task> tasks) {
        String message = " Here are the matching tasks in your list:\n";
        int taskIndex = 1;
        for (Task task : tasks) {
            message = message + " " + taskIndex + "." + task + "\n";
            taskIndex++;
        }
        Ui.printMessage(message);
    }

    /**
     * Finds all tasks with descriptions that contain the keyword.
     *
     * @param tasks Array list of tasks.
     * @param keyword Word that is used to find matching tasks.
     */
    public static void findTask(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            String[] wordsInTask = task.getDescription().split(" ");
            boolean keywordInTask = keywordChecker(wordsInTask, keyword);
            if (keywordInTask) {
                matchingTasks.add(task);
            }
        }
        printMatchingTasksList(matchingTasks);
    }

}
