package duke.command;

public class Bye extends Command {
    static final String NAME = "bye";
    private static final String USAGE = "";

    Bye(String argument) {
        super(NAME, USAGE, argument);
    }

    boolean isValid(){
        return argument.length() == 0;
    }

    boolean execute() {
        return false;
    }

}
