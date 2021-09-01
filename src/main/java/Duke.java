// import libraries here

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> list = new ArrayList<>();
    private static ArrayList<Integer> done = new ArrayList<>();
    private static ArrayList<String> type = new ArrayList<>();
    private static ArrayList<String> date = new ArrayList<>();

    private static void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        // get user input
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + line);
            System.out.println("    ____________________________________________________________");
            line = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    private static void showList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String description = list.get(i);
            String time = date.get(i);
            String category = type.get(i);
            System.out.printf("     %d.", i + 1);
            if (done.get(i) == 1) {
                switch (category) {
                case "T":
                    Todo todo = new Todo(description);
                    System.out.println(todo.doneToString());
                    break;
                case "D":
                    Deadline deadline = new Deadline(description, time);
                    System.out.println(deadline.doneToString());
                    break;
                case "E":
                    Event event = new Event(description, time);
                    System.out.println(event.doneToString());
                    break;
                default: System.out.println("       Unknown Object");
                }
            } else {
                switch (category) {
                case "T":
                    Todo todo = new Todo(description);
                    System.out.println(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(description, time);
                    System.out.println(deadline);
                    break;
                case "E":
                    Event event = new Event(description, time);
                    System.out.println(event);
                    break;
                default: System.out.println("       Unknown Object");
                }
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void addList() {
        String line;
        Scanner in = new Scanner(System.in);
        // get user input
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                showList();
                line = in.nextLine();
                continue;
            }
            if (line.startsWith("done")) {
                int index = Integer.parseInt(line.substring(line.length() - 1));
                markAsDone(index);
                line = in.nextLine();
                continue;
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("     Got it. I've added this task: ");
            done.add(0);
            String description;
            String time;
            if (line.startsWith("todo")) {
                type.add("T");
                description = line.substring(5);
                Todo todo = new Todo(description);
                list.add(description);
                date.add("");
                System.out.println("       " + todo);
            } else if (line.startsWith("deadline")) {
                type.add("D");
                int seperate = line.indexOf("/by");
                description = line.substring(9, seperate - 1);
                time = line.substring(seperate + 4);
                Deadline deadline = new Deadline(description, time);
                list.add(description);
                date.add(time);
                System.out.println("       " + deadline);
            } else if (line.startsWith("event")) {
                type.add("E");
                int seperate = line.indexOf("/at");
                description = line.substring(6, seperate - 1);
                time = line.substring(seperate + 4);
                Event event = new Event(description, time);
                list.add(description);
                date.add(time);
                System.out.println("       " + event);
            } else {
                type.add("unknown");
            }
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
            line = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    private static void markAsDone(int index) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        String description = list.get(index - 1);
        String time = date.get(index - 1);
        String category = type.get(index - 1);
        // mark as done
        done.set(index - 1, 1);
        switch (category) {
        case "T":
            Todo todo = new Todo(description);
            System.out.println("       " + todo.doneToString());
            break;
        case "D":
            Deadline deadline = new Deadline(description, time);
            System.out.println("       " + deadline.doneToString());
            break;
        case "E":
            Event event = new Event(description, time);
            System.out.println("       " + event.doneToString());
            break;
        default: System.out.println("       Unknown Object");
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
//        echo();
        addList();
    }
}