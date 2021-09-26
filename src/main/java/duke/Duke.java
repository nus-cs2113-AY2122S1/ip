package duke;

import duke.DukeExceptions.EmptyCommand;
import duke.DukeExceptions.InvalidCommandException;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasksLs;
    private Ui ui;

    private Scanner in = new Scanner(System.in);

    /*public Duke(String filepath) {
        this.tasksLs = new TaskList(new ArrayList<Task>());
        this.storage = new Storage(filepath);
        this.ui = new Ui();
    }*/

    public Duke() {
        this.tasksLs = new TaskList(new ArrayList<Task>());
        this.storage = new Storage("duke.txt");
        this.ui = new Ui();
    }
    
    private void run() {
        String input = "";
        ui.greetMessage();
        try {
            tasksLs = storage.loadFile(tasksLs.getList());
        } catch (FileNotFoundException e) {
            storage.createFile();
        }

        boolean closeDuke = false;
        do {
            try {
                input = getInput();
                String parsed = new Parser(tasksLs, ui).parse(input);
                System.out.println(parsed);
                storage.autoSave(tasksLs.getList());
                closeDuke = input.trim().equals("bye");
            } catch (InvalidCommandException e) {
                System.out.println(e.printMessage());
            } catch (EmptyCommand e) {
                System.out.println(e.printMessage());
            }
        } while (!closeDuke);
    }

    private String getInput() {
        return in.nextLine();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
