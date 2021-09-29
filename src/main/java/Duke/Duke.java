package Duke;

/**
 * Runs Duke on startup
 * If no save file is detected, a new file is created upon first boot.
 *
 * Prints intro, then proceeds to run the application.
 */

public class Duke {

    public static void main(String[] args) {

        Storage.loadData();
        Ui ui = new Ui();
        ui.printIntro();
        Parser.parseCommand();

    }
}
