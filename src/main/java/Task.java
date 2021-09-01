public class Task {

    protected String description;

    public Task(String description) {
        this.description = description;
    }

    public String toString() {
        return "[ ] " + description;
    }

    public String doneToString() {
        return "[X] " + description;
    }
}