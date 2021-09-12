package duke;

import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInformationException;
import duke.exceptions.InvalidRequestException;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        TaskList list = new TaskList();
        String request = in.nextLine();
        while (Request.isBye(request)) {
            try {
                if (Request.isList(request)) {
                    list.printTasks();
                } else if (Request.isDone(request)) {
                    list.doneTask(request);
                } else {
                    list.addTask(request);
                }
                request = in.nextLine();
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                request = in.nextLine();
            } catch (Exception ex) {
                if (ex instanceof IndexOutOfBoundsException) {
                    System.out.println("Sorry I can't do that! " +
                            "Try \"done <number inside the list>\" instead :)");
                } else {
                    System.out.println(ex.getClass());
                    System.out.println("Some error i don't know of");
                }
                request = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
