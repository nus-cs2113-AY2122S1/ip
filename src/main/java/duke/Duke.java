package duke;

import duke.exception.AtEmptyException;
import duke.exception.ByEmptyException;
import duke.exception.NumberOutOfBoundsException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {
    private static final String SAD_FACE = "\uD83D\uDE41";

    private Ui dukeUi;
    private Storage dukeStorage;
    private TaskList dukeTask;

    public Duke () {
        dukeUi = new Ui();
        dukeStorage = new Storage();
        dukeTask = new TaskList(dukeStorage.taskArrayList);
    }

    public void run(){
        dukeUi.printLogo();
        dukeStorage.loadFile();
        dukeUi.printGreeting("Hello! I'm Duke", "What can I do for you?");
        while (true) {
            String userInput = dukeUi.getUserInput();
            Ui.printReply(userInput);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
