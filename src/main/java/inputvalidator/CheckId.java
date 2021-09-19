package inputvalidator;

import tasks.Task;

import java.util.ArrayList;

/**
 * Helps to check if a task id provided is valid.
 */

public class CheckId {
    /**
     * Checks if a task id is valid.
     *
     * @param id String ID of task to be checked.
     * @return a boolean value indicating if a task was valid.
     * @throws NumberFormatException If id was not a number or < 1 or > tasks.size()
     */
    public static boolean isValidTaskId(String id, ArrayList<Task> tasks) {
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
