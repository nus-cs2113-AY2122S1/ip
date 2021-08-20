public class Task {
    protected String descr; //descr as an abbreviation for description
    protected boolean isDone;

    public Task(String descr) {
        this.descr = descr;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //icon "[X]" as done and "[ ]" as not done
    }

    public String getDescr() {
        return this.descr;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
