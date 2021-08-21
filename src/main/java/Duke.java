import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static void printMessage(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine + System.lineSeparator() + message + System.lineSeparator()
                            + horizontalLine);
    }

    public static void main(String[] args) {
        //Starting message
        String horizontalLine = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalLine + System.lineSeparator()
                            + "Hello! I'm Duke" + System.lineSeparator()
                            + "What can I do for you?" + System.lineSeparator()
                            + horizontalLine);
        //initialise Scanner
        Scanner in = new Scanner(System.in);
        //initialise TaskManager
        TaskManager taskManager = new TaskManager();

        boolean conversationIsOver = false;
        while (!conversationIsOver) {
            //switch to lowercase so that Duke won't be case sensitive
            String inputCommand = in.nextLine();
            String command = inputCommand.toLowerCase();
            switch(command) {
            case "list":
                taskManager.printTasks();
                break;
            case "bye":
                conversationIsOver = true;
                break;
            default:
                taskManager.addTask(inputCommand.trim());
                printMessage("added: " + inputCommand.trim());
            }
        }


        //Ending message
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
        //
    }
}
