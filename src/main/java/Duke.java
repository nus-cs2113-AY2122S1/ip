import java.util.Scanner;

public class Duke {

    private static final Task list[] = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        greetUser();
        engageUser();
        byeUser();
    }

//    That's one dope logo.
//    public static void printDukeLogo() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n and poop";
//        System.out.println("Hello from\n" + logo);
//    }

    /**
     * Prints horizontal line to demarcate text from Chatbot.
     */
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints greeting message to user when code is ran.
     */
    public static void greetUser() {
        printLine();
        System.out.println("      Hello! I'm Duke\n      What can I do for you?");
        printLine();
    }

    /**
     * Prints farewell message to user.
     */
    public static void byeUser() {
        printLine();
        System.out.println("      Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints the list of tasks collated by the chatbot.
     */
    public static void printList() {
        System.out.println("      Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("      " + (i + 1) + ".[" + list[i].getStatusIcon() + "] " + list[i].description);
        }
    }

    /**
     * Adds input from user to list[] to keep track of user's tasks.
     *
     * @param userInput text the user has typed as task to add to list.
     */
    public static void addTask(String userInput) {
        list[taskCount] = new Task(userInput);
        taskCount++;
        printLine();
        System.out.println("      added: " + userInput);
        printLine();
    }

    /**
     * Marks tasks in list[] as done, if they exist or has not been completed.
     *
     * @param taskNumber Number n associated with the task, where n is the nth task in list.
     */
    public static void crossOutTask(int taskNumber) {
        printLine();
        if (taskNumber < 0 || taskNumber >= taskCount) {
            System.out.println("      Task does not exist :P");
        } else if (list[taskNumber].isDone) {
            System.out.println("      Task has already been done.");
        } else {
            list[taskNumber].isDone = true;
            System.out.println("      Nice! I've marked this task as done:");
            System.out.println("        [" + list[taskNumber].getStatusIcon() + "] " + list[taskNumber].description);
        }
        printLine();
    }

    /**
     * Engages user base on what the user has typed and executes that command.
     */
    public static void engageUser() {
        Scanner text = new Scanner(System.in);
        String userInput;
        String taskNumber = "100";

        do {
            userInput = text.next();

            if (userInput.equals("done")) {
                taskNumber = text.next();
            } else {
                userInput = userInput + text.nextLine();
            }

            switch (userInput) {
            case "bye":
                break;
            case "list":
                printLine();
                printList();
                printLine();
                break;
            case "done":
                crossOutTask(Integer.parseInt(taskNumber) - 1);
                break;
            default:
                addTask(userInput);
            }
        } while (!userInput.equals("bye"));
    }
}
