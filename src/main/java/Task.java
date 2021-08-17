public class Task {

    private String name;
    private int id;
    private boolean isDone;

    public static int count = 0;

    public Task(String name, int id) {
        this.name = name;
        this.id = id;
        this.isDone = false;
    }

    public void markAsDone() { this.isDone = true; }

    public String status() { return (isDone ? "X" : " "); }

    public void printTask() {
        System.out.println("\t" + (id + 1) + ".[" + status() + "] " + name);
    }
}
