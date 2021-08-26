import java.util.Scanner;

public class Duke {
    public static void greeting( boolean isStart) {
        if (isStart) {
            String greeting = "____________________________________________________________\n" +
                    " Hello! I'm Duke\n" +
                    " What can I do for you?\n" +
                    "____________________________________________________________\n";
            System.out.println(greeting);
        }
    }

    public static void echo (String x){
        String output = "____________________________________________________________\n" +
                x + "\n" +
                "____________________________________________________________\n";
        System.out.println(output);
    }
    public static void main (String[]args) {
        greeting(true);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            input = sc.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}