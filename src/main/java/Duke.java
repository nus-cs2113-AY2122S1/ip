import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * This class is the main body of the Duke task manager. It contains a Storage class to read and write Task files from
 * disk, a TaskList class to store Tasks and perform actions on the task list, a Parser class to process user input,
 * and a UI class to print information to the command line.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;
    private final UI ui;

    public Duke() {
        ui = new UI();
        parser = new Parser();
        tasks = new TaskList();
        storage = new Storage(tasks);
    }

    /**
     * Prints a starting message to the command line. The function then runs in a loop, taking user input and
     * processing it until a command to end the program is issued by the user. If the program does not end, the
     * command output by the Scanner is executed if it is not null. The list of tasks is then saved to a file.
     * When the program ends, it prints an ending message to the command line.
     */
    public void run() {
        ui.printGreeting();
        while (parser.parseNextLine()) {
            Command c = parser.processCommands(ui);
            if (c != null) {
                c.execute(tasks);
            }
            ui.printDivLine();
            storage.saveTasks(tasks);
        }
        ui.printFarewell();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
