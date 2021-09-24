package tasks;

import java.time.LocalDateTime;

/**
 * <h1>A class to represent tasks from users</h1>
 */
abstract public class Task {
    private String taskName;
    private boolean isCompleted;


    public Task (String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    private String outputTaskStatus () {
        return String.format("[%s]", isCompleted ? "X":" ");
    }

    public String getTaskStatus() {
        return isCompleted? "yes" : "no";
    }

    public boolean hasCompleted() {
        return this.isCompleted;
    }

    public String getTaskName() {
        return this.taskName;
    }


    @Override
    public String toString () {
        return this.outputTaskStatus() + this.taskName;
    }

    public void setCompleted () {
        this.isCompleted = true;
    }

    abstract public String save();
    public abstract String getTaskType();

    public boolean searchKeyword(String keyword) {
        return taskName.contains(keyword);
    }

    public abstract LocalDateTime getTime();

}