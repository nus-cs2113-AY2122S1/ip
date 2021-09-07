import exceptions.DukeException;
import parser.Parser;
import task.TaskManager;
import ui.UI;
import command.Command;

public class Duke {

    private final TaskManager taskManager;
    private final UI ui;
    private final Parser parser;

    /**
     * Constructor for Duke (renamed to taro) that initializes the key properties
     */
    public Duke() {
        ui = new UI();
        taskManager = new TaskManager();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().startDuke();
    }

    /**
     * Greets the user by showing the logo and prepares to take user input to perform commands
     */
    private void startDuke() {
        ui.printLogo();
        ui.greetUser();
        handleCommands();
    }

    /**
     * Handles the main functionality of duke by using the ui object to read from standard input and then pass the
     * input to the parser to return an executable command object. After the command is executed, the user can enter
     * more commands until "bye" is entered. An invalid command prints an error message to standard output.
     */
    private void handleCommands() {
        while (true) {
            String userInput = ui.readUserInput();
            try {
                Command currentCommand = parser.parseInput(userInput);
                currentCommand.execute(taskManager, ui);
            } catch (DukeException e) {
                ui.printString(e.getMessage());
            }
        }
    }

}
