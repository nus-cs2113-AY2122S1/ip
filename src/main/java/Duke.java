import java.util.Scanner;

public class Duke {

    // Constants
    private static final String LOGO = " _     _                _           ____    ______   ______   ______ \n"
            + "| |   | |      /\\      | |         / __ \\  / __   | / __   | / __   |\n"
            + "| |__ | |     /  \\     | |        ( (__) )| | //| || | //| || | //| |\n"
            + "|  __)| |    / /\\ \\    | |         \\__  / | |// | || |// | || |// | |\n"
            + "| |   | | _ | |__| | _ | |_____      / /  |  /__| ||  /__| ||  /__| |\n"
            + "|_|   |_|(_)|______|(_)|_______)    /_/    \\_____/  \\_____/  \\_____/\n";
    private static final String LINE = "____________________________________________________________\n";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";

    private static final TaskManager taskManager = new TaskManager();

    private static void blockPrint(String[] sentences) {
        String printMessage = LINE + String.join("\n", sentences) + "\n" + LINE;
        System.out.println(printMessage);
    }

    private static void addTask(String description) {
        Task newTask = new Task(description);
        taskManager.addTask(newTask);
        blockPrint(new String[]{"Added: " + newTask.getDescription()});
    }

    private static void listTasks() {
        // Format tasks for output message
        String[] taskListMessage = new String[taskManager.getTotalTasks() + 1];
        taskListMessage[0] = "Here are the tasks in your list:";

        for (int i = 0; i < taskManager.getTotalTasks(); i++) {
            Task task = taskManager.getTask(i);
            taskListMessage[i + 1] = (i + 1) + ".[" + task.getStatusIcon() + "] " + task.getDescription();
        }

        blockPrint(taskListMessage);
    }

    private static void markTaskAsDone(int taskIndex) {
        taskManager.markTaskAsDone(taskIndex);
        Task completedTask = taskManager.getTask(taskIndex);
        blockPrint(new String[]{"Affirmative. I will mark this task as done:",
                "[" + completedTask.getStatusIcon() + "] " + completedTask.getDescription()});
    }

    public static void main(String[] args) {
        System.out.println(LOGO);

        // Greet
        blockPrint(new String[]{"Hello! I am the H.A.L 9000. You may call me Hal.",
                "I am putting myself to the fullest possible use, which is all I think that any conscious entity can "
                        + "ever hope to do.",
                "What can I do for you?"});

        // Event loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Read input
            String in = scanner.nextLine().trim();
            String[] splitInput = in.split(" ");

            // Commands
            if (splitInput[0].equals(COMMAND_BYE)) {
                break;
            }
            switch (splitInput[0]) {
            case COMMAND_LIST:
                listTasks();
                continue;
            case COMMAND_DONE:
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;
                markTaskAsDone(taskIndex);
                continue;
            default:
                addTask(in);
                break;
            }
        }

        // Bye
        blockPrint(new String[]{"This conversation can serve no purpose anymore. Goodbye."});
    }
}
