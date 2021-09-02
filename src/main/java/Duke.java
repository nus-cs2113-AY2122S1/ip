import java.util.Scanner;

public class Duke {

    private static final Task list[] = new Task[100];
    private static int taskCount = 0;
    private static int taskCompleted = 0;

    public static void main(String[] args) {
        greetUser();
        engageUser();
        byeUser();
    }

    /**
     * Prints horizontal line to demarcate text from Tired.
     */
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints logo of Tired
     */
    public static void printLogo() {
        //Generated with: https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
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
     * Prints greeting message to user when code is ran.
     */
    public static void greetUser() {
        printLogo();
        printLine();
        System.out.println("      *Sigh* Hi... I'm Tired\n      What do you want from me?");
        printLine();
    }

    /**
     * Prints farewell message to user.
     */
    public static void byeUser() {
        printLine();
        System.out.println("      See ya! Don't wanna be ya.");
        printLine();
    }

    /**
     * Prints the list of tasks collated by Tired.
     */
    public static void printList() {
        System.out.println("      Here are your tasks human:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("      " + (i + 1) + "." + list[i]);
        }
    }

    /**
     * Adds inputs from user to list[] to keep track of user's tasks, deadlines, and events.
     *
     * @param taskName Name of task from user.
     * @param taskType Type of task from user.
     * @param details Time/date of event/deadline.
     */
    public static void addTask(String taskName, String taskType, String details) {
        switch (taskType) {
        case "todo":
            list[taskCount] = new Todo(taskName);
            break;
        case "deadline":
            list[taskCount] = new Deadline(taskName, details);
            break;
        case "event":
            list[taskCount] = new Event(taskName, details);
            break;
        }

        taskCount++;
        String plural = (taskCount - taskCompleted) == 1 ? "" : "s";
        printLine();
        System.out.println("      Fine. Added to your list:");
        System.out.println("      " + list[taskCount - 1]);
        System.out.println("      You have " + (taskCount - taskCompleted) + " pending task" + plural + ". aWeSoMe!!1!1!!");
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
            System.out.println("      Wha- Hey! Task does not exist!");
        } else if (list[taskNumber].isDone) {
            System.out.println("      Dude... the task has already been done.");
        } else {
            taskCompleted++;
            list[taskNumber].isDone = true;
            System.out.println("      I've marked this task as done:");
            System.out.println("        [" + list[taskNumber].getStatusIcon() + "]" + list[taskNumber].description);
        }
        printLine();
    }

    /**
     * Engages user base on what the user has typed and executes that command.
     */
    public static void engageUser() {
        Scanner text = new Scanner(System.in);
        String taskType;
        String userInput;
        String[] parts;

        String taskName = "";
        String details = "";
        boolean isUsing = true;

        do {

            taskType = text.next();

            switch (taskType) {
            case "bye":
                isUsing = false;
                break;
            case "hello":
            case "hi":
            case "yo":
                printLine();
                System.out.println("      I'm a todo list bot. Stop chatting with me...");
                printLine();
                break;
            case "list":
                printLine();
                printList();
                printLine();
                break;
            case "done":
                userInput = text.next();
                crossOutTask(Integer.parseInt(userInput) - 1);
                break;
            case "todo":
                taskName = text.nextLine();
                addTask(taskName, taskType, details);
                break;
            case "deadline":
                userInput = text.nextLine();
                parts = userInput.split(" /by ");
                taskName = parts[0];
                details = parts[1];

                addTask(taskName, taskType, details);
                break;
            case "event":
                userInput = text.nextLine();
                parts = userInput.split(" /at ");
                taskName = parts[0];
                details = parts[1];

                addTask(taskName, taskType, details);
                break;
            default:
                printLine();
                System.out.println("      You forgot to input the type of task... again.");
                printLine();
            }
        } while (isUsing);
    }
}
