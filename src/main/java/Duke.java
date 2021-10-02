import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Contains the main method and is responsible for structure of program-flow.
 */
public class Duke {
    private static ArrayList<Task> tasks;
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;

    /**
     * Runs the whole program as per the program flow:
     * Initialize Program -> Run Duke Conversation Loop -> Exits Program
     *
     * @throws IOException due to creation of save file when file not found.
     */
    public static void run() throws IOException{
        initDuke();
        runDuke();
        exitDuke();
    }

    /**
     * Initializes the program.
     * This method initializes all the required classes for the program, and runs the method
     * to create a folder for the save file if not found, and also the method to creates a save file if not found. This then
     * loads the saved data from the saved file.
     *
     * @throws IOException due to creation of save file when file not found.
     */
    public static void initDuke() throws IOException {
        tasks = new ArrayList<>();
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();

        try {
            storage.initSaveFile(tasks);
        } catch (FileNotFoundException e) {
            storage.createSaveFile();
        }
    }

    /**
     *  Runs the main conversation loop of the program.
     *  Allows for the inputs to the command line and takes them in. The method will then pass the
     *  inputs to the the parser for command processing.
     */
    public static void runDuke() {
        Scanner in = new Scanner(System.in);
        String line;

        // start the taskbot
        ui.printWelcome();
        line = in.nextLine();

        // process inputs by user
        parser.parseInputs(in, line, tasks);
    }

    /**
     * Saves and exits the program.
     * Calls the method required to save the program, and then exits the program with an exit message.
     */
    public static void exitDuke() {
        // save to file and exit after finished
        try {
            storage.writeToSave(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        ui.printExit();
    }

    /**
     * Main method of the program.
     * Calls the run() method of the Duke class to begin the program.
     *
     * @param args allows for arguments when running the program in terminal.
     * @throws IOException due to creation of save file when file not found.
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();

    }
}
