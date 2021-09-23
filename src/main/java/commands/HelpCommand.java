package commands;

import processors.Ui;

public class HelpCommand extends Command {
    public Ui ui = new Ui();

    /**
     * Function calls the help method in ui that displays the accepted commands from the user
     */
    public void execute() {
        String output = ui.help();
        System.out.println(output);
    }
}
