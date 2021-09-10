import duke.*;

import java.util.Scanner;

public class Duke {

    public static void sayHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you, my liege?");
    }

    public static void sayBye() {
        Formatter.printFormattedOutput("Farewell, my liege. Happy hunting!");
    }

    public static void echoUserInput(String input) {
        Formatter.printFormattedOutput(input);
    }

    public static void main(String[] args) {
        String line;
        TaskHandler th = new TaskHandler();

        Scanner in = new Scanner(System.in);

        sayHello();

        Formatter.printInputStart();
        line = in.nextLine();
        
        while (!th.inputIsBye(line.toLowerCase())) {
            try {
                Formatter.printFormattedOutput(th.handleTasks(line));
            } catch (IllegalArgumentException e) {
                Formatter.printFormattedOutput(e.getMessage());
            } catch (DukeException e) {
                Formatter.printFormattedOutput(e.getMessage());
            } finally {
                Formatter.printInputStart();
                line = in.nextLine();
            }
        }

        sayBye();
    }
}
