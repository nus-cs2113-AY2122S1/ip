
package tasks;



/**
 * <h1>The <b>Todo</b> type {@link Task} from users</h1>
 */
public class Todo extends Task {

    public Todo (String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String save() {
        return "T | " + (super.hasCompleted()? "1 | " : "0 | ") + this.getTaskName() + "\n";
    }
}
