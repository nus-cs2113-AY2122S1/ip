package task;

public class Todo extends Task
{

    public Todo(String description) {
        super(description);
    }

    public String fileForm() {
        return "T / " + this.getStatusIcon() + " / " + this.description + "\n";
    }

    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}