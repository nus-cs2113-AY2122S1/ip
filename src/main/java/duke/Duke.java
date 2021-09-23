package duke;

import duke.utilities.DukeException;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

import java.util.Scanner;

public class Duke {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static Storage storage = new Storage();
    private static TaskList tasks = new TaskList();
    private static Ui ui = new Ui();

    /**
     * Main entry point of the application and starts the interaction with user
     */
    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        startChat();
        Ui.printByeMessage();
    }

    private static void startChat() {
        tasks = storage.loadFile();
        boolean isActive = true;
        try {
            while (isActive) {
                ui.printDivider();
                String input = SCANNER.nextLine();
                ui.printDivider();
                switch (Parser.getCommand(input)) {
                case "bye":
                    isActive = false;
                    break;
                case "list":
                    ui.printList(tasks.getTasks());
                    break;
                case "todo":
                    tasks.addTask(input, "T");
                    break;
                case "event":
                    tasks.addTask(input, "E");
                    break;
                case "deadline":
                    tasks.addTask(input, "D");
                    break;
                case "done":
                    tasks.setTaskAsDone(input);
                    break;
                case "delete":
                    tasks.deleteTask(input);
                    break;
                default:
                    ui.printNoInput();
                    break;
                }
            }
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
