package duke.commands;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    /**
     * Command when user wants to end the application.
     * When executed, it will return a goodbye message.
     */

    public ByeCommand(){
    }

    public void execute(TaskList tasks, Ui ui){
        ui.showBye();
    };

    public boolean isExit(){
        return true;
    }
}
