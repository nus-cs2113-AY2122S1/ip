package duke;

import java.util.Scanner;
import java.io.IOException;

public class Duke {
    protected Scanner in;

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
