import java.util.Scanner;

public class Duke {
    public static final String LOGO = "    ____        _        \n"
            + "   |  _ \\ _   _| | _____ \n"
            + "   | | | | | | | |/ / _ \\\n"
            + "   | |_| | |_| |   <  __/\n"
            + "   |____/ \\__,_|_|\\_\\___|\n";

    public static final String BORDER_LINE = "------------------------------------------------";
    
    public static final int MAX_TASK = 100;
    
    public static final int CHARS_UNTIL_NUMBER = 5;

    public static Task[] tasks;

    public static void initializeTasks() {
        tasks = new Task[MAX_TASK];
    }

    public static void greetUser() {
        System.out.println("Hello from\n" + LOGO);
        
        System.out.println(BORDER_LINE + System.lineSeparator() 
                + "    Hello!, I'm Duke" + System.lineSeparator()
                + "    How can I help you?" + System.lineSeparator() 
                + BORDER_LINE);
    }

    public static void goodbye() {
        System.out.println(BORDER_LINE + System.lineSeparator() 
                + "    Bye, see you again!" + System.lineSeparator()
                + BORDER_LINE);
    }
    
    public static String getCommand(String userInput) {
        String[] words = userInput.split(" ");
        return words[0];
    }

    public static void showTaskList() {
        System.out.println(BORDER_LINE);
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks[i]);
        }
        System.out.println(BORDER_LINE);
    }

    public static void markDone(String userInput) {
        String extractedNumber = userInput.substring(CHARS_UNTIL_NUMBER);
        int taskNumber = Integer.parseInt(extractedNumber) - 1;
        tasks[taskNumber].markAsDone();
        
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    The following task is now marked as done:" + System.lineSeparator()
                + "      " + tasks[taskNumber] + System.lineSeparator() 
                + BORDER_LINE);
    }

    public static void addTask(String userInput, String command) {
        switch(command) {
        case "todo":
            tasks[Task.getNumberOfTasks()] = new Todo(userInput);
            break;
        case "deadline":
            tasks[Task.getNumberOfTasks()] = new Deadline(userInput);
            break;
        case "event":
            tasks[Task.getNumberOfTasks()] = new Event(userInput);
            break;
        default:
            System.out.println("Invalid Command");
            break;
        }
        
        System.out.println(BORDER_LINE + System.lineSeparator() 
                + "    Task added: " + System.lineSeparator()
                + "      " + tasks[Task.getNumberOfTasks() - 1] + System.lineSeparator()
                + "    You have " + Task.getNumberOfTasks() + " tasks in the list." + System.lineSeparator()
                + BORDER_LINE);
    }
    
    public static void userOperation() {
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean hasEnded = false;
        
        while (!hasEnded) {
            userInput = input.nextLine();
            String command = getCommand(userInput);
            switch (command) {
            case "list":
                showTaskList();
                break;
            case "done":
                markDone(userInput);
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(userInput, command);
                break;
            case "bye":
                hasEnded = true;
                break;
            default:
                System.out.println("Invalid Input");
                break;
            }
        }
    }

    public static void main(String[] args) {
        initializeTasks();
        greetUser();
        userOperation();
        goodbye();
    }
}
