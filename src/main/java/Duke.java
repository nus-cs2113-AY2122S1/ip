import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import src.main.java.Task;

public class Duke {


    private static String[] stringList = new String[100];
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int listCount = 0;

    private static final String LINE = "-------------------------------------------------------------------\n";
    private static final String GREET_USER = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private static void greetUser() {
        System.out.println(GREET_USER + "\n \n" + LINE);
    }

    private static void markAsDone(String line) {
        String taskNumber = line.substring(5);
        int taskListElement = Integer.parseInt(taskNumber) - 1;
        taskList.get(taskListElement).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskList.get(taskListElement).getDescription());
        System.out.println(LINE);
    }

    private static void list() {
        int taskCount = 1;
        for (int i = 0; i < listCount; i++) {
            System.out.println(taskCount + "." + taskList.get(i));
            taskCount++;
        }
        System.out.println(LINE);
    }

    private static Todo todo(String line) {
        char taskType = line.toUpperCase().charAt(0);
        String taskDisplay = line.substring(5);

        stringList[listCount] = taskDisplay;
        Todo todoTask = new Todo(taskDisplay, taskType);
        taskList.add(listCount, todoTask);
        return todoTask;
    }

    private static Deadline deadline(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(9, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList[listCount] = taskDisplay;
        Deadline deadlineTask = new Deadline(taskDisplay, taskType, doBy);
        taskList.add(listCount, deadlineTask);
        return deadlineTask;
    }

    private static Event event(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(6, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList[listCount] = taskDisplay;
        Event eventTask = new Event(taskDisplay, taskType, doBy);
        taskList.add(listCount, eventTask);
        return eventTask;
    }

    private static void printTask(Task task, int listCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + listCount + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void delete(String line){
        int taskIndex = Integer.parseInt(line.substring(7)) - 1;
        Task task = taskList.get(taskIndex);
        listCount--;
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + listCount + " tasks in the list.");
        System.out.println(LINE);
        taskList.remove(taskIndex);
    }

    private static void mainProgram(String line, Scanner in) {

        try {
            if (line.startsWith("done")) {
                markAsDone(line);

            } else if (line.equals("list")) {
                list();

            } else if (line.startsWith("todo")) {
                try {
                    Todo todoTask = todo(line);
                    listCount++;
                    printTask(todoTask, listCount);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("Please format your input as 'todo <task>'");
                    System.out.println(LINE);
                }

            } else if (line.startsWith("deadline")) {
                try {
                    Deadline deadlineTask = deadline(line);
                    listCount++;
                    printTask(deadlineTask, listCount);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The format of deadline is wrong");
                    System.out.println("Please format your input as 'deadline <task>/<due date>'");
                    System.out.println(LINE);
                }

            } else if (line.startsWith("event")) {
                try {
                    Event eventTask = event(line);
                    listCount++;
                    printTask(eventTask, listCount);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The format of event is wrong");
                    System.out.println("Please format your input as 'event <task>/<event date and time>'");
                    System.out.println(LINE);
                }

            } else if (line.startsWith("delete")) {
                try {
                    delete(line);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The task number is not valid");
                    System.out.println("Please check again and format your input as 'delete <task number>'");
                    System.out.println(LINE);
                }

            } else {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        greetUser();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            mainProgram(line, in);
            line = in.nextLine();
        }

        System.out.println(EXIT_MESSAGE);
        System.out.println(LINE);
    }
}

//testing merge commit





