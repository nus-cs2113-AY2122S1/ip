package duke.command;


import java.io.IOException;

public class Duke {

    /**
     * Creates a file storage of filename.
     * Instantiate an instance of tasklist.
     *
     * @param fileName name of txt file to store data
     */
    public Duke(String fileName) {
        Storage storage = new Storage(fileName);
        TaskList tasks = new TaskList();
    }


    public void run() {
        try {
            Storage.loadFromFile();
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
