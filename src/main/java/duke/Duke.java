package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

public class Duke {
    public static final int FIND_TASK_TODO = 5;
    public static final int FIND_TASK_DEADLINE = 9;
    public static final int FIND_TASK_EVENT = 6;
    public static final int FIND_TIME = 3;
    public static ArrayList<List> allList = new ArrayList<>();
    public static ArrayList<List> dList = new ArrayList<>();
    private static final String SPLIT_LINE  = "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public static void main(String[] args) {
        showWelcomeScreen();

        while (true) {
            Scanner in = new Scanner(System.in);
            String order;
            order = in.nextLine();
            if (order.contains("bye")) {
                terminateProgram();
                break;
            } else if (order.contains("done")) {
                try {
                    doneTask(order);
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println(SPLIT_LINE);
                    System.out.println("    ☹ OOPS!!! The index of a done command cannot be empty.");
                    System.out.println(SPLIT_LINE);
                    continue;
                }
            } else if (order.contains("list")) {
                printList();
            } else if (order.contains("delete")) {
                try {
                    int index = Integer. parseInt(order.substring(order.indexOf(" ") + 1));
                    System.out.println(SPLIT_LINE);
                    System.out.println("    Noted. I've removed this task: ");
                    System.out.println("    " + allList.get(index - 1));
                    allList.remove(index - 1);
                    System.out.println("    Now you have " + allList.size() + " task(s) in the list.");
                    System.out.println(SPLIT_LINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(SPLIT_LINE);
                    System.out.println("    ☹ OOPS!!! The description of a delete cannot be empty.");
                    System.out.println(SPLIT_LINE);
                    continue;
                }
            } else {
                if (order.contains("todo")) {
                    try {
                        String task = order.substring(order.indexOf("todo") + FIND_TASK_TODO);
                        allList.add(new Todo(task));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(SPLIT_LINE);
                        System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(SPLIT_LINE);
                        continue;
                    }
                } else if (order.contains("event")) {
                    try {
                        String task = order.substring(order.indexOf("event")
                            + FIND_TASK_EVENT, order.indexOf("/") - 1);
                        String at = order.substring(order.indexOf("at") + FIND_TIME);
                        allList.add(new Event(task, at));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(SPLIT_LINE);
                        System.out.println("    ☹ OOPS!!! The description of an event cannot be empty.");
                        System.out.println(SPLIT_LINE);
                        continue;
                    }
                } else if (order.contains("deadline")) {
                    try {
                        String task = order.substring(order.indexOf("deadline")
                            + FIND_TASK_DEADLINE, order.indexOf("/") - 1);
                        String by = order.substring(order.indexOf("by") + FIND_TIME);
                        allList.add(new Deadline(task, by));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(SPLIT_LINE);
                        System.out.println("    ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(SPLIT_LINE);
                        continue;
                    }
                } else {
                    try {
                        invalidInput(true);
                    } catch (DukeException e) {
                        System.out.println(SPLIT_LINE);
                        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(SPLIT_LINE);
                        continue;
                    }
                }
                System.out.println(SPLIT_LINE);
                System.out.println("    Got it. I've added this task:");
                System.out.println("     " + allList.get(allList.size() - 1));
                System.out.println("    Now you have " + allList.size() + " task(s) in the list.");
                System.out.println(SPLIT_LINE);
            }
        }

    }

    private static void showWelcomeScreen() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(SPLIT_LINE + "\n    Hello! I'm Duke\n"
            + "    What can I do for you?\n" + SPLIT_LINE);
    }

    public static void terminateProgram() {
        System.out.println(SPLIT_LINE);
        System.out.println("    Bye. Hope to see you again soon!\n" + SPLIT_LINE);
    }

    public static void printList() {
        System.out.println(SPLIT_LINE + "\n    Here are the tasks in your list:");
        for (int i = 0; i < allList.size(); i++) {
            System.out.println("    " + (i + 1) + "." + allList.get(i));
        }
        System.out.println(SPLIT_LINE);
    }

    public static void printDoneList() {
        System.out.println("    Congratulations! Now you have done " + dList.size() + " task(s):");
        for (int i = 0; i < dList.size(); i++) {
            System.out.println("    " + (i + 1) + "." + dList.get(i));
        }
        System.out.println(SPLIT_LINE);
    }

    public static void doneTask(String task) {
        task = task.trim();
        String str = "";
        if (task != null && !"".equals(task)) {
            for (int i = 0; i < task.length(); i++) {
                if (task.charAt(i) >= 48 && task.charAt(i) <= 57) {
                    str = str + task.charAt(i);
                }
            }
        }
        int index = Integer.parseInt(str) - 1;
        if (index >= allList.size()) {
            System.out.println(SPLIT_LINE);
            System.out.println("    Out of range. Please try again");
            System.out.println(SPLIT_LINE);
            return;
        } else {
            List temp = allList.get(index);
            temp.description = temp.description.replaceFirst(" ", "X");
            dList.add(temp);
            System.out.println(SPLIT_LINE);
            System.out.println("    Nice! I've marked this task as done:\n    "
                + dList.get(index));
            System.out.println(SPLIT_LINE);
            printDoneList();
        }
    }

    public static void invalidInput(boolean isInValid) throws DukeException{
        if (isInValid) {
            throw new DukeException();
        }
    }
}
