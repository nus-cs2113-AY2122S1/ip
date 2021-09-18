import commands.ExitCommand;
import console.InputParser;
import error.Error;
import task.TaskManager;
import commands.Command;
import ui.Display;
import filemanager.Storage;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskManager taskManager;
    private Scanner inputScanner;

    public Duke() {
        try {
            String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            Display.displayGreetings();
            storage = new Storage();
            taskManager = new TaskManager();
            inputScanner = new Scanner(System.in);
            storage.loadFileData(taskManager);
        } catch (IOException e) {
            Error.displayFileLoadError();
        }
    }

    public void run() {
        interact();
        Display.displayGoodbyes();
    }

    public void interact() {
        Command command;
        do {
            String userInput = InputParser.getUserCommand(inputScanner);
            String[] commandComponents = InputParser.getCommandComponents(userInput);
            command = InputParser.parseCommand(commandComponents, taskManager);
            command.setCommandComponents(commandComponents);
            try {
                storage.updateFileData(taskManager.getAllTasks());
            } catch (IOException e) {
                Error.displayFileUpdateError();
            }
        } while (!command.executeCommand().equals(ExitCommand.COMMAND_WORD));
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
