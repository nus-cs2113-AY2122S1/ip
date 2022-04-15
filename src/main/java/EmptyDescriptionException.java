public class EmptyDescriptionException extends Exception {
    private String taskType;
    public EmptyDescriptionException(String taskType) {
        this.taskType = taskType;
    }

    public void printExceptionMessage() {
        System.out.println("OOPS!!! The description of a " +
                this.taskType + " cannot be empty.");
    }
}
