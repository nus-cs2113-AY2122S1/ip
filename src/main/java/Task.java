public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getStatus() {
        return isDone ? "1" : "0";
    }

    public String getDescription() {
        return description;
    }

    public abstract String getDate();

    public abstract String getType();

    public void markAsDone() {
        isDone = true;
        System.out.print(Ui.INDENT + "Nice! I've marked this task as done: " +
                Ui.LINE_SEPARATOR_AND_INDENT + " ");
        System.out.println(this);
    }

    public void markAsDoneWithoutMessage() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
