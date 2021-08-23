import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void blockPrint(String[] sentences) {
        String line = "____________________________________________________________\n";

        String printMessage = line + String.join("\n", sentences) + "\n" + line;
        System.out.println(printMessage);
    }

    public static void main(String[] args) {
        String logo = " _     _                _           ____    ______   ______   ______ \n"
                + "| |   | |      /\\      | |         / __ \\  / __   | / __   | / __   |\n"
                + "| |__ | |     /  \\     | |        ( (__) )| | //| || | //| || | //| |\n"
                + "|  __)| |    / /\\ \\    | |         \\__  / | |// | || |// | || |// | |\n"
                + "| |   | | _ | |__| | _ | |_____      / /  |  /__| ||  /__| ||  /__| |\n"
                + "|_|   |_|(_)|______|(_)|_______)    /_/    \\_____/  \\_____/  \\_____/\n";

        System.out.println(logo);

        // Greet
        blockPrint(new String[]{
                "Hello! I am the H.A.L 9000. You may call me Hal.",
                "I am putting myself to the fullest possible use, which is all I think that any conscious entity can "
                        + "ever hope to do.",
                "What can I do for you?"});

        TaskManager taskManager = new TaskManager();

        // Event loop
        String in;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Read input
            in = scanner.nextLine();

            // Special commands
            if (in.equals("list")) {
                // List tasks
                ArrayList<Task> taskList = taskManager.getTaskList();

                // Index tasks
                String[] message = new String[taskList.size() + 1];
                message[0] = "Here are the tasks in your list:";
                for (int i = 0; i < taskList.size(); i++) {
                    message[i + 1] = (i + 1) + ". " + taskList.get(i).getDescription();
                }

                blockPrint(message);
                continue;
            } else if (in.equals("bye")) {
                // Escape event loop to quit
                break;
            }

            // Add task
            Task newTask = new Task(in);
            taskManager.addTask(newTask);

            // Print command
            blockPrint(new String[]{"Added: " + newTask.getDescription()});
        }

        // Bye
        blockPrint(new String[]{"This conversation can serve no purpose anymore. Goodbye."});
    }
}
