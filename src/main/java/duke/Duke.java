package duke;

import duke.command.Command;
import duke.exception.CommandDoesNotExistException;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import javax.sound.midi.SysexMessage;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    private static final String fileDir = "./data";
    private static final String fileName = "./data/save.txt";
    private final Storage storage;
    private TaskList task;
    private final Ui ui;


    public Duke(String fileName, String fileDir) {
        storage = new Storage(fileName, fileDir);
        ui = new Ui();
        try {
            task = new TaskList(storage.items);
            storage.load(task);
        } catch (FileNotFoundException e) {
            ui.showSaveFileError();
            storage.create();
            task = new TaskList(storage.items);
        }
    }
    public void run() {
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(in);
                Command c = Parser.parse(fullCommand);
                boolean hasError = Parser.verifyCommand(task, c, ui);
                if (hasError) {
                    throw new DukeException();
                }
                c.execute(task, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError();
            }
        }
        storage.save();
        ui.printEndMessage();
    }
    public static void main(String[] args) {
        new Duke(fileName, fileDir).run();
    }
}
