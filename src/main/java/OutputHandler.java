import java.util.ArrayList;

public class OutputHandler {

    public static final String NUMBER_LIST_SEPARATOR = ". ";
    public static final String TASK_PADDING = "   ";
    public static final String NEWLINE = "\n";
    public static final String DEADLINE_SEPARATOR = "/by ";
    public static final String EVENT_SEPARATOR = "/at ";
    private static final String TASK_SEPARATOR = " ";
    public static final String HELP_SEPARATOR = " - ";

    /**
     * Print a welcome message when starting the program.
     */
    public void printWelcomeMessage() {
        System.out.println(NEWLINE + "Hello! I'm Duke, your personal task assistant.");
        System.out.println("Get started by adding a task, or type help to see what I can do." + NEWLINE);
    }

    /**
     * Prints messages and changes the task list according to the commands given.
     * @param command The command entered by the user
     * @param input The input string read from the Scanner object
     * @param tasks The list of tasks
     */
    public void outputMessage(Command command, String input, ArrayList<Task> tasks) {

        String[] inputTokens = input.split(" ");
        int taskNumber;

        switch (command) {
        case LIST_TASKS:
            if (tasks.size() == 0) {
                printNoTasksInListMessage();
            } else {
                printTaskList(tasks);
            }
            break;

        case MARK_DONE:
            if (input.trim().equals(inputTokens[0])) {
                printInvalidTaskMessage();
            } else {
                //get 1-indexed task number and convert to 0-index
                taskNumber = Integer.parseInt(inputTokens[1]) - 1;
                if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
                    printInvalidTaskMessage();
                } else if (tasks.get(taskNumber).isDone()) {
                    printTaskDoneMessage();
                } else {
                    markAsDone(tasks, taskNumber);
                }
            }
            break;

        case ADD_TODO:
            if (input.trim().equals(inputTokens[0])) {
                printNoTaskNameMessage();
            } else {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                String description = getTrimmedSubstring(input, indexOfTask, input.length());
                if (description.isBlank()) {
                    printNoTaskNameMessage();
                } else {
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    displayAddedTask(tasks, todo);
                }
            }
            break;

        case ADD_DEADLINE:
            if (input.trim().equals(inputTokens[0])) {
                printNoTaskNameMessage();
            } else if (!input.contains(DEADLINE_SEPARATOR)) {
                printNoDueDateMessage();
            } else {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                int indexOfDeadline = input.indexOf(DEADLINE_SEPARATOR);
                //extract task and due date
                String description = getTrimmedSubstring(input, indexOfTask, indexOfDeadline);
                String by = getTrimmedSubstring(input,
                        indexOfDeadline + DEADLINE_SEPARATOR.length(),
                        input.length());
                //TODO exception handling
                if (description.isBlank()) {
                    printNoTaskNameMessage();
                } else if (by.isBlank()) {
                    printNoDueDateMessage();
                } else {
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    displayAddedTask(tasks, deadline);
                }
            }
            break;

        case ADD_EVENT:
            if (input.trim().equals(inputTokens[0])) {
                printNoEventNameMessage();
            } else if (!input.contains(EVENT_SEPARATOR)) {
                printNoEventDateMessage();
            } else {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                int indexOfDeadline = input.indexOf(EVENT_SEPARATOR);
                //extract task and due date
                String description = getTrimmedSubstring(input, indexOfTask, indexOfDeadline);
                String at = getTrimmedSubstring(input,
                        indexOfDeadline + EVENT_SEPARATOR.length(),
                        input.length());
                //TODO exception handling
                if (description.isBlank()) {
                    printNoEventNameMessage();
                } else if (at.isBlank()) {
                    printNoEventDateMessage();
                } else {
                    Event event = new Event(description, at);
                    tasks.add(event);
                    displayAddedTask(tasks, event);
                }
            }
            break;

        case REMOVE_TASK:
            if (input.trim().equals(inputTokens[0])) {
                printInvalidTaskMessage();
            } else {
                //get 1-indexed task number and convert to 0-index
                taskNumber = Integer.parseInt(inputTokens[1]) - 1;
                if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
                    printInvalidTaskMessage();
                } else {
                    displayRemovedTask(tasks, taskNumber);
                }
            }
            break;

        case HELP:
            printHelpMessage();
            break;

        case GREETING:
            printGreetingMessage();
            break;

        case EXIT:
            printExitMessage();
            break;

        case DEFAULT:
            printInvalidCommandMessage();
            break;

        default:
            printUnknownErrorMessage();
            break;
        }
    }

    // ---------------------------------------------------
    // OUTPUT HANDLER METHODS
    // ---------------------------------------------------

    /**
     * Get the substring of a string with leading and trailing spaces removed.
     *
     * @param input         The input string
     * @param startingIndex Starting index of substring
     * @param endingIndex   Ending index of substring
     * @return The trimmed substring
     */
    private static String getTrimmedSubstring(String input, int startingIndex, int endingIndex) {
        return input.substring(startingIndex, endingIndex).trim();
    }

    /**
     * Mark a task as done.
     *
     * @param taskNumber The task number to mark as done
     */
    private void markAsDone(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        System.out.println(NEWLINE + "Nice! I've marked the task as done:");
        System.out.println(TASK_PADDING + tasks.get(taskNumber) + NEWLINE);
    }

    /**
     * Print the task added and number of tasks in the list
     *
     * @param tasks The list of tasks
     */
    private static void displayAddedTask(ArrayList<Task> tasks, Task task) {
        System.out.println(NEWLINE + "Got it. I've added this task:");
        System.out.println(TASK_PADDING + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + NEWLINE);
    }

    /**
     * Print a message when a task is removed.
     *
     * @param tasks      The list of tasks
     * @param taskNumber The task number of the task to be removed
     */
    private void displayRemovedTask(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(NEWLINE + "Got it. I removed this task:");
        System.out.println(TASK_PADDING + tasks.get(taskNumber) + NEWLINE);
        tasks.remove(taskNumber);
    }

    /**
     * Prints a message greeting the user.
     */
    private static void printGreetingMessage() {
        System.out.println(NEWLINE + "Hello! What can I do for you?" + NEWLINE);
    }

    /**
     * Print a message when exiting the program.
     */
    private static void printExitMessage() {
        System.out.println(NEWLINE + "Bye. Hope to see you again soon!" + NEWLINE);
    }

    /**
     * Print a message when input matches none of the commands.
     */
    private void printInvalidCommandMessage() {
        System.out.println(NEWLINE + "I'm not sure what you want to do.");
        System.out.println("Check if you've misspelled something!");
        System.out.println("Type help for a list of commands." + NEWLINE);
    }

    /**
     * Print an error message when the command type returned does not match any of the command types given.
     * (This should not happen.)
     */
    private void printUnknownErrorMessage() {
        System.out.println(NEWLINE + "Something went wrong!" + NEWLINE);
    }

    /**
     * Print a message when there are no tasks in a list.
     */
    private static void printNoTasksInListMessage() {
        System.out.println(NEWLINE + "There are no tasks in the list!" + NEWLINE);
    }

    /**
     * Print the tasks currently in the list.
     *
     * @param tasks The list of tasks
     */
    private static void printTaskList(ArrayList<Task> tasks) {
        System.out.println(NEWLINE + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + NUMBER_LIST_SEPARATOR + tasks.get(i));
        }
        System.out.print(NEWLINE);
    }

    /**
     * Print a message when the task number given is invalid.
     */
    private void printInvalidTaskMessage() {
        System.out.println(NEWLINE + "I can't find that task in the list!" + NEWLINE);
    }

    /**
     * Print a message when attempting to mark an already completed task as done.
     */
    private void printTaskDoneMessage() {
        System.out.println(NEWLINE + "This task has been completed!" + NEWLINE);
    }


    /**
     * Print a message when no task name is given.
     */
    private static void printNoTaskNameMessage() {
        System.out.println(NEWLINE + "Please tell me the name of the task." + NEWLINE);
    }

    /**
     * Print a message when no date is given for a deadline.
     */
    private static void printNoDueDateMessage() {
        System.out.println(NEWLINE + "Please tell me when the task is due." + NEWLINE);
    }

    /**
     * Print an error message when no event date is given.
     */
    private static void printNoEventDateMessage() {
        System.out.println(NEWLINE + "Please tell me when the event is happening." + NEWLINE);
    }

    /**
     * Print an error message when no event name is given.
     */
    private static void printNoEventNameMessage() {
        System.out.println(NEWLINE + "Please tell me the name of the event." + NEWLINE);
    }


    /**
     * Prints the help message, which lists all commands.
     */
    private void printHelpMessage() {
        System.out.println(NEWLINE + "Here are a list of commands:" + NEWLINE);
        printCommandHelpMessage("list", "lists all tasks", "list");
        printCommandHelpMessage("todo", "adds a new to-do task", "todo [task description]");
        printCommandHelpMessage("deadline", "adds a new task with a due date",
                "deadline [task description] /by [due date]");
        printCommandHelpMessage("event", "adds a new event",
                "event [task description] /at [event date]");
        printCommandHelpMessage("done", "lists all tasks", "done [task number]");
        printCommandHelpMessage("remove", "removes a task from the list", "remove [task number]");
        printCommandHelpMessage("help", "lists all commands", "help");
    }

    /**
     * Prints the help message for a specific command.
     * @param command The command
     * @param description The command's description
     * @param usage The usage format for the command
     */
    private void printCommandHelpMessage(String command, String description, String usage) {
        System.out.println(command + HELP_SEPARATOR + description + NEWLINE + "usage: " + usage + NEWLINE);
    }
}

