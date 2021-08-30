//possible to make this class an inner private class in TodoList
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public void setDone() {
        this.isDone = true;
    }

    public boolean status() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }
}
