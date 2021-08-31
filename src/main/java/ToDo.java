public class ToDo extends Task {

    private static final String TODO_ITEM = "[T] ";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TODO_ITEM + super.toString();
    }

}
