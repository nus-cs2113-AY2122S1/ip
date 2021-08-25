import java.util.Scanner;

public class Duke {
    public static void echo(String args){
        if (args.length() > 0) {
            System.out.println(args);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "Hello I'm Duke\n"
                + "What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";
        Scanner in = new Scanner(System.in);
        String line = "";

        System.out.println("Hello from\n" + logo);
        System.out.println(greet);

        while (!line.equals("bye")) {
            echo(line);
            line = in.nextLine();
        }
        
        System.out.println(exit);
    }
}
