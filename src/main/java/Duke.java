import java.util.Scanner; // Import the Scanner class

/*---------LOCAL IMPORT--------*/
import exceptions.DukeException;
import tasks.TaskType;



public class Duke {
    private static boolean isRunning = true;
    public static final TaskManager taskManager = new TaskManager();

    public static void processInput(String input) {
        try {
            CommandHandler command = new CommandHandler(input);
            command.execute();
            TaskSafe.saveToFile(TaskSafe.rootPath + TaskSafe.DATA_PATH,taskManager.getTasks());
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
        UI.greet();
        run();
    }
}
