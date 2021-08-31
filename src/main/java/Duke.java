import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static final String LOGO = " ______   _       _   _\n"
            + "|  __  | | | ___ | | | | \n"
            + "| |  | | | |/   \\| | | | \n"
            + "| |__| | |   / \\   | | |____\n"
            + "|______| |__/   \\__| |______|\n";
    public static final String INVALID_MESSAGE = "Theres something wrong with your command! Type again!";
    public static final String GREETING_MESSAGE = "SQUAWK! See you next time! :)";
    public static final String LISTING_MESSAGE = "This are all the things I've remembered for you:";
    public static final int VALID_ONE_PART_COMMAND = 1;
    public static final int VALID_TWO_PART_COMMAND = 2;
    public static final int MAX_TASK_LENGTH = 100;


    public static void main(String[] args) {
        printWelcome();
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASK_LENGTH];
        int taskCount = 0;
        String command = in.nextLine();

        //while the command is not bye it keeps asking for more
        commandHandler(in, tasks, taskCount, command);
        printBye();
    }

    /**
     * Takes in commands from the command line interface and process it, different commands includes
     * todo description which includes a todo task in the list marked with T
     * deadline description /by when which includes a task with a specific deadline marked with D
     * event description /at when to when includes a task with a specific timeframe marked with E
     * list which list out all tasks in the list
     * done taskIndex which checks the task as done with a "X"
     * bye which terminates the OWL bot
     *
     * @param in Scanner object that takes in inputs from the command line interface
     * @param tasks An array containing Todos, Deadline and Event objects
     * @param taskCount The number of objects currently in the array
     * @param command The given input from the user through the command liner interface
     */
    private static void commandHandler(Scanner in, Task[] tasks, int taskCount, String command) {
        boolean isValid = false;
        while(!command.equals("bye")) {
            String[] brokenDownInput = command.split(" ", 2);
            int commandLength = brokenDownInput.length;

            //if 2 part command but the second part is blank. done <blank> or event <blank> its invalid command
            if(commandLength == VALID_TWO_PART_COMMAND && brokenDownInput[1].isEmpty()) {
                brokenDownInput[0] = "invalid";
            }

            if(commandLength == VALID_TWO_PART_COMMAND && brokenDownInput[0].equals("done")) {
                if(Integer.parseInt(brokenDownInput[1]) > 0 && Integer.parseInt(brokenDownInput[1]) <= taskCount) {
                    markCompletion(tasks, brokenDownInput[1]);
                    isValid = true;
                }
            }

            if(commandLength == VALID_ONE_PART_COMMAND && brokenDownInput[0].equals("list")) {
                listTask(tasks, taskCount);
                isValid = true;
            }

            if(commandLength == VALID_TWO_PART_COMMAND && brokenDownInput[0].equals("todo")) {
                tasks[taskCount] = new Todo(brokenDownInput[1]);
                taskCount = addTask(taskCount, ("[T] " + brokenDownInput[1]));
                isValid = true;
            }

            if(commandLength == VALID_TWO_PART_COMMAND && brokenDownInput[0].equals("deadline")) {
                String[] brokenDownBy = brokenDownInput[1].split(" /by ", 2);
                if(brokenDownInput[1].contains(" /by ") && !brokenDownBy[1].isEmpty()) {
                    tasks[taskCount] = new Deadline(brokenDownBy[0], brokenDownBy[1]);
                    taskCount = addTask(taskCount, ("[D] " + brokenDownBy[0] + "(by: " + brokenDownBy[1]) + ")");
                    isValid = true;
                }
            }
            if(commandLength == VALID_TWO_PART_COMMAND && brokenDownInput[0].equals("event")) {
                String[] brokenDownAt = brokenDownInput[1].split(" /at ", 2);
                if(brokenDownInput[1].contains(" /at ") && !brokenDownAt[1].isEmpty()) {
                    tasks[taskCount] = new Event(brokenDownAt[0], brokenDownAt[1]);
                    taskCount = addTask(taskCount, ("[E] " + brokenDownAt[0] + "(at: " + brokenDownAt[1]) + ")");
                    isValid = true;
                }
            }
            if(brokenDownInput[0].equals("invalid") || !isValid) {
                System.out.println(INVALID_MESSAGE);
            }
            command = in.nextLine();
            isValid = false;
        }
    }

    private static int addTask(int taskCount, String command) {
        taskCount++;
        printTaskCount(taskCount, command);
        return taskCount;
    }

    private static void printWelcome() {
        System.out.println(LOGO);
        printLine();
        System.out.println("SQUAWK!!!");
        System.out.println("How can I help you?");
        printLine();
    }

    private static void listTask(Task[] tasks, int taskCount) {
        if(taskCount > 0) {
            Task[] taskList = Arrays.copyOf(tasks, taskCount);
            int taskIndex = 0;
            int index = 1;
            printLine();
            System.out.println(LISTING_MESSAGE);
            printLine();
            for(Task task: taskList) {
                Task theTask = taskList[taskIndex];
                theTask.printList(theTask,index);
                index++;
                taskIndex++;
            }
            printLine();
        } else {
            System.out.println("There are no task in the list!!");
        }
    }

    private static void markCompletion(Task[] tasks, String s) {
        int taskLabel = Integer.parseInt(s);
        int taskIndex = taskLabel - 1;
        tasks[taskIndex].markDone();
        printTaskCompletionMsg(taskLabel);
    }

    private static void printTaskCompletionMsg(int taskNumber) {
        printLine();
        System.out.println("Oooh I see you've done task " + taskNumber);
        printLine();
    }

    private static void printTaskCount(int taskCount, String command) {
        printLine();
        echoMessage(command);
        System.out.println("There are currently " + taskCount + " task now!");
        printLine();
    }

    private static void printBye() {
        printLine();
        System.out.println(GREETING_MESSAGE);
        printLine();
    }
    public static void printLine() {
        for(int i = 0; i <= 50; i++) {
            System.out.print("~");
        }
        System.out.println(" ");
    }

    public static void echoMessage(String line) {
        System.out.println("Owl: I've added that!\nOwl: You added this: " + line);
    }
}

