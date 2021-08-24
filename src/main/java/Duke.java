import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);


        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n" ;

        String goodbye = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                +  "____________________________________________________________\n";

        System.out.println(greeting);
        line = in.nextLine();

        while(!line.equals("bye")) {
            System.out.println("____________________________________________________________\n"
                    + line + "\n"
                    + "____________________________________________________________\n"
            );
            line = in.nextLine();
        }

        System.out.println(goodbye);
    }
}
