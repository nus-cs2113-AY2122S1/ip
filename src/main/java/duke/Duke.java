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

    public static void greetUserOnStart() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

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

    public static void greetUserOnEnd() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
