package duke.command;

public class EchoCommand extends Command{
    public EchoCommand() {
        super("echo");
    }
    @Override
    public void printDone() {
        super.printDone();
        System.out.println("echoing!");
    }
}
