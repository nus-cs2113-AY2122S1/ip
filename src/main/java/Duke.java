import java.util.Scanner;

public class Duke {
    public static void greeting(boolean isStart) {
        if (isStart) {
            //        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
            String greeting = "____________________________________________________________\n" +
                    " Hello! I'm Duke\n" +
                    " What can I do for you?\n" +
                    "____________________________________________________________\n";
            System.out.println(greeting);
        }
    }

    public static void echo(String x) {
        String output = "____________________________________________________________\n" +
                 x + "\n" +
                "____________________________________________________________\n";
        System.out.println(output);
    }
    public static void main(String[] args) {
        greeting(true);
        Scanner sc = new Scanner(System.in);
        while (true) {
            echo(sc.nextLine());
        }
    }

}
