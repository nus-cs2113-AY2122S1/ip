public class ToDoCommand extends Command {

    public ToDoCommand() {
        super("todo");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) {
        ToDo t = new ToDo(data);
        unker.addTask(t);
        ui.printSection(ADDED_TASK_MESSAGE, "\t" + t);
    }
}
