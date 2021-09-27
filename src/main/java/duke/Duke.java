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
    private final Ui ui;

    private final Scanner in = new Scanner(System.in);

    /**
     * A constructor to initiate Duke.
     */
    public Duke() {
        this.tasksLs = new TaskList(new ArrayList<Task>());
        this.storage = new Storage("duke.txt");
        this.ui = new Ui();
    }

    /**
     * Runs Duke object until user exits with </bye>.
     */
    private void run() {
        ui.greetMessage();
        String input = "";
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
                System.out.println(e.toString());
            } catch (EmptyCommand e) {
                System.out.println(e.toString());
            }
        } while (!closeDuke);
    }

    /**
     * Prompts user for input.
     *
     * @return String input by user.
     */
    private String getInput() {
        return in.nextLine();
    }

    /**
     * application.
     * @param args to start Duke Application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
