public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return itemIndex + ". [T]" + super.toString();
    }
}
