package duke.task;

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
        return itemIndex + ". ["  + this.getTaskIcon() +"]" + super.toString();
    }
}
