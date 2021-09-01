import java.util.Scanner;

public class Duke {

    //CONSTANTS
    public static final String SEPARATOR = "____________________________________________________________\n";

    //METHODS
    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n"
                + logo
                + SEPARATOR
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + SEPARATOR);
    }

    public static void printError() {
        System.out.println(SEPARATOR
                + " Oops, something went wrong!\n"
                + SEPARATOR);
    }

    public static String[] organize(String userInput) {
        String[] organizedUserInput = new String[3];
        if (!userInput.contains(" ")) {
            //command is list
            organizedUserInput[0] = userInput;
        } else {
            int spaceIndex = userInput.indexOf(" ");
            String command = userInput.substring(0, spaceIndex);
            organizedUserInput[0] = command;
            if (!userInput.contains("/")) {
                //command is either done or todo
                String detail = userInput.substring(spaceIndex + 1);
                organizedUserInput[1] = detail;
            } else {
                //command is either deadline or event
                int slashIndex = userInput.indexOf("/");
                String detail = userInput.substring(spaceIndex + 1, slashIndex - 1);
                String time = userInput.substring(slashIndex + 1);
                organizedUserInput[1] = detail;
                organizedUserInput[2] = time;
            }
        }
        return organizedUserInput;
    }

    public static void printTaskList(Task[] taskList, int taskListSize) {
        System.out.println(SEPARATOR
                + " Here are the tasks in your list:");
        for (int i = 0; i < taskListSize; i++) {
            int j = i + 1;
            System.out.println(" " + j + "." + taskList[i]);
        }
        System.out.println(SEPARATOR);
    }

    public static void markAsDone(Task[] taskList, int taskListSize, int taskNumber) {
        if (taskNumber > taskListSize - 1) {
            printError();
        } else {
            taskList[taskNumber].setDone();
            System.out.println(SEPARATOR
                    + " Nice! I've marked this task as done:\n"
                    + "  " + taskList[taskNumber] + "\n"
                    + SEPARATOR);
        }
    }

    public static Task createTask(String[] organizedInput) {
        String category = organizedInput[0];
        String description = organizedInput[1];
        String details = organizedInput[2];
        Task newTask;
        switch (category) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            newTask = new Deadline(description, details);
            break;
        case "event":
            newTask = new Event(description, details);
            break;
        default:
            newTask = new Task("Default");
        }
        return newTask;
    }

    public static void addTask(Task[] taskList, int taskListSize, Task newTask) {
        taskList[taskListSize] = newTask;
        System.out.println(SEPARATOR
                + " Got it. I've added this task:\n"
                + "  " + newTask + "\n"
                + " Now you have " + (taskListSize + 1) + " tasks in the list.\n"
                + SEPARATOR);
    }

    public static void printFarewell() {
        System.out.println(SEPARATOR
                + " Bye. Hope to see you again soon!\n"
                + SEPARATOR);
    }

    //MAIN METHOD
    public static void main(String[] args) {

        //Greeting
        printGreeting();

        //Initialization
        Task[] taskList = new Task[100];
        int taskListSize = 0;
        String userInput = "start";
        Scanner in = new Scanner(System.in);

        //Main Loop
        while (!userInput.equals("bye")) {

            //Scans for user input
            userInput = in.nextLine();

            //Organizes user input
            String[] organizedUserInput = organize(userInput);
            String command = organizedUserInput[0];

            //Duke's actions based on command given
            switch (command) {
            case "list":
                //Prints all tasks in task list
                printTaskList(taskList, taskListSize);
                break;
            case "done":
                //Marks task as "done"
                int taskNumber = Integer.parseInt(organizedUserInput[1]) - 1;
                markAsDone(taskList, taskListSize, taskNumber);
                break;
            case "todo":
            case "deadline":
            case "event":
                //Adds task to task list
                Task newTask = createTask(organizedUserInput);
                addTask(taskList, taskListSize, newTask);
                taskListSize++;
                break;
            case "bye":
                break;
            default:
                printError();
                break;
            }
        }

        //Farewell
        printFarewell();
    }

}
