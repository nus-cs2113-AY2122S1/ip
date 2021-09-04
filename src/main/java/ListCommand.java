public class ListCommand extends Command {

    @Override
    public void runCommand() throws DukeException {
        TaskManager.printList();
    }
}
