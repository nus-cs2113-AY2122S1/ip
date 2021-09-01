public class Todo extends Task {

    public Todo(String description) {
        super(description);
        type = "T";
    }

    @Override
    public void printTaskNotif() {
        System.out.println("Got it. I've added this task:");
        System.out.println("[T] [ ] " + description);
    }
}
