package herrekt;

import herrekt.taskmanager.TaskList;

public class Ui {

    void printWelcomeMessage() {
        String greeting = "Hi! I'm Herrick, your task manager." + "\n"
                + "What would you like to add to your timetable?";
        System.out.println(greeting);
    }

    void printFarewellMessage() {
        System.out.println("Bye. Hope you will complete everything for today!");
    }

    void printTaskList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no outstanding tasks. Good Job!");
        } else {
            System.out.println("Here are the tasks in your list:" + "\n" + tasks.toString());
        }
    }

    void printLoadingError() {
        System.out.println("ERROR! Could not locate save.txt. Creating new one.");
    }

    void printInputBiggerThanTaskList(TaskList tasks) {
        System.out.println("ERROR! You only have " + tasks.getSize() + " current tasks");
    }

    void printInvalidInputError(String phrase) {
        System.out.println("ERROR! Please follow format in README.md");
        System.out.println("Your input: " + phrase);
    }

    void printInvalidTaskError(String phrase) {
        System.out.println("ERROR! What is the task?");
        System.out.println("Your input: " + phrase);
    }

    void printIncorrectFormatError(String phrase) {
        System.out.println("ERROR! I tried to "
                + "Please separate deadline/event and time with '/by' and '/at' respectively.");
        System.out.println("Your input: " + phrase);
    }

    void printInvalidDateError(String phrase) {
        System.out.println("ERROR! The deadline date appears to be invalid.");
        System.out.println("Please use the YYYY-MM-DD format.");
        System.out.println("Your input: " + phrase);
    }

    public void printNoNumericInputError(String phrase) {
        System.out.println("ERROR! Please input an acceptable number after done/delete command");
        System.out.println("Your input: " + phrase);
    }
}
