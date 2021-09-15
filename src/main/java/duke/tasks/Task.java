package duke.tasks;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public abstract String getType();
    public abstract String getDueDate();


    public void markDone(){
        if (!this.isDone) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
    }

    public String toString() {
        return  (isDone ? "[X] " : "[ ] ") + description;
    }
}
