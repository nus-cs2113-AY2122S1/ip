package duke.command;

import duke.task.TaskManager;

import java.util.Scanner;

public class Duke {
    public static void printDividerLine() {
        System.out.println("\t_____________________________________________________________________________");
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printDividerLine();
        System.out.println("\tHello! I'm Duke!\n\tWhat can I do for you?");
        printDividerLine();
        System.out.println("\tHere are the performable actions:");
        System.out.println("\t 1. Add a new To Do by typing \"todo {content of your to do}\".");
        System.out.println("\t 2. Add a new Deadline by typing \"deadline {content of your deadline} /by {date of deadline}\".");
        System.out.println("\t 3. Add a new Event by typing \"event {content of your event} /at {date of event}\".");
        System.out.println("\t 4. Mark a task as done by typing in \"done\" and the index of the task on the list.");
        System.out.println("\t 5. Check all the tasks you have added by typing in \"list\". Done tasks will be marked with an X.");
        System.out.println("\t 6. End the program by typing in \"bye\".");
        printDividerLine();
    }

    public static void printByeMessage() {
        printDividerLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printDividerLine();
    }

    public static void printHandleWrongInput() {
        printDividerLine();
        System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDividerLine();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        String line;

        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        TaskManager t1 = new TaskManager();

        while (!line.equalsIgnoreCase("bye")) {
            String[] words = line.split(" ");
            if (line.equalsIgnoreCase("list")) {
                t1.listTasks();
            } else if (words[0].equalsIgnoreCase("done")) {
                if (words.length > 1) {
                    t1.markAsDone(words[1]);
                } else {
                    printDividerLine();
                    System.out.println("\t ☹ OOPS!!! There must be an input after done.");
                    printDividerLine();
                }
            } else if (words[0].equalsIgnoreCase("todo") || words[0].equalsIgnoreCase("deadline")
                    || words[0].equalsIgnoreCase("event")) {
                t1.addTask(line);
            } else {
                printHandleWrongInput();
            }
            line = in.nextLine();
        }
        printByeMessage();
    }
}