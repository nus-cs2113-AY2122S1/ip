package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    public static final String INDENT = "    │ ";

    // UI embellishments
    /**
     * Prints the top horizontal line to demarcate text from Tired.
     */
    public static void printTopLine() {
        System.out.println("    ┌────────────────────────────────────────────────────────────────────┐");
    }

    /**
     * Prints the bottom horizontal line to demarcate text from Tired.
     */
    public static void printBottomLine() {
        System.out.println("    └────────────────────────────────────────────────────────────────────┘");
    }

    /**
     * Prints logo of Tired
     */
    public static void printLogo() {
        // Generated with: https://patorjk.com/software/taag/
        String logo = "         ______       __     __                 __\n"
                + "        / ____/___   / /_   / /   ____   _____ / /_\n"
                + "       / / __ / _ \\ / __/  / /   / __ \\ / ___// __/\n"
                + "      / /_/ //  __// /_   / /___/ /_/ /(__  )/ /_  \n"
                + "      \\____/ \\___/ \\__/  /_____/\\____//____/ \\__/\n"
                + "            ┌─┐┬  ┌─┐┌─┐┌─┐┌─┐┌─┐\n"
                + "            ├─┘│  ├┤ ├─┤└─┐├┤  ┌┘\n"
                + "      o o o ┴  ┴─┘└─┘┴ ┴└─┘└─┘ o";
        System.out.println(logo);
    }

    // Greetings
    /**
     * Prints a reluctant greeting message.
     */
    public static void printGreeting() {
        System.out.println(INDENT + "*Sigh* Hi... I'm Tired                                             │\n"
                + INDENT + "What do you want from me?                                          │");
    }

    /**
     * Prints a message mocking the user.
     */
    public static void printMockery() {
        System.out.println(INDENT + "You know what a todo list bot is?\n"
                + INDENT + "I'm a todo list bot. So stop chatting with me.");
    }

    /**
     * Prints a goodbye message of Duke pretending to care for user.
     */
    public static void printGoodbye() {
        System.out.println(INDENT + "\"Only in the agony of parting do we look into the depths of love.\" │\n"
                + INDENT + " —— George Eliot                                                   │\n"
                + INDENT + "                                                                   │\n"
                + INDENT + "Ha! As if I care! Goodbye!!                                        │");
    }

    /**
     * Prints all commands that can be used.
     *
     * @param extraText Additional text after `help` command to be printed for fun.
     */
    public static void printHelp(String extraText) {
        System.out.println(INDENT + "Okay. For the last time, don't make me remind you again.           │");
        System.out.println(INDENT + "                                                                   │");
        System.out.println(INDENT + "               todo <name>: Add a todo task to the list.           │");
        System.out.println(INDENT + "deadline <name> /by <time>: Add a task with specified deadline.    │");
        System.out.println(INDENT + "   event <name> /at <time>: Add an event with the specified time.  │");
        System.out.println(INDENT + "        done <task_number>: Mark a task as done.                   │");
        System.out.println(INDENT + "      delete <task_number>: Remove a task from the list.           │");
        System.out.println(INDENT + "              find <query>: Search for text in the list.           │");

        if(!extraText.isBlank()) {
            System.out.println(INDENT + "                                                                   │");
            System.out.println(INDENT + "Oh and you typed extra garbage after \"help\":                       │");
            System.out.println(INDENT + extraText);
        }
    }

    // Negative feedback of erroneous user input
    /**
     * Echoes what user has typed.
     * Probes user to use right commands.
     * Encourages user to check out `help` section.
     *
     * @param text
     */
    public static void printUnexpectedCommand(String text) {
        System.out.println(INDENT + "Look what you typed:\n" + INDENT + text);
        System.out.println(INDENT);
        System.out.println(INDENT + "Please don't embarrass yourself any further.\n"
                + INDENT + "Use the right commands. Type \"help\" if you don't know.");
    }

    /**
     * Prints message urging user to retype.
     */
    public static void printMissingText() {
        System.out.println(INDENT + "Retype and complete your sentence like a grown adult. Please.");
    }

    /**
     * Prints message asking user for a number (the correct input).
     */
    public static void printNumberExpected() {
        System.out.println(INDENT + "Does that look like a number to you? Type. A. Number.");
    }

    /**
     * Prints notice that load file exists but is blank.
     */
    public static void printBlankLoadFileFound() {
        System.out.println(INDENT + "Notice: previously saved file empty.                               │");
    }

    /**
     * Prints message that load file does not exist.
     */
    public static void printLoadFileNotFound() {
        System.out.println(INDENT+ "Load file: not found.                                              │\n"
                + INDENT + "Tasks added in this session will be automatically saved upon exit. │");
    }

    /**
     * Prints messsage that load file has corrupted data.
     */
    public static void printLoadFileCorrupted() {
        System.out.println(INDENT + "One corrupted data found. Corrupted data will be removed upon exit.│");
    }

    /**
     * Prints message that the task user has selected (using done or delete command) does not exist.
     */
    public static void printTaskDoesNotExist() {
        System.out.println(INDENT + "Wha- Hey! Task does not exist!");
    }

    /**
     * Prints message that the task user has selected (using `done` or `delete` command) has been completed.
     */
    public static void printTaskAlreadyDone() {
        System.out.println(INDENT + "Dude... you've done the task already.");
    }

    /**
     * Prints message that user's query cannot be found in the tasks.
     */
    public static void printQueryNotFound() {
        System.out.println(INDENT + "Are you not familiar with your list? Query not found.");
    }

    // Positive feedback of successful executions
    /**
     * Prints message that task has been successfully added to list.
     *
     * @param tasks ArrayList containing all the tasks.
     * @param isPlural Boolean variable to tell whether task(s) should be singular to plural.
     * @param taskPending Number of remaining undone tasks.
     */
    public static void printAddedTask(ArrayList<Task> tasks, String isPlural, int taskPending) {
        System.out.println(INDENT + " Fine. Added to your list:");
        System.out.println(INDENT + "   " + tasks.get(tasks.size() - 1));
        System.out.println(INDENT + " You have " + taskPending + " pending task"
                + isPlural + ". tHaT's aWeSoMe!!!!!1!!");
    }

    /**
     * Prints message that saved file containing previously saved tasks has been loaded successfully.
     */
    public static void printTasksLoadedSuccessfully() {
        System.out.println(INDENT + "Loaded previously saved file.                                      │");
    }

    /**
     * Prints message that tasks inputted in session has been saved on a file.
     */
    public static void printTasksSavedSuccessfully() {
        System.out.println(INDENT + "Tasks successfully saved to file.                                  │");
    }

    /**
     * Prints message of task that has been deleted by the user.
     *
     * @param tasks ArrayList containing all the tasks.
     * @param taskNumber Index of task in the ArrayList.
     */
    public static void printTaskDeleted(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(INDENT + "Lazy eh? Gotcha fam, removed the task:");
        System.out.println(INDENT + "[" + tasks.get(taskNumber).getStatusIcon() + "] "
                + tasks.get(taskNumber).getTaskName());
        System.out.println(INDENT + "You left " + (tasks.size() - 1) + " tasks in the list.");

    }

    /**
     * Prints message that task has been marked as done.
     *
     * @param tasks ArrayList containing all the tasks.
     * @param taskNumber Index of task in the ArrayList.
     */
    public static void printTaskDone(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(INDENT + "About time. I've mark that task as done:");
        System.out.println(INDENT + "[" + tasks.get(taskNumber).getStatusIcon() + "] "
                + tasks.get(taskNumber).getTaskName());
    }

    /**
     * Prints message that a completed task is deleted from the list.
     */
    public static void printDeleteDoneTask() {
        System.out.println(INDENT + "But I'll delete for you, cus' I'm nice. :)");
    }

    /**
     * Prints header message for a list of task to be printed afterwords.
     *
     * @param tasks ArrayList containing all the tasks. No printing if list is empty.
     */
    public static void printListHeader(ArrayList<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println(INDENT + "Here are your tasks, oRgAnIc iTeLlIgEnCe:");
        }
    }

    /**
     * Prints header message for a list of task matching user query to be printed afterwords.
     *
     * @param tasks ArrayList containing all the tasks. No printing if list is empty.
     */
    public static void printQueryListHeader(ArrayList<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println(INDENT + "Here are the matching tasks, oRgAnIc iTeLlIgEnCe:");
        }
    }

    /**
     * Prints list of all tasks with all information in a helpful manner for the user.
     *
     * @param tasks ArrayList containing all the tasks.
     */
    public static void printList(ArrayList<Task> tasks) {
        if (!tasks.isEmpty()) {
            for (Task t : tasks) {
                System.out.println(INDENT + (tasks.indexOf(t) + 1) + "." + t.toString());
            }
        } else {
            System.out.println(INDENT + "Why are you asking for a list???                                   │\n"
                    + INDENT + "It's empty, like something I know.                                 │");
        }
    }

    // `show` methods incorporating the above `print` methods. S L A P P E D
    /**
     * Prints list of tasks with UI embellishments.
     *
     * @param tasks ArrayList containing all the tasks.
     */
    public static void showList(ArrayList<Task> tasks) {
        printTopLine();
        printListHeader(tasks);
        printList(tasks);
        printBottomLine();
    }

    /**
     * Prints list of tasks that matches user query with UI embellishments
     *
     * @param tasks ArrayList containing all the tasks.
     */
    public static void showQueryList(ArrayList<Task> tasks) {
        printTopLine();
        printQueryListHeader(tasks);
        printList(tasks);
        printBottomLine();
    }

    /**
     * Prints message that no task matches user's query with UI embellishments
     */
    public static void showQueryNotFound() {
        printTopLine();
        printQueryNotFound();
        printBottomLine();
    }

    /**
     * Prints error message to user of wrong command used. Prompts user to input correct command.
     *
     * @param text User input.
     */
    public static void showWrongTaskTypeError(String text) {
        printTopLine();
        printUnexpectedCommand(text);
        printBottomLine();
    }

    /**
     * Prints error message for when user leaves out important information in input.
     */
    public static void showMissingTextError() {
        printTopLine();
        printMissingText();
        printBottomLine();
    }

    /**
     * Prints error message for when an integer is expected from user (`done` or `delete` command).
     */
    public static void showNumberFormatError() {
        printTopLine();
        printNumberExpected();
        printBottomLine();
    }

    /**
     * Prints all commands for user to familiarise.
     *
     * @param userInput Extra text from user after `help` to be printed for fun.
     */
    public static void showHelpMessage(String userInput) {
        printTopLine();
        printHelp(userInput);
        printBottomLine();
    }

    /**
     * Prints message that file was loaded successfully with UI embellishments.
     */
    public static void showTaskLoadedSuccessMessage() {
        printTopLine();
        printTasksLoadedSuccessfully();
        printBottomLine();
    }

    /**
     * Prints message that loaded file is blank with UI embellishments.
     */
    public static void showBlankLoadFileMessage() {
        printTopLine();
        printBlankLoadFileFound();
        printBottomLine();
    }

    /**
     * Prints message that load file does not exist with UI embellishments.
     */
    public static void showLoadingFileMissing() {
        printTopLine();
        printLoadFileNotFound();
        printBottomLine();
    }

    /**
     * Prints message that a data in the load file is corrupted with UI embellishments.
     */
    public static void showLoadFileCorrupted() {
        printTopLine();
        printLoadFileCorrupted();
        printBottomLine();
    }

    /**
     * Loads previously save file (if any) and prints greeting message to user when code is ran with UI embellishments.
     */
    public static void greetUser() {
        Storage.load();

        printLogo();
        printTopLine();
        printGreeting();
        printBottomLine();
    }

    /**
     * Prints a snarky remark to user with UI embellishments.
     */
    public static void mockUser() {
        printTopLine();
        printMockery();
        printBottomLine();
    }

    /**
     * Prints farewell message to user and exits code with UI embellishments.
     */
    public static void byeUser() {
        printTopLine();
        printGoodbye();
        printBottomLine();

        printTopLine();
        printTasksSavedSuccessfully();
        printBottomLine();
    }
}
