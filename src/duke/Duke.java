package duke;

import duke.task.TaskList;
import ui.UI;

import java.util.Scanner;

public class Duke {
    public static final String COMMAND_EXIT = "bye";
    public static final String FILE_NAME = "duke/data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke() {
        this.storage = new Storage(FILE_NAME);
        this.tasks = storage.loadData();
        this.parser = new Parser(tasks);
    }

    public void run() {
        UI.printWelcome();
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equalsIgnoreCase(COMMAND_EXIT)) {
            parser.parseCommand(userInput);
            storage.saveFile(tasks.getTasks());
            userInput = in.nextLine();
        }
        UI.printExitMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
