package Duke;/*---------LOCAL IMPORT--------*/
import exceptions.DukeException;
import processing.CommandHandler;
import processing.TaskManager;
import processing.TaskSafe;
import processing.UI;


public class Duke {
    private static boolean isRunning = true;
    public static final TaskManager taskManager = new TaskManager();

    public static void processInput(String input) {
        try {
            CommandHandler command = new CommandHandler(input);
            command.execute(taskManager);
            TaskSafe.saveToFile(taskManager.getTasks());
        } catch (DukeException e) {
            UI.showError(e);
        }

    }

    public static void run() {
        while (isRunning) {
            String input = UI.getCommand();
            UI.printDivider();
            processInput(input);
            UI.printDivider();
        }
    }

    public static void exit() {
        UI.close();
        isRunning = false;
    }

    public static void main(String[] args) {
        UI.greetPartOne();
        TaskSafe.loadFromFile(Duke.taskManager);
        UI.greetPartTwo();
        run();
    }
}
