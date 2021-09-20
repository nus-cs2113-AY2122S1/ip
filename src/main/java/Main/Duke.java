package Main;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import Commands.Command;
import Parsing.ParseInput;
import Parsing.Parser;
import Storage.Storage;
import Tasks.Task;
import Exception.DukeException;
import UI.UI;

public class Duke {
    public static boolean isLoading = true;
    public static boolean isExit = false;

    protected Storage storage;
    List <Task> tasks;

    public Duke (String commandfilePath, String datafilePath) {
        tasks = new ArrayList<>();
        storage = new Storage (commandfilePath, datafilePath);
        storage.loadData(tasks, storage);
    }

    /**
     * Runs Duke app.
     * Note that loading the User data (from previous usages) is already done when Duke is instantiated.
     *
     * @param tasks    User's tasks in Duke
     * @param storage  Actions involving reading/writing to hard drive
     */
    private void run (List<Task> tasks, Storage storage) {
        UI.dukeGreeting();

        while (!isExit) {
            try {
                Scanner userScanner = new Scanner (System.in);
                String userInput = userScanner.nextLine();
                ParseInput parseInput = Parser.parse(userInput);
                Command.executeCommand(parseInput, tasks, storage);
            } catch (DukeException dukeException) {
                UI.dukeError(dukeException.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke ("savedCommands.txt", "savedData.txt");
        duke.run(duke.tasks, duke.storage);
        duke.storage.saveData(duke.tasks);
    }
}
