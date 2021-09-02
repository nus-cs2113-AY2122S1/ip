import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________";
    private static final String INDENT = "    ";
    private static final int MAX_TASK_LIMIT = 100;
    private static final int START_OF_STRING = 0;

    private static Task[] tasks;
    private static String userInput;
    private static String todoTask;
    private static String deadlineDescription;
    private static String by;
    private static String eventDescription;
    private static String at;
    private static int inputNum;
    private static int doneTaskNum;

    private static int dividerPosition;
    private static int charAfterDividerPosition;

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";


    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printGreeting(String s, String s2) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(s);
        System.out.println(s2);
    }

    private static String getUserInput() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Enter command: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void initTaskList() {
        tasks = new Task[MAX_TASK_LIMIT];
    }

    private static void processUserInput() {
        String[] splitInput = userInput.split(" ");
        String inputCommand = splitInput[0];
        switch (inputCommand) {
        case COMMAND_LIST:
            performListTask();
            break;
        case COMMAND_DONE:
            handleIntConversion(splitInput[1]);
            performMarkTaskDone(inputNum);
            break;
        case COMMAND_TODO:
            splitTodo(userInput);
            performAddTodo(todoTask);
            break;
        case COMMAND_DEADLINE:
            splitDeadline(userInput);
            performAddDeadline(deadlineDescription, by);
            break;
        case COMMAND_EVENT:
            splitEvent(userInput);
            performAddEvent(eventDescription, at);
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
        tasks[Task.taskCount - 1] = t;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("added: " + userInput.trim());
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }

    private static void performListTask() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < Task.taskCount; i++) {
            System.out.println(INDENT + tasks[i].taskNum + "." + tasks[i]);
        }
    }

    private static void handleIntConversion(String number){
        number = number.trim();
        inputNum = Integer.parseInt(number);
    }

    private static void performMarkTaskDone(int inputNum){
        //index of taskNum in tasks array
        doneTaskNum = inputNum - 1;

        if ((inputNum > 0) && (inputNum <= Task.taskCount)) {
            tasks[doneTaskNum].markAsDone();
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(INDENT + tasks[doneTaskNum].taskNum + "." + tasks[doneTaskNum]);
        } else {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Invalid input!");
        }
    }

    private static void splitTodo(String userInput){
        todoTask = userInput.replace("todo", "");
        todoTask = todoTask.trim();
    }

    private static void splitDeadline(String userInput){
        dividerPosition = userInput.indexOf("/");
        deadlineDescription = userInput.substring(START_OF_STRING, dividerPosition);
        deadlineDescription = deadlineDescription.replace("deadline", "");
        deadlineDescription = deadlineDescription.trim();
        charAfterDividerPosition = dividerPosition + 1;
        by = userInput.substring(charAfterDividerPosition);
        by = by.replace("by", "");
        by = by.trim();
    }

    private static void splitEvent(String userInput){
        dividerPosition = userInput.indexOf("/");
        eventDescription = userInput.substring(START_OF_STRING, dividerPosition);
        eventDescription = eventDescription.replace("event", "");
        eventDescription = eventDescription.trim();
        charAfterDividerPosition = dividerPosition + 1;
        at = userInput.substring(charAfterDividerPosition);
        at = at.replace("at", "");
        at = at.trim();
    }

    private static void performAddTodo(String todoTask){
        System.out.println(HORIZONTAL_LINE);
        tasks[Task.taskCount] = new Todo(todoTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + tasks[Task.taskCount - 1]);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }

    private static void performAddDeadline(String description, String by){
        System.out.println(HORIZONTAL_LINE);
        tasks[Task.taskCount] = new Deadline(description, by);
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + tasks[Task.taskCount - 1]);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }

    private static void performAddEvent(String description, String at){
        System.out.println(HORIZONTAL_LINE);
        tasks[Task.taskCount] = new Event(description, at);
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + tasks[Task.taskCount - 1]);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }

    public static void exitProgram(){
        printGreeting("Bye. Hope to see you again soon!", HORIZONTAL_LINE);
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
