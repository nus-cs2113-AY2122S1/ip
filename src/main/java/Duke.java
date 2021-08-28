import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = System.lineSeparator()
            + "    ___   ______   ________  _______      ___   ____  _____   .--." + System.lineSeparator()
            + "  .'   `.|_   _ \\ |_   __  ||_   __ \\   .'   `.|_   \\|_   _|.'_\\/_'." + System.lineSeparator()
            + " /  .-.  \\ | |_) |  | |_ \\_|  | |__) | /  .-.  \\ |   \\ | |  '. /\\,.'" + System.lineSeparator()
            + " | |   | | |  __'.  |  _| _   |  __ /  | |   | | | |\\ \\| |    \"||\"" + System.lineSeparator()
            + " \\  `-'  /_| |__) |_| |__/ | _| |  \\ \\_\\  `-'  /_| |_\\   |_    || /\\`" + System.lineSeparator()
            + "  `.___.'|_______/|________||____| |___|`.___.'|_____|\\____|/\\ ||//\\)" + System.lineSeparator()
            + "                                                           (/\\\\||/" + System.lineSeparator()
            + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\||^^^^";

    private static final String HORIZONTAL_BAR = "__________________"
            + "__________________"
            + "__________________"
            + "_______________";


    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Print greeting message upon starting the program
     */
    public static void displayGreetingMessage() {
        echo(LOGO + System.lineSeparator() + "  Hello! I'm Oberon"
                + System.lineSeparator() + "  What can I do for you?");
    }

    /**
     * Print farewell message upon exiting the program
     */
    public static void displayFarewellMessage() {
        echo("  Goodbye. Hope to see you again soon!" + System.lineSeparator() + LOGO);
    }

    /**
     * Reads in a string and prints it with a horizontal bar above & below it
     * @param input String to be echo-ed
     */
    public static void echo(String input) {
        System.out.println(HORIZONTAL_BAR + System.lineSeparator() + input
                + System.lineSeparator() + HORIZONTAL_BAR);
    }

    /**
     * Prints current list of tasks
     */
    public static void printTasks() {
        if (tasks.isEmpty()) {
            echo("  List is empty!");
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

    /**
     * Marks task in array as done and prints echo message depending on validity of task no.
     */
    public static void markTaskAsDone(String input) {
        // Extracting task number as an integer from input
        int taskNumber = Integer.parseInt(input.trim(), 10);
        boolean taskNumberInRange = (taskNumber <= tasks.size()) && (taskNumber >= 1);
        if (taskNumberInRange) {
            tasks.get(taskNumber - 1).setDone();
            echo("Task " + taskNumber + ": " + tasks.get(taskNumber - 1).taskDescription
                    + System.lineSeparator() + "  Marked as done!");
        } else {
            echo("  Invalid Task number!");
        }
    }

    public static void addToDo (String description) {
        tasks.add(new ToDo(description));
        echo("  You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public static void addEvent (String description, String at) {
        tasks.add(new Event(description, at));
        echo("  You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public static void addDeadline (String description, String by) {
        tasks.add(new Deadline(description, by));
        echo("  You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public static void printMessageForInvalidInput () {
        echo("  Invalid Input..." + System.lineSeparator()
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
            checkInputThenAddEvent(eventInput);
            break;
        case ADD_DEADLINE:
            String deadlineInput[] = restOfInput.split("/by", 2);
            checkInputThenAddDeadline(deadlineInput);
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

    private static void checkInputThenAddEvent(String[] eventInput) {
        if (eventInput.length > 1) {
            // eventInput[0] equals description, eventInput[1] equals "at"
            addEvent(eventInput[0].trim(), eventInput[1].trim());
        } else {
            // adds the description, no user input for "at"
            addEvent(eventInput[0].trim(), "");
        }
    }

    private static void checkInputThenAddDeadline(String[] deadlineInput) {
        if (deadlineInput.length > 1) {
            // deadlineInput[0] equals description, deadlineInput[1] equals "by"
            addDeadline(deadlineInput[0].trim(), deadlineInput[1].trim());
        } else {
            // adds the description, no user input for "by"
            addDeadline(deadlineInput[0].trim(), "");
        }
    }

    private static Command getCommand(String input) {
        Command inputCommand;
        if (input.equals("todo")) {
            inputCommand = Command.ADD_TODO;
        } else if (input.equals("event")) {
            inputCommand = Command.ADD_EVENT;
        } else if (input.equals("deadline")) {
            inputCommand = Command.ADD_DEADLINE;
        } else if (input.equals("done")) {
            inputCommand = Command.DONE_TASK;
        } else {
            inputCommand = Command.INVALID;
        }
        return inputCommand;
    }

    public static void inputManager() {
        String input = scanner.nextLine();
        Command currentCommand = Command.INVALID;
        String restOfInput = "";
        while (true) {
            if (input.trim().equals("bye")) {
                break; // exit inputManager
            }
            if (input.trim().equals("list")) {
                currentCommand = Command.SHOW_LIST;
            } else if (input.trim().indexOf(" ") > -1) { // input is more than one word
                String result[] = input.trim().split(" ", 2);
                String firstWord = result[0];
                String otherWords = result[1];
                currentCommand = getCommand(firstWord);
                // if invalid return empty string
                restOfInput = (currentCommand != Command.INVALID) ? otherWords : "";
            } else { // all other cases are also considered invalid inputs
                currentCommand = Command.INVALID;
                restOfInput = "";
            }
            commandManager(currentCommand, restOfInput);
            input = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        displayGreetingMessage();
        inputManager();
        displayFarewellMessage();
    }
}
