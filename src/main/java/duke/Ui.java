package duke;

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

    public static void printMissingTaskType() {
        System.out.println(INDENT + "You didn't input the type of task... Again.\n"
                + INDENT + "Or you're stupid. That's more likely.");
    }

    public static void printMissingText() {
        System.out.println(INDENT + "And?? Retype and complete your sentence like a grown adult. Please.");
    }

    public static void printNumberExpected() {
        System.out.println(INDENT + "Does that look like a number to you? Retype. A. Number.");
    }

    /**
     *  Prints error message to user. Prompts user to input correct command.
     */
    public static void printMissingTaskTypeError() {
        printTopLine();
        printMissingTaskType();
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
