import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    /** Number of tasks in the list */
    private static int taskCount = 0;

    /** List of tasks */
    private static Task[] tasks = new Task [100];

    public static void main(String[] args) {
        printWelcomeMessage();
        String line;
        Scanner in = new Scanner(System.in);
        do {
            line = (in.nextLine()).trim();
            String[] inputs = line.split(" ", 2);
            printActivatedStatement();
            if (line.equals("bye")) {
                printByeStatement();
                continue;
            } else if (line.equals("list")) {
                printList();
            } else if (inputs[0].equals("done")){
                markAsDone(parseInt(inputs[1]));
            } else if (line.equals("help")) {
                printListOfCommands();
            } else {
                addTask(inputs);
            }
            printCompletionMessage();
        } while (!line.equals("bye"));
    }

    /**
     * Prints the logo and the welcome message at the start of the program.
     */
    public static void printWelcomeMessage() {
        String logo = "        ___      __    __       _______.___________.__  .__   __.\n" +
                "       /   \\    |  |  |  |     /       |           |  | |  \\ |  |\n" +
                "      /  ^  \\   |  |  |  |    |   (----`---|  |----|  | |   \\|  |\n" +
                "     /  /_\\  \\  |  |  |  |     \\   \\       |  |    |  | |  . `  |\n" +
                "    /  _____  \\ |  `--'  | .----)   |      |  |    |  | |  |\\   |\n" +
                "   /__/     \\__\\ \\______/  |_______/       |__|    |__| |__| \\__|  ";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Austin\n"
                + "What can I do for you?\n"
                + "In case, if you are unsure of any commands, please type \"help\".");

    }

    /**
     * Prints a goodbye message before the program ends.
     * This method is called once "bye" command is called.
     */
    public static void printByeStatement() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________SHUTTING DOWN______________________");
    }

    /**
     * Prints all the tasks in the list along with the category and status.
     * Error message is printed if there are no tasks in the list.
     * This method is executed once the "list" command is called.
     */
    public static void printList() {
        if (taskCount == 0) {
            System.out.println("No items were added into the list.");
        } else {
            System.out.println("Below are the list of tasks in your list:");
            int i;
            for (i = 0; i < taskCount; i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(tasks[i].toString());
            }
            System.out.println("Currently, you have " + taskCount + " tasks in the list.");
        }
    }

    /**
     * Marks the status of a specific task as done by identifying the task index in the command.
     * Error message is printed if the task index input is out of range.
     *
     * @param taskIndex User's command to mark the task as done
     */
    public static void markAsDone(int taskIndex) {
        if ((taskIndex >= taskCount) || (taskIndex < 0)){
            // if the task is not assigned to the number given by the user
            System.out.println("The task number is invalid.");
        } else {
            tasks[taskIndex].setDone(true);
            System.out.println("Amazing! I have marked this task as done:");
            System.out.println(tasks[taskIndex].toString());
        }
    }

    /**
     * Adds a task into the list.
     * This method is called once the command is called.
     *
     * @param task User's command to aad the task
     */
    public static void addTodoTask(String task) {
        Todo newTodo = new Todo(task);
        tasks[taskCount] = newTodo;
        taskCount++;
        printAddTaskMessage();
    }

    /**
     * Adds a task of "event" type into the list.
     * This method is called once the "event" command is called.
     * Error message is printed if the format of the command is invalid.
     *
     * @param task User's command to add the event task
     */
    public static void addEventTask(String task) {
        if (task.contains("\\at")) {
            int index = task.indexOf("\\");
            Event newEvent = new Event(task.substring(0, index), task.substring(index + 1));
            tasks[taskCount] = newEvent;
            taskCount++;
            printAddTaskMessage();
        } else {
            printInvalidCommandMessage();
        }
    }

    /**
     * Adds a task of "deadline" type into the list.
     * This method is called once the "deadline" command is called.
     * Error message is printed if the format of the command is invalid.
     *
     * @param task User's command to add the deadline task
     */
    public static void addDeadlineTask(String task) {
        if (task.contains("\\by")) {
            int index = task.indexOf("\\");
            Deadline newDeadline = new Deadline(task.substring(0, index), task.substring(index + 1));
            tasks[taskCount] = newDeadline;
            taskCount++;
            printAddTaskMessage();
        } else {
            printInvalidCommandMessage();
        }
    }

    /**
     * Prints a confirmation message to the user to that the task is successfully added into the list.
     */
    public static void printAddTaskMessage() {
        System.out.println("Noted. I have successfully added this task:");
        System.out.println(tasks[taskCount - 1].toString());
        System.out.println("Now, you have " + taskCount + " tasks in the list.");
    }

    /**
     * Calls specific methods to add tasks of specific category based on the input command.
     * Error message is printed if the command does not exist.
     *
     * @param inputs User's command to add the task or if the command is invalid
     */
    public static void addTask(String[] inputs) {
        switch (inputs[0]) {
        case "todo":
            addTodoTask(inputs[1]);
            break;
        case "event":
            addEventTask(inputs[1]);
            break;
        case "deadline":
            addDeadlineTask(inputs[1]);
            break;
        default:
            printInvalidCommandMessage();
        }
    }

    /**
     * Prints the message once the command is called.
     */
    public static void printActivatedStatement() {
        System.out.println("___________________COMMAND ACTIVATED__________________");
    }

    /**
     * Prints the list of commands along with their description and format.
     * This method is called once the "help" command is called.
     */
    public static void printListOfCommands() {
        System.out.println("todo: Adds a todo task into the list.\n" +
                "      Format: todo <task_description>\n");
        System.out.println("event: Adds a event task into the list. " +
                "The event date and time description is also needed while creating this task.\n" +
                "      Format: event <task_description> \\at <date_and_time_information>\n");
        System.out.println("deadline: Adds a task which has a deadline into the list. " +
                "The deadline date and time information is also needed while creating this task.\n" +
                "      Format: deadline <task_description> \\by <date_and_time_information>\n");
        System.out.println("done: Marks a specific task as done.\n" +
                "      Format: done <task_id>\n");
        System.out.println("list: Prints all the tasks along with the status of each task.\n" +
                "      Format: list\n");
        System.out.println("bye: Exits the program with a goodbye message.\n" +
                "      Format: bye\n");
        System.out.println("help: Prints a list of commands.\n" +
                "      Format: help");
    }

    /**
     * Prints out the error message when the command called is invalid.
     */
    public static void printInvalidCommandMessage() {
        System.out.println("Sorry. The command given is invalid.");
    }

    /**
     * Prints after the command is successfully executed.
     */
    public static void printCompletionMessage() {
        System.out.println("___________________COMMAND EXECUTED___________________");
        System.out.println("Anything else?\n"
                 + "In case, if you are unsure of any commands, please type \"help\".");
    }
}