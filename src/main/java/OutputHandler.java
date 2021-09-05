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
     * Print messages and changes the task list according to the commands given.
     * @param command The command entered by the user
     * @param input The input string read from the Scanner object
     * @param tasks The list of tasks
     */
    public void getOutput(Command command, String input, ArrayList<Task> tasks) {

        String[] inputTokens = input.split(" ");

        switch (command) {
        case LIST_TASKS:
            if (tasks.size() == 0) {
                printNoTasksInListMessage();
            } else {
                printTaskList(tasks);
            }
            break;

        case MARK_DONE:
            try {
                //get 1-indexed task number and convert to 0-index
                int taskNumber = Integer.parseInt(inputTokens[1]) - 1;
                if (tasks.get(taskNumber).isDone()) {
                    printTaskDoneMessage();
                } else {
                    markAsDone(tasks, taskNumber);
                }
            } catch (IndexOutOfBoundsException e) {
                printTaskNumberOutOfBoundsMessage();
            } catch (NumberFormatException e) {
                printInvalidTaskNumberMessage();
            }
            break;

        case ADD_TODO:
            try {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                String description = getTrimmedSubstring(input, indexOfTask, input.length());
                if (description.isBlank()) {
                    printNoTaskNameMessage();
                } else {
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    displayAddedTask(tasks, todo);
                }
            } catch (IndexOutOfBoundsException e) {
                printNoTaskNameMessage();
            }
            break;

        case ADD_DEADLINE:
            try {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                int indexOfEventDate = input.indexOf(DEADLINE_SEPARATOR);
                //extract task and due date
                String description = getTrimmedSubstring(input, indexOfTask, indexOfEventDate);
                String by = getTrimmedSubstring(input,
                        indexOfEventDate + DEADLINE_SEPARATOR.length(),
                        input.length());
                if (description.isBlank() || by.isBlank()) {
                    printNoDeadlineMessage();
                } else {
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    displayAddedTask(tasks, deadline);
                }
            } catch (IndexOutOfBoundsException e) {
                printNoDeadlineMessage();
            }
            break;

        case ADD_EVENT:
            try {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                int indexOfEventDate = input.indexOf(EVENT_SEPARATOR);
                //extract task and due date
                String description = getTrimmedSubstring(input, indexOfTask, indexOfEventDate);
                String at = getTrimmedSubstring(input,
                        indexOfEventDate + EVENT_SEPARATOR.length(),
                        input.length());
                if (description.isBlank() || at.isBlank()) {
                    printNoEventMessage();
                } else {
                    Event event = new Event(description, at);
                    tasks.add(event);
                    displayAddedTask(tasks, event);
                }
            } catch (IndexOutOfBoundsException e) {
                printNoEventMessage();
            }
            break;

        case REMOVE_TASK:
            try {
                int taskNumber = Integer.parseInt(inputTokens[1]) - 1;
                displayRemovedTask(tasks, taskNumber);
            } catch (IndexOutOfBoundsException e) {
                printTaskNumberOutOfBoundsMessage();
            } catch (NumberFormatException e) {
                printInvalidTaskNumberMessage();
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
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println(NEWLINE + "Got it. I removed this task:");
        System.out.println(task);
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
     * Print a message when the task number given is out of bounds.
     */
    private void printTaskNumberOutOfBoundsMessage() {
        System.out.println(NEWLINE + "Oops, I can't find that task in the list!" + NEWLINE);
    }

    /**
     * Print a message when the task number given is invalid or not a number.
     */
    private void printInvalidTaskNumberMessage() {
        System.out.println(NEWLINE + "Oops, that is not a valid task number!" + NEWLINE);
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
     * Print a message when no task name or due date is given for a deadline.
     */
    private static void printNoDeadlineMessage() {
        System.out.println(NEWLINE + "Please tell me what the task is and when it is due." + NEWLINE);
    }

    /**
     * Print an error message when no event name or date is given.
     */
    private static void printNoEventMessage() {
        System.out.println(NEWLINE + "Please tell me what the event is and when it is happening." + NEWLINE);
    }


    /**
     * Print the help message, which lists all commands.
     */
    private void printHelpMessage() {
        System.out.println(NEWLINE + "Here are a list of commands:" + NEWLINE);
        printCommandHelpMessage("list", "lists all tasks", "none", "none");
        printCommandHelpMessage("todo", "adds a new to-do task", "todo [task description]",
                "todo organise my desk");
        printCommandHelpMessage("deadline", "adds a new task with a due date",
                "deadline [task description] /by [due date]", "deadline English assignment /by Wed 5pm");
        printCommandHelpMessage("event", "adds a new event",
                "event [task description] /at [event date]", "event meeting /at Thurs 9am");
        printCommandHelpMessage("done", "lists all tasks", "done [task number]",
                "done 2");
        printCommandHelpMessage("remove", "removes a task from the list",
                "remove [task number]", "remove 3");
        printCommandHelpMessage("help", "lists all commands", "none", "none");
    }

    /**
     * Print a help message for a specific command.
     * @param command The command
     * @param description The command's description
     * @param format The usage format for the command
     */
    private void printCommandHelpMessage(String command, String description, String format, String example) {
        printCommandDescription(command, description);
        printCommandUsage(format);
        printCommandExample(example);
        System.out.println();
    }

    /**
     * Print a command description for a specific command.
     * @param command The command
     * @param description The command's description
     */
    private void printCommandDescription(String command, String description) {
        System.out.println(command + HELP_SEPARATOR + description);
    }

    /**
     * Print the usage format for a specific command.
     * @param format The usage format of the command
     */
    private void printCommandUsage(String format) {
        if (!format.equals("none")) {
            System.out.println("usage: " + format);
        }
    }

    /**
     * Print a usage example for a specific command
     * @param example An example for the command
     */
    private void printCommandExample(String example) {
        if (!example.equals("none")) {
            System.out.println("example: " + example);
        }
    }
}

