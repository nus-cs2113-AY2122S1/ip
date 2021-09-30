package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandType;

public class Duke {

    public static void main(String[] args) {
        greetUserOnStart();
        executeDuke();
        greetUserOnEnd();
    }

    /**
     * Prints greeting message to user on startup
     */
    public static void greetUserOnStart() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Retrieves data from backup (if any), then executes program until user exits with exit command
     */
    private static void executeDuke() {
        Storage.importData();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Command command = CommandParser.parse(input);
        while (command.getType() != CommandType.EXIT) {
            try {
                command.run(true);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = in.nextLine();
            command = CommandParser.parse(input);
        }
    }


    /**
     * Prints greeting message to user on exit
     */
    public static void greetUserOnEnd() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
