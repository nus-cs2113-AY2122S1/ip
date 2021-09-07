public class Functions {

    //CONSTANTS
    public static final String SEPARATOR = "____________________________________________________________\n";
    public static final String logo = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    //PRINT
    public static void printGreeting() {
        System.out.println(logo);
        System.out.println("Hello from\n"
                + logo
                + SEPARATOR
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + SEPARATOR);
    }

    public static void printFarewell() {
        System.out.println(SEPARATOR
                + " Bye. Hope to see you again soon!\n"
                + SEPARATOR);
    }

    public static void printError() {
        System.out.println(SEPARATOR
                + " Oops, something went wrong!\n"
                + SEPARATOR);
    }

    //PROCESS USER INPUT
    public static String[] processUserInput(String userInput) {
        String[] processedUserInput = new String[3];
        if (!userInput.contains(" ")) {
            //command is list
            processedUserInput[0] = userInput;
        } else {
            int spaceIndex = userInput.indexOf(" ");
            String command = userInput.substring(0, spaceIndex);
            processedUserInput[0] = command;
            if (!userInput.contains("/")) {
                //command is either done or todo
                String detail = userInput.substring(spaceIndex + 1);
                processedUserInput[1] = detail;
            } else {
                //command is either deadline or event
                int slashIndex = userInput.indexOf("/");
                String detail = userInput.substring(spaceIndex + 1, slashIndex - 1);
                String time = userInput.substring(slashIndex + 1);
                processedUserInput[1] = detail;
                processedUserInput[2] = time;
            }
        }
        return processedUserInput;
    }

    //LIST
    public static void printTaskList(Task[] taskList, int taskListSize) {
        System.out.println(SEPARATOR
                + " Here are the tasks in your list:");
        for (int i = 0; i < taskListSize; i++) {
            int j = i + 1;
            System.out.println(" " + j + "." + taskList[i]);
        }
        System.out.println(SEPARATOR);
    }

    //DONE
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

    //TODO / DEADLINE / EVENT
    public static Task createTask(String[] processedUserInput) {
        String category = processedUserInput[0];
        String description = processedUserInput[1];
        String details = processedUserInput[2];
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

}
