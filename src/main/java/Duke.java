public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String lineBar = "____________________________________________________________\n";
        String helloMessage = "Hello! I'm Duke\nWhat can I do for you?\n";
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(lineBar + helloMessage + lineBar + goodbyeMessage + lineBar);
    }
}
