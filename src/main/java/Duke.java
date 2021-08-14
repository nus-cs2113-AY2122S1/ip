public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(lines());
        System.out.println(greeting());
        System.out.println(lines());
        System.out.println(bye());
        System.out.println(lines());
    }

    public static String lines() {
        return "____________________________________________________________";
    }

    public static String greeting() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
