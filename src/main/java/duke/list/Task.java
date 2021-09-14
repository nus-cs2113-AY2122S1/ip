package duke.list;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        setDescription(description);
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }


    public String getDescription() {
        return (this.description);
    }

    public String toString() {
        if (isDone) {
            return("[X] " + description);
        } else {
            return("[ ] " + description);
        }
    }

}
