package Commands;

import Duke.TaskList;

import javax.swing.text.TabableView;

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
