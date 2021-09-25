package duke.command;


import java.io.IOException;

public class Duke {

    public Duke(String fileName) {
        Storage storage = new Storage(fileName);
        TaskList tasks = new TaskList();
    }

    public void run() {
        try {
            Storage.load();
            Ui.greet();
            Ui.instructions();
            Parser.chooseTask();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();


    }

}
