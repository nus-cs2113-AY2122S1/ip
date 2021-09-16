package duke.taskOperations;

public class StringToRemoveFormat {
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

    public static String getStringEventDeadlineFormat(String taskIsDone, String taskDescription, String taskAtBy, String typeOfTask, String separator) {
        String textToRemove;
        if (taskIsDone.equals("X")) {
            textToRemove = typeOfTask + "-/-1-/-" + taskDescription + separator + taskAtBy;
        } else {
            textToRemove = typeOfTask + "-/-0-/-" + taskDescription + separator + taskAtBy;
        }
        return textToRemove;
    }

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
