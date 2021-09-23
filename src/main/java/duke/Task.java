package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected Type type;

    public Task(String description, int index) {
        this.description = description;
        this.index = index;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void printMarkAsDone() {
        markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + getStatusIcon() + "] " + description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void printTask(){
        System.out.println(index + ". [" + type + "] [" + getStatusIcon() + "] " + description);
    }

    public void printAdded(int count) {
        System.out.println("Got it. I've added this task:");
        printTask();
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public void printTaskDelete(int count){
        System.out.println("Noted. I've removed this task:");
        System.out.println("[" + type + "] [" + getStatusIcon() + "] " + description);
        System.out.println("Now you have " + count + " tasks in the list.");
    }
    public String fileFormat() {
        return (type + " | " + getStatusIcon() + " | " + description);
    }
}