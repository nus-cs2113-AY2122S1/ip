package Duke;/*---------LOCAL IMPORT--------*/
import exceptions.DukeException;
import processing.CommandHandler;
import processing.TaskManager;
import processing.TaskSafe;
import processing.UI;

import java.util.Arrays;


public class Duke {

    private static final String NO_LOAD = "-noload";
    private static boolean isRunning = true;
    public static final TaskManager taskManager = new TaskManager();

    /**
     * Main method that continues looping and accepting commands until user inputs 'bye'
     */
    public static void run() {
        while (isRunning) {
            String input = UI.getCommand();
            UI.printDivider();
            processInput(input);
            UI.printDivider();
        }
    }

    /**
     * Takes a command, executes it, and saves the updates to a local file
     * @param input the command that user input on prompt
     */
    public static void processInput(String input) {
        try {
            CommandHandler command = new CommandHandler(input);
            command.execute(taskManager);
            TaskSafe.saveToFile(taskManager.getTasks());
        } catch (DukeException e) {
            UI.showError(e);
            UI.greetPartTwo();
        }
    }

    /**
     * ties up loose ends and ends the application
     */
    public static void exit() {
        UI.close();
        isRunning = false;
    }

    public static void main(String[] args) {
        UI.greetPartOne();
        boolean isLoadFromSave = true;
        if (Arrays.asList(args).contains(NO_LOAD)) {
            isLoadFromSave = false;
        }
        if (isLoadFromSave) {
            TaskSafe.loadFromFile(taskManager);
        }
        UI.greetPartTwo();
        run();
    }
}
