package duke;

import java.util.Scanner;
import java.lang.String;

import duke.exception.*;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.*;
import java.io.IOException;


public class Duke {
    private static Ui ui = new Ui();
    public static TaskList list = new TaskList();
    public static TaskDoneList doneList = new TaskDoneList();
    private static Storage storage;

    public static void run() {
        boolean isExit = false;
        while (!isExit) {
            Scanner in = new Scanner(System.in);
            String order;
            order = in.nextLine();
            try {
                Command c = Parser.parse(order);
                c.executeCommand(list, doneList, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                e.printErrorMessage(ui);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ui.showWelcomeScreen();
        storage = new Storage("dukeList.txt", "dukeDoneList.txt");
        storage.readFiles(list, doneList, ui);
        run();
    }
}
