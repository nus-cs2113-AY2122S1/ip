import java.util.Scanner; // Import the Scanner class

/*---------LOCAL IMPORT--------*/
import exceptions.DukeException;
import tasks.TaskType;



public class Duke {
    private static Scanner myScan = new Scanner(System.in);
    private static TaskManager taskManager = new TaskManager();
    private static boolean isRunning = true;

    /*--------- PROCESSING CONSTANTS ------------ */
    private static final String FAREWELL_STR = "Bye. Hope to see you again soon!";

    public static void greet() {
        String logo = " ____        _        \n"
                 + "|  _ \\ _   _| | _____ \n"
                 + "| | | | | | | |/ / _ \\\n"
                 + "| |_| | |_| |   <  __/\n"
                 + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void processInput(String input) throws DukeException {
        CommandHandler command = new CommandHandler(input);
        switch (command.getCommand()) {
        case "bye":
            System.out.println(FAREWELL_STR);
            myScan.close();
            isRunning = false;
            return;
        case "list":
            taskManager.listTasks();
            break;
        case "done":
            taskManager.markTaskAsDone(command);
            break;
        case "delete":
            taskManager.deleteTask(command);
            break;
        case "deadline":
            taskManager.addTask(command,TaskType.DEADLINE);
            break;
        case "event":
            taskManager.addTask(command, TaskType.EVENT);
            break;
        case "todo":
            taskManager.addTask(command, TaskType.TODO);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void run() {
        while (isRunning) {
            String input = myScan.nextLine();
            System.out.println("------------------------------------------");
            try {
                processInput(input);
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.println("------------------------------------------");
        }
    }

    public static void main(String[] args) {
        greet();
        run();
    }
}
