import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

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

    public void run() {
        ui.printGreeting();
        while (parser.parseNextLine()) {
            Command c = parser.processCommands(tasks, ui);
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
