import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Exceptions.UnknownCommandException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    //main function to process input

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(Storage.readData("Duke.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(ui.fileNotFound);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("An error has occurred!");
        }
    }

    public void run() {
        ui.greetUser();

        File dukeData = new File("Duke.txt");

        String fullCommand;

        boolean isExit = false;

        //user input loop
        while (!isExit) {
            fullCommand = ui.readCommand();

            //checks for exit command
            if (fullCommand.equals("bye")) {
                ui.sayGoodbye();
                return;
            }

            //process the other commands
            try {
                Parser.processLine(tasks, fullCommand);
                storage.updateData(tasks, dukeData.getPath());
            } catch (UnknownCommandException | StringIndexOutOfBoundsException | InvalidCommandException e) {
                System.out.println(ui.invalidTaskError);
            } catch (EmptyTaskException e) {
                System.out.println(ui.emptyTaskError);
            } catch (IOException e) {
                System.out.println("An error has occurred!");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("Duke.txt").run();
    }
}
