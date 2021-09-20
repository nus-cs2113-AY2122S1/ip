package duke.taskOperations;

/**
 * This class takes in the task description and date+time and converts it to the duke.txt
 * storage format for each type of task.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class StringToRemoveFormat {
    /**
     * Format according to type of task, task done status and rawTaskDescription to
     * match with the duke.txt format.
     *
     * @param typeOfTask todo/event/deadline.
     * @param taskIsDone X marks that the task is done.
     * @param  rawTaskDescription raw task description includes the "at: " or "by: "
     * @return the formatted string in duke.txt to be removed.
     */
    public static String getStringToRemove(String typeOfTask, String taskIsDone, String rawTaskDescription) {
        String textToRemove;
        if (typeOfTask.equals("T") == true) {
            textToRemove = getStringToDoFormat(taskIsDone, rawTaskDescription);
        } else if (typeOfTask.equals("E") == true) {
            String taskDescription = rawTaskDescription.split("at: ")[0];
            int indexOfDescriptionEnd = taskDescription.length() - 2;
            taskDescription = taskDescription.substring(0, indexOfDescriptionEnd);

            String taskAt = rawTaskDescription.split("at: ")[1];
            int indexOfByEnd = taskAt.length() - 1;
            taskAt = taskAt.substring(0, indexOfByEnd);

            textToRemove = getStringEventDeadlineFormat(taskIsDone, taskDescription, taskAt, "e", " -/-/at");
        } else {
            String taskDescription = rawTaskDescription.split("by: ")[0];
            int indexOfDescriptionEnd = taskDescription.length() - 2;
            taskDescription = taskDescription.substring(0, indexOfDescriptionEnd);

            String taskBy = rawTaskDescription.split("by: ")[1];
            int indexOfByEnd = taskBy.length() - 1;
            taskBy = taskBy.substring(0, indexOfByEnd);

            textToRemove = getStringEventDeadlineFormat(taskIsDone, taskDescription, taskBy, "d", " -/-/by");
        }
        return textToRemove;
    }

    /**
     * Format for event and deadline in duke.txt for deletion
     *
     * @param typeOfTask event/deadline.
     * @param taskIsDone X marks that the task is done.
     * @param taskAtBy Only contains the "by" and "at" description without "at: " or "by: ".
     * @param separator Separator string "-/-/by" or "-/-/at"
     * @param taskDescription Only contains task description.
     * @return the formatted event/deadline string in duke.txt to be removed.
     */
    public static String getStringEventDeadlineFormat(String taskIsDone, String taskDescription, String taskAtBy, String typeOfTask, String separator) {
        String textToRemove;
        if (taskIsDone.equals("X")) {
            textToRemove = typeOfTask + "-/-1-/-" + taskDescription + separator + taskAtBy;
        } else {
            textToRemove = typeOfTask + "-/-0-/-" + taskDescription + separator + taskAtBy;
        }
        return textToRemove;
    }

    /**
     * Format for todo task to match in duke.txt for deletion.
     *
     * @param taskIsDone X marks that the task is done.
     * @param  rawTaskDescription raw task description includes the "at: " or "by: "
     * @return the formatted todo string in duke.txt to be removed.
     */
    public static String getStringToDoFormat(String taskIsDone, String rawTaskDescription) {
        String textToRemove;
        if (taskIsDone.equals("X")) {
            textToRemove = "t-/-1-/-" + rawTaskDescription;
        } else {
            textToRemove = "t-/-0-/-" + rawTaskDescription;
        }
        return textToRemove;
    }
}
