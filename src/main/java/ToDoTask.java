public class ToDoTask extends Task{

    public ToDoTask(String task) {
        super(task);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}
