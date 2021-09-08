import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    /** List of tasks */
    private static ArrayList<Task> tasks = new ArrayList<>();

    private final static String INVALID_COMMAND_MESSAGE = "Sorry. The command is invalid.";

    public static void main(String[] args) {
        printWelcomeMessage();
        processCommand();
        printByeMessage();
    }

    /**
     * Processes each command input by the user
     */
    public static void processCommand() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            String[] inputs = line.split(" ", 2);
            inputs[0] = inputs[0].toLowerCase();
            printActivatedMessage();
            try {
                switch (inputs[0]) {
                case "list":
                    printList(inputs);
                    break;
                case "help":
                    printListOfCommands(inputs);
                    break;
                case "done":
                    markAsDone(inputs);
                    break;
                default:
                    addTask(inputs);
                }
            } catch (AustinException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Sorry. The format of the task index is invalid");
            }
            printCompletionMessage();
            line = in.nextLine();
        }
        printActivatedMessage();
    }

    /**
     * Adds a task into the list based on the command
     * @param inputs User's command to add the task
     * @throws AustinException If the command is invalid
     */
    public static void addTask(String[] inputs) throws AustinException {
        try {
            switch (inputs[0]) {
            case "todo":
                addTodoTask(inputs);
                break;
            case "event":
                addEventTask(inputs);
                break;
            case "deadline":
                addDeadlineTask(inputs);
                break;
            default:
                throw new AustinException(INVALID_COMMAND_MESSAGE);
            }
        } catch (AustinException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the logo and the welcome message at the start of the program.
     */
    public static void printWelcomeMessage() {
        String logo =
                "        ___      __    __       _______.___________.__  .__   __.\n" +
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
    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________SHUTTING DOWN______________________");
    }

    /**
     * Prints all the tasks in the list along with the category and status.
     * Error message is printed if there are no tasks in the list.
     * This method is executed once the "list" command is called.
     *
     * @param inputs Used to validate the command
     * @throws AustinException If either there are no items in the list or if
     *         there are additional keywords in the command
     */
    public static void printList(String[] inputs) throws AustinException {
        if (inputs.length > 1) {
            // if the command does not contain extra characters or words
            throw new AustinException(INVALID_COMMAND_MESSAGE);
        }
        if (tasks.size() == 0) {
            // if there are no items in the list
            throw new AustinException("No items were added into the list.");
        }
        System.out.println("Below are the list of tasks in your list:");
        int i;
        for (i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(tasks.get(i).toString());
        }
        System.out.println("Currently, you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks the status of a specific task as done by identifying the task index
     * in the command. Error message is printed if the task index input is out of range.
     *
     * @param inputs User's command to mark the specific task as done
     * @throws AustinException If either the user didn't input the task index or
     *         if the task number is out of range
     * @throws NumberFormatException If the format of the task index is invalid
     */
    private static void markAsDone(String[] inputs) throws AustinException, NumberFormatException {
        if (inputs.length == 1) {
            throw new AustinException("Sorry. The task index is missing.");
        }
        int taskIndex = parseInt(inputs[1]) - 1;
        if ((taskIndex >= tasks.size()) || (taskIndex < 0)){
            // if the task index is out of range
            throw new AustinException("Sorry. The task index given is out of range.");
        }
        tasks.get(taskIndex).setDone(true);
        System.out.println("Amazing! I have marked this task as done:");
        System.out.println(tasks.get(taskIndex).toString());
    }

    /**
     * Adds a todo task into the list.
     * This method is called once the "todo" command is called.
     *
     * @param inputs User's command to add the todo task
     * @throws AustinException If the task description is empty
     */
    private static void addTodoTask(String[] inputs) throws AustinException {
        if (inputs.length == 1) {
            throw new AustinException("Sorry. The description of todo cannot be empty.");
        }
        String task = inputs[1];
        Todo newTodo = new Todo(task);
        tasks.add(newTodo);
        printAddTaskMessage();
    }

    /**
     * Adds a task of "event" type into the list.
     * This method is called once the "event" command is called.
     * Error message is printed if the format of the command is invalid.
     *
     * @param inputs User's command to add the event task
     * @throws AustinException If either the task description is empty or
     *         if the format of the command is invalid
     */
    private static void addEventTask(String[] inputs) throws AustinException {
        if (inputs.length == 1) {
            throw new AustinException("Sorry. The description of event cannot be empty.");
        }
        String task = inputs[1];
        if (!(task.contains("|"))) {
            throw new AustinException("Sorry. The format of calling event command is invalid.");
        }
        int index = task.indexOf("|");
        Event newEvent = new Event(task.substring(0, index), task.substring(index + 1));
        tasks.add(newEvent);
        printAddTaskMessage();
    }

    /**
     * Adds a task of "deadline" type into the list.
     * This method is called once the "deadline" command is called.
     * Error message is printed if the format of the command is invalid.
     *
     * @param inputs User's command to add the deadline task
     * @throws AustinException If either the task description is empty or
     *         if the format of the command is invalid
     */
    private static void addDeadlineTask(String[] inputs) throws AustinException {
        if (inputs.length == 1) {
            throw new AustinException("Sorry. The description of deadline cannot be empty.");
        }
        String task = inputs[1];
        if (!(task.contains("|"))) {
            throw new AustinException("Sorry. The format of calling deadline command is invalid.");
        }
        int index = task.indexOf("|");
        Deadline newDeadline = new Deadline(task.substring(0, index), task.substring(index + 1));
        tasks.add(newDeadline);
        printAddTaskMessage();
    }

    /**
     * Prints a confirmation message to the user to that the task is successfully added into the list.
     */
    public static void printAddTaskMessage() {
        System.out.println("Noted. I have successfully added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now, you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the message once the command is called.
     */
    public static void printActivatedMessage() {
        System.out.println("___________________COMMAND ACTIVATED__________________");
    }

    /**
     * Prints the list of commands along with their description and format.
     * This method is called once the "help" command is called.
     *
     * @param inputs Validate the command
     * @throws AustinException If there are additional keywords
     */
    public static void printListOfCommands(String[] inputs) throws AustinException {
        if (inputs.length > 1) {
            throw new AustinException(INVALID_COMMAND_MESSAGE);
        }
        System.out.println("todo: Adds a todo task into the list.\n" +
                "      Format: todo <task_description>\n");
        System.out.println("event: Adds a event task into the list. " +
                "The event date and time description is also needed while creating this task.\n" +
                "      Format: event <task_description> | <date_and_time_information>\n");
        System.out.println("deadline: Adds a task which has a deadline into the list. " +
                "The deadline date and time information is also needed while creating this task.\n" +
                "      Format: deadline <task_description> | <date_and_time_information>\n");
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
     * Prints after the command is successfully executed.
     */
    public static void printCompletionMessage() {
        System.out.println("___________________COMMAND EXECUTED___________________");
        System.out.println("Anything else?\n"
                 + "In case, if you are unsure of any commands, please type \"help\".");
    }
}