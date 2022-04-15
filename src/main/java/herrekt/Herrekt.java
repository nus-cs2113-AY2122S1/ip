package herrekt;

import herrekt.command.Command;
import herrekt.exceptions.InvalidDeadlineException;
import herrekt.exceptions.InvalidFindException;
import herrekt.exceptions.InvalidInputException;
import herrekt.exceptions.InvalidTodoException;
import herrekt.exceptions.InvalidTaskException;
import herrekt.exceptions.InvalidEventException;
import herrekt.taskmanager.TaskList;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Herrekt {
    private final static String NAME_OF_FILE = "save.txt";
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private final Command command;

    /**
     * Initialize the task manager.
     *
     * @param filePath Search for the .txt file and load up any pre-existing task list.
     */
    private Herrekt(String filePath) {
        this.ui = new Ui();
        this.command = new Command();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            this.ui.printLoadingError();
            this.tasks = new TaskList();
        }
    }
    /**
     * Runs the task manager program.
     * Allows the User to input commands.
     * Recognize the commands to perform certain task in managing the current list of tasks.
     */
    private void run() {
        ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        String phrase = sc.nextLine();

        while (!phrase.equals("bye")) {
            try {
                command.isInputValid(phrase);
                if (phrase.equals("list")) {
                    ui.printTaskList(tasks);
                } else if (phrase.startsWith("done")) {
                    command.runDoneCommand(phrase, tasks);
                } else if (phrase.startsWith("delete")) {
                    command.runDeleteCommand(phrase, tasks);
                } else if (phrase.startsWith("find")) {
                    command.runFindCommand(phrase, tasks);
                } else if (phrase.startsWith("help")) {
                    command.runHelpCommand();
                } else {
                    command.runTaskCommand(phrase, tasks);
                }
            } catch (ArrayIndexOutOfBoundsException e1) {
                ui.printIncorrectFormatError(phrase);
            } catch(InvalidFindException e2) {
                ui.printInvalidFindError(phrase);
            } catch(NumberFormatException | StringIndexOutOfBoundsException e2) {
                ui.printInvalidDoneDeleteFindError(phrase);
            } catch (IndexOutOfBoundsException e2) {
                ui.printInputBiggerThanTaskList(tasks);
            } catch (InvalidInputException e3) {
                ui.printInvalidInputError(phrase);
            } catch (InvalidTodoException e4) {
                ui.printInvalidTodoError(phrase);
            } catch (InvalidDeadlineException e4) {
                ui.printInvalidDeadlineError(phrase);
            } catch (InvalidEventException e4) {
                ui.printInvalidEventError(phrase);
            } catch (InvalidTaskException e4) {
                ui.printInvalidTaskError(phrase);
            } catch (DateTimeParseException e5) {
                ui.printInvalidDateError(phrase);
            }
            phrase = sc.nextLine();
        }
        storage.save(tasks);
        ui.printFarewellMessage();
        sc.close();
    }

    public static void main(String[] args) {
        new Herrekt(NAME_OF_FILE).run();
    }
}
