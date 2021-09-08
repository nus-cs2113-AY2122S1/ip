import java.util.Scanner;
import java.lang.String;

public class Duke {
    public static final int FIND_TASK_TODO = 5;
    public static final int FIND_TASK_DEADLINE = 9;
    public static final int FIND_TASK_EVENT = 6;
    public static final int FIND_TIME = 3;
    public static List[] list = new List[100];
    public static List[] doneList = new List[100];
    public static int count = 0;
    public static int doneCount = 0;
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
                doneTask(order);
                continue;
            } else if (order.contains("list")) {
                printList();
            } else {
                if (order.contains("todo")) {
                    try {
                        String task = order.substring(order.indexOf("todo") + FIND_TASK_TODO);
                        list[count] = new Todo(task);
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
                        list[count] = new Event(task, at);
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
                        list[count] = new Deadline(task, by);
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
                System.out.println("     " + list[count]);
                System.out.println("    Now you have " + (count + 1) + " task(s) in the list.");
                System.out.println(SPLIT_LINE);
                count++;
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
        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + "." + list[i]);
        }
        System.out.println(SPLIT_LINE);
    }

    public static void printDoneList() {
        System.out.println("    Congratulations! Now you have done " + doneCount + " task(s):");
        for (int i = 0; i < doneCount; i++) {
            System.out.println("    " + (i + 1) + "." + doneList[i]);
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
        if (index >= count) {
            System.out.println(SPLIT_LINE);
            System.out.println("    Out of range. Please try again");
            System.out.println(SPLIT_LINE);
            return;
        } else {
            list[index].isDone = true;
            list[index].description = list[index].description.replaceFirst(" ", "X");
            doneList[doneCount] = list[index];
            doneCount++;
            System.out.println(SPLIT_LINE);
            System.out.println("    Nice! I've marked this task as done:\n    "
                + list[index]);
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

/*
public class Duke {
    public static int count = 0;
    public static int doneCount = 0;
    public static List[] list = new List[100];
    public static List[] doneList = new List[100];
    public static final String  SPLIT_LINE= "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

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
        for (int i = 0; i < count; i++) {
            if (str.equals(list[i].description.substring(0, str.length()))) {
                doneList[doneCount].description = list[i].description.substring(str.length() + 1);
                list[i].description = list[i].description.replaceFirst(" ", "X");
                doneList[doneCount].description = doneList[doneCount].
                    description.replaceFirst(" ", "X");
                doneCount++;
                list[i].markAsDone();
                List.printDoneTask();
                break;
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(SPLIT_LINE + "\n    Hello! I'm Duke\n" +
            "    What can I do for you?\n" + SPLIT_LINE);

        while (true) {
            Scanner in = new Scanner(System.in);
            String order;
            order = in.nextLine();
            if (order.equals("bye")) {
                break;
            } else if (order.contains("done")) {
                doneTask(order);
                continue;
            } else if (order.contains("list")) {
                List.printTask();
            } else if (order.contains("todo")) {
                List.addTask(order);
                String taskName = order.substring(order.indexOf("todo") + 5);
                list[count] = new Todo(taskName);
            } else if (order.contains("deadline")) {
                List.addTask(order);
                String taskName = order.substring(order.indexOf("deadline") + 9, order.indexOf("/") - 1);
                String by = order.substring(order.indexOf("by") + 3);
                list[count] = new Deadline(taskName, by);
            } else if (order.contains("event")) {
                List.addTask(order);
                String taskName = order.substring(order.indexOf("event") + 6, order.indexOf("/") - 1);
                String at = order.substring(order.indexOf("at") + 3);
                list[count] = new Event(taskName, at);
            }
        }
        System.out.println(SPLIT_LINE + "\n    Bye. Hope to see you again soon!\n" + SPLIT_LINE);

    }
}


 */