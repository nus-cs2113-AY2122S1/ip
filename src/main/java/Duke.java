public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printSection("Hello from", logo);
        printSection("Hello! I'm Duke", "What can I do for you?");
        printSection("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the given data provided, and a horizontal line denoting the end of a section.
     * If no data is provided, it will print a horizontal line.
     *
     * @param data The string(s) to print to console.
     */
    private static void printSection(String ...data) {
        for (String s: data) {
            System.out.println(s);
        }
        System.out.println("____________________________________________________________");
    }
}
