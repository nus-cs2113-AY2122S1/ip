import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String LINE = "_______________________________________\n";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n" + LINE);
        inputTask();
    }

    public static void inputTask() {
        Scanner in = new Scanner(System.in);
        String inputTask = in.nextLine();

        while(!inputTask.equals("bye")) {
            try{
                if (inputTask.equals("list")) {
                    System.out.println(LINE + "Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    System.out.println(LINE);
                } else if (inputTask.contains("done")) {
                    int sep = inputTask.indexOf(" ");
                    int number = Integer.parseInt(inputTask.substring(5));
                    markAsDone(number);
                } else if (inputTask.contains("todo")) {
                    addToDo(inputTask);
                } else if (inputTask.contains("deadline")) {
                    addDeadline(inputTask);
                } else if (inputTask.contains("event")) {
                    addEvent(inputTask);
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ");
                System.out.println(LINE);
            }
            inputTask = in.nextLine();
        }
        if (inputTask.equals("bye")) {
            System.out.println(LINE + "Bye. Hope to see you again soon!\n");
        }
        in.close();
    }

    private static void addToDo(String inputTask) {
        try {
            String taskToDo = inputTask.substring(5);
            tasks.add(new ToDo(taskToDo));

            System.out.println("Got it. I've added this task:\n" + "[T][]" + taskToDo + "\n" + "Now you have " + tasks.size() + " tasks in this list.");
            System.out.println(LINE);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println(LINE);
        }
    }

    private static void addDeadline(String inputTask) {
        try {
            String description = inputTask.substring(9);
            int index = description.indexOf("/by");
            String deadlineDescription = description.substring(index + 4);
            String taskDescription = description.substring(0, index);
            tasks.add(new Deadline(taskDescription, deadlineDescription));

            System.out.println("Got it. I've added this task:\n" + "[D][]" + taskDescription + " (by: " + deadlineDescription + ")\n" + "Now you have " + tasks.size() + " tasks in this list.");
            System.out.println(LINE);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            System.out.println(LINE);
        }
    }

    private static void addEvent(String inputTask) {
        try {
            String description = inputTask.substring(6);
            int index = description.indexOf("/at");
            String eventDescription = description.substring(index + 4);
            String taskDescription = description.substring(0, index);
            tasks.add(new Event(taskDescription, eventDescription));

            System.out.println("Got it. I've added this task:\n" + "[E][]" + taskDescription + " (by: " + eventDescription + ")\n" + "Now you have " + tasks.size() + " tasks in this list.");
            System.out.println(LINE);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            System.out.println(LINE);
        }
    }

    public static void markAsDone(int number) {
        tasks.get(number-1).setDone();
        System.out.println(LINE + "Nice! I've marked this task as done:\n");
        System.out.println( tasks.get(number-1).toString());
    }
}