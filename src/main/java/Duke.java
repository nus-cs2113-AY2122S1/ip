import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final int TODO_NAME_CONSTANT = 5;
    private static final int DEADLINE_NAME_CONSTANT = 9;
    private static final int DEADLINE_BY_CONSTANT = 5;
    private static final int EVENT_NAME_CONSTANT = 6;
    private static final int EVENT_AT_CONSTANT = 5;
    public static final String LINE = "____________________________________________________________";

    private static ArrayList<Task> list = new ArrayList<Task>();

    public static void printList() {
        System.out.println(LINE);
        if (list.size() == 0) {
            System.out.println("No items added!");
        }
        for (int i = 0; i < list.size(); i += 1) {
            System.out.println((i+1) + ". " + list.get(i));
        }   
        System.out.println(LINE);
    }

    public static void markTaskAsDone(String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            if (taskIndex < list.size() && taskIndex >= 0) {
                if (list.get(taskIndex).isDone()) {
                    System.out.println(LINE + "\nThat task is already done!\n" + LINE);
                } else {
                    list.get(taskIndex).markAsDone();
                    System.out.println(LINE + "\nNice! I've marked this task as done:");
                    System.out.println(list.get(taskIndex) + "\n" + LINE);
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

    public static void deleteTask(String[] inputWords) {
        try {
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            if (taskIndex < list.size() && taskIndex >= 0) {
                System.out.format(LINE + "\nNoted. I've removed this task: \n   " + list.get(taskIndex) 
                + "\nNow you have %d tasks in the list.\n" + LINE + "\n", list.size()-1);
                list.remove(taskIndex);
            } else {
                System.out.println("That task does not exist!\n" + LINE);
            }
        } catch(NumberFormatException e) {
            System.out.println(LINE + "\nPlease enter a number after \'done\'!\n" + LINE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE + "\nPlease enter a number after \'done\'!\n" + LINE);
        }
    }

    public static void printAddedTask() {
        System.out.println(LINE + "\nGot it. I've added this task:");
        System.out.println(list.size() + ". " + list.get(list.size() - 1));
        System.out.format("Now you have %d tasks in the list.\n" + LINE + "\n", list.size());
    }

    public static void addTodo(String input, String[] inputWords) {
        try {
            String taskName = input.substring(TODO_NAME_CONSTANT);
            list.add(new Todo(taskName));
            printAddedTask();
        } catch(StringIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n☹ OOPS!!! The description of a todo cannot be empty.\n" + LINE);
        }
    }

    public static void addDeadline(String input, String[] inputWords) {
        try {
            if (!input.contains("/by")) {
                System.out.println(LINE + "\nIncorrect format for entering deadline!\n" + LINE);
                return;
            }
            int taskEndIndex = input.indexOf("/by") - 1;
            String taskName = input.substring(DEADLINE_NAME_CONSTANT, taskEndIndex);
            String deadline = input.substring(taskEndIndex + DEADLINE_BY_CONSTANT);
            list.add(new Deadline(taskName, deadline));
            printAddedTask();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n☹ OOPS!!! The description of a deadline cannot be empty.\n" + LINE);
        }
    }

    public static void addEvent(String input, String[] inputWords) {
        try {
            if (!input.contains("/at")) {
                System.out.println(LINE + "\nIncorrect format for entering event!\n" + LINE);
                return;
            }
            int taskEndIndex = input.indexOf("/at") - 1;
            String taskName = input.substring(EVENT_NAME_CONSTANT, taskEndIndex);
            String at = input.substring(taskEndIndex + EVENT_AT_CONSTANT);
            list.add(new Event(taskName, at));
            printAddedTask();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n☹ OOPS!!! The description of an event cannot be empty.\n" + LINE);
        }
    }

    public static void addTask(String input, String[] inputWords) {
        String taskType = inputWords[0].toLowerCase();
        try {
            switch(taskType) {
            case "todo":
                addTodo(input, inputWords);
                break;
            case "deadline":
                addDeadline(input, inputWords);
                break;
            case "event":
                addEvent(input, inputWords);
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
                printList();
                continue;
            }
            if (inputWords[0].equalsIgnoreCase("done")) {
                markTaskAsDone(inputWords);
                continue;
            }
            if (inputWords[0].equalsIgnoreCase("delete")) {
                deleteTask(inputWords);
                continue;
            }
            addTask(input, inputWords);
        }
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }
}
