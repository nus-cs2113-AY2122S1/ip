package duke.tasks;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of Task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
