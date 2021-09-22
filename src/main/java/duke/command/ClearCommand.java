package duke.command;

public class ClearCommand extends Command{
    public ClearCommand() {
        super("clear");
    }
    @Override
    public void printDone() {
        super.printDone();
        System.out.println("clearing list!");
    }
}
