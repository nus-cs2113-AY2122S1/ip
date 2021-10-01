package duke.task;

public class Task {
    public String name;
    public boolean isDone;
    public String taskType;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return getStatus() + this.name;
    }

    public String getType(){
        return taskType;
    }

    public String getName() {
        return this.name;
    }
}