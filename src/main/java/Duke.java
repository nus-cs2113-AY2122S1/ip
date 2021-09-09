import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static final int MAX_RECORDS = 100;
    public static final String INDENT = "    │ ";
    private static int taskCount = 0;
    private static int taskCompleted = 0;
    private static final Task list[] = new Task[MAX_RECORDS];

    public static void main(String[] args) {
        greetUser();
        engageUser();
        byeUser();
    }

    /**
     * Prints the top horizontal line to demarcate text from Tired.
     */
    public static void printTopLine() {
        System.out.println("    ┌────────────────────────────────────────────────────────────────────┐");
    }

    /**
     * Prints the bottom horizontal line to demarcate text from Tired.
     */
    public static void printBottomLine() {
        System.out.println("    └────────────────────────────────────────────────────────────────────┘");
    }

    /**
     * Prints logo of Tired
     */
    public static void printLogo() {
        // Generated with: https://patorjk.com/software/taag/
        String logo = "         ______       __     __                 __\n"
                + "        / ____/___   / /_   / /   ____   _____ / /_\n"
                + "       / / __ / _ \\ / __/  / /   / __ \\ / ___// __/\n"
                + "      / /_/ //  __// /_   / /___/ /_/ /(__  )/ /_  \n"
                + "      \\____/ \\___/ \\__/  /_____/\\____//____/ \\__/\n"
                + "            ┌─┐┬  ┌─┐┌─┐┌─┐┌─┐┌─┐\n"
                + "            ├─┘│  ├┤ ├─┤└─┐├┤  ┌┘\n"
                + "      o o o ┴  ┴─┘└─┘┴ ┴└─┘└─┘ o";
        System.out.println(logo);
    }

    /**
     * Prints the list of tasks collated by Tired.
     */
    public static void printList() {
        printTopLine();
        System.out.println(INDENT + "Here are your tasks, \"oRgAnIc iTeLlIgEnCe\":");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(INDENT + (i + 1) + "." + list[i]);
        }
        printBottomLine();
    }

    /**
     *  Prints error message to user. Prompts user to input correct command.
     */
    public static void printError() {
        printTopLine();
        System.out.println(INDENT + "You forgot to input the type of task... again.");
        printBottomLine();
    }

    /**
     * Adds inputs from user to list[] to keep track of user's tasks, deadlines, and events.
     *
     * @param taskName Name of task from user.
     * @param taskType Type of task from user.
     * @param taskDetails Time/date of event/deadline.
     */
    public static void addTask(String taskName, String taskType, String taskDetails) {
        switch (taskType) {
        case "todo":
            list[taskCount] = new Todo(taskName);
            break;
        case "deadline":
            list[taskCount] = new Deadline(taskName, taskDetails);
            break;
        case "event":
            list[taskCount] = new Event(taskName, taskDetails);
            break;
        default:
            return;
        }

        taskCount++;
        String plural = (taskCount - taskCompleted) == 1 ? "" : "s";

        printTopLine();
        System.out.println(INDENT + " Fine. Added to your list:");
        System.out.println(INDENT + "   " + list[taskCount - 1]);
        System.out.println(INDENT + " You have " + (taskCount - taskCompleted)
                + " pending task" + plural + ". tHaT's aWeSoMe!!!1!!1!!");
        printBottomLine();
    }

    /**
     * Marks tasks in list[] as done, if they exist or has not been completed.
     *
     * @param taskNumber Number n associated with the task, where n is the nth task in list.
     */
    public static void markTaskAsDone(int taskNumber) {
        boolean isExists = taskNumber >= 0 || taskNumber < taskCount;

        printTopLine();
        if (!isExists) {
            System.out.println("    │ Wha- Hey! Task does not exist!");
        } else if (list[taskNumber].isDone) {
            System.out.println("    │ Dude... you've done the task already.");
        } else if (isExists){
            taskCompleted++;
            list[taskNumber].isDone = true;
            System.out.println("    │ About time. I've mark that task as done:");
            System.out.println("    │   [" + list[taskNumber].getStatusIcon() + "]" + list[taskNumber].description);
        }
        printBottomLine();
    }

    /**
     * Prints greeting message to user when code is ran.
     */
    public static void greetUser() {
        printLogo();
        printTopLine();
        System.out.println(INDENT + "*Sigh* Hi... I'm Tired                                             │\n"
                + INDENT + "What do you want from me?                                          │");
        printBottomLine();
    }

    /**
     * Prints a snarky remark to user.
     */
    public static void mockUser() {
        printTopLine();
        System.out.println(INDENT + "You know what a todo list bot is?\n"
                + INDENT + "I'm a todo list bot. So stop chatting with me.");
        printBottomLine();
    }

    /**
     * Prints farewell message to user and exits code.
     */
    public static void byeUser() {
        printTopLine();
        System.out.println(INDENT + "\"Only in the agony of parting do we look into the depths of love.\" │\n"
                + INDENT + " —— George Eliot                                                   │\n"
                + INDENT + "                                                                   │\n"
                + INDENT + "Ha! As if I care! Goodbye!!                                        │");
        printBottomLine();
    }

    /**
     * Engages user base on what the user has typed and executes a corresponding command.
     */
    public static void engageUser() {
        Scanner text = new Scanner(System.in);
        String taskType;
        String taskName;
        String taskDetails = "";

        String userInput;
        String[] parts;
        boolean isUsing = true;

        do {
            taskType = text.next().toLowerCase();

            switch (taskType) {
            case "bye":
                isUsing = false;
                break;
            case "hello":
            case "hi":
            case "yo":
                mockUser();
                break;
            case "list":
                printList();
                break;
            case "done":
                userInput = text.next();
                markTaskAsDone(Integer.parseInt(userInput) - 1);
                break;
            case "todo":
                taskName = text.nextLine();
                addTask(taskName, taskType, taskDetails);
                break;
            case "deadline":
                userInput = text.nextLine();
                parts = userInput.split(" /by ");
                taskName = parts[0];
                taskDetails = parts[1];

                addTask(taskName, taskType, taskDetails);
                break;
            case "event":
                userInput = text.nextLine();
                parts = userInput.split(" /at ");
                taskName = parts[0];
                taskDetails = parts[1];

                addTask(taskName, taskType, taskDetails);
                break;
            default:
                printError();
                break;
            }
        } while (isUsing);
    }

}
