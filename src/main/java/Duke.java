import java.util.Scanner;

public class Duke {

    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static final int TASK_ARGUMENT = 1;
    public static final String TASK_SEPARATOR = " ";
    public static final String DEADLINE_SEPARATOR = "/by ";
    public static final String EVENT_SEPARATOR = "/at ";
    public static final String LIST_COMMAND = "list";
    public static final String ADD_TODO_COMMAND = "todo ";
    public static final String ADD_DEADLINE_COMMAND = "deadline ";
    public static final String ADD_EVENT_COMMAND = "event ";
    public static final String MARK_AS_DONE_COMMAND = "done ";
    public static final String EXIT_COMMAND_1 = "bye";
    public static final String EXIT_COMMAND_2 = "exit";

    public static void main(String[] args) {

        printWelcomeMessage();

        Task[] list = new Task[MAX_NUMBER_OF_TASKS];
        int listItemCount = 0; //track number of tasks in the list

        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); //read input

        //run program as long as exit command is not given
        while (!input.equals(EXIT_COMMAND_1) && !input.equals(EXIT_COMMAND_2)) {

            if (input.equals(LIST_COMMAND)) {

                if (listItemCount == 0) {
                    printNoTasksInListMessage();
                } else {
                    listTasks(list, listItemCount);
                }

            } else if (input.startsWith(MARK_AS_DONE_COMMAND)) {
                
                String[] substrings = input.split(TASK_SEPARATOR); //get all the words in the input
                int taskNum = Integer.parseInt(substrings[TASK_ARGUMENT]);
                //check if task number is valid
                if (taskNum > 1 && taskNum <= listItemCount) {
                    markTaskAsDone(list[taskNum - 1]);
                } else {
                    printInvalidTaskNumberMessage();
                }

            } else if (input.startsWith(ADD_TODO_COMMAND)){

                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                String task = getTrimmedSubstring(input, indexOfTask, input.length());
                if (task.isBlank()) {
                    printNoTaskErrorMessage();
                } else {
                    list[listItemCount] = new Todo(task);
                    listItemCount = updateTaskListNumber(list, listItemCount);
                }

            } else if (input.startsWith(ADD_DEADLINE_COMMAND)){

                String arguments = input.substring(ADD_DEADLINE_COMMAND.length(), input.length());
                if (arguments.isBlank()) {
                    printNoTaskErrorMessage();
                } else if (!input.contains(DEADLINE_SEPARATOR)) {
                    printNoDueDateErrorMessage();
                } else {
                    int indexOfTask = input.indexOf(TASK_SEPARATOR);
                    int indexOfDeadline = input.indexOf(DEADLINE_SEPARATOR);
                    //extract task and due date
                    String task = getTrimmedSubstring(input, indexOfTask, indexOfDeadline);
                    String by = getTrimmedSubstring(input, indexOfDeadline + DEADLINE_SEPARATOR.length(), input.length());
                    if (task.isBlank()) {
                        printNoTaskErrorMessage();
                    } else if (by.isBlank()) {
                        printNoDueDateErrorMessage();
                    } else {
                        list[listItemCount] = new Deadline(task, by);
                        listItemCount = updateTaskListNumber(list, listItemCount);
                    }
                }

            } else if (input.startsWith(ADD_EVENT_COMMAND)){

                String arguments = input.substring(ADD_EVENT_COMMAND.length(), input.length());
                if (arguments.isBlank()) {
                    printNoEventErrorMessage();
                } else if (!input.contains(EVENT_SEPARATOR)) {
                    printNoEventDateErrorMessage();
                } else {
                    int indexOfTask = input.indexOf(TASK_SEPARATOR);
                    int indexOfEvent = input.indexOf(EVENT_SEPARATOR);
                    //extract task and event date
                    String task = getTrimmedSubstring(input, indexOfTask, indexOfEvent);
                    String at = getTrimmedSubstring(input, indexOfEvent + EVENT_SEPARATOR.length(), input.length());
                    if (task.isBlank()) {
                        printNoEventErrorMessage();
                    } else if (at.isBlank()) {
                        printNoEventDateErrorMessage();
                    } else {
                        list[listItemCount] = new Event(task, at);
                        listItemCount = updateTaskListNumber(list, listItemCount);
                    }
                }

            } else {
                //default
                printInvalidCommandMessage();

            }

            //clear the buffer when reading input
            input = in.nextLine();

        }

        //show exit message when given exit command
        printExitMessage();

    }

    // ---------------------------------------------------
    // MAIN METHODS
    // ---------------------------------------------------

    /**
     * Mark a task as done.
     * @param task The task to be marked as done
     */
    private static void markTaskAsDone(Task task) {
        task.markAsDone(); //use zero indexing
        System.out.println("Nice! I've marked the following task as done: ");
        System.out.println("   " + task);
    }

    /**
     * Print the task added, then updates and prints the number of tasks in the list
     * @param list The list of tasks
     * @param listItemCount The number of tasks in the list
     * @return The updated number of tasks in the list
     */
    private static int updateTaskListNumber(Task[] list, int listItemCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(list[listItemCount]);
        listItemCount += 1;
        System.out.println("Now you have " + listItemCount + " items in the list.");
        return listItemCount;
    }

    /**
     * Get the substring of a string with leading and trailing spaces removed.
     * @param input The input string
     * @param startingIndex Starting index of substring
     * @param endingIndex Ending index of substring
     * @return The trimmed substring
     */
    private static String getTrimmedSubstring(String input, int startingIndex, int endingIndex) {
        return input.substring(startingIndex, endingIndex).trim();
    }

    /**
     * Print the tasks currently in the list.
     * @param list The list of tasks
     * @param listItemCount The number of tasks in the list
     */
    private static void listTasks(Task[] list, int listItemCount) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < listItemCount; i += 1) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }

    // ---------------------------------------------------
    // PRINTED MESSAGES
    // ---------------------------------------------------

    /**
     * Print an error message when input matches none of the commands.
     */
    private static void printInvalidCommandMessage() {
        System.out.println("I'm not sure what you want to do. Check if you've spelled correctly!");
    }

    /**
     * Print a message when there are no tasks in a list.
     */
    private static void printNoTasksInListMessage() {
        System.out.println("There are no tasks in the list!");
    }

    /**
     * Print an error message when attempting to mark an invalid task number as done.
     */
    private static void printInvalidTaskNumberMessage() {
        System.out.println("I can't find that task in the list.");
    }

    /**
     * Print an error message when no event date is given.
     */
    private static void printNoEventDateErrorMessage() {
        System.out.println("Please tell me when the event is happening.");
    }

    /**
     * Print an error message when no date is given for a deadline.
     */
    private static void printNoDueDateErrorMessage() {
        System.out.println("Please tell me when the task is due.");
    }

    /**
     * Print an error message when no task name is given.
     */
    private static void printNoTaskErrorMessage() {
        System.out.println("Please tell me the name of the task.");
    }

    /**
     * Print an error message when no event name is given.
     */
    private static void printNoEventErrorMessage() {
        System.out.println("Please tell me the name of the event.");
    }

    /**
     * Print a welcome message when starting the program.
     */
    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Print a message when exiting the program.
     */
    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
