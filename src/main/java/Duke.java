import java.util.Scanner;

public class Duke {

    /**
     * A string to append before and after a print message.
     */
    final static String LINE = "____________________________________________________________";

    /**
     * Main function to run the bot.
     *
     * @param args Arguments from console input
     */
    public static void main(String[] args) {
        System.out.println(LINE);
        printWelcomeMesage();
        getMenu();
    }

    /**
     * Prints the exit message when user quits the program.
     */
    public static void printExitMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints the welcome message when user first runs the program.
     */
    public static void printWelcomeMesage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints the logo of the bot.
     */
    public static void printLogoMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE);
    }

    /**
     * A generic print method that appends a line before and after the message.
     *
     * @param message Message to be printed out.
     */
    public static void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Prints the status of all tasks in a given tasks array.
     *
     * @param tasksList Array of task objects.
     */
    public static void printTasksList(Task[] tasksList) {
        if (tasksList == null) {
            System.out.println("There is currently 0 tasks in your list.");
            return;
        }
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasksList) {
            System.out.printf("%d. ", i);
            task.printStatus();
            i++;
        }
    }

    /**
     * Changes the status of a specified task to be mark as done. The format of the userInputs should be "done X" where
     * X is an index within the taskList.
     *
     * @param tasksList  Array of task objects.
     * @param userInputs User command input determining which task to be marked as done.
     */
    public static void setDone(Task[] tasksList, String userInputs) {
        if (tasksList == null) {
            System.out.println("Error: No tasks in list.");
            return;
        }
        try {
            int index = Integer.parseInt(userInputs.split(" ")[1]);
            index = index - 1;
            if (index >= 0 && index < tasksList.length) {
                tasksList[index].setDone(true);
                tasksList[index].printStatus();
            } else {
                System.out.println("Error: task not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: Incorrect format.");
            return;
        }


    }

    /**
     * Add task into tasks array and returns the tasks array.
     *
     * @param tasksList Array of task objects.
     * @param taskName  Task name for the new task object.
     * @return An array of task objects.
     */
    public static Task[] addTaskToList(Task[] tasksList, String taskName) {
        if (tasksList == null) {
            Task task = new Task(taskName);
            tasksList = new Task[1];
            tasksList[0] = task;
            return tasksList;
        }
        Task[] newItemsList = new Task[tasksList.length + 1];
        for (int i = 0; i < tasksList.length; i++) {
            newItemsList[i] = tasksList[i];
        }
        newItemsList[tasksList.length] = new Task(taskName);
        return newItemsList;
    }

    /**
     * Get the menu for user to input commands for the bot to perform.
     */
    public static void getMenu() {
        Scanner in = new Scanner(System.in);
        String userInputs = in.nextLine();
        Task[] tasksList = null;
        menuLoop:
        while (true) {
            switch (userInputs.split(" ")[0]) {
            case "":
                break;
            case "done":
                System.out.println(LINE);
                setDone(tasksList, userInputs);
                System.out.println(LINE);
                break;
            case "list":
                System.out.println(LINE);
                printTasksList(tasksList);
                System.out.println(LINE);
                break;
            case "bye":
                printExitMessage();
                break menuLoop;
            default:
                tasksList = addTaskToList(tasksList, userInputs);
                printMessage("added: " + userInputs);
                break;
            }
            userInputs = in.nextLine();
        }
    }
}
