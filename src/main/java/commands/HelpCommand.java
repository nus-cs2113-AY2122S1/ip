package commands;

import processors.UI;

public class HelpCommand extends Command {
    public UI ui = new UI();

    /**
     * Function calls the help method in ui that displays the accepted commands from the user
     */
    public void execute() {
        String output = ui.help();
        System.out.println(output);
    }
}
