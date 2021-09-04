import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    public static void printList(Task[] list) {
        System.out.println(LINE);
        if (list[0] == null) {
            System.out.println("No items added!");
        }
        int i = 0;
        while (list[i] != null) {
            System.out.println((i+1) + ". " + list[i]);
            i += 1;
        }
        System.out.println(LINE);
    }

    public static void markTaskAsDone(Task[] list, String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            if (taskIndex < Task.numItemsAdded && taskIndex >= 0) {
                if (list[taskIndex].isDone()) {
                    System.out.println(LINE + "\nThat task is already done!\n" + LINE);
                } else {
                    list[taskIndex].markAsDone();
                    System.out.println(LINE + "\nNice! I've marked this task as done:");
                    System.out.println(list[taskIndex] + "\n" + LINE);
                }
            } else {
                System.out.println("That task does not exist!\n" + LINE);
            }
        } catch(NumberFormatException e) {
            System.out.println(LINE + "\nPlease enter a number after \'done\'!\n" + LINE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE + "\nPlease enter a number after \'done\'!\n" + LINE);
        }
    }

    public static void printAddedTask(Task[] list) {
        System.out.println(LINE + "\nGot it. I've added this task:");
        System.out.println(list[Task.numItemsAdded-1]);
        System.out.format("Now you have %d tasks in the list.\n" + LINE + "\n", Task.numItemsAdded);
    }

    public static void addTodo(Task[] list, String input, String[] inputWords) {
        try {
            String taskName = input.substring(5);
            list[Task.numItemsAdded] = new Todo(taskName);
            Task.numItemsAdded += 1;
            printAddedTask(list);
        } catch(StringIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n☹ OOPS!!! The description of a todo cannot be empty.\n" + LINE);
        }
    }

    public static void addDeadline(Task[] list, String input, String[] inputWords) {
        try {
            if (!input.contains("/by")) {
                System.out.println(LINE + "\nIncorrect format for entering deadline!\n" + LINE);
                return;
            }
            int taskEndIndex = input.indexOf("/by") - 1;
            String taskName = input.substring(9, taskEndIndex);
            String deadline = input.substring(taskEndIndex + 5);
            list[Task.numItemsAdded] = new Deadline(taskName, deadline);
            Task.numItemsAdded += 1;
            printAddedTask(list);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n☹ OOPS!!! The description of a deadline cannot be empty.\n" + LINE);
        }
    }

    public static void addEvent(Task[] list, String input, String[] inputWords) {
        try {
            if (!input.contains("/at")) {
                System.out.println(LINE + "\nIncorrect format for entering event!\n" + LINE);
                return;
            }
            int taskEndIndex = input.indexOf("/at") - 1;
            String taskName = input.substring(6, taskEndIndex);
            String at = input.substring(taskEndIndex + 5);
            list[Task.numItemsAdded] = new Event(taskName, at);
            Task.numItemsAdded += 1;
            printAddedTask(list);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n☹ OOPS!!! The description of an event cannot be empty.\n" + LINE);
        }
    }

    public static void addTask(Task[] list, String input, String[] inputWords) {
        String taskType = inputWords[0].toLowerCase();
        try {
            switch(taskType) {
            case "todo":
                addTodo(list, input, inputWords);
                break;
            case "deadline":
                addDeadline(list, input, inputWords);
                break;
            case "event":
                addEvent(list, input, inputWords);
                break;
            default:
                throw new DukeException();
            }
        } catch(DukeException e) {
            System.out.println(LINE + "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
        }
    }

    public static void main(String[] args) { 
        System.out.println(LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE);
        Boolean isCompleted = false;
        Task[] list = new Task[100];
        while (!isCompleted) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] inputWords = input.split(" ");
            if (input.equalsIgnoreCase("bye")) {
                isCompleted = true;
                in.close();
                continue;
            }
            if (input.equalsIgnoreCase("list")){
                printList(list);
                continue;
            }
            if (inputWords[0].equalsIgnoreCase("done")) {
                markTaskAsDone(list, inputWords);
                continue;
            }
            addTask(list, input, inputWords);
        }
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }
}
