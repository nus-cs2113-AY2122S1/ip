import java.util.Scanner;

public class Duke {
    private static Task[] items = new Task[100];
    private static int taskCount = 0;
    private static String logo = " _____  ___ _____\n"
            + "|___  | | ||_____| \n"
            + "   / /  | |   / / \n"
            + "  / /   | |  / /  \n"
            + " / /___ | | /_/__  \n"
            + "|_____| | ||_____| \n";
    private static String border = "____________________________________________________________\n";

    public static void addedTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public static void addEvent(String description, String time) {
        Events newEvent = new Events(description, time);
        items[taskCount] = newEvent;
        taskCount++;
        addedTaskMessage(newEvent);
    }
    public static void addDeadline(String description, String by) {
        Deadlines newDeadline = new Deadlines(description, by);
        items[taskCount] = newDeadline;
        taskCount++;
        addedTaskMessage(newDeadline);
    }
    public static void addTodo(String description) {
        Todo newTodo = new Todo(description);
        items[taskCount] = newTodo;
        taskCount++;
        addedTaskMessage(newTodo);
    }
    public static void addTask(String description) {
        System.out.println(border + "added: " + description + '\n' + border);
        Task newItem = new Task(description);
        items[taskCount] = newItem;
        taskCount++;
    }
    public static void printStartMessage() {
        System.out.println(logo);
        System.out.println(border + "Hi bro, my name is Echo");
        System.out.println("What do you want?\n" + border);
        System.out.println("Type bye to exit\n" + border);
    }
    public static void main(String[] args) {
        String line;
        printStartMessage();

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.matches("list")) {
                int j = 1;
                System.out.println(border);
                System.out.println("Here is your list");

                for (Task item : items) {
                    if (item != null) {
                        System.out.print(j + ".");
                        System.out.println(item);
                        j++;
                    }
                }
                System.out.println(border);

            } else if (line.length() > 4 && line.substring(0,4).contains("done")) {
                int dividerPosition = line.indexOf(" ") + 1;
                int endPosition = line.length();
                if (endPosition > 5) {
                    String num = line.substring(dividerPosition, endPosition);
                    int taskNum = Integer.parseInt(num) - 1;
                    items[taskNum].markDone();
                    System.out.println(border + "Nice! task is done " + '\n' + border);
                }
            } else if (line.length() > 4 && line.substring(0,4).contains("todo")) { //improve condition to first word
                addTodo(line.replace("todo","").trim());
            } else if (line.length() > 5 &&line.substring(0,5).contains("event")) {
                String time = line.split("/at")[1].trim();
                String description = line.split("/at")[0].trim().replace("event","").trim();
                addEvent(description, time);
            } else if (line.length() > 8 && line.substring(0,8).contains("deadline")) {
                String by = line.split("/by")[1].trim();
                String description = line.split("/by")[0].trim().replace("deadline","").trim();
                addDeadline(description, by);
            } else {
                addTask(line);
            }
        } while (!line.matches("bye"));
        System.out.println(border);
        System.out.println("chat again next time!\n" + border);
    }
}
