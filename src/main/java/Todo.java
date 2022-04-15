public class Todo extends Task{
    private final String TODO_TASK_SIGN = "[T]";
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        String str = TODO_TASK_SIGN + super.toString();
        return str;
    }
}
