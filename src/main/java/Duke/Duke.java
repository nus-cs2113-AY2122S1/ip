package Duke;

import Duke.Commands.ByeCommand;
import Duke.Commands.Command;
import Duke.Task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private static int taskCounter = 0;
    private static final ArrayList<Task> TASKS_ARRAY_LIST = new ArrayList<>();
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(CURRENT_DIRECTORY);

    private static final String INVALID_INPUT_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means." + System.lineSeparator()
            + "\tPlease enter a valid input!" + System.lineSeparator()
            + "\ti.e. todo, deadline, event, list, done or bye.";


    public static void main(String[] args) throws DukeException {
        readAndExecuteFromFile();
        UI.printHeaderMessage();
        handleInputs();
        UI.printByeMessage();
    }

    private static void readAndExecuteFromFile() {
        ArrayList<Task> fileTasksList = Storage.initialiseFile();
        TASKS_ARRAY_LIST.addAll(fileTasksList);
    }

    public static void handleInputs() throws DukeException {
        Command command;
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = UI.getInput();
                command = Parser.parseCommand(input);
                command.execute(TASKS_ARRAY_LIST);
                Storage.updateFile(TASKS_ARRAY_LIST);
                if (command instanceof ByeCommand) {
                    isExit = true;
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
