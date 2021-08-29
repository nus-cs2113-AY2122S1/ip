public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String taskLabel;

    TaskType(String taskLabel) {
        this.taskLabel = taskLabel;
    }

    public String getTaskLabel() {
        return taskLabel;
    }
}
