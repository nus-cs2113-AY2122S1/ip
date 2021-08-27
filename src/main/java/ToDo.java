public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskDescriptionWithStatus() {
        return "[T]" + super.getTaskDescriptionWithStatus();
    }
}
