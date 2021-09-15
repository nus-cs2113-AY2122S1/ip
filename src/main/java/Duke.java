import console.InputParser;
import task.TaskManager;
import utils.Command;
import utils.Display;
import utils.FileManager;

import java.util.Scanner;

public class Duke {

    public static void greet() {
        Display.printSeparatingLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    public static void goodbye() {
        Display.printSeparatingLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        Display.printSeparatingLine();
    }

    public static void interact() {
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        FileManager.loadFileData(taskManager);
        boolean isStillInteracting = true;
        while (isStillInteracting) {
            String[] commandComponents = InputParser.getCommandComponents(in);
            Command.executeCommand(commandComponents, taskManager);
            isStillInteracting = !commandComponents[0].equals(Command.COMMAND_EXIT_PROGRAM);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        interact();
        goodbye();
    }
}
