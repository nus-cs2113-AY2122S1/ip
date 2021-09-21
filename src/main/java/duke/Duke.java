package duke;

import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import duke.command.Command;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Duke {
    private static final String TASKS_FILENAME = "./data/duke.txt";

    private final TaskList TASKS;
    private final Storage STORAGE;
    private final Ui UI;

    public Duke(String filename) {
        this.TASKS = new TaskList();
        this.STORAGE = new Storage(filename);
        this.UI = new Ui();
    }

    public void run() {
        loadTasksFromStorage();
        UI.printWelcomeMessage();

        boolean isExit = false;
        do {
            String input = UI.getUserInput();

            try {
                Command command = Parser.parse(input);
                command.execute(TASKS, UI, STORAGE);
                isExit = command.isExit();
            } catch (DukeException e) {
                UI.printException(e.getMessage());
            }
        } while (!isExit);
    }

    /**
     * Loads tasks from the storage file.
     */
    private void loadTasksFromStorage() {
        try {
            ArrayList<Task> tasks = STORAGE.load();
            if (tasks == null) {
                return;
            }

            for (Task task : tasks) {
                TASKS.addTask(task);
            }
        } catch (DukeException e) {
            UI.printException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke(TASKS_FILENAME).run();
    }
}
