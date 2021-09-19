import java.util.Scanner;




public class Duke {

    public static final int DEADLINE_LN = 8;
    public static final int TODO_LN = 4;
    public static final int EVENT_LN = 5;

    private static boolean checkString(String[] arr, String toCheckValue) {
        boolean result = true;

        for (int i = 0; i < arr.length; i++) {
            if (toCheckValue.equalsIgnoreCase(arr[i])) {
                result = false;
                break;
            } else if (arr[i] == null) {
                break;
            }
        }
        return result;
    }

    public static String findCommand(String line) {
        int spaceIndex = line.indexOf(' ');
        if (spaceIndex == -1) {
            return line;
        }
        String command = line.substring(0, spaceIndex);
        return command;
    }

    public static String findContent(String line) {
        int spaceIndex = line.indexOf(" ");
        if (spaceIndex == -1) {
            return line;
        }
        String description = line.substring(spaceIndex);
        return description;
    }

    public static void printList(Task[] schedule, int totalTasks) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < totalTasks; i += 1) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(schedule[i]);
        }
    }

    public static void handleDone(Task[] schedule, String line) {
        int number = Character.getNumericValue(line.charAt(5));
        schedule[number - 1].markAsDone();
    }

    public static void addTodo(Task[] schedule, String content, int totalTasks) {
        Todo t = new Todo(content);
        schedule[totalTasks] = t;
    }

    public static void addDeadline(Task[] schedule, String content, int totalTasks) {
        int byIndex = content.indexOf("/by");
        String description = content.substring(0, byIndex - 1);
        String by = content.substring(byIndex + 4);
        Deadline d = new Deadline(description, by);
        schedule[totalTasks] = d;
    }

    public static void gotItMessage(Task[] schedule, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(schedule[totalTasks - 1]);
        System.out.println("Now you have " + totalTasks +  " tasks in the list.");
    }

    public static void addEvent(Task[] schedule, String content, int totalTasks) {
        int byIndex = content.indexOf("/at");
        String description = content.substring(0, byIndex - 1);
        String eventTime = content.substring(byIndex + 4);
        Event e = new Event(description, eventTime);
        schedule[totalTasks] = e;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Level-3
        String line;
        Scanner in = new Scanner(System.in);
        System.out.print("Type something: ");
        line = in.nextLine();

        // List of variables
        Task[] schedule = new Task[100];
        int totalTasks = 0;

        while (true) {
            // Exit
            String command = findCommand(line);
            String content = findContent(line);

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (command.equalsIgnoreCase("list")) {
                printList(schedule, totalTasks);

            } else if (command.equalsIgnoreCase("done")) {
                handleDone(schedule, line);

            } else if (command.equalsIgnoreCase("todo")) {
                addTodo(schedule, content, totalTasks);
                totalTasks++;
                gotItMessage(schedule, totalTasks);

            } else if (command.equalsIgnoreCase("deadline")) {
                addDeadline(schedule, content, totalTasks);
                totalTasks++;
                gotItMessage(schedule, totalTasks);

            } else if (command.equalsIgnoreCase("event")) {
                addEvent(schedule, content, totalTasks);
                totalTasks++;
                gotItMessage(schedule, totalTasks);

            }

            line = in.nextLine();
        }
    }

}
