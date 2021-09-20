package Duke;

import Duke.Commands.ByeCommand;
import Duke.Commands.Command;

import java.io.IOException;

public class Duke {

//    private static final ArrayList<Task> TASKS_ARRAY_LIST = new ArrayList<>();
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) throws DukeException {
        taskList = Storage.initialiseFile();
        UI.printHeaderMessage();
        handleInputs();
        UI.printByeMessage();
    }

    public static void handleInputs() throws DukeException {
        Command command;
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = UI.getInput();
                command = Parser.parseCommand(input);
                command.setData(taskList);
                command.execute();
                Storage.updateFile(taskList);
                if (command instanceof ByeCommand) {
                    isExit = true;
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
