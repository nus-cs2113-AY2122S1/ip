public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public String listTask() {
        return "[T]" + super.listTask();
    }
}
