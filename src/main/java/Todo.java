public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public void printTask() {
        System.out.println("[T][" + getStatusIcon() + "] " + getDescription());
    }
}
