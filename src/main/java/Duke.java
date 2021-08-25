public class Duke {
    public static void printLine()
    {
        System.out.println("\t____________________________________________________________");
    }
    public static void startDuke()
    {
        printLine();
        String logo = "\t ____        _        \n"
                    + "\t|  _ \\ _   _| | _____ \n"
                    + "\t| | | | | | | |/ / _ \\\n"
                    + "\t| |_| | |_| |   <  __/\n"
                    + "\t|____/ \\__,_|_|\\_\\___|";
        System.out.println("\tHello from\n" + logo);
        printLine();
    }
    public static void endDuke()
    {
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }
    public static void greet()
    {
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }
    public static void main(String[] args)
    {
        startDuke();
        greet();
        endDuke();
    }
}
