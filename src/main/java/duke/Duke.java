package duke;

import duke.commands.Command;
import duke.utilities.DukeException;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Main entry point of the application and starts the interaction with user
     */
    public void run() {
        ui.printWelcomeMessage();
        startChat();
        ui.printByeMessage();
    }

    private void startChat() {
        Scanner in = new Scanner(System.in);
        boolean isExit = false;

        try {
            do {
                ui.printDivider();
                String input = in.nextLine();
                ui.printDivider();
                Command command = Parser.getCommand(input, ui);
                command.execute(input, tasks, ui, storage);
                isExit = command.isExit(command);
            } while (!isExit);
        } catch (DukeException dukeE){
            dukeE.printStackTrace();
        }
    }

//    private void startChat2() {
//        Scanner in = new Scanner(System.in);
//        boolean isActive = true;
//        try {
//            while (isActive) {
//                storage.saveToFile(tasks.getTasks());
//                ui.printDivider();
//                String input = in.nextLine();
//                ui.printDivider();
//                switch (Parser.getCommand(input)) {
//                case "bye":
//                    isActive = false;
//                    break;
//                case "list":
//                    ui.printList(tasks.getTasks());
//                    break;
//                case "todo":
//                    tasks.addTask(input, ui, "T");
//                    break;
//                case "event":
//                    tasks.addTask(input, ui,"E");
//                    break;
//                case "deadline":
//                    tasks.addTask(input, ui, "D");
//                    break;
//                case "done":
//                    tasks.setTaskAsDone(input, ui);
//                    break;
//                case "delete":
//                    tasks.deleteTask(input, ui);
//                    break;
//                default:
//                    ui.printNoInput();
//                    break;
//                }
//            }
//        } catch (DukeException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
