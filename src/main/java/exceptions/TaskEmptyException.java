
package exceptions;

public class TaskEmptyException extends DukeException {
    private String taskType;

    public TaskEmptyException (String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format("     ☹ OOPS!!! The description of a %s cannot be empty", this.taskType);
    }


}
