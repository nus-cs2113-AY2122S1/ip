public class ToDo extends Task {
    protected String description;
    private static final String TODO_CHECKBOX = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String printTaskInfo() {
        return TODO_CHECKBOX + super.printTaskInfo();
    }
}
