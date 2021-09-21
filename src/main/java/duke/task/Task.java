package duke.task;

import duke.text.Text;

import java.time.LocalDate;

public abstract class Task extends Text {

    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public String getDate() {
        return "";
    }

    @Override
    public String toString() {
        if (isDone()) {
            return "[✓] " + taskName;
        }
        else {
            return "[ ] " + taskName;
        }
    }

    public String storageText() {
        if (isDone()) {
            return "|1|" + taskName;
        }
        else {
            return "|0|" + taskName;
        }
    }
}
