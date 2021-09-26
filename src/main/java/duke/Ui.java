package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
import duke.parser.ParseFromRawFormat;
import duke.parser.Parser;
import duke.task.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The user interface class to interact with the user.
 */
public class Ui {

    private static final String SEPARATOR = "\t==============================================";
    private static final String LOGOART = "\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@..............@@@@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@(...................@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@.....................@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@.................../@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@@.................../@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@((#@.((((((((((............@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@#((((((((((((((/(((/.........@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@%#(((((((((((((((//*(/((/,@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@#####(((((((((((/(((/(((@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@#########((((/(((((((@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@%######(####%#&@@@@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@(.,,*/(###((((#/##@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@(...,,*//(((((((###/#@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@* ..,,((((((((((####/#@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@((((((((((((((((####/#%@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@((((((((((((((#######@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@@@@@@%((((((((###&@@@@@@@@@@@@@@@\n" +
            "\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
    private static final String NAMEART = "\t _______  __   __  __   __  ______    _______ \n" +
            "\t|       ||  |_|  ||  | |  ||    _ |  |       |\n" +
            "\t|  _____||       ||  | |  ||   | ||  |    ___|\n" +
            "\t| |_____ |       ||  |_|  ||   |_||_ |   |___ \n" +
            "\t|_____  ||       ||       ||    __  ||    ___|\n" +
            "\t _____| || ||_|| ||       ||   |  | ||   |    \n" +
            "\t|_______||_|   |_||_______||___|  |_||___|    ";

    /**
     * Print the welcome message to the user.
     */
    public void printWelcomeMessage() {
        System.out.println(LOGOART + "\n" + NAMEART);
        System.out.println(SEPARATOR);
        System.out.println("\t...la la la la la la sing a happy song\n");
        System.out.println("\tWelcome to Smurf Village.");
        System.out.println("\tStart smurfing now!!");
        System.out.println(SEPARATOR);
    }

    /**
     * Print the tasks marked done.
     *
     * @param task The task marked done.
     * @param itemNum The item index of the task marked done.
     */
    public void printDoneTask(Task task, int itemNum) {
        System.out.println(SEPARATOR);
        System.out.println("\tBrainy Smurf: aah another thing done");
        System.out.println("\t" + itemNum + ". " + task);
        System.out.println(SEPARATOR);
    }

    /**
     * Print the deleted task.
     *
     * @param deletedTask The task deleted.
     */
    public void printDeleteTask(Task deletedTask) {
        System.out.println(SEPARATOR);
        System.out.println("\tI will get Weakling smurf to do it for you.");
        System.out.println("\t   " + deletedTask);
        System.out.println(SEPARATOR);
    }

    /**
     * Print the new task added.
     *
     * @param task The new task.
     * @param taskIndex The index of the task.
     */
    public void printAddTask(Task task, int taskIndex) {
        System.out.println(SEPARATOR);
        System.out.println("\tHandy Smurf is here to give you a hand!");
        System.out.println("\tI have added: ");
        System.out.println("\t" + taskIndex + ". "+ task);
        System.out.println(SEPARATOR);
    }

    /**
     * Print all tasks with that contains a certain string.
     *
     * @param tasks The ArrayList of tasks.
     * @param description The string to match in task description.
     */
    public void printFindTask(ArrayList<Task> tasks, String description) {
        boolean hasFound = false;
        System.out.println(SEPARATOR);
        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(description)) {
                System.out.println("\t" + (tasks.indexOf(task) + 1) + ". " + task);
                hasFound = true;
            }
        }
        if (!hasFound) {
            System.out.println("\tno tasks found!");
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Print the task list.
     *
     * @param tasks The ArrayList of tasks.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println(SEPARATOR);
        System.out.println("\t\"Tracker Smurf!! I need you here!!\"");
        for (Task task : tasks) {
            System.out.println("\t" + (tasks.indexOf(task) + 1) + ". "+ task);
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Print the default error message.
     */
    public void printErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\toops i can't find a smurf to help you with your task.");
        System.out.println(SEPARATOR);
    }

    /**
     * Print the error message that indicates the user did not provide a description.
     */
    public void printEmptyDescriptionErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tHey you needa gimme a description!!");
        System.out.println(SEPARATOR);
    }

    /**
     * Print the error message that indicates the user did not provide a time field.
     */
    public void printEmptyTimeFieldErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tHey you didn't provide a time :(");
        System.out.println(SEPARATOR);
    }

    /**
     * Print the error message the indicates IO Exception.
     *
     * @param exception The IOException object.
     */
    public void printIoExceptionErrorMessage(IOException exception) {
        System.out.println("something went wrong while saving..." + exception.getMessage());
    }

    /**
     * Print the error message that indicates wrong time format.
     */
    public void printWrongTimeFormatErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tplease give your time field in YYYY-MM-DD format");
        System.out.println(SEPARATOR);
    }

    /**
     * Print the exit message.
     */
    public void printExitMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\toh shucks! Gargamel is here..we gotta hide");
        System.out.println(SEPARATOR);
    }

    /**
     * Read user input from command line.
     * @param in The scanner object from stdout.
     * @return The Parser object that handles the user input.
     * @throws EmptyDescriptionException Indicates that the user did not provide a task description.
     * @throws EmptyTimeFieldException Indicated that the user did not provide a time field for event or deadline tasks.
     */
    public Parser readUserInput(Scanner in) throws EmptyDescriptionException, EmptyTimeFieldException {
        System.out.println("\tCall out a smurf to do a job for you!");
        return new ParseFromRawFormat(in.nextLine());
    }
}
