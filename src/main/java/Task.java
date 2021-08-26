public class Task {
    private Boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getName() {
        return this.name;
    }

    public Boolean isDone() {
        return this.isDone;
    }
}
