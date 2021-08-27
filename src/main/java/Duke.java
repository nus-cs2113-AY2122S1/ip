import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = System.lineSeparator() +
            "    ___   ______   ________  _______      ___   ____  _____   .--." + System.lineSeparator() +
            "  .'   `.|_   _ \\ |_   __  ||_   __ \\   .'   `.|_   \\|_   _|.'_\\/_'." + System.lineSeparator() +
            " /  .-.  \\ | |_) |  | |_ \\_|  | |__) | /  .-.  \\ |   \\ | |  '. /\\,.'" + System.lineSeparator() +
            " | |   | | |  __'.  |  _| _   |  __ /  | |   | | | |\\ \\| |    \"||\"" + System.lineSeparator() +
            " \\  `-'  /_| |__) |_| |__/ | _| |  \\ \\_\\  `-'  /_| |_\\   |_    || /\\`" + System.lineSeparator() +
            "  `.___.'|_______/|________||____| |___|`.___.'|_____|\\____|/\\ ||//\\)" + System.lineSeparator() +
            "                                                           (/\\\\||/" + System.lineSeparator() +
            "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\||^^^^";

    private static final String HORIZONTAL_BAR = "__________________" +
            "__________________" +
            "__________________" +
            "_______________";


    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void printGreetingMessage() {
        System.out.println(HORIZONTAL_BAR);
        System.out.println(LOGO);
        System.out.println("  Hello! I'm Oberon");
        System.out.println("  What can I do for you?");
        System.out.println(HORIZONTAL_BAR);
    }

    public static void printFarewellMessage() {
        System.out.println(HORIZONTAL_BAR);
        System.out.println("  Goodbye. Hope to see you again soon!");
        System.out.println(LOGO);
        System.out.println(HORIZONTAL_BAR);
    }

    public static void echo(String input) {
        System.out.println(HORIZONTAL_BAR);
        System.out.println("  " + input);
        System.out.println(HORIZONTAL_BAR);
    }

    public static void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println(HORIZONTAL_BAR);
            System.out.println("  List is empty!");
            System.out.println(HORIZONTAL_BAR);
        } else {
            System.out.println(HORIZONTAL_BAR);
            // Traverse down the ArrayList and prints out each task
            for (int i = 0; i < tasks.size(); i++) {
                int currentIndexInOnesIndexing = i + 1;
                System.out.println("  " + currentIndexInOnesIndexing + ". "
                        + tasks.get(i).getTaskDescriptionWithStatus());
            }
            System.out.println(HORIZONTAL_BAR);
        }
    }

    public static void markTaskAsDone(String input) {
        // Extracting task number as an integer from input
        int taskNumber = Integer.parseInt(input.trim(), 10);
        boolean taskNumberInRange = (taskNumber <= tasks.size()) && (taskNumber >= 1);
        if (taskNumberInRange) {
            tasks.get(taskNumber - 1).setDone();
            System.out.println(HORIZONTAL_BAR);
            System.out.println("Task " + Integer.toString(taskNumber) + ": "
                    + tasks.get(taskNumber - 1).taskDescription);
            System.out.println("  Marked as done!");
        } else {
            System.out.println(HORIZONTAL_BAR);
            System.out.println("  Invalid Task number!");
        }
        System.out.println(HORIZONTAL_BAR);
    }
    
    public static void addToDo (String description) {
        tasks.add(new ToDo(description));
        echo("You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public static void addEvent (String description, String at) {
        tasks.add(new Event(description, at));
        echo("You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public static void addDeadline (String description, String by) {
        tasks.add(new Deadline(description, by));
        echo("You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }
    
    public static void printMessageForInvalidInput () {
        echo("Invalid Input..." + System.lineSeparator()
                + "  Please enter a valid input!");
    }

    public static void commandManager (Command inputCommand, String restOfInput) {
        String description = "";
        String additionalParameter = "";
        switch(inputCommand) {
        case SHOW_LIST:
            printTasks();
            break;
        case ADD_TODO:
            addToDo(restOfInput); // restOfInput is the description for ToDos
            break;
        case ADD_EVENT:
            String eventInput[] = restOfInput.split("/at", 2);

            if (eventInput.length > 1) {
                // result[0] equals description, result[1] equals "at"
                addEvent(eventInput[0].trim(), eventInput[1].trim()); 
            } else {
                // adds the description, no user input for "at"
                addEvent(eventInput[0].trim(), "");
            }
            break;
        case ADD_DEADLINE:
            String deadlineInput[] = restOfInput.split("/by", 2);
            if (deadlineInput.length > 1) {
                // result[0] equals description, result[1] equals "by"
                addDeadline(deadlineInput[0].trim(), deadlineInput[1].trim());
            } else {
                // adds the description, no user input for "by"
                addDeadline(deadlineInput[0].trim(), "");
            }
            break;
        case DONE_TASK:
            markTaskAsDone(restOfInput);
            break;
        case INVALID:
        default:
            printMessageForInvalidInput();
            break;
        }
    }

    public static void inputManager() {
        String input = scanner.nextLine();
        Command currentCommand = Command.INVALID;
        String restOfInput = "";
        while (true) {
            if (input.trim().equals("bye")) {
                break; //exit inputManager
            } else if (input.trim().equals("list")) {
                currentCommand = Command.SHOW_LIST;
            } else if (input.trim().indexOf(" ") > -1) { // input more than one word
                String result[] = input.trim().split(" ", 2);
                String firstWord = result[0];
                String otherWords = result[1];
                if (firstWord.equals("todo")) {
                    currentCommand = Command.ADD_TODO;
                    restOfInput = otherWords;
                } else if (firstWord.equals("event")) {
                    currentCommand = Command.ADD_EVENT;
                    restOfInput = otherWords;
                } else if (firstWord.equals("deadline")) {
                    currentCommand = Command.ADD_DEADLINE;
                    restOfInput = otherWords;
                } else if (firstWord.equals("done")) {
                    currentCommand = Command.DONE_TASK;
                    restOfInput = otherWords;
                } else {
                    currentCommand = Command.INVALID;
                    restOfInput = "";
                }
            } else {
                currentCommand = Command.INVALID;
                restOfInput = "";
            }
            commandManager(currentCommand, restOfInput);
            input = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        printGreetingMessage();
        inputManager();
        printFarewellMessage();
    }
}
