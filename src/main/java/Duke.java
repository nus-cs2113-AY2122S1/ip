import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "____________________________________________";
    private static final int MAX_TASK_LIMIT = 100;
    private static int taskCount;
    private static int taskNum;
    private static String XMark;
    private static Task[] tasks;
    private static String userInput;
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";


    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printGreeting(String s, String s2) {
        System.out.println(horizontalLine);
        System.out.println(s);
        System.out.println(s2);
    }

    private static String getUserInput() {
        System.out.println(horizontalLine);
        System.out.println("Enter command: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void initTaskList() {
        tasks = new Task[MAX_TASK_LIMIT];
        taskCount = 0;
        taskNum = 1;
    }

    private static void processUserInput() {
        String[] splitInput = userInput.split(" ");
        String inputCommand = splitInput[0];
        switch (inputCommand) {
        case COMMAND_LIST:
            performListTask();
            break;
        case COMMAND_DONE:
            int inputNum = Integer.parseInt(splitInput[1]);
            performMarkTaskDone(inputNum);
            break;
        case COMMAND_BYE:
            exitProgram();
            break;
        default:
            addTask();
        }
    }

    private static void addTask(){
        Task t = new Task(userInput);
        tasks[taskCount] = t;
        taskCount++;
        System.out.println(horizontalLine);
        System.out.println("added: " + userInput);
    }

    private static void performListTask() {
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");

        //reset the numbering of tasks everytime the method is called
        taskNum = 1;

        for (int i = 0; i < taskCount; i++) {
            XMark = tasks[i].getStatusIcon();
            System.out.println(taskNum + "." + "[" + XMark + "]" + tasks[i].description);
            taskNum++;
        }
    }

    private static void performMarkTaskDone(int inputNum){
        //index of task in array
        int doneTaskNum = inputNum - 1;

        if ((inputNum > 0) && (inputNum < taskCount + 1)) {
            tasks[doneTaskNum].markAsDone();
            System.out.println(horizontalLine);
            System.out.println("Nice! I've marked this task as done:");
            XMark = tasks[doneTaskNum].getStatusIcon();
            System.out.println("[" + XMark + "] " + tasks[doneTaskNum].description);
        } else {
            System.out.println(horizontalLine);
            System.out.println("Invalid input!");
        }
    }

    public static void exitProgram(){
        printGreeting("Bye. Hope to see you again soon!", horizontalLine);
        System.exit(0);
    }


    public static void main(String[] args) {
        printLogo();
        printGreeting("Hello! I'm Duke", "What can I do for you?");
        initTaskList();
        while (true){
            userInput = getUserInput();
            processUserInput();
        }
    }


}
