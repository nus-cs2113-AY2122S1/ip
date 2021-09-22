package duke.command;

public class DeleteCommand extends Command{
    public DeleteCommand() {
        super("delete");
    }
    @Override
    public void printDone() {
        super.printDone();
        System.out.println("deleting tasks!");
    }
}
