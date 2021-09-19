package Commands;

public class ExitCommand extends Command {

    public ExitCommand(){
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }


}
