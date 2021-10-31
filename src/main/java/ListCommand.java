public class ListCommand extends Command {
    /**
     * Calls printList() method from TaskList object to print
     * out tasks list.
     *
     * @param tl TaskList object which stores all user-created tasks.
     */
    @Override
    public void execute(TaskList tl) {
        tl.printList();
    }

    public ListCommand() {
    }
}
