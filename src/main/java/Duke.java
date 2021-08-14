public class Duke {

    public static void printLine(String[] args) {
        for(int x =0;x <= 50;x++){
            System.out.print("-");
        }
        System.out.println(" ");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine(args);
        System.out.println("Hello! I'm Duke'");
        System.out.println("What can I do for you?");
        printLine(args);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
