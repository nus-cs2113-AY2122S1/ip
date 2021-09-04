import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // ArrayList to store all the tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWelcomeMessage();
        getUserInput();
    }

    /**
     * Prompts input from user and processes it
     */
    private static void getUserInput() {
        // String to store user's input
        String userInput = "";
        Scanner in = new Scanner(System.in);

        // Loops till bye is received
        while (true) {
            // Reads user input
            userInput = in.nextLine();
            // Splits user input by spaces
            String[] userInputSplitted = userInput.split("\\s+", 2);
            String command = userInputSplitted[0];
            String description = "";

            // Ensure description exists
            if (userInputSplitted.length > 1) {
                description = userInputSplitted[1];
            }
            try{
                processCommands(command, description);
            }catch (InvalidCommand e){
                // Invalid Command
                printInvalidCommand();
            }

        }
    }

    /**
     * Processes the commands entered by the user.
     *
     * @param command     The command entered by the user
     * @param description Task description entered by user.
     * @throws InvalidCommand If an invalid command is entered.
     */
    private static void processCommands(String command, String description) throws InvalidCommand {
        // Capitalise the command
        switch (command.toUpperCase()) {
        case "LIST":
            listTasks();
            break;
        case "TODO":
            addToDo(description);
            break;
        case "DEADLINE":
            addDeadLine(description);
            break;
        case "EVENT":
            addEvent(description);
            break;
        case "DONE":
            markDone(description);
            break;
        case "BYE":
            exit();
        default:
            throw new InvalidCommand();
        }
    }

    /**
     * Adds a Todo to the Task list
     *
     * @param description Task description entered by user.
     */
    private static void addToDo(String description) {
        if (description.equals("")) {
            customPrint("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            tasks.add(new Todo(description));
            acknowledgeCommand();
        }
    }

    /**
     * Splits the string by the delimiter provided
     *
     * @param delimiter   Delimiter to split string by
     * @param description String to be split
     * @return Array of string of size 2 after string is spilt by the delimiter or null if delimiter was not found
     */
    private static String[] spiltString(String delimiter, String description) {
        int byIndex = description.indexOf(delimiter);
        if (byIndex == -1) {
            return null;
        }
        String taskDescription = description.substring(0, byIndex).trim(); // Remove trailing spaces
        String date = description.substring(byIndex + delimiter.length()).trim(); // Remove leading spaces
        String[] output = {taskDescription, date};
        return output;
    }

    /**
     * Adds a Deadline to the Task list
     *
     * @param description Task description entered by user.
     */
    private static void addDeadLine(String description) {
        String[] split = spiltString("/by", description);
        // Handle case it user did not provide date
        if (split == null) {
            customPrint("I could not find '/by' in your command!");
        } else if (split[0].equals("")) {
            customPrint("I could not find the title of your deadline!");
        } else {
            String taskDescription = split[0];
            String date = split[1];
            tasks.add(new Deadline(taskDescription, date));
            acknowledgeCommand();
        }
    }

    /**
     * Adds an Event to the Task list
     *
     * @param description Task description entered by user.
     */
    private static void addEvent(String description) {
        String[] split = spiltString("/at", description);
        // Handle case it user did not provide date
        if (split == null) {
            customPrint("I could not find '/at' in your command!");
        } else if (split[0].equals("")) {
            customPrint("I could not find the title of your event!");
        } else {
            String taskDescription = split[0];
            String at = split[1];
            tasks.add(new Event(taskDescription, at));
            acknowledgeCommand();
        }
    }

    /**
     * Prints the exit message and ends the program.
     */
    private static void exit() {
        customPrint("Bye. Hope to see you again soon!");
        // Ends the program
        System.exit(0);
    }

    /**
     * Prints the acknowledgement that the command is processed.
     */
    private static void acknowledgeCommand() {
        Task addedTask = tasks.get(tasks.size() - 1);
        customPrint("Got it. I've added this task:\n" + addedTask.toString() + "\nNow you have "
                + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the welcome message.
     */
    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Prints welcome message
        String welcomeMessage = logo;
        welcomeMessage += "Hello! I'm Duke\n";
        welcomeMessage += "What can I do for you?";
        customPrint(welcomeMessage);
    }

    /**
     * Prints the invalid command message.
     */
    private static void printInvalidCommand() {
        customPrint("Sorry! I did not understand your command.");
    }


    /**
     * Prints out a statement with borders.
     *
     * @param statement Statement to be printed.
     */
    public static void customPrint(String statement) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(statement.trim()); // Remove any trailing spaces
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    /**
     * Prints out all the Tasks with borders.
     */
    public static void listTasks() {
        String taskString = "";
        // Checks if tasks exists
        if (tasks.size() == 0) {
            taskString += "You have no tasks in your list!";
        } else {
            taskString += "Here are the tasks in your list:\n";
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            taskString += (i + 1) + "." + currentTask.toString() + "\n";
        }
        customPrint(taskString);
    }

    /**
     * Marks a task as done and calls customPrint to print the completed message.
     *
     * @param description String ID of task to be marked as completed.
     */
    public static void markDone(String description) {
        int taskId = Integer.parseInt(description) - 1; // -1 as array index starts from 0

        Boolean isNotValidTaskID = taskId < 0 || taskId >= tasks.size();

        // Checks for valid taskID
        if (isNotValidTaskID) {
            customPrint("You have entered an invalid task ID!");
            return;
        }

        // Checks if task has been completed
        Task currentTask = tasks.get(taskId);
        if (currentTask.isDone()) {
            customPrint("You have already completed the task [" + currentTask.getDescription() + "]!");
        } else {
            currentTask.markAsDone(); // Mark current task as done
            String textToPrint = "Nice! I've marked this task as done:\n";
            textToPrint += currentTask.toString();
            customPrint(textToPrint);
        }
    }
}
