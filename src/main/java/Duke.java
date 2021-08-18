public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String underscores = "___________________________________________________________\n";
        String greetMessage = underscores
                + "Hello! I'm Duke\n"
                + "What can I do for you?";
        String byeMessage = underscores
                + "Bye. Hope to see you again soon!\n"
                + underscores;
        System.out.println(greetMessage);
        System.out.println(byeMessage);
    }
}
