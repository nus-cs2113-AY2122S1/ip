package duke.processes.utility;


import duke.processes.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Interface {
    public static final String lineBreak = "..........................." +
            ".......................................";

    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Greet user
     */
    public static void introductoryMessage() {
        String logo = "  /\\ _ /\\\n"
                + " #  @ @  #    Welcome to IKAROS!\n"
                + " #   ^   #  Your one and only butler\n"
                + " #########";
        System.out.println(lineBreak);

        System.out.println(logo);
        System.out.println(lineBreak);
        System.out.println("Below is your current list of tasks."
                + System.lineSeparator()
                + "What further assistance do you require?");
        System.out.println(lineBreak);
        LocalDate d = LocalDate.now();

        System.out.println("Today's Date: " + d.getDayOfWeek() + ", "
                + d.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
        System.out.println(lineBreak);
    }

    /**
     * Bid Farewell to the user
     */
    public static void goodbyeMessage() {
        System.out.println("GoodBye, Ikaros awaits for future commands");
        System.out.println(Interface.lineBreak);
    }

    public static void printList(ArrayList<Task> tasks) {
        AtomicInteger i = new AtomicInteger();

        tasks.stream()
                .forEach(task -> {
                    i.getAndIncrement();
                    System.out.print(i + ".[" + task.getTaskID() + "]" +
                            "[" + task.getStatusIcon() +
                            "] " + task.description + " ");
                    if (task.getTaskType().equalsIgnoreCase("event")) {
                        System.out.println("(at: " + task.getDate() + ")");
                    } else if (task.getTaskType().equalsIgnoreCase("deadline")) {
                        System.out.println("(by: " + task.getDate() + ")");
                    } else if (task.getTaskType().equalsIgnoreCase("todo")) {
                        System.out.println(task.getDate());
                    }
                });
    }
}
