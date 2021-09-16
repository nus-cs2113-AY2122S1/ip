public class Task {
    protected String item;
    protected boolean complete;
    public Task(String description) {
        this.item = description;
        this.complete = false;
    }
    public void markComplete() {
        this.complete = true;
    }
    public String getStatus() {
        if (complete) {
            return "X";
        }
        return " ";
    }
    public String getDescription() {
        return item;
    }

    public String getOriginalInput() {
        return item;
    }

    public String getType() {
        return " ";
    }

    public String toString() {
        if (complete) {
            return "[X] " + item;
        }
        return "[ ] " + item;
    }

}
