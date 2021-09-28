package duke;

import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor class for Duke
     * Initialises a UI, Storage and TaskList class for Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());

    }

    /**
     * Takes in user input repeatedly
     * If user inputs "bye", the program is terminated
     */
    public void run() {
        ui.showWelcomeMessage();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            Parser.distinguishCommand(line);

            try{
                storage.writeToFile("data/data.txt",tasks.list);
            } catch (IOException e) {
                System.out.println("    OOPS!!! There was an error updating the file storage");
            }

            line = in.nextLine();
        }

        ui.showExitMessage();
    }



    public static void main(String[] args) {

        new Duke().run();

    }


}
