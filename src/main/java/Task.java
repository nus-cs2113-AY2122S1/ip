public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numTasks++;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); //marks task done with "X"
    }

    public void markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            System.out.println("Well done! I've marked this task as done. ヽ༼｡> ل͜ <｡༽ﾉ ");
        } else {
            System.out.println("Task has already been marked as done! Good job!ヽ༼◔ل͜◔༽ﾉ");
            System.out.println("Try marking another task as done!");
        }
    }

    public static int getNumTasks() {
        return numTasks;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
