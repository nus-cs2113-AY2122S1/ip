package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandType;

public class Duke {

    public static void main(String[] args) {
        Ui.greetUserOnStart();
        executeDuke();
        Ui.greetUserOnEnd();
    }

    /**
     * Retrieves data from backup (if any), then executes program until user exits with exit command
     */
    private static void executeDuke() {
        Storage.importData();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Command command = Parser.parse(input);
        while (command.getType() != CommandType.EXIT) {
            try {
                command.run(true);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = in.nextLine();
            command = Parser.parse(input);
        }
    }
}
