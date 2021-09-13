package duke;

import java.util.Locale;

public class Task {
    private String description;
    private boolean status;

    public static final int TASK_INDEX = 5;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public void setDone() {
        this.status = true;
    }

    public boolean isDone() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString(){
        String done = this.isDone()? "X" : " ";
        String taskType = this.getClass().getName().substring(5,6).toUpperCase();
        return String.format("[%s][%s] %s",taskType, done, this.getDescription());
    }
}
