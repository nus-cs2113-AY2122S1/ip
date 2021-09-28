public class ListCommand extends Command {
    @Override
    public void execute(TaskList tl) {
        tl.printList();
    }

    public ListCommand() {
    }
}
