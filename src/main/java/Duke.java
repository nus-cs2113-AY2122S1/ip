public class Duke {
    public static void greet() {
        System.out.println("_".repeat(30));
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println("_".repeat(30));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_".repeat(30));
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
    }
}
