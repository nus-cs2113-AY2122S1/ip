package duke;

import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;


    //constructor for duke class
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
        //if method is static, other class cannot call it. But why?

    }


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
