package duke;

import java.util.Scanner;

public class Duke {

    private static final Ui ui = new Ui();
    private static TaskList tasks = new TaskList();
    private static Parser parser = new Parser();
    private static Storage storage = new Storage();
    private static TaskManager taskManager = new TaskManager(tasks);

    public static void main(String[] args) {
        storage.loadTasks(tasks);
        ui.printHelloMessage();
        handleCommand();
        ui.printByeMessage();
        storage.saveTasks(tasks);
    }

    /**
     * Handles the commands entered by users and gives respective output
     * This process continues until user enters "bye" to terminate the program
     */
    private static void handleCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            try {
                String keyword = parser.getPrefix(input);
                switch (keyword) {
                case "bye":
                    if (parser.getInputWordCount(input) != 1) {
                        ui.promptInvalidInput();
                    }
                    break;
                case "list":
                    taskManager.listTasks();
                    break;
                case "todo":
                    taskManager.addTodo(input);
                    storage.saveTasks(tasks);
                    break;
                case "deadline":
                    taskManager.addDeadline(input);
                    storage.saveTasks(tasks);
                    break;
                case "event":
                    taskManager.addEvent(input);
                    storage.saveTasks(tasks);
                    break;
                case "done":
                    taskManager.finishTask(input);
                    storage.saveTasks(tasks);
                    break;
                case "delete":
                    taskManager.deleteTask(input);
                    storage.saveTasks(tasks);
                    break;
                case "find":
                    taskManager.findTask(input);
                    break;
                default:
                    ui.promptInvalidInput();
                    break;
                }
            } catch (DukeException e) {
                ui.printMessage(DukeException.getErrorMessage());
            } catch (NumberFormatException e) {
                ui.printMessage("You are supposed to enter a number!");
            }
            input = in.nextLine();
        }
    }

}
