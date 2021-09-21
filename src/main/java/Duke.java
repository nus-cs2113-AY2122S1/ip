import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {
    /**
     * Necessary variables for Duke to work properly
     */
    public static Ui ui;
    private static Storage storage;
    private static Parser parser;
    private static TaskList tasks;

    /**
     * Initializes the above necessary variables for Duke program
     */
    public static void initDuke() {
        ui = new Ui();
        tasks = new TaskList(ui);
        storage = new Storage();
        storage.instantiateTasksFromFile(tasks);
        parser = new Parser(ui);
    }

    public static void main(String[] args) {
        initDuke();
        ui.printLogo();
        ui.printGreeting();
        String userInput;
        while (true) {
            userInput = ui.getUserInput();
            parser.parseUserCommand(userInput, tasks);
        }
    }
}