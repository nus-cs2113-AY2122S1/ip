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

    public static void printGreeting() {
        System.out.println(INDENT + "*Sigh* Hi... I'm Tired                                             │\n"
                + INDENT + "What do you want from me?                                          │");
    }

    public static void printMockery() {
        System.out.println(INDENT + "You know what a todo list bot is?\n"
                + INDENT + "I'm a todo list bot. So stop chatting with me.");
    }

    public static void printGoodbye() {
        System.out.println(INDENT + "\"Only in the agony of parting do we look into the depths of love.\" │\n"
                + INDENT + " —— George Eliot                                                   │\n"
                + INDENT + "                                                                   │\n"
                + INDENT + "Ha! As if I care! Goodbye!!                                        │");
    }

    public static void printHelp(String userInput) {
        System.out.println(INDENT + "Okay. For the last time, don't make me remind you again.           │");
        System.out.println(INDENT + "                                                                   │");
        System.out.println(INDENT + "               todo <name>: Add a todo task to the list.           │");
        System.out.println(INDENT + "deadline <name> /by <time>: Add a task with specified deadline.    │");
        System.out.println(INDENT + "   event <name> /at <time>: Add an event with the specified time.  │");
        System.out.println(INDENT + "        done <task_number>: Mark a task as done.                   │");
        System.out.println(INDENT + "      delete <task_number>: Remove a task from the list.           │");
        System.out.println(INDENT + "              find <query>: Search for text in the list.           │");

        if(!userInput.isBlank()) {
            System.out.println(INDENT + "                                                                   │");
            System.out.println(INDENT + "Oh and you typed extra garbage after \"help\":                       │");
            System.out.println(INDENT + userInput);
        }
    }

    // Error related texts

    public static void printWrongTaskType(String text) {
        System.out.println(INDENT + "Look what you typed:\n" + INDENT + text);
        System.out.println(INDENT);
        System.out.println(INDENT + "Please don't embarrass yourself any further.\n"
                + INDENT + "Use the right commands. Type \"help\" if you don't know.");
    }

    public static void printMissingText() {
        System.out.println(INDENT + "Retype and complete your sentence like a grown adult. Please.");
    }

    public static void printNumberExpected() {
        System.out.println(INDENT + "Does that look like a number to you? Type. A. Number.");
    }

    public static void printBlankLoadFileFound() {
        System.out.println(INDENT + "Notice: previously saved file empty.                               │");
    }

    public static void printLoadFileNotFound() {
        System.out.println(INDENT+ "Load file: not found.                                              │\n"
                + INDENT + "Tasks added in this session will be automatically saved upon exit. │");
    }

    public static void printLoadFileCorrupted() {
        System.out.println(INDENT + "One corrupted data found. Corrupted data will be removed upon exit.│");
    }

    public static void printTaskDoesNotExist() {
        System.out.println(INDENT + "Wha- Hey! Task does not exist!");
    }

    public static void printTaskAlreadyDone() {
        System.out.println(INDENT + "Dude... you've done the task already.");
    }

    public static void printQueryNotFound() {
        System.out.println(INDENT + "Are you not familiar with your list? Query not found.");
    }

    // Positive feedback of successful executions

    public static void printAddedTask(ArrayList<Task> tasks, String isPlural, int taskPending) {
        System.out.println(INDENT + " Fine. Added to your list:");
        System.out.println(INDENT + "   " + tasks.get(tasks.size() - 1));
        System.out.println(INDENT + " You have " + taskPending + " pending task"
                + isPlural + ". tHaT's aWeSoMe!!!!!1!!");
    }

    public static void printTasksLoadedSuccessfully() {
        System.out.println(INDENT + "Loaded previously saved file.                                      │");
    }

    public static void printTasksSavedSuccessfully() {
        System.out.println(INDENT + "Tasks successfully saved to file.                                  │");
    }

    public static void printTaskDeleted(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(INDENT + "Lazy eh? Gotcha fam, removed the task:");
        System.out.println(INDENT + "[" + tasks.get(taskNumber).getStatusIcon() + "] "
                + tasks.get(taskNumber).getTaskName());
        System.out.println(INDENT + "You left " + (tasks.size() - 1) + " tasks in the list.");

    }

    public static void printTaskDone(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(INDENT + "About time. I've mark that task as done:");
        System.out.println(INDENT + "[" + tasks.get(taskNumber).getStatusIcon() + "] "
                + tasks.get(taskNumber).getTaskName());
    }

    public static void printDeleteDoneTask() {
        System.out.println(INDENT + "But I'll delete for you, cus' I'm nice. :)");
    }

    public static void printListHeader(ArrayList<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println(INDENT + "Here are your tasks, oRgAnIc iTeLlIgEnCe:");
        }
    }

    public static void printQueryListHeader(ArrayList<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println(INDENT + "Here are the matching tasks, oRgAnIc iTeLlIgEnCe:");
        }
    }

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

    // `show` methods incorporating the above `print` methods
    /**
     *
     * @param tasks
     */
    public static void showList(ArrayList<Task> tasks) {
        printTopLine();
        printListHeader(tasks);
        printList(tasks);
        printBottomLine();
    }

    /**
     *
     * @param tasks
     */
    public static void showQueryList(ArrayList<Task> tasks) {
        printTopLine();
        printQueryListHeader(tasks);
        printList(tasks);
        printBottomLine();
    }

    public static void showQueryNotFound() {
        printTopLine();
        printQueryNotFound();
        printBottomLine();
    }

    /**
     *  Prints error message to user. Prompts user to input correct command.
     */
    public static void showWrongTaskTypeError(String text) {
        printTopLine();
        printWrongTaskType(text);
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

    public static void showNumberFormatError() {
        printTopLine();
        printNumberExpected();
        printBottomLine();
    }

    public static void showHelpMessage(String userInput) {
        printTopLine();
        printHelp(userInput);
        printBottomLine();
    }

    public static void showTaskLoadedSuccessMessage() {
        printTopLine();
        printTasksLoadedSuccessfully();
        printBottomLine();
    }

    public static void showBlankLoadFileMessage() {
        printTopLine();
        printBlankLoadFileFound();
        printBottomLine();
    }

    public static void showLoadingFileMissing() {
        printTopLine();
        printLoadFileNotFound();
        printBottomLine();
    }

    public static void showLoadFileCorrupted() {
        printTopLine();
        printLoadFileCorrupted();
        printBottomLine();
    }

    /**
     * Loads previously save file (if any) and prints greeting message to user when code is ran.
     */
    public static void greetUser() {
        Storage.load();

        printLogo();
        printTopLine();
        printGreeting();
        printBottomLine();
    }

    /**
     * Prints a snarky remark to user.
     */
    public static void mockUser() {
        printTopLine();
        printMockery();
        printBottomLine();
    }

    /**
     * Prints farewell message to user and exits code.
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
