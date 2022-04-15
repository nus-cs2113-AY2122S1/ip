import java.io.IOException;
import java.util.NoSuchElementException;

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
        try {
            Parser.parseAndExecuteCommand();

            /* When input redirecting an empty file to Duke */
        } catch (NoSuchElementException e) {
            Ui.printlnTab("☹ OOPS!!! You have input redirected an empty file to me!");
            Ui.printlnTab("Ending Program...");
            Ui.printDivider();
        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}
