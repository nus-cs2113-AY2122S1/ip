public class TimeMissingException extends CommandWrongFormatException {
    private String taskType;

    TimeMissingException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "     ☹ OOPS!!! The time of a " + this.taskType + " cannot be empty";
    }
}
