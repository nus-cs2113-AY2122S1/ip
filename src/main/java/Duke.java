public class Duke {
    public static void main(String[] args) {
        Greet.greet();
    }
}

class Greet {
    public static void greet() {
        String logo = "      ____        _        \n"
                    + "     |  _ \\ _   _| | _____ \n"
                    + "     | | | | | | | |/ / _ \\\n"
                    + "     | |_| | |_| |   <  __/\n"
                    + "     |____/ \\__,_|_|\\_\\___|\n";

        String line = "    ____________________________________________________________\n";

        System.out.print(line);
        System.out.println(logo);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.print(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.print(line);
    }
}