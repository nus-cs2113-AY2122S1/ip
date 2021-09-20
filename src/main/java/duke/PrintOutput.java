package duke;

import duke.task.Task;

import java.util.ArrayList;

public interface PrintOutput {
    static void printWelcomeMessage() {
        System.out.println(Duke.logoArt + "\n" + Duke.nameArt);
        System.out.println(Duke.SEPARATOR);
        System.out.println("\t...la la la la la la sing a happy song\n");
        System.out.println("\tWelcome to Smurf Village.");
        System.out.println("\tStart smurfing now!!");
        System.out.println(Duke.SEPARATOR);
    }

    static void printDoneTask(Task task, int itemNum) {
        System.out.println(Duke.SEPARATOR);
        System.out.println("\tBrainy Smurf: aah another thing done");
        System.out.printf("\t%d. [%s][%s] %s\n", itemNum, task.getTaskIcon(), task.getStatusIcon(),
                task.getDescription());
        System.out.println(Duke.SEPARATOR);
    }

    static void printDeleteTask(Task deletedTask) {
        System.out.println(Duke.SEPARATOR);
        System.out.println("\tI will get Weakling smurf to do it for you.");
        System.out.printf("\t   [%s][%s] %s\n", deletedTask.getTaskIcon(), deletedTask.getStatusIcon(),
                deletedTask.getDescription());
        System.out.println(Duke.SEPARATOR);
    }

    static void printAddTask(Task task) {
        System.out.println(Duke.SEPARATOR);
        System.out.println("\tHandy Smurf is here to give you a hand!");
        System.out.println("\tI have added: ");
        System.out.println("\t" + (Duke.tasks.indexOf(task) + 1) + task);
        System.out.println(Duke.SEPARATOR);
    }

    static void printTaskList(ArrayList<Task> tasks) {
        System.out.println(Duke.SEPARATOR);
        System.out.println("\t\"Tracker Smurf!! I need you here!!\"");
        for (Task task : Duke.tasks) {
            System.out.println("\t" + (tasks.indexOf(task) + 1) + task);
        }
        System.out.println(Duke.SEPARATOR);
    }

    static void printErrorMessage() {
        System.out.println(Duke.SEPARATOR);
        System.out.println("\toops i can't find a smurf to help you with your task.");
        System.out.println(Duke.SEPARATOR);
    }

    static void printEmptyDescriptionErrorMessage() {
        System.out.println(Duke.SEPARATOR);
        System.out.println("\tHey you needa gimme a description!!");
        System.out.println(Duke.SEPARATOR);
    }

    static void printEmptyTimeFieldErrorMessage() {
        System.out.println(Duke.SEPARATOR);
        System.out.println("\tHey you didn't provide a time :(");
        System.out.println(Duke.SEPARATOR);
    }

    static void printExitMessage() {
        System.out.println(Duke.SEPARATOR);
        System.out.println("\toh shucks! Gargamel is here..we gotta hide");
        System.out.println(Duke.SEPARATOR);
    }
}
