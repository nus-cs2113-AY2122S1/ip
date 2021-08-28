public class Todo extends Task {

    public Todo(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
