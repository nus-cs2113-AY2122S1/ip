public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        bye();
    }

    public static void greet(){
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void bye(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine(){
        for(int i = 0; i <30 ; i ++){
            System.out.print("-");
        }
        System.out.println();
    }

}
