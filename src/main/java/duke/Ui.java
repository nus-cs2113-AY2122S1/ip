package duke;

import duke.task.Task;
import java.util.ArrayList;

public class Ui {
    public static final String INDENT = "    │ ";

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

    public static void printWrongTaskType(String firstWord, String remainingWords) {
        System.out.println(INDENT + "Look what you typed:\n"
                + INDENT + firstWord + remainingWords + "\n" + INDENT);
        System.out.println(INDENT + "Please don't embarrass yourself any further.\n"
                + INDENT + "Use the right commands. Type \"help\" if you don't know.");
        System.out.println(INDENT + "~\"help\" command still under development.~");
    }

    public static void printMissingText() {
        System.out.println(INDENT + "And?? Retype and complete your sentence like a grown adult. Please.");
    }

    public static void printNumberExpected() {
        System.out.println(INDENT + "Does that look like a number to you? Retype. A. Number.");
    }

    public static void printAddedTask(ArrayList<Task> tasks, String isPlural, int taskPending) {
        System.out.println(INDENT + " Fine. Added to your list:");
        System.out.println(INDENT + "   " + tasks.get(tasks.size() - 1));
        System.out.println(INDENT + " You have " + taskPending + " pending task"
                + isPlural + ". tHaT's aWeSoMe!!!!!1!!");
    }

    public static void printTaskDoesNotExist() {
        System.out.println(INDENT + "Wha- Hey! Task does not exist!");
    }

    public static void printTaskAlreadyDone() {
        System.out.println(INDENT + "Dude... you've done the task already.");
    }

    public static void printTaskMarkedAsDone(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(INDENT + "About time. I've mark that task as done:");
        System.out.println(INDENT + "[" + tasks.get(taskNumber).getStatusIcon() + "]"
                + tasks.get(taskNumber).getTaskName());
    }

    /**
     * Prints the tasks of tasks collated by Tired.
     */
    public static void printList(ArrayList<Task> tasks) {
        printTopLine();
        System.out.println(INDENT + "Here are your tasks, oRgAnIc iTeLlIgEnCe:");
        for (Task t : tasks) {
            System.out.println(INDENT + (tasks.indexOf(t) + 1) + "." + t.toString());
        }
        printBottomLine();
    }

    /**
     *  Prints error message to user. Prompts user to input correct command.
     */
    public static void printWrongTaskTypeError(String firstWord, String remainingWords) {
        printTopLine();
        printWrongTaskType(firstWord, remainingWords);
        printBottomLine();
    }

    /**
     * Prints error message for when user leaves out important information in input.
     */
    public static void printMissingTextError() {
        printTopLine();
        printMissingText();
        printBottomLine();
    }

    public static void printNumberFormatError() {
        printTopLine();
        printNumberExpected();
        printBottomLine();
    }

    /**
     * Prints greeting message to user when code is ran.
     */
    public static void greetUser() {
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
    }

}
