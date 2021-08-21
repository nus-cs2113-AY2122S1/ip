public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        if (!isDone) {
            isDone = true;
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done:");
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Warning! The task is already done:");
        }
        System.out.println("       [X] " + description);
        System.out.println("    ____________________________________________________________");
    }

    public void markAsNotDone() {
        if (isDone) {
            isDone = false;
            System.out.println("    ____________________________________________________________");
            System.out.println("     Ok! I've marked this task as not done:");
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Warning! The task is not done yet:");
        }
        System.out.println("       [ ] " + description);
        System.out.println("    ____________________________________________________________");
    }

    public String printTaskWithStatus() {
        return getStatusIcon() + " " + description;
    }
}
