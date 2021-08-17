public class Duke {
    public static void greeting() {
        System.out.println("_____________________________________________________");
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you today, sir?");
        System.out.println("_____________________________________________________");
    }

    public static void goodbyeMessage() {
        System.out.println("Goodbye. Till we meet again!");
        System.out.println("_____________________________________________________");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        goodbyeMessage();
    }
}
