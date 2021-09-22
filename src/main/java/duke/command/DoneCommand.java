package duke.command;

public class DoneCommand extends Command{
    public DoneCommand() {
        super("done");
    }
    @Override
    public void printDone() {
        super.printDone();
        System.out.println("marking as done!");
    }
}
