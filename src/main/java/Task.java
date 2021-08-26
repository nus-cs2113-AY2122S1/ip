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
}
