public class Task {
    protected String description;
    protected boolean isDone;
    public static int COUNT = 0;
    public static final int MAX = 100;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


    public void isDone() {
        this.isDone = true;
        System.out.println("\n____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.getDescription());
        System.out.println("____________________________________________________________");
    }
}