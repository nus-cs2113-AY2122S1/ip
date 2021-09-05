package InputHandler.exception;

public class TimeMissingException extends DukeException {
    private String taskType;

    public TimeMissingException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "     ☹ OOPS!!! The time of a " + this.taskType + " cannot be empty";
    }
}
