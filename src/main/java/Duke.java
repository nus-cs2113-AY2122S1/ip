import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontal_line = "____________________________________________________________";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println(horizontal_line + "\n");
        System.out.println(" Hello! I'm Duke\n");
        System.out.println(" What can I do for you?\n");
        System.out.println(horizontal_line + "\n");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(horizontal_line + "\n");
            System.out.println(line + "\n");
            System.out.println(horizontal_line + "\n");
            line = in.nextLine();
        }
        System.out.println(horizontal_line + "\n");
        System.out.println(" Bye. Hope to see you again soon!\n");
        System.out.println(horizontal_line + "\n");
    }
}
