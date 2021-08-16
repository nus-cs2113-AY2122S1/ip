public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________";

        String greetMsg = line
                + "\nHello! I'm Duke\n"
                + "What can I do for you?\n"
                + line;

        String byeMsg = "Bye. Hope to see you again soon!\n" + line;

        System.out.println(greetMsg + "\n" + byeMsg);
    }
}
