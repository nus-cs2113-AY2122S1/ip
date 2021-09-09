package duke;

import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private boolean active = false;
    Tasks list = new Tasks();

    public Duke() {
        active = true;
        printLine();
        String logo = "\t ____        _        \n"
                    + "\t|  _ \\ _   _| | _____ \n"
                    + "\t| | | | | | | |/ / _ \\\n"
                    + "\t| |_| | |_| |   <  __/\n"
                    + "\t|____/ \\__,_|_|\\_\\___|";
        System.out.println("\tHello from\n" + logo);
        printLine();
    }

    public boolean getStatus() {
        return active;
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void endDuke() {
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
        active = false;
    }

    public void greet() {
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public void unknownAction() {
        System.out.println("\tSorry! I don't understand");
        printLine();
    }

    public void addList(String item,String type) {
        printLine();
        list.insert(item,type);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + list.getItem(list.getLength()-1));
        System.out.println("\tNow you have " + list.getLength() + " tasks in the list.");
        printLine();
    }

    public void listOut() {
        System.out.print(list);
        printLine();
    }

    public void markDone(String line) {
        try {
            line = line.replaceAll("[^(\\d)]", "");
            int index = Integer.parseInt(line);
            index -= 1;
            list.markDone(index);
        } catch (NumberFormatException e) {
            System.out.println("\tSorry! I don't understand");
        } finally {
            printLine();
        }
    }
}
