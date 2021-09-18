package exceptions;

public class TimeMissingException extends DukeException {

    private String taskType;

    public TimeMissingException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! The time of a %s cannot be empty", this.taskType);
    }
}
