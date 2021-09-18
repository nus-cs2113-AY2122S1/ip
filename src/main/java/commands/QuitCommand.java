package commands;


/**
 * A class to handle commands of exiting the program.
 */
public class QuitCommand extends UserCommand{
    @Override
    public String execute () {
        return "     Bye. Hope to see you again soon!\n";
    }
}
