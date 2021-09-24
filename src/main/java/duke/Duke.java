package duke;

import duke.tasks.DukeException;
import duke.tasks.TaskList;

import java.io.IOException;
import duke.commands.Command;
import java.util.Scanner;

public class Duke {

    private boolean exit = false;
    private TaskList taskList = new TaskList();
    private Ui ui;
    private Storage storage;

    public Duke(String path) throws IOException {
        this.ui = new Ui();
        ui.hello();
        this.storage = new Storage("data.txt");
        try {
            taskList = storage.read();
        }catch(IOException e){
            System.out.println(e);
        }
    }


    public void run() throws IOException {
        String userInput;
        exit = true;
        while (exit) {
                try {
                    userInput = ui.readCommand();
                    Command c = Parser.parse(userInput, taskList, ui,storage);
                    c.run();
                    exit = c.exit();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
        }
    }

        public static void main (String[]args) throws IOException {
            Duke d = new Duke("data.txt");
            d.run();
        }
}
