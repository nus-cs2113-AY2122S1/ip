/**
 * Contains methods that help to print out strings more easily
 */
public class Ui {

    /**
     * prints a string with a tab prepended to it and a newline appended to it
     *
     * @param str string to print
     */
    public static void printlnTab(String str) {
        System.out.println("\t" + str);
    }

    /**
     * prints a text divider to separate blocks of text in the program
     */
    public static void printDivider() {
        System.out.println("\t______________________________________________________________________");
        System.out.println();
    }

    /**
     * Prints when program is just started by the user
     */
    public static void welcomeMessage() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        printDivider();
        System.out.println(logo);
        printlnTab("Hello! I'm Duke\n\tWhat can I do for you?");
        printDivider();
    }
}