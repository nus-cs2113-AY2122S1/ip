package duke;

import java.util.Scanner;
import java.io.IOException;

/**
 * This program helps you keep track of your tasks and manage them
 *
 * @author  Peng Yanjia
 * @version 0.2
 * @since   2021-09-01
 */
public class Duke {
    protected Scanner in;

    /** Initialises the program */
    public void run() {
        Ui.startup();
        in = new Scanner(System.in);
        String input = in.nextLine();
        TaskList tasklist = new TaskList();
        Parser parser = new Parser();
        while (true) {
            try {
                parser.parseCommand(input);
                if (parser.executeCommand(tasklist) == -1) {
                    break;
                }
            } catch (StringIndexOutOfBoundsException e) {
                Ui.emptyDescriptionError();
            }
            try {
                Storage.writeToFile("temp/tasks.txt", tasklist);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
