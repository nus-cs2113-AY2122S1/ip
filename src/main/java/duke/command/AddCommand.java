package duke.command;

public class AddCommand extends Command{
    public AddCommand() {
        super("add");
    }

    @Override
    public void printDone() {
        super.printDone();
        System.out.println("adding");
    }
}
