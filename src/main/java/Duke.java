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
    public static final String TASK_PADDING = "   ";
    public static final String NUMBER_LIST_SEPARATOR = ". ";
    public static final String HELP_COMMAND = "help";
    public static final String GREETING_COMMAND = "hello";

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
                if (taskNum > 0 && taskNum <= listItemCount) {
                    if (list[taskNum - 1].isDone) {
                        printTaskAlreadyDoneMessage();
                    } else {
                        markTaskAsDone(list[taskNum - 1]);
                    }
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

                String arguments = input.substring(ADD_DEADLINE_COMMAND.length());
                if (arguments.isBlank()) {
                    printNoTaskErrorMessage();
                } else if (!input.contains(DEADLINE_SEPARATOR)) {
                    printNoDueDateErrorMessage();
                } else {
                    int indexOfTask = input.indexOf(TASK_SEPARATOR);
                    int indexOfDeadline = input.indexOf(DEADLINE_SEPARATOR);
                    //extract task and due date
                    String task = getTrimmedSubstring(input, indexOfTask, indexOfDeadline);
                    String by = getTrimmedSubstring(input, indexOfDeadline + DEADLINE_SEPARATOR.length(), 
                            input.length());
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

                String arguments = input.substring(ADD_EVENT_COMMAND.length());
                if (arguments.isBlank()) {
                    printNoEventErrorMessage();
                } else if (!input.contains(EVENT_SEPARATOR)) {
                    printNoEventDateErrorMessage();
                } else {
                    int indexOfTask = input.indexOf(TASK_SEPARATOR);
                    int indexOfEvent = input.indexOf(EVENT_SEPARATOR);
                    //extract task and event date
                    String task = getTrimmedSubstring(input, indexOfTask, indexOfEvent);
                    String at = getTrimmedSubstring(input, indexOfEvent + EVENT_SEPARATOR.length(),
                            input.length());
                    if (task.isBlank()) {
                        printNoEventErrorMessage();
                    } else if (at.isBlank()) {
                        printNoEventDateErrorMessage();
                    } else {
                        list[listItemCount] = new Event(task, at);
                        listItemCount = updateTaskListNumber(list, listItemCount);
                    }
                }

            } else if (input.equals(HELP_COMMAND)){

                printHelpMessage();

            } else if (input.equals(GREETING_COMMAND)){

                printGreetingMessage();

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
        System.out.println(TASK_PADDING + task);
    }

    /**
     * Print the task added, then updates and prints the number of tasks in the list
     * @param list The list of tasks
     * @param listItemCount The number of tasks in the list
     * @return The updated number of tasks in the list
     */
    private static int updateTaskListNumber(Task[] list, int listItemCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(TASK_PADDING + list[listItemCount]);
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
            System.out.println((i + 1) + NUMBER_LIST_SEPARATOR + list[i]);
        }
    }

    // ---------------------------------------------------
    // PRINTED MESSAGES
    // ---------------------------------------------------

    /**
     * Print an error message when input matches none of the commands.
     */
    private static void printInvalidCommandMessage() {
        System.out.println("I'm not sure what you want to do. Check if you've spelled correctly! " +
                "Type help for a list of commands.");
    }

    /**
     * Print a message when there are no tasks in a list.
     */
    private static void printNoTasksInListMessage() {
        System.out.println("There are no tasks in the list!");
    }

    /**
     * Prints a message when attempting to mark an already completed task as done.
     */
    private static void printTaskAlreadyDoneMessage() {
        System.out.println("This task has been completed!");
    }

    /**
     * Print an error message when attempting to mark a task not on the list as done.
     */
    private static void printInvalidTaskNumberMessage() {
        System.out.println("I can't find that task in the list!");
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
        System.out.println("Hello! I'm Duke, your personal task assistant.");
        System.out.println("Type help to see what I can do.");
    }

    /**
     * Print a message when exiting the program.
     */
    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message greeting the user.
     */
    private static void printGreetingMessage() {
        System.out.println("Hello! What can I do for you?");
    }

    /**
     * Prints the help message, which lists all commands.
     */
    private static void printHelpMessage() {
        printWithNewLine("Here are a list of commands:");
        printWithNewLine("list - lists all tasks");
        System.out.println("done - marks a task as done");
        printWithNewLine("usage: done [task number]");
        System.out.println("todo - adds a new to-do task");
        printWithNewLine("usage: todo [task description]");
        System.out.println("deadline - adds a new task with a due date");
        printWithNewLine("usage: deadline [task description] /by [due date]");
        printWithNewLine("help - lists all commands");
    }

    /**
     * Prints a string with a new line
     * @param s The string to be printed
     */
    private static void printWithNewLine(String s) {
        System.out.println(s);
        System.out.println();
    }
}
