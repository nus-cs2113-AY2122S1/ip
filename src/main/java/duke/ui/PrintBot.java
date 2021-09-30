package duke.ui;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

import java.util.ArrayList;

/*
 * This class prints the results of manipulation
 * from ActionBot to the CLI User Interface.
 */
public class PrintBot {
    static final String LINE = "--------------------------------";

    public static void print(String input) {
        System.out.println(input);
    }

    public void line() {
        print(LINE);
    }

    /*
     * Prints to the screen a welcome message immediately after
     * the user has keyed in the correct trigger sentence
     * (I solemnly swear that I am up to no good.).
     */
    public void greet() {
        String logo = "  _                                         _   _            \n" +
                " | |__   __ _ _ __ _ __ _   _   _ __   ___ | |_| |_ ___ _ __ \n" +
                " | '_ \\ / _` | '__| '__| | | | | '_ \\ / _ \\| __| __/ _ \\ '__|\n" +
                " | | | | (_| | |  | |  | |_| | | |_) | (_) | |_| ||  __/ |   \n" +
                " |_| |_|\\__,_|_|  |_|   \\__, | | .__/ \\___/ \\__|\\__\\___|_|   \n" +
                "                        |___/  |_|                         \n" +
                "  S. Lu - I solemnly swear that I am up to no good.\n";
        print("Hello from\n" + logo);
        print(
                "       Messrs Moony, Wormtail, Padfoot, and Prongs\n" +
                        "       Purveyors of Aids to Magical Mischief-Makers\n" +
                        "                  are proud to present\n" +
                        "                 --THE MARAUDER'S MAP--");
    }

    /*
     * Prints to screen a message when user types in "hello".
     */
    public void hello() {
        print("I see you are lost. \n" +
                "Read the charm beneath out loud, and I shall serve you.");
        print("- \"I solemnly swear that I am up to no good.\" ");
    }

    /*
     * Prints to screen a farewell message when user types in "bye".
     * The duke program exits after this method.
     */
    public void exit() {
        line();
        print("Mischief managed.");
        line();
    }

    /*
     * Prints to screen the task that has been added
     * and the updated amount of tasks in the list.
     */
    public void addTask(Task t, int taskCount) {
        print("|| Got it. I've added this task");
        print("|| \t" + t.toString());
        print("|| Now you have " + taskCount + " tasks in the list.");
    }

    /*
     * Prints to screen the task that has been marked
     * as Done just now. Shows the task's position in
     * the list as well.
     */
    public void markDone(int id, boolean isDone, Task t) throws InvalidIndexException, EmptyListException {
        if (isDone) {
            print("Nice!I've marked this task as done:");
            print(id + ". " + t.toString());
        }
    }

    /*
     * Tells the user if the input word exists inside the task list.
     * Print out the id and description of all results.
     */
    public void searchList(ArrayList<String> searchResult) {
        if (searchResult.isEmpty()) {
            print("No task found.");
        } else {
            print(" Here are the matching tasks in your list: ");
            for (String s : searchResult) {
                print(s);
            }
        }
    }

    public static void loadingData() {
        print("Please wait. Loading data ..... ");
    }

    public static void loadData(String data) {
        print(data);
    }

    /*
     * Prints the information of the task that
     * has just been deleted from the list.
     */
    public void delete(Task t, int taskCount) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("|| \t" + t.toString());
        System.out.println("|| Now you have " + taskCount + " in the list.");
    }

    /*
     * Prints four sentences, one by one if the user continues to
     * get the activation phrase wrong.
     */
    public void printFalseCharmMsg (int n) {
        if (n == 0) {
            System.out.println("Mr Moony presents his compliments to Professor Snape \n" +
                    "and begs him to keep his abnormally large nose \n" +
                    "out of other people's business. ");
        } else if (n == 1) {
            System.out.println("Mr Prongs agrees with Mr Moony \n" +
                    "and would like to add that Professor Snape is an ugly git.");
        } else if (n == 2) {
            System.out.println("Mr Padfoot would like to register \n" +
                    "his astonishment that an idiot like that ever became a Professor.");
        } else if (n == 3) {
            System.out.println("Mr Wormtail bids Professor Snape good day, \n" +
                    "and advises him to wash his hair, the slime-ball.\"");
        }
    }
}
