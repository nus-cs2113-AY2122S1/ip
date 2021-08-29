public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public void printType() {
        System.out.println("[T]");
    }
}
