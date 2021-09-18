package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exception.MissingArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;

/**
 * Represents a task manager. A duke object combines the functionality of storage, tasks and ui to
 * perform as a task manager
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a duke object with specified save file path
     *
     * @param filePath relative path to save file
     * @return duke object
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Runs the duke program until user exits
     *
     * @return null
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        ui.greet();

        while (!exit) {
            String command = scan.nextLine();
            HashMap<String, String> commandArgs = Parser.parseCommand(command);
            ui.printDivider();
            try {
                switch (commandArgs.get("command").toLowerCase()) {
                case "list":
                    ui.listAll(tasks.getTasks());
                    break;
                case "done": {
                    Integer index = Integer.parseInt(commandArgs.get("param"));
                    ui.markDone(tasks.setTaskDone(index));
                    break;
                }
                case "delete": {
                    Integer index = Integer.parseInt(commandArgs.get("param"));
                    ui.deleteSuccess(tasks.deleteTask(index), tasks.size());
                    break;
                }
                case "todo": {
                    String description = commandArgs.get("param");
                    Todo todo = tasks.addTodo(description);
                    ui.addSuccess(todo, tasks.size());
                    break;
                }
                case "deadline": {
                    String description = commandArgs.get("param");
                    String by = commandArgs.get("by");
                    Deadline deadline = tasks.addDeadline(description, by);
                    ui.addSuccess(deadline, tasks.size());
                    break;
                }
                case "event": {
                    String description = commandArgs.get("param");
                    String at = commandArgs.get("at");
                    Event event = tasks.addEvent(description, at);
                    ui.addSuccess(event, tasks.size());
                    break;
                }
                case "find": {
                    String search = commandArgs.get("param");
                    ui.listSearch(tasks.findTasks(search));
                    break;
                }
                case "bye":
                    exit = true;
                    ui.bye();
                    break;
                default:
                    ui.printUnknownCommand();
                    break;
                }
            } catch (MissingArgumentException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (NumberFormatException e) {
                ui.printErrorMessage("☹ OOPS!!! Index must be a number.");
            } catch (IndexOutOfBoundsException e) {
                String indexErrorMsg = String.format("☹ OOPS!!! Item of index %s does not exist in tasks.",
                        commandArgs.get("param"));
                ui.printErrorMessage(indexErrorMsg);
            } finally {
                try {
                    storage.saveTasks(tasks.getTasks());
                } catch (IOException e) {
                    ui.printErrorMessage("☹ OOPS!!! Error saving tasks");
                }
                ui.printDivider();
                System.out.println("");
            }
        }

        scan.close();
    }

    public static void main(String[] args) {
        new Duke("save.txt").run();
    }
}
