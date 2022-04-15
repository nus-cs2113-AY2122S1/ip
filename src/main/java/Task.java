public class Task {
    private String task;
    private String trail;
    private char type;
    private boolean done;

    /**
     * Constructor for the Task class.
     * @param task The given task.
     * @param trail The trail.
     * @param type The type.
     * @param done Status.
     */
    public Task(String task, String trail, char type, boolean done) {
        this.task = task;
        this.trail = trail;
        this.type = type;
        this.done = done;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }
}
