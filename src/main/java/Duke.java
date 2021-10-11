import java.util.Scanner;

/**
 * Represents the Duke Chat bot.
 */
public class Duke {

    private static boolean isDukeDone = false;

    private final TaskList tasks;

    /**
     * Initialises the variables TaskList and Storage.
     *
     * @param filePath Contains the path to where the stored tasks are saved in.
     */
    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        tasks = new TaskList();
        storage.readSavedTasks(tasks);
    }

    /**
     * Creates an instance of duke and runs it.
     *
     * @param args System Arguments added to program.
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

    /**
     * Takes in input from user and sends it to parser function.
     */
    public void run() {
        Ui.printWelcomeBanner();
        if (isDukeDone) {
            Ui.printFileCorrupted();
        }
        Scanner in = new Scanner(System.in);
        while (!isDukeDone) {
            Ui.padLines();
            String input = in.nextLine();
            try {
                Parser.parseInput(input, tasks);
            } catch (InvalidInputException e) {
                Ui.printGeneralInvalidInput();
            }
            Ui.padLines();
        }
    }

    /**
     * Sets isDukeDone to true, signalling end of program.
     */
    public static void setDukeDone() {
        isDukeDone = true;
    }


}


