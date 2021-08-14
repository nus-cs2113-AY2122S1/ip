public class Duke {

    public static void printLine(){
        String line = "__________________________________________________";
        System.out.println(line);
    }

    public static void Greet(){
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();

    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
    }
}
