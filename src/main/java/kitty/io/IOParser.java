package kitty.io;

import java.time.LocalDate;

/**
 * The class <code>IOParser</code> takes in raw data of String type and extracts relevant information from the data file.
 */
public class IOParser {

    /**
     * Returns the task type, between Todo, Deadline and Event from the data file.
     * @param rawData rawData is the String containing the task details from teh data file.
     * @return Returns the task type from rawData.
     */
    public static String getDataType(String rawData) {
        return rawData.substring(0, rawData.indexOf("|"));
    }

    /**
     * Returns whether the task has been marked as completed or not in String type.
     * @param rawData rawData is the String containing the task details from teh data file.
     * @return Returns the task status from rawData in String type.
     */
    public static String getDataStatus(String rawData) {
        return rawData.substring(rawData.indexOf("|") + 1, rawData.indexOf("|") + 2);
    }

    /**
     * Returns the task description and task date (if applicable) from the data file.
     * @param rawData rawData is the String containing the task details from teh data file.
     * @return Returns the task details from rawData.
     */
    public static String getDataTask(String rawData) {
        return rawData.substring(rawData.indexOf("|") + 3);
    }

    /**
     * Returns whether the task has been marked as completed or not as a boolean value.
     * @param status status is the String containing the status of the task.
     * @return Returns true if the task has been marked as completed. False otherwise.
     */
    public static boolean isTaskDone(String status) {
        return status.equals("1");
    }

    /**
     * Returns the task description from the String task.
     * @param task task is the task details, including the task description and task date (if applicable).
     * @return Returns the task description as a String type.
     */
    public static String getTaskName(String task) {
        return task.substring(0, task.indexOf("|"));
    }

    /**
     * Returns the task date from the String task.
     * @param task task is the task details, including the task description and task date (if applicable).
     * @return Returns the task date as a String type.
     */
    public static LocalDate getTaskDate(String task) {
        String dateAsString = task.substring(task.indexOf("|") + 1);
        return LocalDate.parse(dateAsString);
    }
}
