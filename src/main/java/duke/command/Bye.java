package duke.command;

public class Bye extends Command {
    static final String NAME = "bye";
    private static final String USAGE = "";
    private static final boolean CONTINUE_EXECUTING = false;

    Bye(String argument) {
        super(NAME, USAGE, argument);
    }

    /**
     * Command is valid when there are no arguments
     */
    boolean isValid(){
        return argument.length() == 0;
    }
    /**
     * 'bye' does not execute any code. Only signals that it is time to end the program.
     *
     * @return false
     */
    boolean execute() {
        return CONTINUE_EXECUTING;
    }

}
