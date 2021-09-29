package duke.ui;

import duke.exception.DukeException;
import duke.task.*;
import duke.util.DukeActions;

import static duke.Duke.taskCount;
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

    public void printList() {
        if (taskCount == 0) {
            System.out.println("No items in list yet.");
            return;
        }

        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks[i];
            System.out.println(t.id + ". " + t);
        }
    }

    @Override
    public void addTask(Task t) {
        print("|| Got it. I've added this task");
        print("|| \t" + t.toString());
        print("|| Now you have " + taskCount + " tasks in the list.");
    }

    @Override
    public void markDone(int id, boolean isDone) throws DukeException {
        int i = id - 1;
        if (isDone ) {
            System.out.println("Nice!I've marked this task as done:");
            System.out.println(tasks[i].id + ". " + tasks[i].toString());
        }
    }


    public void loadingData() {
        print("Please wait. Loading data ..... ");
    }

    @Override
    public void loadData(String data) {
        print(data);
    }
}
