public class Todo extends Task {

    protected String type = "[T]";

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
         return type + super.toString();
    }
}
