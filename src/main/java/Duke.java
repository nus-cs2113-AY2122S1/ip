public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static void greet() {
        String greeting = " Hello! I'm Duke\n"
                + " What can I do for you?";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(greeting);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void exit() {
        String farewell = " Bye. Hope to see you again soon!";
        System.out.println(farewell);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}
