import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {
    public static final String LINE = "____________________________________________________________\n";
    public static void main(String[] args) {
        printHelloMessage();
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int index = 0;
        processUserInput(input, tasks, index);
    }

    /**
     * Processes user input after the app starts
     * @param input User input
     * @param tasks Task list represented by an array of Tasks
     * @param index Index number of the newest Task added into tasks array
     */
    private static void processUserInput(Scanner input, Task[] tasks, int index) {
        String command = "";
        while (!(command.equalsIgnoreCase("bye"))) {
            command = input.nextLine();
            if ((command.equalsIgnoreCase("bye"))) {
                break;
            }
            if (command.equalsIgnoreCase("list")) {
                printList(tasks, index);
            } else if ((command.toLowerCase()).contains("done")) {
                markItemAsDone(tasks, command);
            } else if ((command.toLowerCase()).contains("todo")) {
                addTodoTask(tasks, index, command);
                printAddTaskMessage(index, tasks[index]);
                index++;
            } else if ((command.toLowerCase()).contains("deadline")) {
                if (command.contains("/by")) {
                    addDeadlineTask(tasks, index, command);
                    printAddTaskMessage(index, tasks[index]);
                    index++;
                } else {
                    System.out.println("Please include deadline: /by deadline");
                }
            } else if ((command.toLowerCase()).contains("event")) {
                if (command.contains("/at")) {
                    addEventTask(tasks, index, command);
                    printAddTaskMessage(index, tasks[index]);
                    index++;
                } else {
                    System.out.println("Please include event time! /at event time");
                }
            } else {
                System.out.println("Please specify type (deadline, event, todo) of task before Task description");
            }
        }
        printByeMessage();
    }

    /**
     * Adds an event Task to the Task list
     * @param tasks Task list stored in form of an array
     * @param index Index number of the newest item in tasks array
     * @param command User input
     */
    private static void addEventTask(Task[] tasks, int index, String command) {
        int endOfDescriptionIndex = command.indexOf("/");
        //5 is index after "event" in input string
        String eventTask = command.substring(5, endOfDescriptionIndex);
        //3 is no of chars after "at"
        String at = command.substring(endOfDescriptionIndex + 3);
        tasks[index] = new Events(eventTask, at);
    }

    /**
     * Adds a deadline Task to the Task list
     * @param tasks Task list stored in form of an array
     * @param index Index number of the newest item in tasks array
     * @param command User input
     */
    private static void addDeadlineTask(Task[] tasks, int index, String command) {
        int endOfDescriptionIndex = command.indexOf("/");
        //8 is index after "deadline" in input string
        String deadlineTask = command.substring(8, endOfDescriptionIndex);
        //3 is no of chars after 'by'
        String by = command.substring(endOfDescriptionIndex + 3);
        tasks[index] = new Deadline(deadlineTask, by);
    }

    /**
     * Adds a todo Task to the Task list
     * @param tasks Task list stored in form of an array
     * @param index Index number of the newest item in tasks array
     * @param command User input
     */
    private static void addTodoTask(Task[] tasks, int index, String command) {
        //4 is index after todo in input string
        String toDoTask = command.substring(4);
        tasks[index] = new ToDos(toDoTask);
    }

    /**
     * Marks a task as done based on the user's input
     * @param tasks Task list stored in form of an array
     * @param command User input
     */
    private static void markItemAsDone(Task[] tasks, String command) {
        int itemNo = getItemNo(command);
        //-1 because array index starts counting from 0
        tasks[itemNo - 1].markAsDone();
        printTaskDone(tasks[itemNo - 1]);
    }

    /**
     * Extracts Item Number of task to mark as done through user input
     * @param command User input
     * @return Returns the Extracted itemNo from user input
     */
    private static int getItemNo(String command) {
        String[] extractedCommand = command.split(" ");
        //1 is index of item no from user input
        return parseInt(extractedCommand[1]);
    }

    /**
     * Prints a short message with the task added when a task is added by the user
     * @param index Index number of current task in tasklist array
     * @param task Task added by user
     */
    private static void printAddTaskMessage(int index, Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + (index + 1) + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints Goodbye message before app exits
     */
    private static void printByeMessage() {
        String byeGreeting = "Bye. Hope to see you again soon!\n";
        System.out.println(LINE + byeGreeting + LINE);
    }

    /**
     * Prints the welcome message when app is launched
     */
    private static void printHelloMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String helloGreeting = "Hello! I'm Duke \n" + "What can I do for you?\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE + helloGreeting + LINE);
    }

    /**
     * Displays a message after a task is done
     * @param x Task that was done
     */
    private static void printTaskDone(Task x) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(x);
        System.out.println(LINE);
    }

    /**
     * Prints the task list
     *
     * @param list Task list represented in array.
     * @param index Index number of last task in list array.
     */
    public static void printList(Task[] list, int index) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + "." + list[i]);
        }
        System.out.println(LINE);
    }

}
