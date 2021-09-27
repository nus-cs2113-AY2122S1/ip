package duke.commands;
import duke.Ui;

public class ByeCommand extends Command{

    private Ui ui;

    /**
     * Constructor when user wants to close the application.
     * @param ui Handles interaction with the user.
     */
    public ByeCommand(Ui ui){
        this.ui = ui;
    }

    public boolean exit(){
        ui.bye();
        return false;
    }

}
