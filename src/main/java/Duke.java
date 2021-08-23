import java.util.*;

public class Duke {
    public static void echo(String args) {
        System.out.println(args);
        System.out.println("_____________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        /** greet **/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        Scanner sc = new Scanner(System.in);
        while(true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                /** exit **/
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________");
                break;
            } else {
                echo(str);
                continue;
            }
        }
    }
}
