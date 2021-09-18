package herrekt;

import herrekt.exceptions.InvalidInputException;
import herrekt.exceptions.NoTaskException;
import herrekt.taskmanager.*;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Herrekt {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

    public Herrekt(String filePath) {
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            this.ui.printLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        String phrase = sc.nextLine();

        while (!phrase.equals("bye")) {
            try {
                if (phrase.equals("list")) {
                    ui.printTaskList(tasks);
                } else if (phrase.contains("done")) {
                    int taskNumber = parser.parseDoneInputToInt(phrase);
                    tasks.markAsDone(taskNumber);
                    ui.printMarkTaskAsDone(taskNumber, tasks);
                } else if (phrase.contains("delete")) {
                    int taskNumber = parser.parseDeleteInputToInt(phrase);
                    ui.printTaskDeleted(taskNumber, tasks);
                    tasks.delete(taskNumber);
                } else {
                    isInputValid(phrase);
                    Task task = parser.parsePhraseToTask(phrase);
                    tasks.add(task);
                    ui.printNumberOfTasks(tasks);
                }
            } catch (ArrayIndexOutOfBoundsException e3) {
                ui.printIncorrectFormatError(phrase);
            } catch (IndexOutOfBoundsException e) {
                ui.printInputBiggerThanTaskList(tasks);
            } catch (InvalidInputException e1) {
                ui.printInvalidInputError(phrase);
            } catch (NoTaskException e2) {
                ui.printNoTaskError(phrase);
            }
            phrase = sc.nextLine();
        }
        storage.save(tasks);
        ui.printFarewellMessage();
        sc.close();
    }

    public static void main(String[] args) {
        new Herrekt("save.txt").run();

    }

    static void isInputValid(String phrase) throws InvalidInputException, NoTaskException {
        if (!(phrase.contains("todo")
                || phrase.contains("deadline")
                || phrase.contains("event"))
            || !(phrase.split(" ")[0].equals("todo")
                || phrase.split(" ")[0].equals("deadline")
                || phrase.split(" ")[0].equals("event"))) {
            throw new InvalidInputException("ERROR! Please follow format in README.md" + "\n"
                    + "Your input: " + phrase);
        } else if (phrase.split(" ").length == 1) {
            throw new NoTaskException("ERROR! What is the task?" + "\n"
                    + "Your input: " + phrase);
        }
    }
}
