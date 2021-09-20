package Commands;

import Tasks.TaskList;

/**
 * Stops execution of the program
 */
public class ExitCommand extends Command {

    public ExitCommand(){
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public String execute(TaskList taskList) {
        return "The program will now save and exit\n";
    }

}
