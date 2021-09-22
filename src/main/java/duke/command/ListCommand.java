package duke.command;

public class ListCommand extends Command{
    public ListCommand() {
        super("list");
    }
    @Override
    public void printDone() {
        super.printDone();
        System.out.println("listing tasks!");
    }
}
