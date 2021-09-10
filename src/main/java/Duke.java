import Duke.task.*;
import Duke.exception.*;
import java.util.Scanner;

public class Duke {
    private static final String DOTTED_LINE = "___________________________________________________\n";
    private static int taskCount = 0;
    private static final int MAX_TASKS = 100;

    private static Task[] tasks = new Task[MAX_TASKS];

    private static void doneCommand(String input) {
        int pos = Integer.parseInt(input.split(" ")[1]) - 1;
        try {
            tasks[pos].setDone();
            System.out.println(DOTTED_LINE);
            System.out.println("Nice I've marked this task as done:");
            System.out.println(tasks[pos]);
            System.out.println(DOTTED_LINE);
        } catch (NullPointerException e) {
            System.out.println(DOTTED_LINE);
            System.out.println("No such task exists");
            System.out.println(DOTTED_LINE);
        }
    }

    private static void listCommand() {
        System.out.println(DOTTED_LINE);
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + "." + tasks[i]);
        }
        System.out.println(DOTTED_LINE);
    }


    private static void addCommand(String input) throws DukeInvalidInputException {
        String taskType = input.split(" ")[0];
        int commandIndex = input.indexOf(' ');
        int timeIndex = input.indexOf('/');
        if(commandIndex == -1)
            throw new DukeInvalidInputException();
        switch (taskType) {
        case "todo":
            tasks[taskCount] = new ToDo(input.substring(commandIndex + 1));
            taskCount++;
            break;
        case "deadline":
            tasks[taskCount] = new Deadline(input.substring(commandIndex + 1 , timeIndex), input.split("/")[1]);
            taskCount++;
            break;
        case "event":
            tasks[taskCount] = new Event(input.substring(commandIndex + 1 , timeIndex), input.split("/")[1]);
            taskCount++;
            break;
        default:
        }
        System.out.println(DOTTED_LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("You now have " + taskCount + " items in the list.");
        System.out.println(DOTTED_LINE);
    }

    private static void handleInput(String input) throws DukeCommandException {
        String command = input.split(" ")[0];
        switch (command) {
        case "done":
            doneCommand(input);
            break;

        case "list":
            listCommand();
            break;

        case "todo":
        case "deadline":
        case "event":
            try {
                addCommand(input);
            } catch (IndexOutOfBoundsException | DukeInvalidInputException e) {
                System.out.println(DOTTED_LINE);
                System.out.println("Invalid input. Please try again");
                System.out.println(DOTTED_LINE);
            }
            break;
        default:
           throw new DukeCommandException();
        }
    }

    private static void welcomeMessage() {
        System.out.println(DOTTED_LINE);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println(DOTTED_LINE);
    }

    private static void exitMessage() {
        System.out.println(DOTTED_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DOTTED_LINE);
    }

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        welcomeMessage();

        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            try {
                handleInput(userInput);
            } catch (DukeCommandException e) {
                System.out.println(DOTTED_LINE);
                System.out.println("Invalid command entered. Please try again");
                System.out.println(DOTTED_LINE);
            }
            userInput = in.nextLine();
        }

        exitMessage();
    }
}
