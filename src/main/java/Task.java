public class Task {
    protected String description;
    protected boolean isDone;

    private static int numOfTasks = 0;
    private int id;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.id = numOfTasks;
        Task.numOfTasks++;
    }

    public static int getNumOfTasks() {
        return Task.numOfTasks;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    
    public void setDone() {
        this.isDone = true;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            @SuppressWarnings("unchecked")
            Task t = (Task) obj;
            return this.description.equals(t.description) && this.isDone == (t.isDone);
        }
        return false;
    }

    @Override
    public String toString() {
        return (this.id + 1) + ".[" + this.getStatusIcon() + "] " 
            + this.description;
    }
}

