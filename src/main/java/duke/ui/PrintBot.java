package duke.ui;

import duke.exception.DukeException;
import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.task.*;
import duke.util.DukeActions;

import static duke.Duke.tasks;

/*
 * This class prints the results of manipulation
 * from ActionBot to the CLI User Interface.
 */
public class PrintBot implements DukeActions {
    static final String LINE = "--------------------------------";

    public static void print(String input) {
        System.out.println(input);
    }

    public void line() {
        print(LINE);
    }

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

    public void hello() {
        print("I see you are lost. \n" +
                "Read the charm beneath out loud, and I shall serve you.");
        print("- \"I solemnly swear that I am up to no good.\" ");
    }

    public void exit() {
        print("Mischief managed.");
    }

    public void printList() throws EmptyListException {
        print("Here are the tasks in your list:\n");
        int id = 1;
        for (Task t : tasks) {
            System.out.println(id + ". " + t);
            id++;
        }
    }

    @Override
    public void addTask(Task t) {
        print("|| Got it. I've added this task");
        print("|| \t" + t.toString());
        print("|| Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public void markDone(int id, boolean isDone) throws InvalidIndexException, EmptyListException {
        int i = id - 1;
        if (isDone) {
            print("Nice!I've marked this task as done:");
            print(id + ". " + tasks.get(i).toString());
        }
    }


    public void loadingData() {
        print("Please wait. Loading data ..... ");
    }

    @Override
    public void loadData(String data) {
        print(data);
    }

    public void delete(Task t) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("|| \t" + t.toString());
        System.out.println("|| Now you have " + tasks.size() + " in the list.");
    }
}
