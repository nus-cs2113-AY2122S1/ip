package tasks;

import java.time.LocalDateTime;

/**
 * <h1>The <b>Todo</b> type {@link Task} from users</h1>
 */
public class Todo extends Task {

    public Todo (String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String save() {
        return String.format("T | %b | %s\n", super.hasCompleted(), this.getTaskName());
    }

    @Override
    public String getTaskType () {
        return "todo";
    }

    public LocalDateTime getTime() {
        return null;
    }
}
