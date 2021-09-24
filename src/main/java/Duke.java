import java.io.IOException;

/**
 * The main class of the whole program
 */
public class Duke {

    public static final TaskList taskList = new TaskList();

    /**
     * Starts Duke
     * print welcome message and read from DukeData/data.txt
     * wait for standard input into Duke and execute command thereafter
     */
    public Duke() {
        Ui.welcomeMessage();
        try {
            Storage.initializeStorage();

        } catch (IOException e) {
            Ui.printlnTab(Storage.dataPath + " not found or cannot be created!");
            Ui.printDivider();
            return;
        }
        //TODO: Further testing to see if this try block is necessary
//        try {
        Parser.parseAndExecuteCommand();
//        } catch (NoSuchElementException e) {
//            Ui.printlnTab("Bye. Hope to see you again soon!");
//            Ui.printDivider();
//        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}
