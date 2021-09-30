package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import duke.command.DukeException;
import duke.Ui;



import java.io.IOException;

public class Duke {

    private Storage storage;
    private static TaskList tasks;
    private int test;

    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFileContents());
    }


    public static void executeCommand(Parser c, TaskList tasks, Storage storage) throws DukeException, IOException {
        switch (c.getCommand()) {
        case "bye":
            //fileManager.writeArrayToFile(taskManager);
            Ui.showBye();
            break;
        case "list":
            tasks.printTaskList();
            break;
        case "done":
            tasks.markTaskAsDone(c.getDescription());
            break;
        case "todo":
            tasks.addToDoTaskToList(c.getDescription());
            break;
        case "deadline":
            tasks.addDeadlineTaskToList(c.getDescription());
            break;
        case "event":
            tasks.addEventTaskToList(c.getDescription());
            break;
        case "delete":
            tasks.deleteTask(c.getDescription());
            break;
        /*case COMMAND_FIND:
            if (isValidFindCommand(inputStr)) {
                String keyword = Parser.getItem(inputStr);
                printArrayList(taskManager.findTask(keyword));
            }
            break;*/
        default:
            throw new DukeException("Oops, command not recognised!");
        }
        storage.writeToFile(tasks);
    }


    public void run() {

        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                Ui.showLine(); // show the divider line ("_______")
                Parser c = new Parser(fullCommand);
                c.parse();
                executeCommand(c,tasks,storage);
                //c.execute(tasks, ui, storage);

                isExit = c.getIsExit();
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description cannot be empty.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! It's out of range.");
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        new Duke("data/duke.txt").run();
    }
}



