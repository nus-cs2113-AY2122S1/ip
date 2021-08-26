public class Task {
    final private String name;
    private Boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public String getName() {
        return name;
    }

    public void markDone() {
        status = true;
    }

    public Boolean isDone() {
        return status;
    }
}
