package shikabot.task;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getType() {
        return "T";
    }

    public String getAtBy() {
        return "null";
    }

}
