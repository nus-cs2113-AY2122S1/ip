public class Task {
    private String name;
    private boolean isDone;

    public Task(String description) {
        this.name = description;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns string in list entry format.
     * eg. [ ] task1
     *     [x] task2
     *
     * @return string The formatted string
     */
    public String getListEntryString() {
        return String.format("[%c] %s",(isDone() ? 'x' : ' '),name);
    }
}
