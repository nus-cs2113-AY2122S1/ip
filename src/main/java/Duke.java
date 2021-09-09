import java.util.Scanner;


public class Duke {
    //Array of tasks
    private static Task[] tasks;
    //Total number of tasks
    private static int taskCount;
    //Scanner
    private static final Scanner sc = new Scanner(System.in);
    //GenshinImpact conversion rates
    private static final int TOP_LOW_CONVERSION_RATE = 27;
    private static final int HIGH_LOW_CONVERSION_RATE = 9;
    private static final int MID_LOW_CONVERSION_RATE = 3;
    //The sacred texts
    private static final String LINES = "____________________________________________________________\n";
    private static final String GREETING = " HeLLO! I'm Jim, a real person who definitely passes" +
            " reCaptchas!\n";
    private static final String BIRTHDAY_MESSAGE = " ^o^ Happy birthday to you! ^o^\n";
    private static final String ECHO_MESSAGE = " Echoing after you!\n";
    private static final String QUIT_MESSAGE = " That was annoying huh...\n";
    private static final String NO_TASK_MESSAGE = "No such task! You're not THAT productive...\n";
    private static final String NO_TASK_DONE_NUMBER_MESSAGE = "Please specify the task you would like to " +
            "mark as done!\n";
    private static final String DONE_NOT_INTEGER_MESSAGE = "Please input an integer after done!\n";
    private static final String TASK_COMPLETE_MESSAGE = "Nice! You're a real champ for finishing this: \n";
    private static final String BYE_MESSAGE = " Bye! Remember, stay out of fire, suuuuuuper high level " +
            "tactic yea?\n";
    private static final String ADD_MESSAGE = " Got it. I've added this task:\n   ";
    private static final String PLEASE_ADD_A_TASK_DESCRIPTION = "Please add a task description!\n" ;
    private static final String MISSING_IN_DEADLINE_MESSAGE = "Please format your input as 'deadline [task]" +
            " /by [deadline]'!\n";
    private static final String MISSING_IN_EVENT_MESSAGE = "Please format your input as 'event [task]" +
            " /at [time range]'!\n";
    private static final String MISSING_BY_MESSAGE = "Please add '/by' in between your task and deadline!\n";
    private static final String MISSING_AT_MESSAGE = "Please add '/at' in between your task and time range!\n";
    private static final String INVALID_COMMAND = "I don't quite understand :/\n";
    private static final String HELP_MESSAGE = " Here are the commands for the things I can do:\n" +
            "    1. todo [task] = adds a Todo task\n" +
            "    2. deadline [task] /by [deadline] = adds a Deadline task\n" +
            "    3. event [task] /at [time range] = adds an Event task\n" +
            "    4. done [taskIndex] = marks the inputted task as done\n" +
            "    5. list = lists out all current tasks with their taskIndex\n" +
            "    6. echo = turn into a huge cave and echo your inputs back to you!!\n" +
            "    7. quit (only in echo mode) = turn back to normal and stop echoing\n" +
            "    8. genshin = begin the genshin helper\n" +
            "    9. bye = shuts me down... ;-;\n";



    


    //Prints greeting message
    public static void greeting() { 
        System.out.println(LINES + GREETING + LINES);
    }

    //Begins echo function
    public static void echo() {
        System.out.println(LINES + ECHO_MESSAGE + LINES);
        boolean echoState = true;
        while (echoState) {
            String s = sc.nextLine();
            if (s.equalsIgnoreCase("quit")) {
                System.out.println(LINES + QUIT_MESSAGE + LINES);
                echoState = false;
            }
            else {
                String output = LINES + " " + s + "\n" + LINES;
                System.out.println(output);
            }
        }
    }

    //list function
    public static void list(Task[] tasks) {
        System.out.print(LINES);
        for (int i = 0; tasks[i] != null; i++) {
            int j = i + 1;
            System.out.println(j + "." + tasks[i]);
        }
        System.out.print(LINES);
    }

    //Marks a specified task input as done
    public static void markDone(String input, Task[] tasks, int taskCount) {
        try {
            //isolate 'x' from 'done x', where x is a number
            int index = Integer.parseInt(input.substring(5));
            if (index > taskCount) {
                System.out.println(LINES + NO_TASK_MESSAGE + LINES);
            }
            else {
                tasks[index - 1].markAsDone();
                System.out.print(LINES + TASK_COMPLETE_MESSAGE + tasks[index - 1] + "\n" + LINES);
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINES + NO_TASK_DONE_NUMBER_MESSAGE + LINES);
        } catch (NumberFormatException e) {
            System.out.println(LINES + DONE_NOT_INTEGER_MESSAGE + LINES);
        }
    }
    //Adds task to tasks
    public static void addTask(Task[] tasks, String input, int taskCount) {
        if (input.toLowerCase().startsWith("todo")) {
            addTodo(tasks, input, taskCount);
        }
        else if (input.toLowerCase().startsWith("deadline")) {
            addDeadline(tasks, input, taskCount);
        }
        else if (input.toLowerCase().startsWith("event")){
            addEvent(tasks, input, taskCount);
        }
    }
    private static void addTodo(Task[] tasks, String input, int taskCount) {
        try {
            tasks[taskCount] = new Todo(input.substring(5));
            printTaskAcknowledgement(tasks[taskCount], taskCount);
        } catch (StringIndexOutOfBoundsException e){
            System.out.println(LINES + PLEASE_ADD_A_TASK_DESCRIPTION + LINES);
        }
    }
    private static void addDeadline(Task[] tasks, String input, int taskCount) {
        try {
            if (input.trim().length() == 8) {
                System.out.println(LINES + PLEASE_ADD_A_TASK_DESCRIPTION + LINES);
            }
            else if (!input.contains("/by")) {
                System.out.println(LINES + MISSING_BY_MESSAGE + LINES);
            }
            else {
                int slashIndex = input.indexOf("/by");
                String task = input.substring(9, (slashIndex - 1));
                String dueDate = input.substring(slashIndex + 4);
                tasks[taskCount] = new Deadline(task, dueDate);
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINES + MISSING_IN_DEADLINE_MESSAGE + LINES);
        }
    }
    private static void addEvent(Task[] tasks, String input, int taskCount) {
        try {
            if (input.trim().length() == 5) {
                System.out.println(LINES + PLEASE_ADD_A_TASK_DESCRIPTION + LINES);
            }
            else if (!input.contains("/at")) {
                System.out.println(LINES + MISSING_AT_MESSAGE + LINES);
            }
            else {
                int slashIndex = input.indexOf("/");
                String task = input.substring(6, (slashIndex-1));
                String timeRange = input.substring(slashIndex+4);
                tasks[taskCount] = new Event(task, timeRange);
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(LINES + MISSING_IN_EVENT_MESSAGE + LINES);
        }
    }
    private static void printTaskAcknowledgement(Task task, int taskCount) {
        System.out.println(LINES + ADD_MESSAGE + task);
        taskCount += 1;
        System.out.println(" Now you have " + taskCount + " tasks in the list.\n" + LINES);
    }

    //Clears all tasks
    public static void initJim() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public static String getUserInput() {
        System.out.print(" How can I help you: ");
        return sc.nextLine();
    }

    public static void genshinHelper() {
        int totalMats = 0;
        System.out.print(LINES + " How many Top Tier: ");
        String top = sc.nextLine();
        totalMats += Integer.parseInt(top)*TOP_LOW_CONVERSION_RATE;
        System.out.print(" How many High Tier: ");
        String high = sc.nextLine();
        totalMats += Integer.parseInt(high)*HIGH_LOW_CONVERSION_RATE;
        System.out.print(" How many Mid Tier: ");
        String mid = sc.nextLine();
        totalMats += Integer.parseInt(mid)*MID_LOW_CONVERSION_RATE;
        System.out.print(" How many Low Tier: ");
        String low = sc.nextLine();
        totalMats += Integer.parseInt(low);
        System.out.println(" Total low tier mats required: " + totalMats + "\n" + LINES);
    }

    //Executes next line command
    public static void commandExecute(String input) {
        try {
            if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline")
                    || input.toLowerCase().startsWith("event")) {
                addTask(tasks, input, taskCount);
                taskCount += 1;
            }
            else if (input.toLowerCase().startsWith("done")) {
                markDone(input, tasks, taskCount);
            }
            else if (input.equalsIgnoreCase("list")) {
                list(tasks);
            }
            else if (input.equalsIgnoreCase("echo")) {
                echo();
            }
            else if (input.toUpperCase().contains("BIRTHDAY")) {
                System.out.println(LINES + BIRTHDAY_MESSAGE + LINES);
            }
            else if (input.equalsIgnoreCase("help")) {
                System.out.println(LINES + HELP_MESSAGE + LINES);
            }
            else if (input.equalsIgnoreCase("genshin")) {
                genshinHelper();
            }
            else if (input.equalsIgnoreCase("bye")) {
                System.out.println(LINES + BYE_MESSAGE + LINES);
                System.exit(0);
            }
            else {
                throw new JimException();
            }
        } catch (JimException e) {
            System.out.println(LINES + INVALID_COMMAND + LINES);
        }
    }
    public static void main(String[] args) {
        greeting();
        initJim();
        while (true) {
            String userCommand = getUserInput();
            commandExecute(userCommand);
        }
    }
}