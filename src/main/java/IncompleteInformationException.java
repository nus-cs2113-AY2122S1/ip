public class IncompleteInformationException extends Exception{
    private String taskType;
    private String description;

    public IncompleteInformationException(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }
}
