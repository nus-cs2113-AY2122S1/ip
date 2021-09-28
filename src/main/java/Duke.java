import java.util.Scanner;

public class Duke {
    private Ui ui;

    /**
     * Constructor for class Duke.
     * @param filePath File path for the save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
    }

    /**
     * Runs the bot.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        String curCommand = "";
        String line = "________________________________________";
        ui.printWelcome();
        while (!curCommand.equals("bye")) {
            curCommand = input.nextLine();
            System.out.println(line);
            Parser.handleCommand(curCommand);
            System.out.println(line);
        }
    }

    /**
     * Main method for the program.
     * @param args Unused.
     */
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        new Duke(dir + "\\duke.txt").run();
    }
}
