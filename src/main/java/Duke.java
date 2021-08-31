import java.util.Scanner;

public class Duke {


    //Commands
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";

    //Commonly used message formats in UI
    private static final String DIVIDER = "________________________________________________________________";
    private static final String LS = System.lineSeparator();
    private static final String QUOTATION = "\"";
    private static final String MESSAGE_WELCOME_DUDE = "Hello! I'm Dude ヽ༼ ・ ل͜ ・ ༽ﾉ";
    private static final String MESSAGE_BYE = "Bye! Hope to see you again soon! ヽ༼ ͠° ͟ل͜ ͠° ༽ﾉ ";
    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! %1$s";
    private static final String MESSAGE_COMMAND_LIST = "Commands:" + LS
            + QUOTATION + COMMAND_TODO + " X" + QUOTATION + " : Add Todo task X" + LS
            + QUOTATION + COMMAND_DEADLINE + " X Y" + QUOTATION + " : Add task X with deadline Y" + LS
            + QUOTATION + COMMAND_EVENT + " X Y" + QUOTATION + " : Add event X with date/time details Y" + LS
            + QUOTATION + COMMAND_LIST + QUOTATION + " : See lists of tasks" + LS
            + QUOTATION + COMMAND_DONE + " X" + QUOTATION + " : Mark task number X as done" + LS
            + QUOTATION + COMMAND_BYE + QUOTATION + " : Stop Dude :(";


    public static void showMessage(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public static void showListOfCommands() {
        showMessage(DIVIDER, MESSAGE_COMMAND_LIST, DIVIDER);
    }

    public static void printDivider() {
        System.out.println("________________________________________________________________");
    }
    
    public static void welcome() {
        printDivider();
        System.out.println("Hello! I'm Dude ヽ༼ ・ ل͜ ・ ༽ﾉ");
        printDivider();
    }

    public static void exit() {
        printDivider();
        System.out.println("Bye! Hope to see you again soon! ヽ༼ ͠° ͟ل͜ ͠° ༽ﾉ ");
        printDivider();
    }

    public static void addTask(Task[] listOfTasks, String description) {
        Task t = new Task(description);
        listOfTasks[Task.getNumTasks()] = t; //index 0 in listOfTasks is empty
        printDivider();
        System.out.println("Added to list: " + description);
        System.out.println("Add another task now or type \"list\" to see current list!");
        printDivider();
    }

    public static void printTasks(Task[] listOfTasks) {
        if (Task.getNumTasks() == 0) {
            printDivider();
            System.out.println("No tasks yet, add a task now!");
            printDivider();
        } else {
            printDivider();
            System.out.println("These are your current tasks:");
            for (int i = 1; i <= Task.getNumTasks(); i++) {
                System.out.println(i + ". " + listOfTasks[i].getStatusIcon()
                        + " " + listOfTasks[i].getDescription());
            }
            printDivider();
        }
    }

    public static void markTaskAsDone(Task[] listOfTasks, String input) {
        String[] words = input.split(" ");
        if (words.length == 1) {
            //error if user inputs only "done" with no number behind
            printDivider();
            System.out.println("Invalid format! Please input a task number to be marked as done, "
                    + System.lineSeparator() + "in the format \"done X\", where X is the task number!");
            printDivider();
        } else {
            try {
                int taskNum = Integer.parseInt(words[1]);
                if (Task.getNumTasks() == 0) {
                    //error if user does not have any tasks to be marked completed
                    printDivider();
                    System.out.println("No tasks yet to be marked as done, add a task now!");
                    printDivider();
                } else if (taskNum > Task.getNumTasks() || taskNum < 1) {
                    //error if user inputs a task number that does not exist
                    printDivider();
                    System.out.println("Please input a valid task number from 1 to " + Task.getNumTasks() + "!");
                    printDivider();
                } else {
                    printDivider();
                    listOfTasks[taskNum].markAsDone();
                    System.out.println(listOfTasks[taskNum].getStatusIcon()
                            + " " + listOfTasks[taskNum].getDescription());
                    printDivider();
                }
            } catch (NumberFormatException e) {
                //error if user did not input a valid integer for task number
                printDivider();
                System.out.println("Invalid format! Please input a task number to be marked as done, "
                        + System.lineSeparator() + "in the format \"done X\", where X is the task number!");
                printDivider();
            }
        }
    }

    /**
     * Continuously adds user inputs into list of tasks.
     * Prints list of tasks when user inputs "List" (not case-sensitive),
     * Marks task number X as done when user inputs "done X" (not case-sensitive),
     * Exits when user inputs "Bye" (not case-sensitive)
     */
    public static void enterToDoListMode() {
        showListOfCommands();
        Task[] listOfTasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String input;
        boolean hasSaidBye = false;
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                //exit loop
                hasSaidBye = true;
            } else if (input.equalsIgnoreCase("list")) {
                //print task
                printTasks(listOfTasks);
            } else if (input.toLowerCase().startsWith("done")) {
                //mark as done
                markTaskAsDone(listOfTasks, input);
            } else {
                //add task
                addTask(listOfTasks, input);
            }
        } while (!hasSaidBye);
    }


    public static void main(String[] args) {
      welcome();
      enterToDoListMode();
      exit();
    }
}


