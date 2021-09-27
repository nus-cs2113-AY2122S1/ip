package duke;

import duke.commands.Command;
import duke.commands.CommandOutput;
import duke.tasks.*;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        TaskListResponse response = storage.initialiseTasks();
        tasks = response.taskList;
        Ui.printMessage(response.response);
    }

    //attempts to execute the user command
    public CommandOutput executeCommand(Command command) {
        try {
                return command.execute();
        } catch (Exception e){
            return new CommandOutput(e.getMessage(), false, null);
        }
    }

    //runs the program
    public void run() {
        ui.printGreeting();
        String line;
        Scanner in = new Scanner(System.in);
        ui.printPrompt();
        line = in.nextLine();
        while(!line.equals(parser.BYE)) {
            Command command = parser.parseCommand(tasks, line);
            CommandOutput commandOutput = executeCommand(command);
            if (commandOutput.isUpdated()) {
                tasks = commandOutput.getTaskList();
                String errorMessage = storage.write(tasks);
                ui.printMessage(errorMessage);
            }
            ui.printMessage(commandOutput.getResponse());
            ui.printPrompt();
            line = in.nextLine();
        }
        ui.printExit();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
