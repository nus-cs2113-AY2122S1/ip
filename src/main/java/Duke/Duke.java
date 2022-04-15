package Duke;

import Duke.Commands.ByeCommand;
import Duke.Commands.Command;

import java.io.IOException;

public class Duke {
    private static TaskList taskList = new TaskList();

    /**
     * This is the main function of the Duke program.
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        taskList = Storage.initialiseFile();
        UI.printHeaderMessage();
        handleInputs();
        UI.printByeMessage();
    }

    /**
     * Handles the commands inputted by the user until an exit command is given.
     * Exits the function upon exit command.
     */
    public static void handleInputs() {
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
