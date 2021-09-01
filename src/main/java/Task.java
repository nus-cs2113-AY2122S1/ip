public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription(){
        return this.description;
    }

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
