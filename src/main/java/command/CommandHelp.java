public class CommandHelp extends Command{


    public CommandHelp() {
    }

    @Override
    public void run() throws DukeException {
        Ui.printHelp();
    }
}
