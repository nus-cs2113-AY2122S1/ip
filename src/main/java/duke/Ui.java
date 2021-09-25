package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
import duke.parser.ParseFromRawFormat;
import duke.parser.Parser;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void printWelcomeMessage() {
        System.out.println(LOGOART + "\n" + NAMEART);
        System.out.println(SEPARATOR);
        System.out.println("\t...la la la la la la sing a happy song\n");
        System.out.println("\tWelcome to Smurf Village.");
        System.out.println("\tStart smurfing now!!");
        System.out.println(SEPARATOR);
    }

    public void printDoneTask(Task task, int itemNum) {
        System.out.println(SEPARATOR);
        System.out.println("\tBrainy Smurf: aah another thing done");
        System.out.println("\t" + itemNum + ". " + task);
        System.out.println(SEPARATOR);
    }

    public void printDeleteTask(Task deletedTask) {
        System.out.println(SEPARATOR);
        System.out.println("\tI will get Weakling smurf to do it for you.");
        System.out.println("\t   " + deletedTask);
        System.out.println(SEPARATOR);
    }

    public void printAddTask(Task task, int taskIndex) {
        System.out.println(SEPARATOR);
        System.out.println("\tHandy Smurf is here to give you a hand!");
        System.out.println("\tI have added: ");
        System.out.println("\t" + taskIndex + ". "+ task);
        System.out.println(SEPARATOR);
    }

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

    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println(SEPARATOR);
        System.out.println("\t\"Tracker Smurf!! I need you here!!\"");
        for (Task task : tasks) {
            System.out.println("\t" + (tasks.indexOf(task) + 1) + ". "+ task);
        }
        System.out.println(SEPARATOR);
    }

    public void printErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\toops i can't find a smurf to help you with your task.");
        System.out.println(SEPARATOR);
    }

    public void printEmptyDescriptionErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tHey you needa gimme a description!!");
        System.out.println(SEPARATOR);
    }

    public void printEmptyTimeFieldErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tHey you didn't provide a time :(");
        System.out.println(SEPARATOR);
    }

    public void printIoExceptionErrorMessage(IOException exception) {
        System.out.println("something went wrong while saving..." + exception.getMessage());
    }

    public void printWrongTimeFormatErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\tplease give your time field in YYYY-MM-DD format");
        System.out.println(SEPARATOR);
    }

    public void printExitMessage() {
        System.out.println(SEPARATOR);
        System.out.println("\toh shucks! Gargamel is here..we gotta hide");
        System.out.println(SEPARATOR);
    }

    public Parser readUserInput(Scanner in) throws EmptyDescriptionException, EmptyTimeFieldException {
        System.out.println("\tCall out a smurf to do a job for you!");
        return new ParseFromRawFormat(in.nextLine());
//        return new Parser(in.nextLine(), 1);
    }
}
