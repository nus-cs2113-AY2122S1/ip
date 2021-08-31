public class Task {
    protected boolean isDone;
    protected String description;
    private int id;
    private static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

        totalTasks++;
        id = totalTasks;
    }

    public int getID() {
        return id;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskIcon() {
        if (Task.class.equals(this.getClass())) {return " ";}
        else {
            return this.getClass().getName().substring(0,1);
        }
    }

    public String getDescription(){
        return description;
    }

}
