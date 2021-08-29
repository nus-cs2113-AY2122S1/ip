public class Task {
    protected String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public String getStatusSymbol() {
        return ("[" + (isDone ? "X" : " ") + "]");
    }

    public String getTaskSymbol() {
        return "[T]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public String toString() {
        return (getTaskSymbol() + getStatusSymbol() + " " + name);
    }
}
