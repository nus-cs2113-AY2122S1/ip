package duke;

import duke.task.TaskList;
import java.util.Objects;
import java.io.File;

public class Duke {
    public static final int COMMAND_INDEX = 0;
    public static final File myFile = new File("tasks.txt");
    private static Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;



    public Duke(File myFile) {
        ui = new Ui();
        ui.printStart();
        storage = new Storage(myFile);

        if (!storage.newFileCreated) {
            tasks = new TaskList(storage.load());
        }
        else {
            tasks = new TaskList();
        }

        parser = new Parser(tasks, ui, storage);
    }


    public void run() {
        while (true) {
            ui.getNextLine();

            if (Objects.equals(ui.userInput.split(" ")[COMMAND_INDEX], "bye")) {
                ui.printEnd();
                return;
            }
            parser.parseInput(ui.userInput);
        }
    }


    /**
     * This function is called upon program execution.
     *
     * @param args System arguments that are added to the program
     */
    public static void main(String[] args) {
        new Duke(myFile).run();
    }
}