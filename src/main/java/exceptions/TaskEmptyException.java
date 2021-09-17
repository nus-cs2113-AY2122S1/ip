
package exceptions;

public class TaskEmptyException extends DukeException {
    private String taskType;

    public TaskEmptyException (String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
             return "     ☹ OOPS!!! The description of a " + this.taskType + " cannot be empty\n";
    }


}
