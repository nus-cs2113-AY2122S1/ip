public class Task {
    private static int count = 0;
    private boolean isDone;
    private String description;

    public Task(String description) {
        setDescription(description);
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static int getCount() {
        return count;
    }

    public static int incrementCount() {
        return count++;
    }

    private char getStatusIcon() {
        return (isDone ? 'X' : ' '); // mark done task with X
    }

    public String getTaskMessage() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription() + '\n';
    }
}
