import java.util.Scanner;

public class Duke {

    public static void printMessage(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine + System.lineSeparator() + message + System.lineSeparator()
                + horizontalLine);
    }

    //Made this as a separate function so that main function doesn't become too big
    public static void printStartingOrEndingMessage(boolean isStart) {
        if (isStart) {
            //Starting message
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            printMessage("Hello! I'm Duke" + System.lineSeparator()
                    + "What can I do for you?");
        } else {
            //Ending message
            printMessage("Bye. Hope to see you again soon!");
        }
    }

    public static void main(String[] args) {
        printStartingOrEndingMessage(true);
        //initialise Scanner and TaskManager
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        boolean conversationIsOver = false;
        while (!conversationIsOver) {
            //switch to lowercase so that Duke won't be case-sensitive
            String inputCommand = in.nextLine();
            String command = inputCommand.toLowerCase().split(" ")[0];
            switch (command) {
            case "list":
                taskManager.printTasks();
                break;
            case "done":
                taskManager.setTaskAsDone(Integer.parseInt(inputCommand.split(" ")[1]));
                break;
            case "bye":
                conversationIsOver = true;
                break;
            default:
                taskManager.addTask(inputCommand.trim());
                printMessage("added: " + inputCommand.trim());
                break;
            }
        }
        printStartingOrEndingMessage(false);
    }
}
