package duke.Ui;

public class Parser {
    private static final int INDEX_COMMAND = 0;
    private static final int INDEX_TASK_INFO = 1;

    public String extractCommand(String line) {
        String[] inputs = line.split(" ", 2);
        return inputs[INDEX_COMMAND];
    }

    public String extractTaskInfo(String line) {
        String[] inputs = line.split(" ", 2);
        return inputs[INDEX_TASK_INFO];
    }

    /**
     * @param taskInfo contains the information of the task
     * @return taskComponents -> index 0: description, and index 1: dateTime
     */
    public String[] splitTaskComponents(String taskInfo) {
        String[] taskComponents;
        taskComponents = taskInfo.replace("/", "#/").split("#");

        for (int i = 0; i < taskComponents.length; i++) {
            String taskComponent = taskComponents[i].replaceAll("/by", "");
            taskComponent = taskComponent.replaceAll("/at", "");
            taskComponents[i] = taskComponent.trim();
        }

        return taskComponents;
    }
}
